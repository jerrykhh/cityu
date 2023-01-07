#! /usr/bin/env python3

import argparse
import os
import signal
import subprocess
import sys
import time
import binascii
import json

#
# helper routines
#

class bcolors:
    HEADER = '\033[95m'
    OKBLUE = '\033[94m'
    OKGREEN = '\033[92m'
    WARNING = '\033[93m'
    FAIL = '\033[91m'
    ENDC = '\033[0m'
    BOLD = '\033[1m'
    UNDERLINE = '\033[4m'


def read_file(f, do_binary):
    read_string = 'r'
    if do_binary:
        read_string = 'rb'
    with open(f, read_string) as content_file:
        content = content_file.read()
    return content

def write_file(f, do_binary, data):
    write_string = 'w'
    if do_binary:
        write_string = 'wb'
    write_string = write_string + '+'
    with open(f, write_string) as content_file:
        content_file.write(data)

def dump_hex(binary, group=2):
    return b' '.join(binascii.hexlify(binary[i:i+group]) for i in range(0, len(binary), group))

def print_failed(msg, expected, actual, test_passed, verbosity):
    if test_passed: # up til now, no one said that the test failed
        print('RESULT failed')
    if verbosity >= 0:
        print(msg)
        if verbosity < 2 and hasattr(expected, '__len__') and len(expected) > 1000:
            print('Showing first 500 bytes; use -vv to show full output')
            expected = expected[:500]
            actual = actual[:500]
        if hasattr(expected, 'decode') or hasattr(actual, 'decode'): # binary, dump to hex
            expected = dump_hex(expected)
            actual = dump_hex(actual)
        print('expected: [%s]' % expected)
        print('got:      [%s]\n' % actual)
    return

# some code from:
# stackoverflow.com/questions/18404863/i-want-to-get-both-stdout-and-stderr-from-subprocess
def run_command(cmd, timeout_length, verbosity, do_binary):
    if verbosity >= 1:
        #print('COMMAND', cmd)
        pass
    proc = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=True, preexec_fn=os.setsid)

    try:
        std_output, std_error = proc.communicate(timeout=timeout_length)
    except subprocess.TimeoutExpired:
        if verbosity >= 0:
            print('WARNING test timed out (i.e., it took too long)')
        # proc.kill()
        os.killpg(os.getpgid(proc.pid), signal.SIGTERM)
        std_output, std_error = proc.communicate()
    error_code = proc.returncode

    if not do_binary:
        # Python3 subprocess exec() sometimes returns bytes not a string -
        # make sure to turn it into ascii before treating as string
        output_actual = std_output.decode('ascii', 'ignore')
        error_actual = std_error.decode('ascii', 'ignore')
    else:
        # does
        output_actual = std_output
        error_actual = std_error
    rc_actual = int(error_code)

    return output_actual, error_actual, rc_actual

def handle_outcomes(stdout_expected, stdout_actual, stderr_expected, stderr_actual, rc_expected, rc_actual, verbosity, total_time):
    test_passed = True
    if verbosity != -1 and total_time is not None:
        print('Test finished in %.3f seconds' % total_time)
    if rc_actual != rc_expected:
        print_failed('return code does not match expected', rc_expected, rc_actual, test_passed, verbosity)
        test_passed = False
    if stdout_expected != stdout_actual:
        print_failed('standard output does not match expected', stdout_expected, stdout_actual, test_passed, verbosity)
        test_passed = False
    # if stderr_actual != stderr_expected:
    #     print_failed('standard error does not match expected', stderr_expected, stderr_actual, test_passed, verbosity)
    #     test_passed = False
    if test_passed:
        print(bcolors.OKGREEN + 'RESULT passed' + bcolors.ENDC)
        return True
    return False

def test_exists(test_number, input_dir):
    input_file = '%s/%d-0.in' % (input_dir, test_number)
    input_dir = '%s/%d-0' % (input_dir, test_number)
    return os.path.exists(input_file) or os.path.exists(input_dir)

def save_output(test_number, stdout_dir, stdout_actual, stderr_actual, rc_actual, do_binary):
    write_file('%s/%d.out' % (stdout_dir, test_number), do_binary, stdout_actual)
    # write_file('%s/%d.err' % (stdout_dir, test_number), do_binary, stderr_actual)
    write_file('%s/%d.rc' % (stdout_dir, test_number), False, str(rc_actual))
    return True

def get_test_description(base_desc, filesize, timeout):
    base_desc = base_desc.replace('${timeout}', str(timeout))
    directory_desc = []
    file_desc = []
    for fsl in filesize:
        if type(fsl) == list:
            content = " MB, ".join(fsl) + " MB"
            directory_desc.append(content)
        else:
            file_desc.append(fsl)
    if "${filesize}" in base_desc:
        file_desc = " MB, ".join(file_desc) + " MB"
        base_desc = base_desc.replace("${filesize}", file_desc)
    if "${directory}" in base_desc:
        for desc in directory_desc:
            base_desc = base_desc.replace("${directory}", desc, 1)
    return base_desc

# return outcome of test AND whether test exists
def test_one(test_number, config_dir, stdout_dir, input_dir, binary_file, timeout_length, verbosity, show_timing, save):
    config = json.load(open(os.path.join(config_dir, '%d.json' % test_number), 'r'))
    do_binary = int(config['binary'])
    for i, fsl in enumerate(config['filesize']):
        if type(fsl) == list:
            for j in range(len(fsl)):
                config['filesize'][i][j] = str(config['filesize'][i][j])
        else:
            config['filesize'][i] = str(config['filesize'][i])
    test_desc = get_test_description(config['description'], config['filesize'], config['timeout'])

    if not save:
        stdout_expected = read_file('%s/%d.out' % (stdout_dir, test_number), do_binary)
        # stderr_expected = read_file('%s/%d.err' % (stdout_dir, test_number), do_binary)
        stderr_expected = ''
        rc_expected = int(read_file('%s/%d.rc' % (stdout_dir, test_number), False))

    def get_input_files(res, path):
        for f in os.listdir(path):
            f_path = os.path.join(path, f)
            if os.path.isdir(f_path):
                get_input_files(res, f_path)
            elif os.path.isfile(f_path):
                res.append(f_path)
        return res

    files_list = get_input_files([], os.path.join(input_dir, str(test_number)))

    print('TEST %d - %s' % (int(test_number), test_desc))
    if config['preparation'] != '': # do some prepare work
        for f in files_list:
            command = config['preparation'].replace('${filename}', f)
            try:
                subprocess.check_call(command, timeout=20, shell=True)
            except:
                print('WARN preparation work failed, may need to try again')
    if do_binary and verbosity >= 0:
        #print('NOTE output in hex not ascii text')
        pass
    if config['timeout'] > 0:
        timeout_length = config['timeout']
    if show_timing:
        start_time = time.time()

    test_dir_path = os.path.join(input_dir, str(test_number))
    files_list = os.listdir(test_dir_path)
    files_list = list(map(lambda x: os.path.join(test_dir_path, x), files_list))
    run_desc = './' + binary_file + ' ' + ' '.join(files_list)
    stdout_actual, stderr_actual, rc_actual = run_command(run_desc, timeout_length, verbosity, do_binary)
    if show_timing:
        total_time = time.time() - start_time
    else:
        total_time = None

    if save:
        return save_output(test_number, stdout_dir, stdout_actual, stderr_actual, rc_actual, do_binary)
    else:
        return handle_outcomes(stdout_expected, stdout_actual, stderr_expected, stderr_actual, rc_expected, rc_actual, verbosity, total_time)

#
# main test code
#
parser = argparse.ArgumentParser()
parser.add_argument('-c', '--continue', dest='continue_testing', help='continue testing even when a test fails', action='store_true')
parser.add_argument('-C', '--config_dir', dest='config_directory', help='path to location of configs', type=str, default='')
parser.add_argument('-S', '--start_test', dest='start_test', help='start with this test number', type=int, default=1)
parser.add_argument('-E', '--end_test', dest='end_test', help='end with this test number; -1 means go until done', type=int, default=-1)
parser.add_argument('-s', '--source_file', dest='source_file', help='name of source file to test', type=str, default='')
parser.add_argument('-b', '--binary_file', dest='binary_file', help='name of binary to produce', type=str, default='a.out')
parser.add_argument('-B', '--build', dest='build', help='whether to build', type=int, default=0)
parser.add_argument('-t', '--test_dir', dest='test_directory', help='path to location of tests', type=str, default='')
parser.add_argument('-T', '--timeout', dest='timeout_length', help='length of timeout in seconds', type=int, default=30)
parser.add_argument('--timed', dest='show_timing', help='show time taken by each test in seconds', action='store_true', default=True)
parser.add_argument('-f', '--build_flags', help='extra build flags for gcc', type=str, default='')
parser.add_argument('-std', '--stdout_dir', dest='stdout_directory', help='path to location of standard outputs', type=str, default='')
parser.set_defaults(verbosity=0)
group = parser.add_mutually_exclusive_group()
group.add_argument('-q', '--quiet', dest='verbosity', help='only display test number and result', action='store_const', const=-1)
group.add_argument('-v', '--verbose', dest='verbosity', help='show command line so you can replicate for debugging, and also some other extra information', action='count')
args = parser.parse_args()

input_file = args.source_file
binary_file = args.binary_file
build = args.build
input_dir = args.test_directory
config_dir = args.config_directory
stdout_dir = args.stdout_directory
if build == 1:
    print('TEST 0 - clean build (program should compile without errors or warnings)')
    if args.show_timing:
        start_time = time.time()
    stdout_actual, stderr_actual, rc_actual = run_command('gcc %s -o %s -Wall -Werror %s' % (input_file, binary_file, args.build_flags),
                                                        args.timeout_length, args.verbosity, do_binary=False)
    if args.show_timing:
        total_time = time.time() - start_time
    else:
        total_time = None
    if handle_outcomes('', stdout_actual, '', stderr_actual, 0, rc_actual, args.verbosity, total_time) == False:
        exit(1)
    print('')

test_number = args.start_test
while True:
    # in this case, we are all done
    # if not test_exists(test_number, input_dir):
    #     if args.end_test != -1:
    #         print('ERROR cannot run test %d; it does not exist' % test_number)
    #     break

    test_result = test_one(test_number, config_dir, stdout_dir, input_dir, binary_file, args.timeout_length, args.verbosity, args.show_timing, False)
    print('')

    if not args.continue_testing and not test_result:
        print('TESTING HALTED (use -c or --continue to keep going if you like)')
        exit(1)

    test_number += 1
    if args.end_test != -1 and test_number > args.end_test:
        break

exit(0)



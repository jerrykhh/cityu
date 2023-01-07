#!/usr/bin/env python3

import sys
import os
import time
import subprocess as sp

if len(sys.argv) <= 2:
    sys.exit('usage: serialized_runner.py <id> <executable> [args]')

serial_id = int(sys.argv[1])
my_pid = os.getpid()

queue = []

for pid in os.listdir('/proc'):
    if not pid.isdigit():
        continue
    try:
        with open(os.path.join('/proc', pid, 'cmdline'), 'rb') as f:
            cmdline_raw = f.read()
    except IOError: # proc already terminated
        continue
    cmdline = cmdline_raw.split(b'\0')
    if len(cmdline) > 3 and \
            cmdline[0] == b'python3' and \
            cmdline[1].endswith(b'/serialized_runner.py') and \
            cmdline[2].isdigit():
                _serial_id = int(cmdline[2])
                if (_serial_id, int(pid)) < (serial_id, my_pid):
                    queue.append((_serial_id, pid, cmdline_raw))
queue = sorted(queue)

def print_wait_msg(pids):
    if not hasattr(print_wait_msg, 'counter'):
        print_wait_msg.counter = 0
    msg = 'Waiting for pids: [%s] %s' % (','.join(pids), '.'*print_wait_msg.counter)
    msg += max(80 - len(msg), 0) * ' '
    sys.stdout.write('\r' + msg + '\r')
    print_wait_msg.counter = (print_wait_msg.counter + 1) % 6

while len(queue) != 0:
    _, pid, cmdline_raw = queue[0]
    try:
        with open(os.path.join('/proc', pid, 'cmdline'), 'rb') as f:
            curr_cmdline_raw = f.read()
    except IOError:
        queue.pop(0)
    else:
        if curr_cmdline_raw != cmdline_raw:
            queue.pop(0)
        else:
            print_wait_msg([proc[1] for proc in queue])
            time.sleep(1)

# print('-'*40)
# print('**NOTE** output in hex not ascii text')
# print('-'*40)

# print('\nStart running `%s`\n' % ' '.join(sys.argv[2:]))
os.system(' '.join(sys.argv[2:]))

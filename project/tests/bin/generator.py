#!/usr/bin/env python
import os
import sys
import string
import struct
import json
import random

from itertools import repeat

base_seed = 10000079
low = 1
high = 200000
fibs  = [1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597,
        2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418]
alphs = ['q','w','e','r','t','y','u','i','o','p','a','s','d','f','m','n','b','g','h','k','j','l','z','x','c','v']

def dump_into_file(filename):
    step = 2000
    remain = 2000
    for i, a in enumerate(alphs):
        random.seed(base_seed + fibs[i])
        rep = random.randint(low, high)

        s = ''.join(repeat(a, min(remain, rep)))
        remain = remain - min(remain, rep)
        rep = rep - min(remain, rep)
        if remain == 0:
            s = s + '\n'
            remain = step
        for j in range(0, rep, step):
            if j == 0:
                continue
            s = s + ''.join(repeat(a, step)) + '\n'
        if rep % step:
            s = s + ''.join(repeat(a, rep % step))
            remain = step - rep % step

        filename.write(s)

def create_files(filesize, output_dir, input_filename):
    filesize.sort(key=lambda x: 0 if type(x) == list else 1)
    for i, fsl in enumerate(filesize):
        create_dir = False if type(fsl) != list else True
        path_list = []
        if not create_dir:
            input_file_path = os.path.join(output_dir, input_filename + ('-%d.in' % i))
            path_list.append((input_file_path, fsl))
        else:
            input_dirname = '%s-%d' % (input_filename, i)
            dirname = os.path.join(output_dir, input_dirname)
            if not os.path.exists(dirname):
                os.mkdir(dirname)
            create_files(fsl, dirname, input_dirname)

        for path, fs in path_list:
            if not os.path.exists(path):
                os.mknod(path)
                with open(path, 'a') as file_pointer:
                    while os.path.getsize(path) < fs * 1024 * 1024:
                        dump_into_file(file_pointer)


if __name__ == '__main__':
    if len(sys.argv) != 3:
        sys.exit('usage: generator.py <config_location> <output_dir>\n example: python generator.py 1.config tests-pzip')

    config_path = sys.argv[1]
    output_dir = sys.argv[2]
    with open(config_path, 'r') as f:
        config = json.load(f)
        input_filename = str(config['filename'])
        if config.has_key('seed'):
            base_seed = config['seed']
        output_dir = os.path.join(output_dir, input_filename)
        if not os.path.exists(output_dir):
            os.mkdir(output_dir)
            print('Generate data based on ' + config_path)
            create_files(config['filesize'], output_dir, input_filename)

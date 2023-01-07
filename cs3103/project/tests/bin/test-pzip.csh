#! /bin/csh -f
set TEST_HOME = ${PWD}/tests
set source_file = pzip.c
set binary_file = pzip
set bin_dir = ${TEST_HOME}/bin
set test_dir = ${TEST_HOME}/tests-pzip
set config_dir = ${TEST_HOME}/config
set stdout_dir = ${TEST_HOME}/stdout

foreach file (${config_dir}/*)
    python ${bin_dir}/generator.py ${file} ${test_dir}
end

${bin_dir}/serialized_runner.py -1 ${bin_dir}/generic-tester.py -s ${source_file} -b ${binary_file} -B $1 -t ${test_dir} -C ${config_dir} -std ${stdout_dir} -E 10 -f="'-pthread -O'"

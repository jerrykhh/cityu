#! /bin/bash

if ! [[ -x cunzip ]]; then
    echo "cunzip executable does not exist"
    exit 1
fi

../run-tests.sh $*



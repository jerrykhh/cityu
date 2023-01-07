#! /bin/bash

if ! [[ -x czip ]]; then
    echo "czip executable does not exist"
    exit 1
fi

../run-tests.sh $*



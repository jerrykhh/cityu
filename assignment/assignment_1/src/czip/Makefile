FLAGS = -Wall -Werror

all: czip

czip: czip.c
	gcc -o czip czip.c $(FLAGS)

test:
	./test-czip.sh

clean:
	rm -f czip
	rm -rf tests-out


FLAGS = -Wall -Werror

all: cunzip

cunzip: cunzip.c
	gcc -o cunzip cunzip.c $(FLAGS)

test:
	./test-cunzip.sh

clean:
	rm -f cunzip
	rm -rf tests-out


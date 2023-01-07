CC := gcc
CFLAGS := -Wall -Werror -pthread -g
TARGETS := pzip

all: $(TARGETS)

%.o: %.c
	$(CC) -c $(CFLAGS) $< -o $@

test:
	@tests/bin/test-pzip.csh 1

run:
	@tests/bin/test-pzip.csh 0

.PHONY: clean
clean:
	@rm -f $(TARGETS)

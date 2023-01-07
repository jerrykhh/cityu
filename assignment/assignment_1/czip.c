//
//  czip.c
//  czip
//
//  Created by Jerry Kwok on 28/9/2020.
//  Copyright © 2020 Jerry Kwok. All rights reserved.
//
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char** argv) {
    if (argc < 2) {
        printf("czip: file1 [file2 ...]\n");
        exit(EXIT_FAILURE);
    }
    char buffer = '\0';
    char charLast = '\0';
    int count = 0;
    
    for (int i = 1; i < argc; i++) {
        FILE* filePointer = fopen(argv[i], "r");
        if (filePointer == NULL) {
            printf("czip: cannot open file\n");
            exit(EXIT_FAILURE);
        }
        while((buffer = fgetc(filePointer)) != EOF) {
            if (charLast == '\0') { // init
                count++;
                charLast = buffer;
            } else if (charLast != buffer) {
                fwrite(&count, sizeof(int), 1, stdout);
                fputc(charLast, stdout);
                count = 1;
            } else { // charLast equal buffer
                count++;
            }
            charLast = buffer;
        }
        fclose(filePointer);
    }
    fwrite(&count, sizeof(int), 1, stdout);
    fputc(charLast, stdout);
    
    return 0;
}



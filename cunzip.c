//
//  cunzip.c
//  czip
//
//  Created by Jerry Kwok on 29/9/2020.
//  Copyright © 2020 Jerry Kwok. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char** argv)
{
  if (argc == 1) {
    printf("cunzip: file1 [file2 ...]\n");
    exit(EXIT_FAILURE);
  }

  int count;
  char bufferChar;
  for (int i = 1; i < argc; i++) {
    FILE* filePointer = fopen(argv[i], "r");
    if (filePointer == NULL) {
      printf("czip: cannot open file\n");
      exit(EXIT_FAILURE);
    }

    while ((fread(&count, sizeof(int), 1, filePointer)) == 1) {
      fread(&bufferChar, sizeof(char), 1,filePointer);
      for (int j = 0; j < count; j++) {
        printf("%c", bufferChar);
      }
    }
    fclose(filePointer);
  }

  return 0;
}


//
// Created by root on 14/11/18.
//

#ifndef HUFF_ENCODE_H
#define HUFF_ENCODE_H

#include <stdlib.h>
#include <assert.h>
#include <stdio.h>
#include <string.h>
#include "huff.h"

typedef struct bitfile {
    FILE *file;
    unsigned char buffer;
    int index;
} bitfile;

bitfile *bitfile_new(char *filename, char *rw);

void encoded_file_write(bitfile *this, int bit);

void encode_file(huffcoder *coder, char *inputFile, char *outputFile);

#endif //HUFF_ENCODE_H

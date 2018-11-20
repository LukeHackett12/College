//
// Created by root on 14/11/18.
//

#ifndef HUFF_DECODE_H
#define HUFF_DECODE_H

#include <stdbool.h>
#include <assert.h>
#include <stdio.h>
#include "encode.h"
#include "huff.h"

void decoded_file_write(bitfile *this, char data);
void read_codes(huffcoder *coder, FILE *file, bitfile *bitfile, huffchar *leaf,
                bool inFile);
void decode_file(huffcoder *coder, char *inputFile, char *outputFile);

#endif //HUFF_DECODE_H

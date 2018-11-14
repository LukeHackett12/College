//
// Created by root on 14/11/18.
//

#ifndef HUFF_DECODE_H
#define HUFF_DECODE_H

#include <bits/types/FILE.h>
#include "huff.h"
#include "encode.h"

void decoded_file_write(bitfile *this, char data);
void decode_file(huffcoder *coder, char *inputFile, char *outputFile);

#endif //HUFF_DECODE_H

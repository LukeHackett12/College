//
// Created by root on 14/11/18.
//
#include <stdlib.h>
#include <assert.h>
#include <stdio.h>
#include <string.h>
#include "encode.h"

bitfile *bitfile_new(char *filename, char *rw) {
    bitfile *result = (bitfile *) malloc(sizeof(bitfile));
    result->buffer = 0;
    result->index = 0;
    result->file = fopen(filename, rw);

    return result;
}

//Write a bit to a bitfile
void encoded_file_write(bitfile *this, int bit) {
    this->buffer = (this->buffer << 1) | bit;
    this->index++;

    //Alright here
    assert(this->index <= 8);
    if (this->index == 8) {
        fputc(this->buffer, this->file);
        this->index = 0;
        this->buffer = 0;
    }
}

void encode_file(huffcoder *coder, char *inputFile, char *outputFile) {
    FILE *file;
    file = fopen(inputFile, "r");
    if (file == NULL) {
        printf("FATAL: Error opening file %s. Aborting program.\n", inputFile);
        exit(1);
    }

    bitfile *bitfile = bitfile_new(outputFile, "w");

    char ch;
    while ((ch = (char) fgetc(file)) != EOF) {
        unsigned long long code = coder->codes[ch];
        int length = coder->code_lengths[ch];

        for(int i = length-1; i >= 0; i--){
            int bit = ((code >> i) & 1);
            encoded_file_write(bitfile, bit);
        }
    }

    unsigned long long code = coder->codes[3];
    int length = coder->code_lengths[3];

    for(int i = length-1; i >= 0; i--){
        int bit = ((code >> i) & 1);
        encoded_file_write(bitfile, bit);
    }


    fclose(file);
    fclose(bitfile->file);
}
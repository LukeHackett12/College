//
// Created by root on 14/11/18.
//

#include "encode.h"

bitfile *bitfile_new(char *filename, char *rw) {
    bitfile *result = (bitfile *) malloc(sizeof(bitfile));
    result->buffer = 0;
    result->index = 0;
    result->file = fopen(filename, rw);

    return result;
}

void encoded_file_write(bitfile *this, int bit) {
    if(bit == 1){
        this->buffer = this->buffer | (1 << this->index);
    }
    this->index++;

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

    int ch;
    while ((ch = fgetc(file)) != EOF) {
        unsigned long long code = coder->codes[ch];
        int length = coder->code_lengths[ch];

        for(int i = length-1; i >= 0; i--){
            int bit = ((code >> i) & 1);
            encoded_file_write(bitfile, bit);
        }
    }

    unsigned long long code = coder->codes[4];
    int length = coder->code_lengths[4];

    for(int i = length-1; i >= 0; i--){
        int bit = ((code >> i) & 1);
        encoded_file_write(bitfile, bit);
    }

    if(bitfile->index != 0){
        fputc(bitfile->buffer, bitfile->file);
    }

    fclose(file);
    fclose(bitfile->file);
}
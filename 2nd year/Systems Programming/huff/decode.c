//
// Created by root on 14/11/18.
//

#include "decode.h"
#include "huff.h"

void decoded_file_write(bitfile *this, char data) {
    fputc(data, this->file);
}

void read_codes(huffcoder *coder, FILE *file, bitfile *bitfile, huffchar *leaf, bool inFile) {
    while (inFile) {
        unsigned char ch = (unsigned char) fgetc(file);

        for (int i = 0; i < 8; i++) {
            int dir = (ch >> i) & 1;

            if (leaf->is_compound == 1) {
                if (dir == 1) leaf = leaf->u.compound.right;
                else leaf = leaf->u.compound.left;
            } else {
                if (leaf->u.c != 4) {
                    decoded_file_write(bitfile, leaf->u.c);
                    leaf = coder->tree;
                    if (dir == 1) {
                        leaf = leaf->u.compound.right;
                    } else {
                       leaf = leaf->u.compound.left;
                    }
                } else {
                    inFile = false;
                }
            }
        }
    }
}

void decode_file(huffcoder *coder, char *inputFile, char *outputFile) {
    FILE *file;
    file = fopen(inputFile, "r");
    if (file == NULL) {
        printf("FATAL: Error opening file %s. Aborting program.\n", inputFile);
        exit(1);
    }

    bitfile *bitfile = bitfile_new(outputFile, "w");

    huffchar *leaf = coder->tree;

    bool inFile = true;

    read_codes(coder, file, bitfile, leaf, inFile);

    fclose(bitfile->file);
    fclose(file);
}
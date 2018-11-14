//
// Created by root on 14/11/18.
//

#include <assert.h>
#include <stdio.h>
#include <stdbool.h>
#include <libnet.h>
#include "decode.h"
#include "huff.h"

void decoded_file_write(bitfile *this, char data){
    fputc(data, this->file);
}

void decode_file(huffcoder *coder, char *inputFile, char *outputFile){
    //read file char by char, then shift the bits out of that while following
    //the tree, when the leaf isnt compound use decoded_file_write to add that char
    // to the file

    //01111010001

    FILE *file;
    file = fopen(inputFile, "r");
    if (file == NULL) {
        printf("FATAL: Error opening file %s. Aborting program.\n", inputFile);
        exit(1);
    }

    bitfile *bitfile = bitfile_new(outputFile, "w");

    unsigned char ch;
    huffchar *leaf = coder->tree;

    bool inFile = true;

    //TODO find out why the loop isn't exiting
    while (inFile) {
        ch = (unsigned char) fgetc(file);

        for (int i = 7; i >= 0; i--) {
            int dir = (ch >> i) & 1;

            if (leaf->is_compound == 1) {
                if (dir == 1) leaf = leaf->u.compound.right;
                else leaf = leaf->u.compound.left;
            } else {
                if (leaf->u.c != 3) {
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

    fclose(bitfile->file);
    fclose(file);
}

//01111010001
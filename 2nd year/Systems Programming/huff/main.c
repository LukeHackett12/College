// main program for a huffman coder

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "huff.h"
#include "encode.h"
#include "decode.h"


int main(int argc, char **argv) {
    struct huffcoder *coder;

    if (argc != 5) {
        fprintf(stderr, "Usage: %s <filename> <filename> <filename>\n", argv[0]);
        exit(1);
    }
    // create a new huffcoder structure
    coder = huffcoder_new();

    // find character frequencies
    huffcoder_count(coder, argv[2]);

    // build the Huffman tree
    huffcoder_build_tree(coder);

    // from the Huffman tree fill the table with Huffman codes
    huffcoder_tree2table(coder);

    // print the Huffman codes
    huffcoder_print_codes(coder);

    //huffcoder_print_text(coder, argv[1]);

    if(strcmp(argv[1], "encode") == 0){
        encode_file(coder, argv[3], argv[4]);
    } else if(strcmp(argv[1], "decode") == 0){
        decode_file(coder, argv[3], argv[4]);
    } else {
        printf("Not a clue what ur on\n");
    }

    return 0;
}

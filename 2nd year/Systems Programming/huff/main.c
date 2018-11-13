// main program for a huffman coder

#include <stdio.h>
#include <stdlib.h>

#include "huff.h"


int main(int argc, char **argv) {
    struct huffcoder *coder;

    if (argc != 2) {
        fprintf(stderr, "Usage: %s <filename>\n", argv[0]);
        exit(1);
    }

    // create a new huffcoder structure
    coder = huffcoder_new();

    // find character frequencies
    huffcoder_count(coder, argv[1]);

    // build the Huffman tree
    huffcoder_build_tree(coder);

    // from the Huffman tree fill the table with Huffman codes
    huffcoder_tree2table(coder);

    // print the Huffman codes
    huffcoder_print_codes(coder);

    huffcoder_print_text(coder, argv[1]);

    return 0;
}

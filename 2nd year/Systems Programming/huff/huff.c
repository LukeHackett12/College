// code for a huffman coder


#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <ctype.h>
#include <string.h>

#include "huff.h"
#include "encode.h"
#include "decode.h"

// create a new huffcoder structure
struct huffcoder *huffcoder_new() {
    huffcoder *new_huffcoder = (huffcoder *) malloc(sizeof(huffcoder));

    for (int i = NUM_CHARS - 1; i >= 0; i--) {
        new_huffcoder->freqs[i] = 0;
        new_huffcoder->code_lengths[i] = 0;
        new_huffcoder->codes[i] = 0;
    }

    new_huffcoder->tree = (huffchar *) malloc(sizeof(huffchar));
    new_huffcoder->tree->freq = 1;
    new_huffcoder->tree->is_compound = 0;
    new_huffcoder->tree->seqno = 0;
    new_huffcoder->tree->u.c = '\0';
    new_huffcoder->tree->u.compound.left = (huffchar *) malloc(sizeof(huffchar));
    new_huffcoder->tree->u.compound.right = (huffchar *) malloc(sizeof(huffchar));

    return new_huffcoder;
}

FILE *open_file(char filename[]) {
    FILE *file;

    file = fopen(filename, "r");
    if (file == NULL) {
        printf("FATAL: Error opening file %s. Aborting program.\n", filename);
        exit(1);
    }

    return file;
}

// count the frequency of characters in a file; set chars with zero
// frequency to one
void huffcoder_count(struct huffcoder *this, char *filename) {
    FILE *file = open_file(filename);
    char ch;
    while ((ch = (char) fgetc(file)) != EOF) {
        this->freqs[(int) ch]++;
    }

    for (int i = 0; i < NUM_CHARS; i++) {
        if (this->freqs[i] == 0) this->freqs[i] = 1;
    }
}

// using the character frequencies build the tree of compound
// and simple characters that are used to compute the Huffman codes
void switchElements(huffchar *array[], int i, int j) {
    huffchar *temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}

void selectionSort(huffchar *array[], int size) {
    for (int i = 0; i < size; i++) {
        int selected = i;
        for (int j = i + 1; j < size; j++) {
            if (array[j]->freq < array[selected]->freq) {
                selected = j;
            } else if (array[j]->freq == array[selected]->freq) {
                if (array[j]->seqno < array[selected]->seqno) {
                    selected = j;
                }
            }
        }

        if (selected != i) {
            switchElements(array, i, selected);
        }
    }
}

void shiftElemsDown(huffchar **array, int size, int num) {
    for (int i = 0; i < size; i++) {
        array[i] = array[i + num];
    }
}

void huffcoder_build_tree(struct huffcoder *this) {
    huffchar *sorting[NUM_CHARS];
    for (int i = NUM_CHARS - 1; i >= 0; i--) {
        sorting[i] = (huffchar *) malloc(sizeof(huffchar));
        sorting[i]->u.c = i;
        sorting[i]->freq = this->freqs[i];
        sorting[i]->seqno = i;
    }
    selectionSort(sorting, NUM_CHARS);

    int seqNo = NUM_CHARS;
    int currentSize = NUM_CHARS;
    while (currentSize > 1) {
        huffchar *one = sorting[0];
        huffchar *two = sorting[1];

        huffchar *new = (huffchar *) malloc(sizeof(huffchar));
        new->freq = one->freq + two->freq;
        new->is_compound = 1;
        new->u.compound.left = one;
        new->u.compound.right = two;
        new->seqno = seqNo;

        sorting[0] = NULL;
        sorting[1] = new;

        shiftElemsDown(sorting, currentSize, 1);
        currentSize -= 1;
        selectionSort(sorting, currentSize);

        seqNo++;
    }

    this->tree = sorting[0];
}

// recursive function to convert the Huffman tree into a table of
// Huffman codes
void tree2table_recursive(struct huffcoder *this, struct huffchar *node, int *path, int depth) {
    if (node->is_compound == 1) {
        if (node->u.compound.left != NULL) {
            int *temp = malloc(sizeof(int) * depth + 1);
            for (int i = 0; i < depth; i++) {
                temp[i] = path[i];
            }
            temp[depth] = 0;

            tree2table_recursive(this, node->u.compound.left, temp, depth + 1);
        }
        if (node->u.compound.right != NULL) {
            int *temp = malloc(sizeof(int) * depth + 1);
            for (int i = 0; i < depth; i++) {
                temp[i] = path[i];
            }
            temp[depth] = 1;

            tree2table_recursive(this, node->u.compound.right, temp, depth + 1);
        }
    } else {
        unsigned long long code = 0;
        for (int i = 0; i < depth; i++) {
            code = (code << 1) | path[i];
        }

        this->codes[node->u.c] = code;
        this->code_lengths[node->u.c] = depth;
    }
}

// using the Huffman tree, build a table of the Huffman codes
// with the huffcoder object
void huffcoder_tree2table(struct huffcoder *this) {
    tree2table_recursive(this, this->tree, 0, 0);
}

// print the Huffman codes for each character in order
void huffcoder_print_codes(struct huffcoder *this) {
    int i, j;
    char buffer[NUM_CHARS];

    for (i = 0; i < NUM_CHARS; i++) {
        // put the code into a string
        for ( j = this->code_lengths[i]-1; j >= 0; j--) {
            buffer[j] = ((this->codes[i] >> ((this->code_lengths[i]-1) - j)) & 1) + '0';
        }
        // don't forget to add a zero to end of string
        buffer[this->code_lengths[i]] = '\0';

        // print the code
        printf("char: %d, freq: %d, code: %s\n", i, this->freqs[i], buffer);
    }
}// code for a huffman coder


#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <ctype.h>
#include <string.h>

#include "huff.h"
#include "encode.h"
#include "decode.h"

// create a new huffcoder structure
struct huffcoder *huffcoder_new() {
    huffcoder *new_huffcoder = (huffcoder *) malloc(sizeof(huffcoder));

    for (int i = NUM_CHARS - 1; i >= 0; i--) {
        new_huffcoder->freqs[i] = 0;
        new_huffcoder->code_lengths[i] = 0;
        new_huffcoder->codes[i] = 0;
    }

    new_huffcoder->tree = (huffchar *) malloc(sizeof(huffchar));
    new_huffcoder->tree->freq = 1;
    new_huffcoder->tree->is_compound = 0;
    new_huffcoder->tree->seqno = 0;
    new_huffcoder->tree->u.c = '\0';
    new_huffcoder->tree->u.compound.left = (huffchar *) malloc(sizeof(huffchar));
    new_huffcoder->tree->u.compound.right = (huffchar *) malloc(sizeof(huffchar));

    return new_huffcoder;
}

FILE *open_file(char filename[]) {
    FILE *file;

    file = fopen(filename, "r");
    if (file == NULL) {
        printf("FATAL: Error opening file %s. Aborting program.\n", filename);
        exit(1);
    }

    return file;
}

// count the frequency of characters in a file; set chars with zero
// frequency to one
void huffcoder_count(struct huffcoder *this, char *filename) {
    FILE *file = open_file(filename);
    char ch;
    while ((ch = (char) fgetc(file)) != EOF) {
        this->freqs[(int) ch]++;
    }

    for (int i = 0; i < NUM_CHARS; i++) {
        if (this->freqs[i] == 0) this->freqs[i] = 1;
    }
}

// using the character frequencies build the tree of compound
// and simple characters that are used to compute the Huffman codes
void switchElements(huffchar *array[], int i, int j) {
    huffchar *temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}

void selectionSort(huffchar *array[], int size) {
    for (int i = 0; i < size; i++) {
        int selected = i;
        for (int j = i + 1; j < size; j++) {
            if (array[j]->freq < array[selected]->freq) {
                selected = j;
            } else if (array[j]->freq == array[selected]->freq) {
                if (array[j]->seqno < array[selected]->seqno) {
                    selected = j;
                }
            }
        }

        if (selected != i) {
            switchElements(array, i, selected);
        }
    }
}

void shiftElemsDown(huffchar **array, int size, int num) {
    for (int i = 0; i < size; i++) {
        array[i] = array[i + num];
    }
}

void huffcoder_build_tree(struct huffcoder *this) {
    huffchar *sorting[NUM_CHARS];
    for (int i = NUM_CHARS - 1; i >= 0; i--) {
        sorting[i] = (huffchar *) malloc(sizeof(huffchar));
        sorting[i]->u.c = i;
        sorting[i]->freq = this->freqs[i];
        sorting[i]->seqno = i;
    }
    selectionSort(sorting, NUM_CHARS);

    int seqNo = NUM_CHARS;
    int currentSize = NUM_CHARS;
    while (currentSize > 1) {
        huffchar *one = sorting[0];
        huffchar *two = sorting[1];

        huffchar *new = (huffchar *) malloc(sizeof(huffchar));
        new->freq = one->freq + two->freq;
        new->is_compound = 1;
        new->u.compound.left = one;
        new->u.compound.right = two;
        new->seqno = seqNo;

        sorting[0] = NULL;
        sorting[1] = new;

        shiftElemsDown(sorting, currentSize, 1);
        currentSize -= 1;
        selectionSort(sorting, currentSize);

        seqNo++;
    }

    this->tree = sorting[0];
}

// recursive function to convert the Huffman tree into a table of
// Huffman codes
void tree2table_recursive(struct huffcoder *this, struct huffchar *node, int *path, int depth) {
    if (node->is_compound == 1) {
        if (node->u.compound.left != NULL) {
            int *temp = malloc(sizeof(int) * depth + 1);
            for (int i = 0; i < depth; i++) {
                temp[i] = path[i];
            }
            temp[depth] = 0;

            tree2table_recursive(this, node->u.compound.left, temp, depth + 1);
        }
        if (node->u.compound.right != NULL) {
            int *temp = malloc(sizeof(int) * depth + 1);
            for (int i = 0; i < depth; i++) {
                temp[i] = path[i];
            }
            temp[depth] = 1;

            tree2table_recursive(this, node->u.compound.right, temp, depth + 1);
        }
    } else {
        unsigned long long code = 0;
        for (int i = 0; i < depth; i++) {
            code = (code << 1) | path[i];
        }

        this->codes[node->u.c] = code;
        this->code_lengths[node->u.c] = depth;
    }
}

// using the Huffman tree, build a table of the Huffman codes
// with the huffcoder object
void huffcoder_tree2table(struct huffcoder *this) {
    tree2table_recursive(this, this->tree, 0, 0);
}

// print the Huffman codes for each character in order
void huffcoder_print_codes(struct huffcoder *this) {
    int i, j;
    char buffer[NUM_CHARS];

    for (i = 0; i < NUM_CHARS; i++) {
        // put the code into a string
        for ( j = this->code_lengths[i]-1; j >= 0; j--) {
            buffer[j] = ((this->codes[i] >> ((this->code_lengths[i]-1) - j)) & 1) + '0';
        }
        // don't forget to add a zero to end of string
        buffer[this->code_lengths[i]] = '\0';

        // print the code
        printf("char: %d, freq: %d, code: %s\n", i, this->freqs[i], buffer);
    }
}

// encode the input file and write the encoding to the output file
void huffcoder_encode(struct huffcoder *this, char *input_filename,
                      char *output_filename) {
    encode_file(this, input_filename, output_filename);

}

// decode the input file and write the decoding to the output file
void huffcoder_decode(struct huffcoder *this, char *input_filename,
                      char *output_filename) {
    decode_file(this, input_filename, output_filename);
}

// encode the input file and write the encoding to the output file
void huffcoder_encode(struct huffcoder *this, char *input_filename,
                      char *output_filename) {
    encode_file(this, input_filename, output_filename);

}

// decode the input file and write the decoding to the output file
void huffcoder_decode(struct huffcoder *this, char *input_filename,
                      char *output_filename) {
    decode_file(this, input_filename, output_filename);
}
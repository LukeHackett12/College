#define _GNU_SOURCE

#include <stdlib.h>
#include <stdio.h>
#include <limits.h>
#include "bitset.h"

// create a new, empty bit vector set with a universe of 'size' items
struct bitset *bitset_new(int size) {
    bitset *new_bitset = (bitset *) malloc(sizeof(bitset));
    new_bitset->size_in_bits= size;
    new_bitset->bits_in_word= WORD_BIT;
    new_bitset->size_in_words= size/new_bitset->bits_in_word;
    new_bitset->keys = malloc(sizeof(unsigned) * size / new_bitset->bits_in_word);
    for (int i = 0; i < size / new_bitset->bits_in_word; i++) {
        new_bitset->keys[i] = 0;
    }

    return new_bitset;
}

// get the size of the universe of items that could be stored in the set
int bitset_size(struct bitset *this) {
    return this->size_in_bits;
}

// get the number of items that are stored in the set
int bitset_cardinality(struct bitset *this) {
    int count = 0;
    for (int i = 0; i < this->size_in_bits; i++) {
        if (this->keys[i] == 1) {
            count++;
        }
    }
    return count;
}

// check to see if an item is in the set
int bitset_lookup(struct bitset *this, int item) {
    return ((this->keys[item / this->bits_in_word] & (1 << (item % this->bits_in_word))) != 0);
}

// add an item, with number 'item' to the set
// has no effect if the item is already in the set
void bitset_add(struct bitset *this, int item) {
    this->keys[item / this->bits_in_word] |= (1 << (item % this->bits_in_word));
}


// remove an item with number 'item' from the set
void bitset_remove(struct bitset *this, int item) {
    this->keys[item / this->bits_in_word] &= ~(1 << (item % this->bits_in_word));
}

// place the union of src1 and src2 into dest
void bitset_union(struct bitset *dest, struct bitset *src1, struct bitset *src2) {
    for(int i = dest->size_in_bits/dest->bits_in_word; i-- > 0;){
        dest->keys[i] = src1->keys[i] | src2->keys[i];
    }
}

// place the intersection of src1 and src2 into dest
void bitset_intersect(struct bitset *dest, struct bitset *src1, struct bitset *src2) {
    for(int i = 8; i-- > 0;){
        dest->keys[i] = src1->keys[i] & src2->keys[i];
    }
}

// print the contents of the bitset
void bitset_print(struct bitset * this)
{
    int i;
    int size = bitset_size(this);
    for ( i = 0; i < size; i++ ) {
        if ( bitset_lookup(this, i) == 1 ) {
            printf("%d ", i);
        }
    }
    printf("\n");
}

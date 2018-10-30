#define _GNU_SOURCE

#include <stdlib.h>
#include <stdio.h>
#include "bitset.h"

// create a new, empty bit vector set with a universe of 'size' items
struct bitset *bitset_new(int size) {
    bitset *new_bitset = (bitset *) malloc(sizeof(bitset));
    new_bitset->size = size;
    new_bitset->keys = malloc(sizeof(unsigned) * size / MEM);
    for (int i = 0; i < size / MEM; i++) {
        new_bitset->keys[i] = 0;
    }

    return new_bitset;
}

// get the size of the universe of items that could be stored in the set
int bitset_size(struct bitset *this) {
    return this->size;
}

// get the number of items that are stored in the set
int bitset_cardinality(struct bitset *this) {
    int count = 0;
    for (int i = 0; i < this->size; i++) {
        if (this->keys[i] == 1) {
            count++;
        }
    }
}

// check to see if an item is in the set
int bitset_lookup(struct bitset *this, int item) {
    return ((this->keys[item / MEM] & (1 << (item % MEM))) != 0);
}

// add an item, with number 'item' to the set
// has no effect if the item is already in the set
int bitset_add(struct bitset *this, int item) {
    this->keys[item / MEM] |= (1 << (item % MEM));
}


// remove an item with number 'item' from the set
int bitset_remove(struct bitset *this, int item) {
    this->keys[item / MEM] &= ~(1 << (item % MEM));
}

// place the union of src1 and src2 into dest
void bitset_union(struct bitset *dest, struct bitset *src1, struct bitset *src2) {
    for(int i = dest->size/MEM; i-- > 0;){
        dest->keys[i] = src1->keys[i] | src2->keys[i];
    }
}

// place the intersection of src1 and src2 into dest
void bitset_intersect(struct bitset *dest, struct bitset *src1, struct bitset *src2) {
    for(int i = 8; i-- > 0;){
        dest->keys[i] = src1->keys[i] & src2->keys[i];
    }
}

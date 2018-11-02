//
// Created by root on 30/10/18.
//

#include <malloc.h>
#include <stdbool.h>
#include "bloom.h"
#include "bitset.h"

const int BLOOM_SEED1 = 17;
const int BLOOM_SEED2 = 29;

// compute a hash of a string using a seed value, where the result
// falls between zero and range-1
int hash_string(char *string, int seed, int range) {
    int i;
    int hash = 0;

    // simple loop for mixing the input string
    for (i = 0; string[i] != '\0'; i++) {
        hash = hash * seed + string[i];
    }
    // check for unlikely case that hash is negative
    if (hash < 0) {
        hash = -hash;
    }
    // bring the hash within the range 0..range-1
    hash = hash % range;

    return hash;
}

// create a new, empty Bloom filter of 'size' items
struct bloom *bloom_new(int size) {
    bloom *new_bloom = (bloom *) malloc(sizeof(bloom));
    new_bloom->size = size;
    new_bloom->bit_vector = bitset_new(size);
    return new_bloom;
}

// check to see if a string is in the set
int bloom_lookup(struct bloom *this, char *item) {
    int hash_one = hash_string(item, BLOOM_SEED1, this->size);
    int hash_two = hash_string(item, BLOOM_SEED2, this->size);

    bool contains_one = (this->bit_vector->keys[hash_one / this->bit_vector->bits_in_word] & (1 << (hash_one % this->bit_vector->bits_in_word))) != 0;
    bool contains_two = (this->bit_vector->keys[hash_two / this->bit_vector->bits_in_word] & (1 << (hash_two % this->bit_vector->bits_in_word))) != 0;

    return (contains_one && contains_two);
}

// add a string to the set
// has no effect if the item is already in the set
void bloom_add(struct bloom *this, char *item) {
    int hash_one = hash_string(item, BLOOM_SEED1, this->size);
    int hash_two = hash_string(item, BLOOM_SEED2, this->size);

    this->bit_vector->keys[hash_one / this->bit_vector->bits_in_word] |= (1 << (hash_one % this->bit_vector->bits_in_word));
    this->bit_vector->keys[hash_two / this->bit_vector->bits_in_word] |= (1 << (hash_two % this->bit_vector->bits_in_word));
}

// Note that you cannot safely remove items from a Bloom filter

// place the union of src1 and src2 into dest
void bloom_union(struct bloom *dest, struct bloom *src1, struct bloom *src2) {
    for (int i = dest->size / dest->bit_vector->bits_in_word; i-- > 0;) {
        dest->bit_vector->keys[i] = src1->bit_vector->keys[i] | src2->bit_vector->keys[i];
    }
}

// place the intersection of src1 and src2 into dest
void bloom_intersect(struct bloom *dest, struct bloom *src1, struct bloom *src2) {
    for (int i = 8; i-- > 0;) {
        dest->bit_vector->keys[i] = src1->bit_vector->keys[i] & src2->bit_vector->keys[i];
    }
}

void bloom_print(bloom *this) {
    bitset_print(this->bit_vector);
}



#ifndef BITSET_H
#define BITSET_H

#define MEM 32

typedef struct bitset{
    int size;
    unsigned *keys;
} bitset;

// create a new, empty bit vector set with a universe of 'size' items
struct bitset *bitset_new(int size);

// get the size of the universe of items that could be stored in the set
int bitset_size(struct bitset *this);

// get the number of items that are stored in the set
int bitset_cardinality(struct bitset *this);

// check to see if an item is in the set
int bitset_lookup(struct bitset *this, int item);

// add an item, with number 'item' to the set
// has no effect if the item is already in the set
int bitset_add(struct bitset *this, int item);

// remove an item with number 'item' from the set
int bitset_remove(struct bitset *this, int item);

// place the union of src1 and src2 into dest;
// all of src1, src2, and dest must have the same size universe
void bitset_union(struct bitset *dest, struct bitset *src1,
                  struct bitset *src2);

// place the intersection of src1 and src2 into dest
// all of src1, src2, and dest must have the same size universe
void bitset_intersect(struct bitset *dest, struct bitset *src1,
                      struct bitset *src2);


#endif
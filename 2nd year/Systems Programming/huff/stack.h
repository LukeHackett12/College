//
// Created by root on 11/11/18.
//

#ifndef HUFF_STACK_H
#define HUFF_STACK_H

#include <stdbool.h>
#include "huff.h"

typedef struct node {
    huffchar *leaf;
    struct node *next;
} node;

bool isEmpty(node *this);
huffchar *pop(node *this);
void push(node *this, huffchar *data);
void pushSorted(node *this, huffchar *data);
int size(node *this);

#endif //HUFF_STACK_H

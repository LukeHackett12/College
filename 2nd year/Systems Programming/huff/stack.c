#include <stdlib.h>
#include "stack.h"

bool isEmpty(node *this) {
    if (this->leaf == NULL) return true;
    return false;
}

huffchar *pop(node *this) {
    node *current = this;
    if (!isEmpty(this)) {
        if (this->next == NULL) {
            huffchar *last = current->leaf;
            current->leaf = NULL;
            return last;
        } else {
            while (current->next != NULL) {
                if (current->next->next == NULL) {
                    huffchar *last = current->next->leaf;
                    current->next = NULL;
                    return last;
                }
                current = current->next;
            }
        }
    }
    return NULL;
}

void push(node *this, huffchar *data) {
    if (this->leaf != NULL) {
        node *current = this;
        while (current->next != NULL) {
            current = current->next;
        }

        node *new = (node *) malloc(sizeof(node));
        new->leaf = data;
        new->next = NULL;
        current->next = new;
    } else {
        this->next = NULL;
        this->leaf = data;
    }
}

void pushSorted(node *this, huffchar *data) {
    node *current = this;
    bool correctPos = false;
    while (current->next != NULL && !correctPos) {
        if ((current->leaf->freq >= data->freq && current->next->leaf->freq <= data->freq)
            || (current->leaf->freq <= data->freq)) {
            correctPos = true;
        } else {
            current = current->next;
        }
    }

    if (isEmpty(this)) {
        current->leaf = data;
        current->next = NULL;
    } else if (this->leaf->freq < data->freq) {
        node *new = (node *) malloc(sizeof(node));
        new->leaf = data;
        node *temp = this;
        new->next = temp;
        this->leaf = new->leaf;
        this->next = new->next;
    } else {
        node *new = (node *) malloc(sizeof(node));
        new->leaf = data;
        if (current->next != NULL) {
            node *test = current->next->next;
            new->next = test;
        } else new->next = NULL;

        current->next = new;
    }
}

int size(node *this) {
    int count = 1;
    node *current = this;
    while (current->next != NULL) {
        current = current->next;
        count++;
    }
    return count;
}
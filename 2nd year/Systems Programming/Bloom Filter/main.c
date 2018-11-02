#include <stdio.h>
#include "bloom.h"

// print the contents of the bloom
void bloom_print(struct bloom *this, char *item) {
    if (bloom_lookup(this, item)) {
        printf("%s is in the set", item);
    } else {
        printf("%s is not in the set", item);
    }
    printf("\n");
}

// small routine to test a bloom
void my_test() {
    struct bloom *a = bloom_new(256);
    struct bloom *b = bloom_new(256);
    struct bloom *c = bloom_new(256);
    char *string1 = "What";
    char *string2 = "Nothing";

    bloom_add(a, string1);
    bloom_add(a, string2);

    bloom_print(a, string1);
    bloom_print(b, string2);

    // compute and print the union of sets
    bloom_union(c, a, b);
    bloom_print(c, string1);
    bloom_print(c, string2);

    // compute and print the intersection of sets
    bloom_intersect(c, a, b);
    bloom_print(c, string1);
    bloom_print(c, string2);
}

int main() {
    my_test();
    return 0;
}
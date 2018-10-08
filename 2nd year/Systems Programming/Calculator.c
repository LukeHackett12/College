#define _GNU_SOURCE

#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <ctype.h>
#include <math.h>
#include <string.h>

#define BUFSIZE 100

typedef struct node {
    int data;
    struct node *next;
} node;

bool needsConversion(char *line) {
    char *notation;
    char *target;

    bool match = false;

    notation = strtok(line, " ");
    target = "infix";
    if (strcmp(notation, target) == 0) {
        match = true;
    }

    for (int i = 0; i < strlen(notation); i++) {
        line[i] = ' ';
    }

    line[strlen(notation)] = ' ';

    return match;
}

bool isNumber(char *string) {
    int i = 0;

    while (string[i] != '\0') {
        if (!isdigit(string[i]) && string[i] != '.') {
            return false;
        }
        i++;
    }

    if (i == 0) return false;

    return true;
}

void addToList(node *head, char *string) {
    int number;
    sscanf(string, "%d", &number);

    node *next = head;
    while (next->next != NULL) {
        next = next->next;
    }

    if (head->next == NULL && head->data == 0) {
        head->data = number;
    } else {
        node *new = (node *) malloc(sizeof(node));
        new->data = number;
        new->next = NULL;
        next->next = new;
    }
}

void removeLastElement(node *head) {
    if (head->next != NULL) {
        node *next = head;
        while (next->next != NULL && next->next->next != NULL) {
            next = next->next;
        }

        next->next = NULL;
    } else {
        head->data = 0;
        head->next = NULL;
    }
}

int *numbersFromList(node *head) {
    int *elements;
    elements = malloc(sizeof(int));

    for (int i = 0; i < 2; i++) {
        node *next = head;
        while (next->next != NULL) {
            next = next->next;
        }
        elements[i] = next->data;

        removeLastElement(head);
    }

    return elements;
}

int performOperation(node *head, char *operator) {
    int *elements;
    elements = numbersFromList(head);

    switch (operator[0]) {
        case '^':
            return pow(elements[0], elements[1]);;
        case 'X':
            return elements[1] * elements[0];;
        case '/':
            return elements[1] / elements[0];;
        case '+':
            return elements[1] + elements[0];
        case '-':
            return elements[1] - elements[0];
    }

    return 0;
}

void addNumToList(node *head, int number) {
    node *next = head;
    while (next->next != NULL) {
        next = next->next;
    }

    if (head->next == NULL && head->data == 0) {
        head->data = number;
    } else {
        node *new = (node *) malloc(sizeof(node));
        new->data = number;
        new->next = NULL;
        next->next = new;
    }
}

int calculateResult(char *toCalc[], int size) {
    node *head = (node *) malloc(sizeof(node));
    head->data = 0;
    head->next = NULL;

    for (int i = 0; i < size; i++) {
        char *element = toCalc[i];
        if (isNumber(element)) {
            addToList(head, element);
        } else {
            int latest = performOperation(head, element);
            addNumToList(head, latest);
        }
    }

    int result = head->data;

    return result;
}

char *append(char character, char *string) {
    char *result = NULL;
    asprintf(&result, "%s%c", string, character);
    return result;
}

void readFileString(char *string, char **toCalc) {
    for (int i = 0; i < BUFSIZE; i++) {
        toCalc[i] = "";
    }

    int i = 0;
    int size = 0;
    char c = string[i];
    bool stringStarted = false;
    while (c != '\0' || c != '\n') {
        c = string[i];

        if (c >= 33 && c <= 126 && c != ' ') {
            toCalc[size] = append(c, toCalc[size]);
            stringStarted = true;
        } else if (c == ' ' && stringStarted) {
            size++;
        } else if (c == '\n' ||  c ==  '\0') {
            break;
        }
        i++;
    }

    toCalc[++size] = "\0";
}

int getArraySize(char **array) {
    int i = 0;

    while (array[i][0] != 0 && array[i][0] != '\0' && array[i] != "") {
        i++;
    }

    return i + !(array[i][0] == 0);
}

int isOperator(char *string) {
    int boolean = 0;
    if (string[0] == '^'
        || string[0] == 'X'
        || string[0] == '/'
        || string[0] == '+'
        || string[0] == '-') {
        boolean = 1;
    }

    return boolean;
}

void pushNonNumToStack(node *head, char character) {
    node *next = head;
    while (next->next != NULL) {
        next = next->next;
    }

    if (head->next == NULL && head->data == 0) {
        head->data = (int) character;
    } else {
        node *new = (node *) malloc(sizeof(node));
        new->data = (int) character;
        new->next = NULL;
        next->next = new;
    }
}

void popNonNumFromStack(node *head, char element[2]) {
    if (head != NULL) {
        node *next = head;
        while (next->next != NULL) {
            next = next->next;
        }
        element[0] = (char) next->data;
        element[1] = '\0';
        removeLastElement(head);
    }
}

void peekNonNumFromStack(node *head, char element[2]) {
    node *next = head;
    while (next->next != NULL) {
        next = next->next;
    }
    element[0] = (char) next->data;
    element[1] = '\0';
}

int getPrecedence(char operator) {
    switch (operator) {
        case '^':
            return 5;
        case 'X':
            return 4;
        case '/':
            return 3;
        case '+':
            return 2;
        case '-':
            return 1;
    }

    return -1;
}

int lowerPrecedence(node *head, char *operator) {
    char newOp[2];
    peekNonNumFromStack(head, newOp);

    if (newOp != NULL) {
        int isLower = -1;

        if (isOperator(newOp) == 1) {
            int new = getPrecedence(newOp[0]);
            int old = getPrecedence(operator[0]);

            if (new > old) {
                isLower = 1;
            }
            else{
                isLower = 0;
            }
        }

        return isLower;
    }

    return 0;
}

char **reversePolishNotation(char **array, int size) {
    node *head = (node *) malloc(sizeof(node));
    head->data = 0;
    head->next = NULL;

    char **resultArray;
    resultArray = malloc(size * sizeof(char *));
    for (int i = 0; i < size; i++)
        resultArray[i] = malloc((100) * sizeof(char));

    int current = 0;
    for (int i = 0; i < size; i++) {
        char *currentElement = array[i];
        if (isNumber(currentElement)) {
            resultArray[current] = currentElement;
            current++;
        } else if (currentElement[0] == '(') {
            pushNonNumToStack(head, currentElement[0]);
        } else if (isOperator(currentElement)) {
            while (lowerPrecedence(head, currentElement)  == 1) {
                char operator[2];
                popNonNumFromStack(head, operator);
                *resultArray[current] = *operator;
                current++;
            }

            pushNonNumToStack(head, currentElement[0]);
        } else if (currentElement[0] == ')') {
            char next[2];
            popNonNumFromStack(head, next);
            next[1] = '\0';

            while (next[0] != '(') {
                *resultArray[current] = *next;

                current++;
                popNonNumFromStack(head, next);
            }
        }
    }

    char next[2];
    popNonNumFromStack(head, next);
    while (next[0] != NULL) {
        if (isOperator(next)) {
            *resultArray[current] = *next;
        }
        current++;
        popNonNumFromStack(head, next);
    }

    return resultArray;
}

int main(int argc, char **argv) {
    char buf[BUFSIZE];
    char *filename;

    if (argc == 1) {
        printf("Error: No input filename provided\n");
        printf("Usage: %s <input filename>\n", argv[0]);
        exit(1);
    } else if (argc > 2) {
        printf("Error: Too many command line parameters\n");
        printf("Usage: %s <input filename>\n", argv[0]);
        exit(1);
    } else {
        filename = argv[1];
    }


    printf("%s\n", filename);

    FILE *fp = fopen(filename, "r");
    int lines = 0;
    int ch = 0;

    char *result = malloc(strlen(filename) + strlen(".results") + 1);
    strcpy(result, filename);
    strcat(result, ".results");
    FILE *np = fopen(result, "w");

    while (!feof(fp)) {
        fgets(buf, BUFSIZE, fp);

        fprintf(np, buf);

        if(buf[strlen(buf)-1] != '\n'){
            fprintf(np, "\n");
        }

        bool toConvert = needsConversion(buf);

        char **toCalc = malloc((size_t) (char *) BUFSIZE);
        readFileString(buf, toCalc);

        int size = getArraySize(toCalc);

        if (toConvert) {
            toCalc = reversePolishNotation(toCalc, size);
            size = getArraySize(toCalc);
        }

        int result = calculateResult(toCalc, size);
        printf("%d", result);
        fprintf(np, "%d\n", result);
    }

    fclose(fp);
}



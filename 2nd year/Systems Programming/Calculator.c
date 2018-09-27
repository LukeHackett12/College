#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <ctype.h>
#include <math.h>

#define BUZZ_SIZE 1024

typedef struct node{
	double data;
	struct node *next;
} node;

bool isNumber(char *string){
	int i = 0;
	while(string[i] != '\0'){
		if(!isdigit(string[i]) && string[i] != '.'){
			return false;
		}
		i++;
	}

	if(i == 0) return false; 

	return true;
}

void addToList(node *head, char *string){
	double number;
	sscanf(string, "%lf", &number);

	node *next = head;
	while(next->next!= NULL){
		next = next->next;
	}

	node *new = (node*)malloc(sizeof(node));
	new->data = number;
	new->next = NULL;
	next->next = new;
}

void removeLastElement(node *head){
	node *next = head;
	while(next->next != NULL && next->next->next != NULL){
		next = next->next;
	}

	next->next = NULL;
}

double *numbersFromList(node *head){
	double *elements;
	elements = malloc(sizeof(double));

	for(int i = 0; i < 2; i++){
		node *next = head;
		while(next->next != NULL){
			next = next->next;
		}
		elements[i] = next->data;

		removeLastElement(head);
	}

	return elements;
}

double performOperation(node *head, char *operator){
	double *elements;
	elements = numbersFromList(head);

	switch(operator[0]){
		case '^':
			return pow(elements[0],elements[1]);
			break;
		case '*':
			return elements[1]*elements[0];
			break;
		case '/':
			return elements[1]/elements[0];
			break;
		case '+':
			return elements[1]+elements[0];
			break;
		case '-':
			return elements[1]-elements[0];
			break;
	}

	return 0;
}

void addNumToList(node *head, double number){
	node *next = head;
	while(next->next!= NULL){
		next = next->next;
	}

	node *new = (node*)malloc(sizeof(node));
	new->data = number;
	new->next = NULL;
	next->next = new;
}

double popFromStack(node *head){
	double element;

	node *next = head;
	while(next->next != NULL){
		next = next->next;
	}
	element = next->data;
	
	removeLastElement(head);

	return element;
}

double calculateResult(char *toCalc[], int size){
	node *head = (node*)malloc(sizeof(node));
	head->next = NULL;

	for(int i = 0; i < size; i++){
		if(isNumber(toCalc[i])){
			addToList(head, toCalc[i]);
		}
		else{
			double latest = performOperation(head, toCalc[i]);
			addNumToList(head, latest);
		}
	}

	double result = head->next->data;

	return result;
}

char *append(char character, char *string)
{
	char * result = NULL;
	asprintf(&result, "%s%c", string, character);
	return result;
}

char **readFileString(char *file){
	char buff[BUZZ_SIZE];
	FILE *f = fopen(file, "r");
	fgets(buff, BUZZ_SIZE, f);
	fclose(f);

	char *toCalc[BUZZ_SIZE];
	for(int i = 0; i < BUZZ_SIZE; i++){
		toCalc[i] = "";
	}

	int i = 0;
	int size = 0;
	char c = buff[i];
	while(c != '\0'){
		c = buff[i];

		if(c >= 33 && c <= 126 && c != ' '){
			toCalc[size] = append(c, toCalc[size]);
		}
		else if(c == ' '){
			size++;
		}

		i++;
	}

	return toCalc;
}

int getArraySize(char **array){
	int i = 0;

	while(array[i][0] != 0 && array[i][0] != '\0' && array[i] != ""){
		i++;
	}

	return i + !(array[i][0] == 0);
}

int isOperator(char *string){
	int boolean = 0;
	if(string[0] == '^'
		|| string[0] == '*'
		|| string[0] == '/'
		|| string[0] == '+'
		|| string[0] == '-'){
		boolean = 1;
	}

	return boolean;
}

void pushNonNumToStack(node *head, char character){
	node *next = head;
	while(next->next != NULL){
		next = next->next;
	}

	node *new = (node*)malloc(sizeof(node));
	new->data = (double) character;
	new->next = NULL;
	next->next = new;
}

char *popNonNumFromStack(node *head){
	char *element[1];

	node *next = head;
	while(next->next != NULL){
		next = next->next;
	}
	element[0] = (char)next->data;

	removeLastElement(head);

	return element;
}

char *peekNonNumFromStack(node *head){
	char *element[1];

	node *next = head;
	while(next->next != NULL){
		next = next->next;
	}
	element[0] = (char)next->data;

	return element;
}

int getPrecedence(char operator){
	switch(operator){
		case '^':
			return 5;
		case '*':
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

int lowerPrecedence(node *head, char *operator){
	char *newOp = peekNonNumFromStack(head);
	char key[5]={'^','*','/','+','-'};

	int isLower = 0;

	if(isOperator(newOp) == 1){
		/* --------------
		 * PROBLEM IS HERE
		 * --------------
		 * */

		int new = getPrecedence(newOp[0]);
		int old = getPrecedence(operator[0]);

		if(new > old){
			isLower=1;
		}
	}

	return isLower;
}

char **reversePolishNotation(char **array, int size){
	node *head = (node*)malloc(sizeof(node));

	char **resultArray;
	resultArray = malloc(size * sizeof(char*));
	for (int i = 0; i < size; i++)
		resultArray[i] = malloc((100) * sizeof(char));
	
	int current = 0;
	for(int i = 0; i < size; i++){
		if(isNumber(array[i])){
			resultArray[current] = array[i];
			current++;
		}
		else if(array[i][0] == '('){
			pushNonNumToStack(head, array[i][0]);
		}
		else if(isOperator(array[i])){
			while(lowerPrecedence(head, array[i]) == 1){
				char *operator = popNonNumFromStack(head);
				*resultArray[current] = *operator;
				current++;
			}

			pushNonNumToStack(head, array[i][0]);
		}
		else if(array[i][0] == ')'){
			char *next = popNonNumFromStack(head);
			next[1] = '\0';

			while(next[0] != '('){
				*resultArray[current] = *next;

				current++;
				next = popNonNumFromStack(head);
			}
		}
	}

	char *next = popNonNumFromStack(head);
	while(next[0] != NULL){
		if(isOperator(next)){
			*resultArray[current] = *next;
		}
		current++;
		next = popNonNumFromStack(head);
	}

	return resultArray;
}

int main(){
	char **toCalc = readFileString("numbers.txt");
	int size = getArraySize(toCalc);

	toCalc = reversePolishNotation(toCalc, size);
	size = getArraySize(toCalc);

	double result = calculateResult(toCalc, size);
	printf("%f\n", result);
}

#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <ctype.h>
#include <math.h>

#define BUZZ_SIZE 1024

typedef struct node{
	int data;
	struct node *next;
} node;

bool isNumber(char *string){
	int i = 0;
	while(string[i] != '\0'){
		if(!isdigit(string[i])){
			return false;
		}
		i++;
	}
	return true;
}

void addToList(node *head, char *string){
	int number;
	sscanf(string, "%d", &number);

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

int *numbersFromList(node *head){
	int *elements;
	elements = malloc(sizeof(int));

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

int performOperation(node *head, char *operator){
	int *elements;
	elements = numbersFromList(head);

	switch(operator[0]){
		case '^':
			return (int) pow((double)elements[0],(double)elements[1]);
			break;
		case '*':
			return elements[0]*elements[1];
			break;
		case '/':
			return elements[0]/elements[1];
			break;
		case '+':
			return elements[0]+elements[1];
			break;
		case '-':
			return elements[0]-elements[1];
			break;
	}

	return 0;
}

int calculateResult(char *toCalc[], int size){
	node *head = (node*)malloc(sizeof(node));
	head->next = NULL;

	int result = 0;
	for(int i = 0; i < size; i++){
		if(isNumber(toCalc[i])){
			addToList(head, toCalc[i]);
		}
		else{
			result += performOperation(head, toCalc[i]);	
		}
	}

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
		if(c != ' '){
			toCalc[size] = append(c, toCalc[size]);
		}
		else{
			size++;
		}
		i++;
	}

	return toCalc;
}

int getArraySize(char **array){
	int i = 0;
	while(array[i] != ""){
		i++;
	}

	return i+1;
}

int main(){
	char **toCalc = readFileString("numbers.txt");
	int size = getArraySize(toCalc);

	int result = calculateResult(toCalc, size);
	printf("%d\n", result);
}

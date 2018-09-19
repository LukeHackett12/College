#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>

void switchElements(int array[], int i, int j){
	int temp = array[i];
	array[i] = array[j];
	array[j] = temp;
}

void selectionSort(int array[], int size){
	for(int i = 0; i < size; i++){
		int selected = i;
		for(int j = i+1; j < size; j++){
			if(array[j] < array[selected]){
				selected = j;
			}
		}

		if(selected != i){
			switchElements(array, i, selected);
		}
	}
}

void insertionSort(int array[], int size){
	for(int i = 0; i  < size; i++){
		int temp = array[i];
		int j = i-1;

		while(j >= 0 && array[j] > temp){
			array[j+1] = array[j];
			j = j - 1;
		}
		array[j+1] = temp;
	}
}

void bubbleSort(int array[], int size) {
	for(int i = 0; i < size - 1; i++) { 
		bool swapped = false;
		for(int j = 0; j < size - 1 - i; j++) {
			if(array[j] > array[j+1]) {
				switchElements(array, j, j+1);
				swapped = true;
			}
		}

		if(!swapped) {
			break;
		}   
	}
}

void assignArray(int array[], int size){
	for(int i = 0; i < size; i++){	
		array[i] = rand()%(15)+1;
	}
}

void printArray(int array[], int size){
	for(int i = 0; i < size; i++){
		printf("%d, ", array[i]);
	}
	printf("\n");
}

void callSelection(int array[], int size){
	assignArray(array, size);
	printArray(array, size);
	selectionSort(array, size);
	printArray(array, size);
}

void callInsertion(int array[], int size){
	assignArray(array, size);
	printArray(array, size);
	insertionSort(array, size);
	printArray(array, size);
}

void callBubble(int array[], int size){
	assignArray(array, size);
	printArray(array, size);
	bubbleSort(array, size);
	printArray(array, size);
}

int main(){
	int size = 10;
	int array[size];

	callSelection(array, size);
	printf("\n");
	callInsertion(array, size);
	printf("\n");
	callBubble(array, size);
}

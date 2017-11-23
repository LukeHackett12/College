	AREA	Sets, CODE, READONLY
	IMPORT	main
	EXPORT	start

start

	LDR R0, =ASize
	LDR R0, [R0]	; Load Values
	LDR R1, =AElems
	LDR R2, =BSize
	LDR R2, [R2]
	LDR R3, =BElems
	LDR R4, =CSize
	LDR R4, [R4]
	LDR R5, =CElems

	LDR R6, =0	;	ASize
	LDR R7, =0	;	BSize

while
	LDR R8, [R1]	;	AElems
	CMP R7, R2
	BEQ endwhile
	ADD R7, R7, #1	;	BElems checked + 1
	LDR R9, [R3]	;	BElems

	CMP R8, R9
	BEQ equal
	ADD R3, R3, #4	;	BElems + 4
	B while

equal
	LDR R10, ='%'
	STR R10, [R3]
	ADD R1, R1, #4
	LDR R3, =BElems

	LDR R7, =0		; BSize back to zero
	ADD R6, R6, #1 	; AElems checked plus 1

	B while

endwhile

	; Add the element to C if it gets to this stage
	ADD R6, R6, #1 	; AElems to check is 1 less
	STR R8, [R5]	;	A element into C
	ADD R5, R5, #4	;
	;LDR R5, [R5]	;	CElems + 4
	ADD R4, R4, #1	;
	STR R4, [R4]	;	CSize++

	CMP R6, R0
	BEQ endsets
	ADD R1, R1, #4	;	AElems + 4
	LDR R3, =BElems

	LDR R7, =0		; BSize back to zero

	B while

endsets

	LDR R3, =BElems
	LDR R7, =0

whileB
	; Go through set B, if its not a percent sign add to C
	CMP R7, R2
	BEQ endProgram
	LDR R9, [R3]
	CMP R9, #'%'
	BNE addelem
	ADD R3, R3, #4
	ADD R7, R7, #1
	B whileB

addelem
	STR R9, [R5]
	ADD R5, R5, #4	;	CElems + 4
	ADD R4, R4, #1	;
	STR R4, [R4]	;	CSize++
	ADD R3, R3, #4	; 	BElems + 4
	ADD R7, R7, #1	;	BElems added + 1
	B whileB

endProgram

stop	B	stop


	AREA	TestData, DATA, READWRITE

ASize	DCD	8			; Number of elements in A
AElems	DCD	4,6,2,13,19,7,1,3	; Elements of A

BSize	DCD	6			; Number of elements in B
BElems	DCD	13,9,1,9,5,8		; Elements of B

CSize	DCD	0			; Number of elements in C
CElems	SPACE	56			; Elements of C
							; 4,6,2,19,7,3,9,9,5,8

	END

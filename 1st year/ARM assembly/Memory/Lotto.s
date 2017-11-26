	AREA	Lotto, CODE, READONLY
	IMPORT	main
	EXPORT	start

start

	;	have a count for the current ticket
	;	and the number on the ticket
	;	Compare to the draw and add to count if
	;	they are the same. When the ticket count is equal
	;	to six determine which match i goes in, increment
	;	ticket and start again. End when ticket count is 
	;	equal the count stored in memory
	
	LDR	R1, =TICKETS
	LDR R7, =COUNT
	LDR R7, [R7]
	
while

	CMP R7, #0
	BEQ endwhile

while1

	LDR R0, =6
	LDR R4, =0

while2

	CMP R0, #0
	BEQ endwhile1

	LDR R2, =6
	LDR R3, =DRAW

while3

	CMP R2, #0
	BEQ endwhile2
	LDRB R5, [R1]
	LDRB R6, [R3]

	CMP R5, R6
	BNE	endwhile3
	SUB R0, R0, #1
	ADD R4, R4, #1
	ADD R1, R1, #1
	B while2

endwhile3

	SUB R2, R2, #1
	ADD R3, R3, #1
	B while3

endwhile2

	SUB R0, R0, #1
	ADD R1, R1, #1
	LDR R3, =DRAW
	B while2

endwhile1

	SUB R7, R7, #1
	CMP R4, #4
	BEQ four
	CMP R4, #5
	BEQ five
	CMP R4, #6
	BEQ six
	B while

four

	LDR R8, =MATCH4
	LDRB R9, [R8]
	ADD R9, R9, #1
	STRB R9, [R8]
	B while

five

	LDR R8, =MATCH5
	LDRB R9, [R8]
	ADD R9, R9, #1
	STRB R9, [R8]
	B while

six

	LDR R8, =MATCH6
	LDRB R9, [R8]
	ADD R9, R9, #1
	STRB R9, [R8]
	B while

endwhile

stop	B	stop 

	AREA	TestData, DATA, READWRITE

COUNT	DCD	3			; Number of Tickets
TICKETS	DCB	9, 11, 12, 22, 26, 30
	DCB	10, 4, 6, 22, 26, 30
	DCB	10, 11, 12, 22, 26, 30

DRAW	DCB	10, 11, 12, 22, 26, 30	; Lottery Draw

MATCH4	DCD	0
MATCH5	DCD	0
MATCH6	DCD	0

	END	

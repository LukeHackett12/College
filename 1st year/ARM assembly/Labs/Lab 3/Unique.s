	AREA	Unique, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	
	LDR R0, =1
	LDR	R1, =VALUES
	LDR R2, =COUNT
	LDR R2, [R2]

while

	CMP R2, #0
	BEQ endwhile
	LDR R3, [R1]
	LDR R4, =VALUES
	LDR R7, =COUNT
	LDR R7, [R7]
	LDR R6, =1

while1

	CMP R7, #0
	BEQ endwhile1
	SUB R7, R7, #1
	LDR R5, [R4]
	CMP R3, R5
	BEQ equal
	ADD R4, R4, #4
	B while1
	
equal

	CMP R6, #0
	BEQ negative
	SUB R6, R6, #1
	ADD R4, R4, #4
	B while1

endwhile1

	ADD R1, R1, #4
	SUB R2, R2, #1
	B while

negative
	LDR R0, =0
	B endwhile

endwhile
	 
stop	B	stop


	AREA	TestData, DATA, READWRITE
	
COUNT	DCD	10
VALUES	DCD	5, 2, 1, 4, 13, 7, 18, 8, 9, 12


	END	
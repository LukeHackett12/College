	AREA	Expressions, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R1, =5	; x = 5
	LDR	R2, =6	; y = 6
	LDR R3, =3
	LDR R4, =5
	
	MUL R0, R1, R1
	MUL R0, R3, R0
	MUL R5, R1, R4
	ADD R0, R0, R5
	
	;end of first sum
	
	LDR R3, =2
	LDR R4, =6
	LDR R5, =3
	
	MUL R0, R1, R1
	MUL R0, R3, R0
	MUL R6, R1, R2
	MUL R6, R4, R6
	ADD R0, R6, R0
	MUL R7, R2, R2
	MUL R7, R5, R7
	ADD R0, R7, R0
	
	LDR R3, =-4
	LDR R4, =3
	LDR R5, =8
	
	MUL R0, R1, R1
	MUL R0, R1, R0
	MUL R6, R1, R1
	MUL R6, R3, R6
	ADD R0, R6, R0
	MUL R7, R4, R1
	ADD R0, R7, R0
	ADD R0, R0, R5
	
	; your program goes here
	
stop	B	stop

	END	
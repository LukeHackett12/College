	AREA	AsciiValue, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R4, ='2'	; Load '2','0','3','4' into R4...R1
	LDR	R3, ='0'	;
	LDR	R2, ='3'	;
	LDR	R1, ='4'	;
	
	LDR R5, = 0X3E8
	LDR R6, = 0X64
	LDR R7, = 0XA
	
	SUB R4, R4, #0x30
	SUB R3, R3, #0x30
	SUB R2, R2, #0x30
	SUB R1, R1, #0x30
	
	MUL R4, R5, R4
	MUL R3, R6, R3
	MUL R2, R7, R2

	ADD R0, R4, R3
	ADD R0, R0, R2
	ADD R0, R0, R1
	
stop	B	stop

	END	
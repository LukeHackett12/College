	AREA	ShiftAndAdd, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R1, =9
	LDR	R2, =10

	LDR R3, =0
	MOV R4, R2
	LDR R5, =0

while
	CMP R4, #0
	BEQ endwhile
	MOVS R4, R4, LSR #1
	BCC addCount
	ADD R3, R3, R1, LSL R5

addCount
	ADD R5, R5, #1
	B while

endwhile

stop	B	stop


	END

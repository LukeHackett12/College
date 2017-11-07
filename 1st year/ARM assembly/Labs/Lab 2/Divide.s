	AREA	Divide, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR R0, =0x0 ; Quotient
	LDR R1, =0x0 ; Remainder
	LDR R2, =0X5 ; a
	LDR R3, =0X2 ; b

	MOV R1, R2

WHILE
	CMP R1, R3
	BLO ENDWH
	SUB R1, R1, R3
	ADD R0, R0, #1
	B WHILE
ENDWH

stop	B	stop
	END

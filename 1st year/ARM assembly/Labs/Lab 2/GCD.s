	AREA	GCD, CODE, READONLY
	IMPORT	main
	EXPORT	start

start

	LDR R2, =0x18 ; a
	LDR R3, =0x20 ; b

WHILE
	CMP R2, R3
	BEQ ENDWH ; IF a=b end while

	CMP R2, R3
	BGE AISGREATER ; BGE- Granch if greater, go to AISGREATER
	SUB R3, R3, R2 ; b is greater then a, so b = b - a

AISGREATER
	SUB R2, R2, R3 ; a is greater then b, so a = a - b

	B WHILE ; branch back to top of while loop
ENDWH

	MOV R0, R2 ; Set R0 equal to the result R2(a)

stop	B	stop

	END

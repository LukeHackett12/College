	AREA	StringReverse, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R1, =strSrc
	LDR	R2, =strDst
	LDR R0, =0
	
count
	LDRB R3, [R1]
	CMP R3, #0
	BEQ endCount
	ADD R0, R0, #1
	ADD R1, R1, #1
	B count
endCount
	
	SUB R1, R1, #1
reverse
	LDRB R3, [R1]
	CMP R0, #0
	BEQ endReverse
	STRB R3, [R2]
	ADD R2, R2, #1
	SUB R1, R1, #1
	SUB R0, R0, #1
	B reverse
endReverse

stop	B	stop


	AREA	TestData, DATA, READWRITE


strSrc	DCB	"hello",0
strDst	SPACE	128

	END	
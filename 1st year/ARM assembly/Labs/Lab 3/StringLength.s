	AREA	StringLength, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R1, =str1
	LDR R0, =0
	
count
	LDRB R2, [R1]
	CMP R2, #0
	BEQ endCount
	ADD R0, R0, #1
	ADD R1, R1, #1
	B count
endCount
	
stop	B	stop



	AREA	TestData, DATA, READWRITE

str1	DCB	"Friday",0

	END	

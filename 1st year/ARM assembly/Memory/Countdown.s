	AREA	Countdown, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R1, =cdWord	; Load start address of word
	LDR	R2, =cdLetters	; Load start address of letters

	;	Take letter from cdWord
	;	Compare to each value in cdLetters
	;	When found, replace value in cdLetters with %
	;	Repeat for all letters in cdWord
	;	If at the end without errors store 1 in R0
	;	Else store 0 in R0

while

	LDRB R3, [R1]	;	cdWord value
	CMP R3, #0
	BEQ endwhile

while2
	LDR R0, =0
	LDRB R4, [R2]	; 	cdLetters value

	CMP R4, #0
	BEQ endwhile2
	CMP R3, R4
	BEQ equal
	ADD R2, R2, #1
	B while2

equal
	LDR R0, =1
	LDR R4, ='%'
	STRB R4, [R2]
	B endwhile2

endwhile2

	CMP R0, #1
	BNE endwhile
	ADD R1, R1, #1
	LDR R2, =cdLetters
	B while

endwhile

stop	B	stop



	AREA	TestData, DATA, READWRITE

cdWord
	DCB	"beems",0

cdLetters
	DCB	"daetebzsb",0

	END

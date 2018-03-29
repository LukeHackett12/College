	AREA	FpAdd, CODE, READONLY
	IMPORT	main
	EXPORT	start

start

	;
	; Part 1 - decode
	;

	LDR	r0, =0x3F000000		; fpval1 = 0.5
	LDR	r1, =0x3ee00000		; fpval2 = -5

	BL	fpadd

stop	B	stop

; fpadd subroutine
; Adds two IEEE754 single precision floating point values
; Parameters:
;   R0: first floating point value
;   R1: second floating point value
; Return value:
;   R0: floating point result
;
fpadd
	STMFD sp!, {R4-R11, lr}
	MOV R10, R1
	
	;
	; YOUR SUBROUTINE IMPLEMENTATION HERE
	;
	BL decodeFraction
	MOV R4, R1
	MOV R5, R2
	MOV R6, R3
	
	MOV R0, R10
	BL decodeFraction
	MOV R7, R1
	MOV R8, R2
	MOV R9, R3

	CMP R5, R8
	BGE firstBigger
	B secondBigger

firstBigger
	SUB R10, R5, R8
	ORR R9, R9, #0x800000
	MOV R11, R5
forFirst
	CMP R10, #0
	BEQ endForFirst
	MOV R9, R9, LSR #1
	ADD R8, #1
	SUB R10, #1
	B forFirst
endForFirst
	B endNormalise

secondBigger
	SUB R10, R8, R5
	ORR R6, R6, #0x800000
	MOV R11, R8
forSecond
	CMP R10, #0
	BEQ endForSecond
	MOV R6, R6, LSR #1
	ADD R5, #1
	SUB R10, #1
	B forSecond
endForSecond
	B endNormalise

endNormalise

	ADD R2, R11, #0x7F
	MOV R2, R2, LSL #0x17
	
	CMP R4, #1
	BEQ negSub
	CMP R7, #1
	BEQ secNeg
	ADD R3, R6, R9
	B signPos
	
negSub
	CMP R7, #1
	BEQ bothNeg
	SUB R3, R6, R9
	CMP R6, R9
	BGT signNeg
	B signPos

secNeg
	SUB R3, R9, R6
	CMP R9, R6
	BGT signNeg
	B signPos

bothNeg
	ADD R3, R6, R9
	B signNeg

signPos
	MOV R1, #0
	B encode
signNeg
	MOV R1, #1
	B encode
encode
	BL encodeFraction
	
	LDMFD sp!, {R4-R11, pc}

decodeFraction
	STMFD sp!, {R4, lr}
	;Get sign
	;bit mask is 0x80000000
	;Put signs in r5 and r6
	
	LDR r4, =0x80000000
	AND r1, r4, r0
	MOV r1, r1, LSR #31

	;Get exponent
	;Use bitmask for the next 8 bits
	;Hex value of 0x7F800000
	;Put exponents in r7 and r8 
	
	LDR r4, =0x7F800000
	AND r2, r4, r0
	MOV R2, R2, LSR #0x17
	SUBS R2, R2, #0x7F

	;Get fraction
	;Use bitmask for the next 8 bits
	;Hex value of 0x7FFFFF00
	;Put fractions in r9 and r10
	
	LDR r4, =0x007FFFFF
	AND r3, r4, r0
	
	LDMFD sp!, {R4, pc}

encodeFraction
	STMFD sp!, {lr}
	MOV R0, R1, LSL #31
	ADD R0, R0, R2
	ADD R0, R0, R3
	LDMFD sp!, {pc}

	END

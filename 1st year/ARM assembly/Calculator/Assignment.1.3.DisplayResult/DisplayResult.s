	AREA	DisplayResult, CODE, READONLY
	IMPORT	main
	IMPORT	getkey
	IMPORT	sendchar
	EXPORT	start
	PRESERVE8

start

startProgram

	LDR R3, =10
	LDR R4,=0
	LDR R6, =0

read
    BL	getkey		; read key from console
    CMP	R0, #0x0D  	; while (key != enter)
    BEQ	endReadAgain; {
    CMP R0, #0x1B
	BEQ endProgram
	BL	sendchar	;   echo key back to console

    ;
    ; do any necessary processing of the key
    ;

	CMP R0, #'*'
    BEQ multiplyOperator
    CMP R0, #'+'
    BEQ addOperator
    CMP R0, #'-'
    BEQ subtractOperator

    MUL R4, R3, R4
    SUB R0, R0, #0x30
    ADD R4, R0, R4

	B read

multiplyOperator
    LDR R7,= 1
    B endRead

addOperator
    LDR R7,= 2
    B endRead

subtractOperator
    LDR R7,= 3
    B endRead

endRead

readAgain

    BL	getkey		; read key from console
    CMP	R0, #0x0D  	; while (key != enter)
    BEQ	endReadAgain		; {
    BL	sendchar	;   echo key back to console

    ;
    ; do any necessary processing of the key
    ;

    MUL R6, R3, R6
    SUB R0, R0, #0x30
    ADD R6, R0, R6

	B readAgain

endReadAgain
	
	LDR R0, ='='
	BL sendchar

    CMP R7,#1
    BEQ multiplyExp
    CMP R7,#2
    BEQ addExp
    CMP R7,#3
    BEQ subtractExp

multiplyExp
    MUL R5, R4, R6
    B endCalculate

addExp
    ADD R5, R4, R6
    B endCalculate

subtractExp
    SUB R5, R4, R6
    B endCalculate

endCalculate

	LDR R10, =0X30 ;ASCII offset
	
	LDR R8, =1 ;Power to test
	LDR R12, =1 ;Actual power of number
	
digits
	CMP R5, R8
	BLE endDigits
	MUL R8, R3, R8
	ADD R12, R12, #1
	B digits
endDigits

print
	MOV R8, #1 ;Power to test
	MOV R9, #1 ;Actual number to divide
	
power
	CMP R5, R8
	BLE endPower
	MOV R9, R8
	MUL R8, R3, R8
	B power
endPower

	LDR R11, =0 ; Quotient	
	
divide
	CMP R5, R9 ; while(remainder >= power)
	BLO endDivide
	SUB R5, R5, R9
	ADD R11, R11, #1
	B divide
endDivide
	
	SUB R12, R12, #1
	CMP R12, #0
	BEQ endPrint
	ADD R0, R10, R11
	BL sendchar
	B print

endPrint

	LDR R0, =0xA
	BL sendchar
	
	B startProgram
	
endProgram

	LDR R0, =0x47
	BL sendchar
	LDR R0, =0x6F
	BL sendchar
	LDR R0, =0x6F
	BL sendchar
	LDR R0, =0x64
	BL sendchar
	LDR R0, =0x62
	BL sendchar
	LDR R0, =0x79
	BL sendchar
	LDR R0, =0x65
	BL sendchar
	LDR R0, =0x21
	BL sendchar
	
stop	B	stop

	END
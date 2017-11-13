	AREA	CalculatorStageOne, CODE, READONLY
	IMPORT	main
	IMPORT	getkey
	IMPORT	sendchar
	EXPORT	start
	PRESERVE8

start

startProgram	; do 
				; {

	LDR R3, =10
	LDR R4,=0 ; number1
	LDR R6, =0 ; number2
	LDR R7, =0 ; operator
	
read
    BL	getkey		; read key from console
	CMP R0, #0x1B   ; 
	BEQ endProgram	; 
	BL	sendchar	;   echo key back to console
		
    ;
    ; do any necessary processing of the key
    ;

	CMP R0, #'*'			;if(input == '*')
    BEQ multiplyOperator	;	multiplyOperator()
    CMP R0, #'+'			;else if(input == '+')
    BEQ addOperator			;	addOperator()
    CMP R0, #'-'			;else if(input == '-')
	BEQ subtractOperator	;	subtractOperator()
	CMP R0, #'/'			;else if(input == '/')
	BEQ divideOperator		;	divideOperator()
	CMP R0, #'^'			;else if(input == '/')
	BEQ powerOperator		;	powerOperator()

    MUL R4, R3, R4		; number1 *= 10
    SUB R0, R0, #0x30	; input -= ASCII Offset
	MOV R5, R0			; lastDigit = input
    ADD R4, R0, R4		; number1 += input

	B read	; read next digit

multiplyOperator 	; int multiplyOperator()
    LDR R7,= 1 		; 	operator = 1;
    B endRead

addOperator 		; int addOperator()
    LDR R7,= 2 		; 	operator = 2
    B endRead

subtractOperator 	; subtractOperator()
    LDR R7,= 3 		; 	operator = 3
    B endRead

divideOperator		; divideOperator()
    LDR R7,= 4 		; 	operator = 4
	B endRead
	
powerOperator		; divideOperator()
    LDR R7,= 5 		; 	operator = 5
	B endRead

endRead

readAgain

    BL	getkey		; read key from console
    CMP	R0, #0x0D  		; while (key != enter)
    BEQ	endReadAgain	; {
    BL	sendchar	; echo key back to console

    ;
    ; do any necessary processing of the key
    ;

    MUL R6, R3, R6		; number2 *= 10
    SUB R0, R0, #0x30	; input -= ASCII offset
	MOV R5, R0			; lastDigit = input
    ADD R6, R0, R6		; number2 += input
    
	B readAgain
	
endReadAgain

endProgram

stop	B	stop

	END
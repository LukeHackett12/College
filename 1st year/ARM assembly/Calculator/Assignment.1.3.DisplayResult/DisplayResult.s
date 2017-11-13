	AREA	CalculatorStageThree, CODE, READONLY
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
	CMP R0, #0x8	; 	 if(key == backspace)
	BEQ backspace 	; 		 check()
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

backspace
	BL sendchar		; print backspace
	LDR R0, =0x20	;
	BL sendchar		; print space
	LDR R0, =0x8	;	
	BL sendchar		; print backspace
	SUB R4, R4, R5  ; number2 -= lastDigit
	
	MOV R5, R4		; R5 is now equal to the number to divide
	
	LDR R4, =0		; number1 = quotient
	
removePower
	CMP R5, #10			; while(remainder >= power)
	BLO read      		; {
	SUB R5, R5, #10		;	 remainder = remainder - 10
	ADD R4, R4, #1		;	 number1++
	B removePower       ; }

endRead

readAgain

    BL	getkey		; read key from console
    CMP	R0, #0x0D  		; while (key != enter)
    BEQ	endReadAgain	; {
	CMP R0, #0x8		; 	 if(key == backspace)
	BEQ backspaceSecond ; 		 check()
    BL	sendchar	; echo key back to console

    ;
    ; do any necessary processing of the key
    ;

    MUL R6, R3, R6		; number2 *= 10
    SUB R0, R0, #0x30	; input -= ASCII offset
	MOV R5, R0			; lastDigit = input
    ADD R6, R0, R6		; number2 += input
    
	B readAgain
	
backspaceSecond
	BL sendchar		; print backspace
	LDR R0, =0x20	;
	BL sendchar		; print space
	LDR R0, =0x8	;	
	BL sendchar		; print backspace
	SUB R6, R6, R5  ; number 2 -= lastDigit
	
	MOV R5, R6 ; R5 now equal character to divide
	
	LDR R6, =0 ; number2 = quotient

removePowerTwo
	CMP R5, #10			; while(remainder >= power)
	BLO readAgain		; {
	SUB R5, R5, #10		;	 remainder = remainder - 10
	ADD R6, R6, #1		;	 number2++
	B removePowerTwo	; }

endReadAgain

	LDR R5, =0x0		; result = 0
	
	LDR R0, =' '
	BL sendchar
	LDR R0, ='='
	BL sendchar
	LDR R0, =' '
	BL sendchar
	
    CMP R7,#1		;if(operator == 1)
    BEQ multiplyExp	;	multiplyExp()
    CMP R7,#2		;else if(operator == 2)
    BEQ addExp		;	addExp()
    CMP R7,#3		;else if(operator == 3)
    BEQ subtractExp	;	subtractExp()
	CMP R7,#4		;else if(operator == 4)
    BEQ divideExp	;	divideExp()
	CMP R7,#5		;else if(operator == 5)
    BEQ powerExp	;	powerExp()

multiplyExp
    MUL R5, R4, R6	; result = number1 * number2
    B endCalculate

addExp
    ADD R5, R4, R6	; result = number1 + number2
    B endCalculate

subtractExp
    SUB R5, R4, R6	; result = number1 - number2
    B endCalculate

divideExp
	LDR R2, =0x0	; remainder = 0
	
	MOV R2, R4		; remainder = number1
	
	CMP R6, #0
	BEQ nope
	
subDivide			; 
	CMP R2, R6		; while(remainder >= number2)
	BLO endCalculate; {
	SUB R2, R2, R6	;	 remainder -= number2
	ADD R5, R5, #1	;	 result += 1
	B subDivide		; }

powerExp
	MOV	R5, #1		; result = 1
	
calcPower			
	CMP R6, #0
	BEQ endCalculate; while(number2 != 0) {	 
	MUL R5, R4, R5	;	 result = result * number1
	SUB R6, R6, #1	; 	 number2 -= 1
	B calcPower		; }
	
endCalculate
	
	LDR R10, =0X30 ;ASCII offset
	
remainderPrint

	LDR R8, =10 ;testPower = 1
	LDR R12, =1 ;numberOfDigits = 1 

digits					;
	CMP R5, R8			;
	BLO endDigits		;	 while(result > testPower)
						; 	 {
	MUL R8, R3, R8		; 	 	testPower *= 10
	ADD R12, R12, #1	; 	 	numberOfDigits += 1
	B digits            ;    }
endDigits

print
	MOV R8, #1 			; testPower = 10
	MOV R9, #1 			; realPower = 1
	
power
	CMP R5, R8			; if(result <= testPower)
	BLE endPower		;	 end division
						; else
	MOV R9, R8			; 	 realPower = testPower
	MUL R8, R3, R8		;	 testPower *= 10
	B power				;
endPower

	LDR R11, =0 ; quotient	
	
divide
	CMP R5, R9 			; while(remainder >= power)
	BLO endDivide		; {
	SUB R5, R5, R9		;	 result = result - realPower
	ADD R11, R11, #1	;	 quotient++
	B divide			; }
endDivide
	
	CMP R12, #0			; if(numberOfDigits == 0)
	BEQ endPrint		;	 end print
						; else
	ADD R0, R10, R11	;	 character = ASCII offset + quotient
	BL sendchar			;	 print character
	SUB R12, R12, #1	; 	 numberOfDigits--
	B print				;

endPrint

	CMP R7, #4
	BNE notDiv			; if(operator == /)
	
	LDR R7, =0			; operator - 0
	
	CMP R2, #0
	BEQ notDiv
	
	LDR R0, =' '
	BL sendchar
	LDR R0, ='R'
	BL sendchar
	LDR R0, ='e'
	BL sendchar
	LDR R0, ='m'
	BL sendchar
	LDR R0, ='a'
	BL sendchar
	LDR R0, ='i'
	BL sendchar
	LDR R0, ='n'
	BL sendchar
	LDR R0, ='d'
	BL sendchar
	LDR R0, ='e'
	BL sendchar
	LDR R0, ='r'
	BL sendchar
	LDR R0, =' '
	BL sendchar
	MOV R5, R2
	B remainderPrint
	
nope
	LDR R0, ='n'		
	BL sendchar
	LDR R0, ='o'		
	BL sendchar
	LDR R0, ='p'		
	BL sendchar
	LDR R0, ='e'		
	BL sendchar

notDiv
	LDR R0, =0xA		;
	BL sendchar			; print new line
	
	B startProgram		; } while (input != escape key)
	
endProgram

	LDR R0, ='G'
	BL sendchar			
	LDR R0, ='o'
	BL sendchar			
	LDR R0, ='o'
	BL sendchar			
	LDR R0, ='d'
	BL sendchar			
	LDR R0, ='b'
	BL sendchar			
	LDR R0, ='y'
	BL sendchar			
	LDR R0, ='e'
	BL sendchar			
	LDR R0, ='!'
	BL sendchar
	
stop	B	stop

	END
	AREA	ConsoleInput, CODE, READONLY
	IMPORT	main
	IMPORT	getkey
	IMPORT	sendchar
	EXPORT	start
	PRESERVE8

start

	MOV R3, #10
	MOV R4,#0
	MOV R6, #0
	
read
    BL	getkey		; read key from console
    CMP	R0, #0x0D  	; while (key != enter)
    BEQ	endRead		; {
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

;Ok, so at this point we have all the input and the operators and all that jazz, do what you have to do...
;By that I mean calculate stuff, its called a calculator

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

stop	B	stop

	END

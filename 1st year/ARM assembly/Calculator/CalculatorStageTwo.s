AREA	CalculatorStageTwo, CODE, READONLY
IMPORT	main
IMPORT	getkey
IMPORT	sendchar
EXPORT	start
PRESERVE8

start

MOV R4,#0
MOV R5, #10

read
    BL	getkey		; read key from console
    CMP	R0, #0x0D  	; while (key != enter)
    BEQ	endRead		; {
    BL	sendchar	;   echo key back to console

    ;
    ; do any necessary processing of the key
    ;

parseoperator
    CMP R0, '*'
    BEQ multiplyoperator
    CMP R0, '+'
    BEQ addoperator
    CMP R0, '-'
    BEQ subtractoperator

    MUL R4, R5, R4
    SUB R0, R0, #0x30
    ADD R4, R0, R4

multiplyoperator
    MOV R1, '*'
    B readagain

addoperator
    MOV R1, '+'
    B readagain

subtractoperator
    MOV R1, '-'
    B readagain

B	read		; }

endRead

readagain

    BL	getkey		; read key from console
    CMP	R0, #0x0D  	; while (key != enter)
    BEQ	endReadagain		; {
    BL	sendchar	;   echo key back to console

    ;
    ; do any necessary processing of the key
    ;

    MUL R4, R5, R4
    SUB R0, R0, #0x30
    ADD R4, R0, R4

endreadagain

;Ok, so at this point we have all the input and the operators and all that jazz, do what you have to do...
;By that I mean calculate stuff, its called a calculator

calculate
    CMP R1,'*'
    BEQ multiply
    CMP R1,'+'
    BEQ add
    CMP R1,'-'
    BEQ divide

    B endcalculate
    
endcalculate

stop	B	stop

END

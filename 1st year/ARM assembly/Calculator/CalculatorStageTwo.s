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
    BEQ multiply
    CMP R0, '+'
    BEQ add
    CMP R0, '-'
    BEQ subtract

    MUL R4, R5, R4
    SUB R0, R0, #0x30
    ADD R4, R0, R4

multiply
    MOV R2, '*'
    B readagain

add
    MOV R2, '+'
    B readagain

subtract
    MOV R3, '-'
    B readagain

B	read		; }

endRead

readagain

    

endreadagain

stop	B	stop

END

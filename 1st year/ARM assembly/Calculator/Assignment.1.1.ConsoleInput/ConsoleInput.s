	AREA	ConsoleInput, CODE, READONLY
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
	
	MUL R4, R5, R4
	SUB R0, R0, #0x30
	ADD R4, R0, R4
	
	B	read		; }
	
endRead

stop	B	stop

	END	
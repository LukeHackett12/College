	AREA	ConsoleInput, CODE, READONLY
	IMPORT	main
	IMPORT	getkey
	IMPORT	sendchar
	EXPORT	start
	PRESERVE8

start

read
	BL	getkey		; read key from console
	CMP	R0, #0x0D  	; while (key != CR)
	BEQ	endRead		; {
	BL	sendchar	;   echo key back to console

	;
	; do any necessary processing of the key
	;

	B	read		; }
	
endRead

stop	B	stop

	END	
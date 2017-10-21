	AREA	Flags2, CODE, READONLY
	IMPORT	main
	EXPORT	start

start

	LDR R0, =0x60001000
	LDR R1, =0x11004000
	ADDS R2, R0, R1 ; 0x71005000 No flags
	
	LDR R0, =0xC0001000
	LDR R1, =0x10070200
	ADDS R2, R0, R1 ; 0xD0071200 Negative
	
	LDR R0, =0xC0001000
	LDR R1, =0x51004000
	ADDS R2, R0, R1 ; 0x11005000 Carry
	
	LDR R0, =0x9F000000
	LDR R1, =0xFF001000
	ADDS R2, R0, R1 ; 0x9E001000 Negative, Carry
	
	LDR R0, =0x61000000
	LDR R1, =0x9F000000
	ADDS R2, R0, R1 ; 0x00000000 Zero, Carry
	
	LDR R0, =0x00000000
	LDR R1, =0x00000000
	ADDS R2, R0, R1 ; 0x00000000 Zero
	
	LDR R0, =0x50001000
	LDR R1, =0x31004000
	ADDS R2, R0, R1 ; 0x81005000 Negative, Overflow
	
	LDR R0, =0xC0001000
	LDR R1, =0x91004000
	ADDS R2, R0, R1 ; 0x51005000 Carry, Overflow
	
	;LDR R0, =0x00000000
	;LDR R1, =0x00000000
	;ADDS R2, R0, R1 ; 0x00000000 Zero, Carry, Overflow

stop	B	stop

	END	
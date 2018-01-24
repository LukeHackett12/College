	AREA	Shift64, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R0, =0xF3E6A262
	LDR	R1, =0xAFC31256
	LDR	R2, =-2

	AND R3, R2, #0xF0000000
	CMP R3, #0
	BEQ shiftRight
	NEG R2, R2
	B shiftLeft
	
shiftRight

while
	CMP R2, #0
	BEQ endwhile
	SUB R2, R2, #1
	MOVS R1, R1, LSR #1
	BCS bitone
	MOV R0, R0, LSR #1
	B while
	
bitone
	MOV R0, R0, LSR #1
	ORR R0, R0, #0x80000000
	B while
	
endwhile
	B stop

shiftLeft

while1
	CMP R2, #0
	BEQ endwhile1
	SUB R2, R2, #1
	MOVS R0, R0, LSL #1
	BCS bitone1
	MOV R1, R1, LSL #1
	B while1
	
bitone1
	MOV R1, R1, LSL #1
	ORR R1, R1, #0x00000001
	B while1
	
endwhile1
	B stop
	
stop	B	stop


	END
		

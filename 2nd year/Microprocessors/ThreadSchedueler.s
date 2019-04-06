; Definitions  -- references to 'UM' are to the User Manual.

IO1DIR	EQU	0xE0028018
IO1SET	EQU	0xE0028014
IO1CLR	EQU	0xE002801C

IO0DIR        EQU        0xE0028008
IO0SET        EQU        0xE0028004
IO0CLR        EQU        0xE002800C

; Timer Stuff -- UM, Table 173

T0	equ	0xE0004000		; Timer 0 Base Address
T1	equ	0xE0008000

IR	equ	0			; Add this to a timer's base address to get actual register address
TCR	equ	4
MCR	equ	0x14
MR0	equ	0x18

TimerCommandReset	equ	2
TimerCommandRun	equ	1
TimerModeResetAndInterrupt	equ	3
TimerResetTimer0Interrupt	equ	1
TimerResetAllInterrupts	equ	0xFF

; VIC Stuff -- UM, Table 41
VIC	equ	0xFFFFF000		; VIC Base Address
IntEnable	equ	0x10
VectAddr	equ	0x30
VectAddr0	equ	0x100
VectCtrl0	equ	0x200

Timer0ChannelNumber	equ	4	; UM, Table 63
Timer0Mask	equ	1<<Timer0ChannelNumber	; UM, Table 63
IRQslot_en	equ	5		; UM, Table 58

	AREA	InitialisationAndMain, CODE, READONLY
	IMPORT	main

; (c) Mike Brady, 2014 -- 2019.

	EXPORT	start
start
; initialisation code
	ldr lr, =practical2
	ldr r1, =stackA
	stmia r1, {r0-r12, lr}
	ldr lr, =randomProgram
	ldr r1, =stackB
	stmia r1, {r0-r12, lr}

; Initialise the VIC
	ldr	r0,=VIC			; looking at you, VIC!

	ldr	r1,=irqhan
	str	r1,[r0,#VectAddr0] 	; associate our interrupt handler with Vectored Interrupt 0

	mov	r1,#Timer0ChannelNumber+(1<<IRQslot_en)
	str	r1,[r0,#VectCtrl0] 	; make Timer 0 interrupts the source of Vectored Interrupt 0

	mov	r1,#Timer0Mask
	str	r1,[r0,#IntEnable]	; enable Timer 0 interrupts to be recognised by the VIC

	mov	r1,#0
	str	r1,[r0,#VectAddr]   	; remove any pending interrupt (may not be needed)

; Initialise Timer 0
	ldr	r0,=T0			; looking at you, Timer 0!

	mov	r1,#TimerCommandReset
	str	r1,[r0,#TCR]

	mov	r1,#TimerResetAllInterrupts
	str	r1,[r0,#IR]

	ldr	r1,=(14745600/200)-1	 ; 5 ms = 1/200 second
	str	r1,[r0,#MR0]

	mov	r1,#TimerModeResetAndInterrupt
	str	r1,[r0,#MCR]

	mov	r1,#TimerCommandRun
	str	r1,[r0,#TCR]

;from here, initialisation is finished, so it should be the main body of the main program

wloop	b	wloop  		; branch always
;main program execution will never drop below the statement above.

	AREA	InterruptStuff, CODE, READONLY
irqhan	sub	lr,lr,#4
	stmfd	sp!,{r0}	; the lr will be restored to the pc

	ldr r0, =current
	ldr r0, [r0]
	cmp r0, #0
	beq init
	cmp r0, #1
	beq	proc2Store
	b proc1Store
	
init
	ldmfd sp!, {r0}
	ldr r0, =current
	ldr r1, =1
	str r1, [r0]
	b endStore
	
proc1Store
	ldr r0, =stackB
	add r0, #4
	stmia r0, {r1-r12, lr}
	ldmfd sp!, {r0}
	ldr r1, =stackB
	stmia r1, {r0}
	
	ldr r0, =current
	ldr r1, =1
	str r1, [r0]
	b endStore
	
proc2Store
	ldr r0, =stackA
	add r0, #4
	stmia r0, {r1-r12, lr}
	ldmfd sp!, {r0}
	ldr r1, =stackA
	stmia r1, {r0}
	
	ldr r0, =current
	ldr r1, =2
	str r1, [r0]
	b endStore
	
endStore

;here you'd put the unique part of your interrupt handler
;all the other stuff is "housekeeping" to save registers and acknowledge interrupts

;this is where we stop the timer from making the interrupt request to the VIC
;i.e. we 'acknowledge' the interrupt
	ldr	r0,=T0
	mov	r1,#TimerResetTimer0Interrupt
	str	r1,[r0,#IR]	   	; remove MR0 interrupt request from timer

;here we stop the VIC from making the interrupt request to the CPU:
	ldr	r0,=VIC
	mov	r1,#0
	str	r1,[r0,#VectAddr]	; reset VIC

	ldr r0, =current
	ldr r0, [r0]
	cmp r0, #1
	beq	proc1Load
	b proc2Load
	
proc1Load
	ldr r0, =stackA
	ldmia r0, {r0-r12, pc}^
proc2Load
	ldr r0, =stackB
	ldmia r0, {r0-r12, pc}^

	ldmfd	sp!,{r0}	; return from interrupt, restoring pc from lr
				; and also restoring the CPSR

practical2
	ldr	r1,=IO1DIR
	ldr	r2,=0x000f0000	;select P1.19--P1.16
	str	r2,[r1]		;make them outputs
	ldr	r1,=IO1SET
	str	r2,[r1]		;set them to turn the LEDs off
	
	ldr	r2,=IO1CLR

; r1 points to the SET register
; r2 points to the CLEAR register

; The LEDS are active low -- writing a zero to the bit turns the LED on, writing a 1 turns the LED off.

; Part 2 -- convert the number to decimal digits and encode them

	ldr r4,=powersoften	; start with the billions
	ldr	r7,=result		; point to where the resulting characters will do

	ldr	r3,=sample
	ldr	r3,[r3]			; get the sample number
	mov r5,#2_1010	; code for "+"
	cmp	r3,#0			; see if it's negative
	bpl	is_positive
	rsb	r3,#0				; get the negative number's magnitude
	mov r5,#2_1011	; code for "-"
is_positive
	strb	r5,[r7]		; store the sign code
	add	r7,#1			; point to the next free space
	mov	r8,#0			; use this a a flag. 1 means a non-zero digit was seen, 0 otherwise
loop2
	mov	r5,#0
	ldr	r6,[r4]         ; load the "next" power
	cmp	r6,#0			; are we finished?
	beq	conversion_done
	add	r4,#4			; point to the next lowest power of 10 for next time
loop1
	add	r5,#1
	subs	r3,r3,r6	; try to subtract another power of ten
	bcs	loop1			; so long as the C bit is set after a subtract, it was successful
	
	add	r3,r3,r6		; restore the over-subtraction
	sub	r5,#1			; one less than we thought
;now, if it was zero, substitute 0_21111
	cmp	r5,#0
	bne	not_zero
	mov	r5,#2_1111
	cmp	r8,#0			; have there been any non-zeros yet?
	beq	loop2			; branch if not
not_zero
	mov	r8,#1			; indicate at least one non-zero
	strb	r5,[r7]		; store the character
	add	r7,#1			; point to next space in the result
	b	loop2			; continue the conversion

conversion_done
	cmp	r8,#0			; was no non-zero seen (i.e. was the number zero?)
	bne	dont_put_in_a_zero
	mov	r6,#2_1111
	strb	r6,[r7]
	add	r7,#1			; point to next space in the result
; put a zero in the last byte to act as an end-of-string
dont_put_in_a_zero
	strb	r5,[r7]		; put a NUL on to the end of the sequence
	
; Part 3 -- enter an endless loop where you display the sign and the digits followed by a blank pause
	
	ldr	r7,=0x000f0000	; select all four LEDs
	ldr	r5,=lookuptable
display_loop
	ldr	r0,=result
display_next
	mov	r3,#0
	ldrb	r3,[r0]		; get next code to display
	mov	r6,r3			; keep if for later
	add	r0,#1			; point to the next code
	and	r3,#2_1111		; remove any other stuff
	mov	r3,r3,lsl #2	; by four because the entries are 4 bytes in size
	add	r3,r5,r3
	ldr	r3,[r3]			; get the 32-bit GPIO code

	str	r7,[r1]			; turn all the LEDs off
	str	r3,[r2]			; turn on the relevant bits

;delay for about a second
		ldr	r4,=2000000
dloop	subs	r4,r4,#1
	bne	dloop

	cmp	r6,#0			; was that the last part of it
	bne	display_next	; if not, do the next digit
	b	display_loop	; otherwise, start the display again from the start


randomProgram
	
	ldr        r1,=IO0DIR
	
rgbLoop
	ldr        r2,=0x00260000        ;select P0.17,P0.18,P0.21
	str        r2,[r1]                ;make them outputs
	ldr        r1,=IO0SET
	str        r2,[r1]                ;set them to turn the LEDs off
	ldr        r2,=IO0CLR

	ldr        r3,=0x00020000
	str        r3,[r2]
	
	ldr	r4,=1000000
dloop1	subs	r4,r4,#1
	bne	dloop1
	
	ldr        r2,=0x00260000        ;select P0.17,P0.18,P0.21
	str        r2,[r1]                ;make them outputs
	ldr        r1,=IO0SET
	str        r2,[r1]                ;set them to turn the LEDs off
	ldr        r2,=IO0CLR
	ldr        r3,=0x00200000
	str        r3,[r2]
	
	ldr	r4,=2000000
dloop2	subs	r4,r4,#1
	bne	dloop2
	
	ldr        r2,=0x00260000        ;select P0.17,P0.18,P0.21
	str        r2,[r1]                ;make them outputs
	ldr        r1,=IO0SET
	str        r2,[r1]                ;set them to turn the LEDs off
	ldr        r2,=IO0CLR
	ldr        r3,=0x00040000
	str        r3,[r2]
	
	ldr	r4,=1000000
dloop3	subs	r4,r4,#1
	bne	dloop3
	
	b rgbLoop
	

STACK_SIZE equ 56

	AREA	Memory, DATA, READWRITE

stackA SPACE STACK_SIZE
stackB SPACE STACK_SIZE
current DCD 0
	
		AREA	P2Constants, DATA, READONLY

sample
	dcd		10			; the is 1049 in decimal
	dcd		0x00000008	; the is 1049 in decimal
lookuptable
	dcd	0x00000000 			; 0 (unused)
	dcd	0x00080000			; 1	
	dcd	0x00040000			; 2
	dcd	0x000c0000			; 3
	dcd 0x00020000			; 4	
	dcd	0x000A0000			; 5
	dcd	0x00060000			; 6
	dcd	0x000E0000			; 7
	dcd	0x00010000			; 8
	dcd	0x00090000			; 9
	dcd	0x00050000			; A (which is +)
	dcd	0x000D0000			; B (which is -)
	dcd	0x00030000			; C (unused)
	dcd	0x000B0000			; D (unused)
	dcd	0x00070000			; E (unused)
	dcd	0x000F0000			; F (which is 0)
		

; Use for converting the number to its decimal digits equivalent
powersoften
	dcd		1000000000
	dcd		100000000
	dcd		10000000
	dcd		1000000
	dcd		100000
	dcd		10000
	dcd		1000
	dcd		100
	dcd		10
	dcd		1
	dcd		0		; use this as a "sentinel" so we'll know when we're finished

	AREA	P2Data, DATA, READWRITE
result
	space	12

	END

	END

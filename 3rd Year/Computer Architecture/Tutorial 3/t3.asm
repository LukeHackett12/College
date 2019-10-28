add r0, #4, r20

min:
    add r0, r26, r1
    sub r27. r1, r0 {C}
    jge cmpc
    xor r0, r0, r0
    add r0, r27, r1
cmpc:
    sub r28, r1, r0 {C}
    jge minret
    xor r0, r0, r0
    add r28, r1
minret:
    RET r25, 0
    xor r0, r0, r0

p:
    add r0, r3, r10
    add r0, r26, r11
    CALLR r25, min
    add r0, r27, r12
    add r0, r1, r10
    add r0, r28, r11
    CALLR r25, min
    add r0, r29, r12
    RET r25, 0
    xor r0, r0, r0

gcd:
    sub r0, r27, r0 {C}
    jeq gcdret
    add r0, r26, r1
    add r0, r26, r10
    CALLR r25, mod
    add r0, r27, r11
    add r0, r27, r10
    CALLR r25, gcd
    add r0, r1, r11

gcdret:		
    RET r25, 0
	xor r0, r0, r0
    AREA	BubbleSort, CODE, READONLY
    IMPORT	main
    EXPORT	start

start

    LDR R0, =array

sortLoop
    LDR R4, =0

    LDR R6, =1
for
    CMP R6, #9
    BEQ endFor
    SUB R7, R6, #1
    LDR R1, [R0, R6, LSL #2]
    LDR R2, [R0, R7, LSL #2]
    CMP R2, R1
    BHI swapElems
    ADD R6, R6, #1
    B for

swapElems
    BL swap
    LDR R5, =1
    ADD R6, R6, #1
    B for

endFor

    CMP R5, #1
    BEQ sortLoop

endSort

    stop	B	stop

swap
    STMFD sp!, {R4-R7,lr}
    STR R1, [R0, R7, LSL #2]
    STR R2, [R0, R6, LSL #2]
    LDMFD sp!, {R4-R7,pc}

    AREA	TestData, DATA, READWRITE

array
    DCD 7, 2, 5, 9, 1, 3, 2, 3, 4

    END

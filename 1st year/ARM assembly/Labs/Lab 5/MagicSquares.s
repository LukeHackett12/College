    AREA	MagicSquares, CODE, READONLY
    IMPORT	main
    EXPORT	start

start

    LDR R1, =array      ;array base address
    LDR R2, =3          ;N

    MUL R4, R2, R2      ;N^2
    ADD R4, R4, #1      ;N^2 + 1
    MUL R4, R2, R4      ;N(N^2 + 1)
    MOV R4, R4, LSR #1  ;N(N^2 + 1) / 2

    LDR R0, =1

    ;Sum of each row
    LDR R5, =0
rowLoop
    CMP R5, R2
    BEQ endRowLoop
    LDR R6, =0
    LDR R7, =0
while
    MUL R8, R5, R2
    ADD R8, R8, R6
    LDR R9, [R1, R8, LSL #2]
    ADD R7, R7, R9
    ADD R6, R6, #1
    CMP R6, R2
    BEQ endWhile
    B while
endWhile
    CMP R7, R4
    BNE notMagic
    ADD R5, R5, #1
    B rowLoop
endRowLoop

    ;Sum of each column
    LDR R6, =0
columnLoop
    CMP R6, R2
    BEQ endColumnLoop
    LDR R5, =0
    LDR R7, =0
while1
    MUL R8, R5, R2
    ADD R8, R8, R6
    LDR R9, [R1, R8, LSL #2]
    ADD R7, R7, R9
    ADD R5, R5, #1
    CMP R5, R2
    BEQ endWhile1
    B while1
endWhile1
    CMP R7, R4
    BNE notMagic
    ADD R6, R6, #1
    B columnLoop
endColumnLoop

    ;Sum of each diagonal
    LDR R5, =0
    LDR R6, =0
    LDR R7, =0
diagonalOne
    CMP R5, R2
    BEQ endDiagonalOne
    MUL R8, R5, R2
    ADD R8, R8, R6
    LDR R9, [R1, R8, LSL #2]
    ADD R7, R7, R9
    ADD R5, #1
    ADD R6, #1
    B diagonalOne
endDiagonalOne
    CMP R7, R4
    BNE notMagic

    SUB R7, R2, #1
    LDR R5, =0
    MOV R6, R7
    LDR R7, =0
diagonalTwo
    CMP R5, R2
    BEQ endDiagonalTwo
    MUL R8, R5, R2
    ADD R8, R8, R6
    LDR R9, [R1, R8, LSL #2]
    ADD R7, R7, R9
    ADD R5, #1
    SUB R6, #1
    B diagonalTwo
endDiagonalTwo
    CMP R7, R4
    BNE notMagic

    B stop

notMagic
    LDR R0, =0
    B stop

stop	B	stop

    AREA	TestData, DATA, READWRITE

array
    DCD 8, 1, 6
    DCD 3, 5, 7
    DCD 4, 9, 2

    END

    AREA	RelocateArrayElement, CODE, READONLY
    IMPORT	main
    EXPORT	start

start

    LDR R1, =array  ;array base address
    LDR R2, =6      ;giz the element you want
    LDR R3, =3      ;giz the element you want to move to

    LDR R5, [R1, R2, LSL #2]    ;Step 1. Get the element at array index R2

    ;Step 2. Move element at array index R2-1 to R2
    ;Step 3. Repeat step 2 until R2 = R3

while
    CMP R2, R3
    BEQ endwhile
    SUB R4, R2, #1
    LDR R6, [R1, R4, LSL #2]
    STR R6, [R1, R2, LSL #2]
    MOV R2, R4
    B while
endwhile

    STR R5, [R1, R2, LSL #2]    ;Step 4. Set the initial element to index R3

stop	B	stop

    AREA	TestData, DATA, READWRITE

array
    DCD 7, 2, 5, 9, 1, 3, 2, 3, 4

    END

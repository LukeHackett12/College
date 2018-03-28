    AREA    FpAdd, CODE, READONLY
    IMPORT    main
    EXPORT    start

start

    ;
    ; Part 1 - decode
    ;
    ldr    r0, =0x3F000000        ; fpval1 = 0.5
    LDR    r1, =0x3EE00000        ; fpval2 = 0.4375
    bl getLargerExp
    b stop
    ;ldr r0, =0x3FA00000
    ;ldr r1, =0x3F400000

    b stop

    BL    fpadd

stop    B    stop
; r0 - fp1
; r1 - fp2
; returns r2 - fraction1
;          r3 - fraction2
fixExponents
    stmfd sp!, {r4-r12,lr}
    mov r4, r0                ; fpValOneTemp = fpval1
    mov r5, r1                 ; fpValTwoTemp = fpval2 
    bl getExponent
    mov r6, r1                ; fpValOneExp = getExponent(fpValOneTemp)
    mov r0, r5
    bl getExponent
    mov r7, r1                ; fpValTwoExp = getExponent(fpValTwoTemp)
    cmp r6, r7                ; if(fpValOneExp != fpValTwoExp)
    beq done                ; {
    cmp r6, r7                ;    if(fpValOne < fpValTwoExp)
    bgt fpvalTwoSmaller        ;    {
    mov r0, r5
    bl getFraction
    mov r3, r1
    mov r11, #1
    mov r8, r4                ;        smallerExponentFpValue = fpValOne;
    sub r9, r7, r6            ;        expDifference = fpValTwoExp - fpValOneExp
    b multiplyNumber        ;    }
fpvalTwoSmaller                ;    else {
    mov r0, r4
    bl getFraction
    mov r2, r1
    mov r12, #1
    mov r8, r5                ;        smallerExponentFpValue = fpValTwo;
    sub r9, r6, r7            ;        expDifference = fpValOneExp - fpValTwoExp
multiplyNumber                ;    }
    ldr r10, =0                ;    loopCount = 0;
    mov r0, r8                ;
    bl getFraction            ;
    mov r8, r1                ;    smallerExpFraction = getFraction(smallerExponentFpValue)    
whMultiply                    ;
    cmp r10, r9                ;    while(loopCount < expDifference)
    beq doneMultiply        ;    {
    mov r8, r8, lsr #1        ;        smallerExpFraction >> 1
    add r10, r10, #1        ;        loopCount++
    b whMultiply            ;    }
doneMultiply
done
    cmp r11, #1
    beq r3set
    mov r3, r8
    b doneforrealthistime
r3set
    mov r2, r8
doneforrealthistime
    ldmfd sp!, {r4-r12,pc}



; getExponent
; r0 - ieee
; r1 - return value
getExponent
    stmfd sp!, {r4-r6, lr}
    ldr r4, =0x7F800000
    and r5, r4, r0
    mov r5, r5, lsr #23
    sub r1, r5, #127
    ldmfd sp!, {r4-r6,pc}

; getFraction
; r0 - ieee
; r1 - return value
getFraction
    stmfd sp!, {r4-r6, lr}
    ldr r4, =0x007FFFFF
    and r5, r0, r4
    ldr r4, =0x00800000
    orr r5, r5, r4
    ldr r6, =0
;whShift
;    cmp r6, #0;
;    bne ewhShift
;    movs r5, r5, lsr #1
;    bcc whShift
;    ldr r6, =1
;    b whShift
;ewhShift
;    mov r5, r5, lsl #1
;    add r5, r5, #1
    mov r1, r5
    ldmfd sp!, {r4-r6,pc}
    
    
; getNumberWithLargerExp
; r0 - fp1
; r1 - fp2
; returns : r2 - the larger exponent
getLargerExp
    stmfd sp!, {r4-r7,lr}
    mov r4, r0                 ; tempFp1 = fp1
    mov r5, r1                ; tempFp2 = fp2
    bl getExponent
    mov r6, r1                ; fp1Exponent = getExponent(fp1)
    mov r0, r5
    bl getExponent
    mov r7, r1                ; fp2Exponent = getExponent(fp2)
    cmp r6, r7                ; if(fp1Exponent != fp2Exponent)
    beq fp1ExponentLarger    ; {
    cmp r6, r7                ;    if(fp1Exponent < fp2Exponent)
    bgt fp1ExponentLarger    ;    {
    ;; fp2Exponent is larger
    mov r0, r5
    bl getExponent
    mov r2, r1
    b getNumberWithLargerExpdone;}
fp1ExponentLarger            ; else {
    ;; fp1Exponent is larger
    mov r0, r4
    bl getExponent
    mov r2, r1
getNumberWithLargerExpdone
    ldmfd sp!, {r4-r7, pc}
    
    

    
    
; fpadd subroutine
; Adds two IEEE754 single precision floating point values
; Parameters:
;   R0: first floating point value
;   R1: second floating point value
; Return value:
;   R0: floating point result
;
fpadd

    ;
    ; YOUR SUBROUTINE IMPLEMENTATION HERE
    ;
    stmfd sp!, {r4-r6, lr}
    mov r4, r0                    ; tempfp1 = fp1
    mov r5, r1                    ; tempfp2 = fp2
    bl     fixExponents
    add r6, r2, r3                ; fraction part
    mov r0, r4
    mov r1, r5
    bl getLargerExp
    mov r7, r2                    ; exponent
    add r7, r7, #127            ; add bias
    ldmfd sp!, {r4-r6, pc}
    
    
    END


	AREA	Sudoku, CODE, READONLY
	IMPORT main
	IMPORT getkey
	IMPORT sendchar
	EXPORT	start
	PRESERVE8

start
	
	;
	; write tests for getSquare subroutine
	;
	LDR R1, =gridOne
	LDR R2, =4
	LDR R3, =7
	BL getSquare

	;
	; write tests for setSquare subroutine
	;
	LDR R0, =4
	LDR R1, =gridOne
	LDR R2, =2
	LDR R3, =2
	BL setSquare

	;
	; write tests for isValid subroutine
	;
	LDR R1, =gridThree
	LDR R2, =2
	LDR R3, =1
	BL isValid
	
	;
	; write tests for other subroutines
	;
	LDR R1, =gridFour
	BL printGrid
	
	LDR R0, =0xA
	BL sendchar
	
	;
	; test sudoku subroutine
	;
	
	LDR R1, =gridUser
	BL userInput
	
	LDR R0, =0xA
	BL sendchar
	
	LDR	R1, =gridUser
	MOV	R2, #0
	MOV	R3, #0
	BL	sudoku

	LDR R1, =gridUser
	BL printGrid

stop	B	stop


; getSquare subroutine
;R1 - grid
;R2 - row
;R3 - col

;byte returned to R0
getSquare
	STMFD sp!, {R4-R5, lr}
	LDR R5, =9
	MUL R4, R2, R5
	ADD R4, R3
	LDRB R0, [R1, R4]
	LDMFD sp!, {R4-R5, pc}

; setSquare subroutine
;R0 - value
;R1 - grid
;R2 - row
;R3 - col

;void
setSquare
	STMFD sp!, {R4-R5, lr}
	LDR R5, =9
	MUL R4, R2, R5
	ADD R4, R3
	STRB R0, [R1, R4]
	LDMFD sp!, {R4-R5, pc}


; isValid subroutine
;R1 - grid
;R2 - row
;R3 - col

;boolean returned to R0
;1 for true, 0 for false
isValid
	STMFD sp!,{R4-R5, lr}
	;Save row and col
	BL getSquare
	CMP R0, #0
	BEQ isZero
	
	MOV R4, R2
	MOV R5, R3
	BL isValidRow
	CMP R0, #1
	BNE endVal
	
	MOV R2, R4
	MOV R3, R5
	BL isValidColumn
	CMP R0, #1
	BNE endVal
	
	MOV R2, R4
	MOV R3, R5
	BL isValidSubGrid
	B endVal
	
isZero
	MOV R0, #1
	
endVal
	LDMFD sp!, {R4-R5, pc}

; isValidRow subroutine
;R1 - grid
;R2 - row
;R3 - col
isValidRow
	STMFD sp!, {R4-R6, lr}
	BL getSquare
	MOV R4, R0
	MOV R5, R3
	MOV R6, #0
rowLoop
	CMP R6, #9
	BEQ isCorrectRow
	MOV R3, R6
	BL getSquare
	CMP R0, R4
	BEQ rowSame
	ADD R6, #1
	B rowLoop
	
rowSame
	CMP R5, R6
	BNE falseRow
	ADD R6, #1
	B rowLoop
	
isCorrectRow
	MOV R0, #1
	B endRowLoop

falseRow
	MOV R0, #0
	
endRowLoop

	LDMFD sp!, {R4-R6, pc}

; isValidColumn subroutine
;R1 - grid
;R2 - row
;R3 - col
isValidColumn
	STMFD sp!, {R4-R6, lr}
	BL getSquare
	MOV R4, R0
	MOV R5, R2
	MOV R6, #0
columnLoop
	CMP R6, #9
	BEQ isCorrectColumn
	MOV R2, R6
	BL getSquare
	CMP R0, R4
	BEQ columnSame
	ADD R6, #1
	B columnLoop
	
columnSame
	CMP R5, R6
	BNE falseColumn
	ADD R6, #1
	B columnLoop
	
isCorrectColumn
	MOV R0, #1
	B endColumnLoop

falseColumn
	MOV R0, #0
	
endColumnLoop

	LDMFD sp!, {R4-R6, pc}
	
; isValidSubGrid subroutine
;R1 - grid
;R2 - row
;R3 - col
isValidSubGrid
	STMFD sp!, {R4-R10, lr}
	BL getSquare
	MOV R4, R0	;value = R4
	MOV R5, R2
	MOV R6, R3
	
	MOV R3, #3
	
	BL divide
	MUL R7, R0, R3	;startRow = R7
	
	MOV R2, R6
	BL divide
	MUL R8, R0, R3	;startCol = R8
	
	MOV R2, R5	;set row back
	MOV R3, R6	;set col back
	
	;time to start the nested loop
	ADD R9, R7, #3
	ADD R10, R8, #3
forSubOne
	CMP R7, R9
	BEQ correctSub
	MOV R2, R7
forSubTwo
	CMP R8, R10
	BEQ endForSubTwo
	MOV R3, R8
	BL getSquare
	CMP R0, R4
	BEQ subSame
	ADD R8, #1
	B forSubTwo

subSame
	CMP R7, R5
	BNE falseSub
	CMP R8, R6
	BNE falseSub
	ADD R8, #1
	B forSubTwo
	
falseSub
	MOV R0, #0
	B endForSubOne
	
endForSubTwo
	ADD R7, #1
	SUB R8, #3
	B forSubOne

correctSub
	MOV R0, #1
	B endForSubOne

endForSubOne

	LDMFD sp!, {R4-R10, pc}

;divide subroutine
;R2 - a
;R3 - b
;R0 - result no remainder
divide
	STMFD sp!, {R4-R6, lr}
	LDR R0, =0
	LDR R4, =0
	MOV R5, R2
	MOV R6, R3
	
	MOV R4, R5
divWhile
	CMP R4, R6
	BLO divEndWhile
	SUB R4, R4, R6
	ADD R0, R0, #1
	B divWhile
divEndWhile
	LDMFD sp!, {R4-R6, pc}

; sudoku subroutine
; R1 - grid
; R2 - row
; R3 - column
sudoku
	STMFD sp!, {R4-R12, lr}
	MOV R4, #0 		;result = false
	
	MOV R5, R2 		;row = row
	MOV R6, R3 		;column = column
	
	ADD R8, R6, #1	;nextColumn = column + 1
	MOV R7, R5		;nextRow = row
	CMP R8, #8		;
	BLE fillTest	;if(nextColumn > 8){
	MOV R8, #0		;	nextColumn = 0;
	ADD R7, #1		;	nextRow++;
					;}
fillTest
	MOV R2, R5
	MOV R3, R6
	BL getSquare
	CMP R0, #0
	BEQ isEmpty		;if(getSquare(sudokuGrid, row, col) != 0){
	CMP R5, #8		;	
	BNE	filledElse	;	if(row == 8 
	CMP R6, #8		;	&& column == 8
	BNE filledElse	;	){
	MOV R0, #1		;		return true;
	B endSudoku		;	}
					
filledElse			;	else{
	MOV R2, R7		;		result = 
	MOV R3, R8		;		sudoku(sudokuGrid, 
	BL sudoku		;		nextRow, nextColumn);
	MOV R4, R0		;	}
	B endSudoku
	
isEmpty
	MOV R9, #1
	
sudokuFor
	CMP R9, #9
	BGT endSudokuFor
	CMP R4, #1
	BEQ endSudokuFor
	MOV R0, R9
	MOV R2, R5
	MOV R3, R6
	BL setSquare
	BL isValid
	CMP R0, #1
	BNE stepSudokuFor
	CMP R5, #8
	BNE sudokuForElse
	CMP R6, #8
	BNE sudokuForElse
	MOV R4, #1
	B stepSudokuFor
	
sudokuForElse
	MOV R2, R7
	MOV R3, R8
	BL sudoku
	MOV R4, R0
	B stepSudokuFor
	
stepSudokuFor
	MOV R2, R5
	MOV R3, R6
	ADD R9, #1
	B sudokuFor
	
endSudokuFor
	CMP R4, #0
	BNE endSudoku
	MOV R0, #0
	MOV R2, R5
	MOV R3, R6
	BL setSquare

endSudoku
	LDMFD sp!, {R4-R12, pc}

;printGird
;R1 - grid to print
;void
printGrid
	STMFD sp!, {R4-R12, lr}
	MOV R4, R1
	;start R2 and R3 at 0,0
	;nested loop for each row
	;getsquare and sendchar
	MOV R2, #0
printOuterFor
	MOV R3, #0
printInnerFor
	CMP R3, #9
	BEQ endPrintInnerFor
	MOV R1, R4
	BL getSquare
	ADD R0, R0, #0x30
	BL sendchar
	ADD R3, #1
	B printInnerFor
endPrintInnerFor
	LDR R0, =0xA
	BL sendchar
	ADD R2, #1
	CMP R2, #9
	BEQ endPrint
	B printOuterFor
endPrint
	LDMFD sp!, {R4-R12, pc}
	
userInput
	STMFD sp!, {R4-R12, lr}
	MOV R4, R1
	;start R2 and R3 at 0,0
	;nested loop for each row
	;getkey, setsquare and sendchar
	MOV R2, #0
inputOuterFor
	MOV R3, #0
inputInnerFor
	CMP R3, #9
	BEQ endInputInnerFor
	BL getkey
	CMP R0, #0x8
	BEQ backSpace
	CMP R0, #0x30
	BLT inputInnerFor
	CMP R0, #0x39
	BGT inputInnerFor
	SUB R0,#0x30
	MOV R1, R4
	BL setSquare
	ADD R0, #0x30
	BL sendchar
	ADD R3, #1
	B inputInnerFor
endInputInnerFor
	LDR R0, =0xA
	BL sendchar
	ADD R2, #1
	CMP R2, #9
	BEQ endInput
	B inputOuterFor

backSpace
	CMP R3, #0
	BEQ backRow
	SUB R3, #1
	MOV R0, #0
	MOV R1, R4
	BL setSquare
	BL clearChar
	B inputInnerFor
	
backRow
	CMP R2, #0
	BEQ inputInnerFor
	MOV R3, #8
	SUB R2, #1
	MOV R0, #0
	MOV R1, R4
	BL setSquare
	BL clearChar
	B inputInnerFor
	
endInput
	LDMFD sp!, {R4-R12, pc}

clearChar
	STMFD sp!, {R4-R12, lr}
	LDR R0, =0x8
	BL sendchar
	LDR R0, =0x20
	BL sendchar
	LDR R0, =0x8
	BL sendchar
	LDMFD sp!, {R4-R12, pc}

	AREA	Grids, DATA, READWRITE

gridOne
		DCB	7,9,0,0,0,0,3,0,0
    	DCB	0,0,0,0,0,6,9,0,0
    	DCB	8,0,0,0,3,0,0,7,6
    	DCB	0,0,0,0,0,5,0,0,2
    	DCB	0,0,5,4,1,8,7,0,0
    	DCB	4,0,0,7,0,0,0,0,0
    	DCB	6,1,0,0,9,0,0,0,8
    	DCB	0,0,2,3,0,0,0,0,0
    	DCB	0,0,9,0,0,0,0,0,0;5,4

	;
	; add other grids for test cases
	;
gridTwo
		DCB 2,9,5,7,4,3,8,6,1
		DCB 4,3,1,8,6,5,9,2,7
		DCB 8,7,6,1,9,2,5,4,3
		DCB 3,8,7,4,5,9,2,1,6
		DCB 6,1,2,3,8,7,4,9,5
		DCB 5,4,9,2,1,6,7,3,8
		DCB 7,6,3,5,3,4,1,8,9
		DCB 9,2,8,6,7,1,3,5,4
		DCB 1,5,4,9,3,8,6,7,2

gridThree
		DCB 0,0,8,1,0,6,0,0,5
		DCB 0,0,0,0,3,0,9,1,0
		DCB 3,0,9,0,0,0,0,0,0
		DCB 0,9,0,0,8,5,0,0,0
		DCB 0,9,0,0,8,5,0,0,0
		DCB 0,3,5,4,6,2,1,9,0
		DCB 0,0,0,9,1,0,0,4,0
		DCB 0,0,0,0,0,0,3,0,1
		DCB 0,5,2,0,7,0,0,0,0
		DCB 9,0,0,8,0,1,6,0,0

gridFour
		DCB 6,0,0,0,3,0,8,0,0
		DCB 0,2,0,9,7,0,3,0,0
		DCB 0,4,3,0,0,5,9,0,0
		DCB 0,0,0,8,0,0,5,0,3
		DCB 1,3,0,6,0,2,0,4,9
		DCB 2,0,5,0,0,7,0,0,0
		DCB 0,0,4,5,0,0,1,6,0
		DCB 0,0,2,0,6,8,0,3,0
		DCB 0,0,6,0,1,0,0,0,7
		
gridFive
		DCB 0,0,0,0,0,3,8,9,0
		DCB 0,8,0,8,0,0,0,0,0
		DCB 9,3,1,7,0,0,0,0,0
		DCB 0,5,0,0,0,0,2,6,0
		DCB 1,0,8,0,0,0,5,0,7
		DCB 0,7,4,0,0,0,0,3,0
		DCB 0,0,0,0,0,2,6,7,4
		DCB 0,0,0,0,0,1,0,2,0
		DCB 0,6,2,4,0,0,0,0,0
		
gridSix
		DCB 1,0,3,0,0,0,0,0,0
		DCB 0,0,0,0,0,0,1,0,0
		DCB 0,0,0,0,0,0,0,0,0
		DCB 0,0,0,3,0,0,0,0,0
		DCB 0,0,0,0,0,0,0,0,0
		DCB 0,0,0,0,0,0,0,0,0
		DCB 0,0,0,0,0,0,0,0,0
		DCB 0,0,0,0,0,0,0,0,0
	    DCB 0,0,0,0,0,0,0,0,0
		
gridUser
		SPACE 81

	END

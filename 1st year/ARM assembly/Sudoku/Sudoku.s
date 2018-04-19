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
	LDR R1, =gridTwo
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
	
	LDR R1, =gridUser
	BL userInput
	
	;
	; test sudoku subroutine
	;

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
	STMFD sp!, {R4-R5, lr}	; Save local variables
	LDR R5, =9				; row length = 9;
	MUL R4, R2, R5			; index = rows * 9;
	ADD R4, R3				; index += col;
	LDRB R0, [R1, R4]		; element = array[index];
	LDMFD sp!, {R4-R5, pc}	; Load local variables

; setSquare subroutine
;R0 - value
;R1 - grid
;R2 - row
;R3 - col

;void
setSquare
	STMFD sp!, {R4-R5, lr}	; Save local variables
	LDR R5, =9				; row length = 9;
	MUL R4, R2, R5			; index = rows * 9;
	ADD R4, R3				; index += col;
	STRB R0, [R1, R4]		; grid[row][col] = element;
	LDMFD sp!, {R4-R5, pc}	; Load local variables


; isValid subroutine
;R1 - grid
;R2 - row
;R3 - col

;boolean returned to R0
;1 for true, 0 for false
isValid
	STMFD sp!,{R4-R5, lr}	; Save local variables
	BL getSquare			; element = grid[row][col];
	CMP R0, #0				; if(element == 0)
	BEQ isZero				; 	  return true;
	
	;Save row and col
	MOV R4, R2				; tempRow = row;
	MOV R5, R3				; tempCol = col;
	BL isValidRow			; if(isValidRow(row, col))
	CMP R0, #1				;	  valid = true;
	BNE endVal				; else return false;
	
	MOV R2, R4				; row = tempRow;
	MOV R3, R5				; col = tempCol;
	BL isValidColumn		; if(isValidColumn(row, col))
	CMP R0, #1				; 	  valid = true;
	BNE endVal				; else return false;
	
	MOV R2, R4				; row = tempRow;
	MOV R3, R5				; col = tempCol;
	BL isValidSubGrid		; 
	B endVal				; return isValidSubGrid(row, col);
	
isZero
	MOV R0, #1				
	
endVal
	LDMFD sp!, {R4-R5, pc}	; Load local variables

; isValidRow subroutine
;R1 - grid
;R2 - row
;R3 - col
isValidRow
	STMFD sp!, {R4-R6, lr}	; Save local variables
	BL getSquare			; element = grid[row][col];
	MOV R4, R0				; tempElem = element;
	MOV R5, R3				; tempCol = col;
	MOV R6, #0				; testCol = 0;
	
rowLoop
	CMP R6, #9				; while(testCol < 9){
	BEQ isCorrectRow		;
	MOV R3, R6				;	  col = testCol;
	BL getSquare			;	  element = grid[row][col];
	CMP R0, R4				;	  if(tempElem == element)
	BEQ rowSame				;		  rowSame();
	ADD R6, #1				;	  testCol++;
	B rowLoop				; }
	
rowSame						; void rowSame(){
	CMP R5, R6				;	  if(tempCol != testCol)
	BNE falseRow			;		  return false
	ADD R6, #1				;	  testCol++;
	B rowLoop				; }
	
isCorrectRow				;
	MOV R0, #1				; result = true;
	B endRowLoop			;

falseRow					;
	MOV R0, #0				; result = false;
	
endRowLoop					; return result;

	LDMFD sp!, {R4-R6, pc}	; Load local variables

; isValidColumn subroutine
;R1 - grid
;R2 - row
;R3 - col
isValidColumn
	STMFD sp!, {R4-R6, lr}	;Save local variables
	BL getSquare			; element = grid[row][col];
	MOV R4, R0				; tempElem = element;
	MOV R5, R2				; tempCol = col;
	MOV R6, #0				; testCol = 0;
	
columnLoop					
	CMP R6, #9				; while(testRow < 9){
	BEQ isCorrectColumn		;
	MOV R2, R6				;	  row = testRow;
	BL getSquare			;	  element = grid[row][col];
	CMP R0, R4				;	  if(tempElem == element)
	BEQ columnSame			;		  columnSame();
	ADD R6, #1				;	  testRow++;
	B columnLoop			; }
	
columnSame					; void columnSame(){
	CMP R5, R6;	  			if(tempRow != testRow)
	BNE falseColumn			;	  return false
	ADD R6, #1				;	  testRow++;
	B columnLoop			; }
	
isCorrectColumn
	MOV R0, #1				; result = 1;	
	B endColumnLoop

falseColumn
	MOV R0, #0				; result = 0;
	
endColumnLoop				; return result;

	LDMFD sp!, {R4-R6, pc}
	
; isValidSubGrid subroutine
;R1 - grid
;R2 - row
;R3 - col
isValidSubGrid
	STMFD sp!, {R4-R10, lr}	; Save local variables
	BL getSquare
	MOV R4, R0				; element = R4;
	MOV R5, R2				; saveRow = row;
	MOV R6, R3				; saveCol = col;
	
	MOV R3, #3				; divBy = 3;
	
	BL divide				; 
	MUL R7, R0, R3			; startRow = row/3 * 3;
	
	MOV R2, R6				; 
	BL divide				; 
	MUL R8, R0, R3			; startCol = col/3 * 3;
	
	MOV R2, R5				; row = saveRow;
	MOV R3, R6				; col = saveCol;
	
	ADD R9, R7, #3			; endRow = startRow + 3;
	ADD R10, R8, #3			; endCol = startCol + 3;
	
forSubOne					
	CMP R7, R9				; while(startRow < endRow){
	BEQ correctSub			; 
	MOV R2, R7				; 	row = startRow;
forSubTwo					; 
	CMP R8, R10				; 	while(startCol < endCol){
	BEQ endForSubTwo		;
	MOV R3, R8				; 	  col = startCol;
	BL getSquare			; 	  tempElement = grid[row][col];
	CMP R0, R4				; 	  if(element == tempElement)
	BEQ subSame				; 	  	  subSame();
	ADD R8, #1				; 	  startCol++;
	B forSubTwo				; 	}

subSame						; 	void subSame(){
	CMP R7, R5				; 		if(startRow == savedRow)
	BNE falseSub			; 			return false;
	CMP R8, R6				; 		if(startCol == savedCol)
	BNE falseSub			;			return false
	ADD R8, #1				;			startCol++;
	B forSubTwo				; 	}
	
falseSub					; 
	MOV R0, #0				; 	result = false;
	B endForSubOne			; 
	
endForSubTwo				; 
	ADD R7, #1				; 	startRow++;
	SUB R8, #3				; 	startCol -= 3;
	B forSubOne				; }

correctSub					; 
	MOV R0, #1				; result = true;
	B endForSubOne			; 

endForSubOne				; return result;

	LDMFD sp!, {R4-R10, pc}	; Load local variables

;divide subroutine
;R2 - a
;R3 - b
;R0 - result no remainder
divide
	STMFD sp!, {R4-R6, lr}	; Save local variables
	LDR R0, =0				; result = 0;
	LDR R4, =0				; quotient = 0;
	MOV R5, R2				; tempA = a;
	MOV R6, R3				; tempB = b;
	
	MOV R4, R5				; quotient = tempA;
divWhile
	CMP R4, R6				; while(quotient >= tempB){
	BLO divEndWhile			; 
	SUB R4, R4, R6			; 	  quotient -= tempB;
	ADD R0, R0, #1			; 	  result++;
	B divWhile				; }
divEndWhile					; return result;
	LDMFD sp!, {R4-R6, pc}	; Load local variables

; sudoku subroutine
; R1 - grid
; R2 - row
; R3 - column
sudoku
	STMFD sp!, {R4-R9, lr}	; Save local variables
	MOV R4, #0 				; result = false
	
	MOV R5, R2 				; savedRow = row
	MOV R6, R3 				; savedColumn = column
	
	ADD R8, R6, #1			; nextColumn = savedColumn + 1
	MOV R7, R5				; nextRow = savedRow
	CMP R8, #8				;
	BLE fillTest			; if(nextColumn > 8){
	MOV R8, #0				;	nextColumn = 0;
	ADD R7, #1				;	nextRow++;
							; }
fillTest
	MOV R2, R5				; row = savedRow;
	MOV R3, R6				; column = savedCol;
	BL getSquare			; element = grid[row][column];
	CMP R0, #0				; 
	BEQ isEmpty				; if(getSquare(sudokuGrid, row, col) != 0){
	CMP R5, #8				;	
	BNE	filledElse			;	if(savedRow == 8 
	CMP R6, #8				;	&& savedColumn == 8
	BNE filledElse			;	){
	MOV R0, #1				;		return true;
	B endSudoku				;	}
					
filledElse					;	else{
	MOV R2, R7				;		result = 
	MOV R3, R8				;		sudoku(sudokuGrid, 
	BL sudoku				;		nextRow, nextColumn);
	MOV R4, R0				;	}
	B endSudoku				;}
	
isEmpty						; else {
	MOV R9, #1				;
sudokuFor					; 	for(byte try = 1; 
	CMP R9, #9				; 	try <= 9 
	BGT endSudokuFor		; 	&& 
	CMP R4, #1				; 	!result; try++){
	BEQ endSudokuFor		;
	MOV R0, R9				; 	value = try;
	MOV R2, R5				; 	row = savedRow;
	MOV R3, R6				; 	col = savedCol
	BL setSquare			; 	setSquare(grid, row, col, value);
	BL isValid				; 
	CMP R0, #1				; 	if(isValid(grid, row, col, try)){
	BNE stepSudokuFor		;	  
	CMP R5, #8				;		if(row == 8
	BNE sudokuForElse		;			&&
	CMP R6, #8				;			col == 8){	
	BNE sudokuForElse		;
	MOV R4, #1				;			result = true;
	B stepSudokuFor			;		}

sudokuForElse				;		else {
	MOV R2, R7				; 			row = nextRow;
	MOV R3, R8				;			col = nextCol;
	BL sudoku				;			
	MOV R4, R0				;			result = sudoku(grid, nxtrow, nxtcol);
	B stepSudokuFor			;		}

stepSudokuFor				;
	MOV R2, R5				;;;
	MOV R3, R6				;;; reseting row and col and incrementing try
	ADD R9, #1				;;;
	B sudokuFor				; 	}
	
endSudokuFor				;
	CMP R4, #0				; 	if(!result){
	BNE endSudoku			; 	    value = 0;
	MOV R0, #0				;	    row = savedRow;
	MOV R2, R5				;	  	col = savedCol;
	MOV R3, R6				;	    setSquare(grid, row, col, value);
	BL setSquare			; 	}
							; }
							
endSudoku					; return result

	LDMFD sp!, {R4-R9, pc}	; Load local variables

;printGrid
;start R2 and R3 at 0,0
;nested loop for each row
;getsquare and sendchar

;R1 - grid to print
;void
printGrid
	STMFD sp!, {R4, lr}		; Save local variables
	MOV R4, R1				; savedGrid = grid;
	MOV R2, #0				; gridRow = 0;
	
printOuterFor				; while(gridRow < 9){
	MOV R3, #0				; 	  gridCol = 0;
printInnerFor				;
	CMP R3, #9				;	  while(gridCol < 9){
	BEQ endPrintInnerFor	;
	MOV R1, R4				;		  grid = savedGrid;
	BL getSquare			;		  value = getSquare(grid, row, col);
	ADD R0, R0, #0x30		;		  value += ASCII_OFFSET;
	BL sendchar				;		  System.out.print(value);
	ADD R3, #1				;		  gridCol++;
	B printInnerFor			;		
endPrintInnerFor			;	  }
	LDR R0, =0xA			;
	BL sendchar				;	  System.out.println();
	ADD R2, #1				;	  gridRow++;
	CMP R2, #9				;
	BEQ endPrint			;
	B printOuterFor			;	  
endPrint					; }
	LDR R0, =0xA			;
	BL sendchar				; System.out.println();
				
	LDMFD sp!, {R4, pc}		; Load local variables
	
;start R2 and R3 at 0,0
;nested loop for each row
;getkey, setsquare and sendchar
	
userInput
	STMFD sp!, {R4, lr}		; Save local variables
	MOV R4, R1				; savedGrid = grid
	MOV R2, #0				; inputRow = 0
	
inputOuterFor				; while(inputRow < 9){
	MOV R3, #0				; 		inputCol = 0;
inputInnerFor				;		while(inputCol < 9){
	CMP R3, #9				;			
	BEQ endInputInnerFor	;
	BL getkey				;			input = getInput();
	CMP R0, #0x8			;			if(input == BACKSPACE) {
	BEQ backSpace			;				backspace();
	CMP R0, #0x30			;			}
	BLT inputInnerFor		;			else if(
	CMP R0, #0x39			;				input > 0
	BGT inputInnerFor		;					&&
	SUB R0,#0x30			;				input <= 9){
	MOV R1, R4				;				grid = savedGrid;
	BL setSquare			;				setSquare(grid, row, col, value);
	ADD R0, #0x30			;				input += ASCII_OFFSET;
	BL sendchar				;				System.out.print(input);
	ADD R3, #1				;				inputCol++;
	B inputInnerFor			;		}
endInputInnerFor			;
	LDR R0, =0xA			;
	BL sendchar				;		System.out.println();
	ADD R2, #1				;		inputRow++;
	CMP R2, #9				;
	BEQ endInput			;
	B inputOuterFor			; }

backSpace					; void backSpace(){
	CMP R3, #0				; 		if(inputCol == 0) backRow();
	BEQ backRow				;		else{
	SUB R3, #1				; 			inputCol-;
	MOV R0, #0				; 			value = 0;
	MOV R1, R4				; 			grid = savedGrid;
	BL setSquare			; 			setSquare(grid, row, col, value);
	BL clearChar			; 			clearChar();
							;		}
	B inputInnerFor			; }

backRow						; void backRow(){
	CMP R2, #0				; 	
	BEQ inputInnerFor		; 	if(inputRow != 0){
	MOV R3, #8				; 		inputCol = 8;
	SUB R2, #1				; 		inputRow--;
	MOV R0, #0				; 		value = 0;
	MOV R1, R4				; 		grid = savedGrid;
	BL setSquare			; 		setSquare(grid, row, col, value);
	BL clearChar			; 	}
	B inputInnerFor			; }

endInput					;
	LDR R0, =0xA			; System.out.println();
	BL sendchar				;
	LDMFD sp!, {R4, pc}		; Load local variables

clearChar
	STMFD sp!, {lr}	; Save local variables
	LDR R0, =0x8	; 
	BL sendchar		; System.out.print(BACKSPACE);
	LDR R0, =0x20	;
	BL sendchar		; System.out.print(" ");
	LDR R0, =0x8	;
	BL sendchar		; System.out.print(BACKSPACE);
	LDMFD sp!, {pc}	; Load local variables

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
		DCB 0,0,0,0,0,0,0,0,0
		DCB 0,0,0,0,0,3,0,8,5
		DCB 0,0,1,0,2,0,0,0,0
		DCB 0,0,0,5,0,7,0,0,0
		DCB 0,0,4,0,0,0,1,0,0
		DCB 0,9,0,0,0,0,0,0,0
		DCB 5,0,0,0,0,0,0,7,3
		DCB 0,0,2,0,1,0,0,0,0
		DCB 0,0,0,0,4,0,0,0,9
		
gridUser
		SPACE 81

	END

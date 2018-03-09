//get the value from a grid position
byte getSquare(address grid, word row, word column){
	//It is a 2d array so just use the standard way of getting 2d array elements
	return grid[row][column];
}

//set a value in a certain square
void setSquare(address grid, word row, word column, byte value){
	grid[row][column] = value;
}

//For each square test validity
boolean isValid(address grid, word row, word column){
	if(grid[row][column] != 0){
		if(isValidRow(grid, row, column) 
			&& isValidColumn(grid, row, column) 
			&& isValidSubgrid(grid, row, column)){
			return true;
		}
		else{
			return false;
		}
	}
	return true;
}

boolean isValidRow(address grid, word row, word column){
	byte value = grid[row][column];
	for(byte i = 0; i < 9; i++){
		if(grid[row][i] == value && i != column){
			return false;
		}
	}
	return true;
}
	
boolean isValidColumn(address grid, word row, word column){
	byte value = grid[row][column];
	for(byte i = 0; i < 9; i++){
		if(grid[i][column] == value && i != row){
			return false;
		}
	}
	return true;
}
	
boolean isValidSubgrid(address grid, word row, word column){
	byte value = grid[row][col];
	byte startRow = (row/3) * 3;
	byte startCol = (column/3) * 3;
	
	for(byte i = startRow; i < startRow+3; i++){
		for(byte j = startCol; j < startCol+3; j++){
			if(grid[i][j] == value && i != row || j != column){
				return false;
			}
		}
	}
	return true;
}

boolean sudoku(address grid, word row, word col){
	boolean result = false;
	word nxtcol;
	word nxtrow;
	
	//Precompute next row and col
	nxtcol = col + 1;
	nxtrow = row;
	if(nxtcol > 8){
		nxtcol = 0;
		nxtrow++;
	}
	
	if(getSquare(grid, row, col) != 0){
		//a pre-filled square
		if(row == 8 && col == 8){
			//last square −− success!!
			return true;
		}
		else{
			//nothing to do here, move on to the next square
			result = sudoku(grid, nxtrow, nxtcol);
		}
	}
	
	else{
		//a blank square - try filling it with 1...9
		for(byte try = 1; try <= 9 && !result; try++){
			setSquare(grid, row, col, try);
			if(isValid(grid, row, col)){
				//putting the value here works so far ... 
				if(row == 8 && col == 8){
					//last square -- success!!
					result = true;
				}
				else{
					//... move on to next square
					result = sudoku(grid, nxtrow, nxtcol);
				}
			}
		}
		if(!result){
			//made an earlier mistake - backtrack by setting 
			//the current square back to zero/blank
			setSquare(grid, row, col, 0);
		}
	}
	
	return result;
}

//test cases

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
	
	;
	; write tests for other subroutines
	;
	LDR R1, =gridSix
	BL printGrid
	
	LDR R1, =gridUser
	BL userInput
	
	;
	; test sudoku subroutine
	;
	
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
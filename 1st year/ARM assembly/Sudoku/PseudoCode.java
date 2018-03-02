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
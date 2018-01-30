/* SELF ASSESSMENT
   1. clearBoard:
Did I use the correct method definition?
Mark out of 5:5
Comment: Defined with the exact parameters stated in the question
Did I use loops to set each position to the BLANK character?
Mark out of 5:5
Comment: Used a nested loop to go through each element of the array
   2. printBoard
Did I use the correct method definition?
Mark out of 5:5
Comment: Defined with the exact parameters stated in the question
Did I loop through the array and prints out the board in a way that it looked like a board?
Mark out of 5:5
Comment: Printed out each element spaced with the '|' character and hyphons between rows for it to look like a board
   3. canMakeMove
Did I have the correct function definition and returned the correct item?
Mark out of 5:5
Comment: Defined with the exact parameters stated in the question and returned a boolean
Did I check if a specified location was BLANK?
Mark out of 5:5
Comment:Checked the move location for a blank space
   4. makeMove
Did I have the correct function definition?
Mark out of 5:5
Comment: Defined with the exact parameters stated in the question
Did I set the  currentPlayerPiece in the specified location?
Mark out of 5:5
Comment:Placed the piece in requested tile
   5. isBoardFull
Did I have the correct function definition and returned the correct item?
Mark out of 5:5
Comment: Defined with the exact parameters stated in the question
Did I loop through the board to check if there are any BLANK characters?
Mark out of 5:5
Comment: Looped through adding to a count of blanks
   6. winner
Did I have the correct function definition and returned the winning character
Mark out of 5:5
Comment: Defined with the exact parameters stated in the question
Did I identify all possible horizontal, vertical and diagonal winners
Mark out of 15:15
Comment: Have all possible win conditions checked for a 3*3 grid
   7.main

Did I create a board of size 3 by 3 and use the clearBoard method to set all the positions to the BLANK character ('  ')?
Mark out of 3:3
Comments:Created and cleared the borad at the start of the main function
Did I loop asking the user for a location until either the board was full or there was a winner?
Mark out of 5:5
Comments:Loop was active until a winner was found or the board filled up.
Did I call all of the methods above?
Mark out of 5:5
Comments:Called all methods at some point in main
Did I handle incorrect locations provided by the user (either occupied or invalid locations)?
Mark out of 3:3
Comments:Asked for input again if input was incorrect
Did I switch the current player piece from cross to nought and vice versa after every valid move?
Mark out of 3:3
Comments:Switched the player piece based on the turn
Did I display the winning player piece or a draw at the end of the game?
Mark out of 3:3
Comments:Displayed winning piece or stated there was no winner

   8. Overall
Is my code indented correctly?
Mark out of 3:3
Comments:All code is indented appropriately
Do my variable names and Constants (at least four of them) make sense?
Mark out of 3:3
Comments:variable names and constants make sense
Do my variable names, method names and class name follow the Java coding standard
Mark out of 2:2
Comments:Follow coding standard
      Total Mark out of 100 (Add all the previous marks):100
*/

import java.util.Scanner;

public class NoughtsAndCrosses{

    public static final char BLANK = ' ';
    public static final char PLAYER1 = 'X';
    public static final char PLAYER2 = 'O';
    public static final int SQUARE_DIMENSION = 3;

    public static void main(String[] args){
        char[][] board = new char[SQUARE_DIMENSION][SQUARE_DIMENSION];
        clearBoard(board);
        printBoard(board);

        Scanner in = new Scanner(System.in);
        int playerTurn = 0;
        boolean continueGame = true;
        while(continueGame){
            int player = playerTurn%2 + 1;
            System.out.print("Player " + player + ", please make your move(row column): ");
            int[] pos = new int[2];

            boolean validInput = true;
            if(in.hasNextInt()){
                pos[0] = in.nextInt() - 1;
                if(pos[0] > 2){
                    validInput = false;
                }
            } else {
                validInput = false;
            }
            if(in.hasNextInt()){
                pos[1] = in.nextInt() - 1;
                if(pos[1] > 2){
                    validInput = false;
                }
            } else {
                validInput = false;
            }

            if(validInput){
                if(canMakeMove(board, pos[0], pos[1]) && !isBoardFull(board)){
                     makeMove(board, player == 1 ? PLAYER1 : PLAYER2, pos[0], pos[1]);
                     playerTurn++;
                } else{
                    if(!canMakeMove(board, pos[0], pos[1])){
                        System.out.println("That isn't a valid move, please try again.");
                    } else {
                        continueGame = false;
                        System.out.println("There was no winner, the board is full.");
                    }
                }

                char winner = winner(board);
                printBoard(board);
                if(winner == BLANK && !isBoardFull(board)) {
                    System.out.println("There's no winner yet...");
                } else if(winner != BLANK){
                    System.out.println("The winner is " + winner);
                    continueGame = false;
                }
            } else {
                System.out.println("That isn't a valid move, please try again.");
                in.nextLine();
            }
        }
    }

    public static void clearBoard (char[][] board){
        for(int i = 0; i < SQUARE_DIMENSION; i++){
            for(int j = 0; j < SQUARE_DIMENSION; j++){
                board[i][j] = BLANK;
            }
        }
    }

    public static void printBoard (char[][] board){
        for(int i = 0; i < SQUARE_DIMENSION; i++){
            for(int j = 0; j < SQUARE_DIMENSION; j++){
                System.out.print(board[i][j]);
                if(j != 2){
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if(i != 2){
                System.out.println("---------");
            }
        }
    }

    public static boolean canMakeMove (char[][] board, int row, int column){
        if(board[row][column] == BLANK) return true;
        return false;
    }

    public static void makeMove (char[][] board, char currentPlayerPiece , int row, int column) {
        board[row][column] = currentPlayerPiece;
    }

    public static boolean isBoardFull(char[][] board) {
        int freeSpaces = 0;
        for(int i = 0; i < SQUARE_DIMENSION; i++){
            for(int j = 0; j < SQUARE_DIMENSION; j++){
                if (board[i][j] == BLANK){
                    freeSpaces++;
                }
            }
        }

        if(freeSpaces > 0){
            return false;
        } else {
            return true;
        }
    }

    public static char winner (char[][] board){
        if(board[0][0] == PLAYER1 && board[0][1] == PLAYER1 && board[0][2] == PLAYER1 ||
            board[1][0] == PLAYER1 && board[1][1] == PLAYER1 && board[1][2] == PLAYER1 ||
            board[2][0] == PLAYER1 && board[2][1] == PLAYER1 && board[2][2] == PLAYER1 ||
            board[0][0] == PLAYER1 && board[1][0] == PLAYER1 && board[2][0] == PLAYER1 ||
            board[0][1] == PLAYER1 && board[1][1] == PLAYER1 && board[2][1] == PLAYER1 ||
            board[0][2] == PLAYER1 && board[1][2] == PLAYER1 && board[2][2] == PLAYER1 ||
            board[0][0] == PLAYER1 && board[1][1] == PLAYER1 && board[2][2] == PLAYER1 ||
            board[0][2] == PLAYER1 && board[1][1] == PLAYER1 && board[2][0] == PLAYER1) {
                return PLAYER1;
        } else if(board[0][0] == PLAYER2 && board[0][1] == PLAYER2 && board[0][2] == PLAYER2 ||
            board[1][0] == PLAYER2 && board[1][1] == PLAYER2 && board[1][2] == PLAYER2 ||
            board[2][0] == PLAYER2 && board[2][1] == PLAYER2 && board[2][2] == PLAYER2 ||
            board[0][0] == PLAYER2 && board[1][0] == PLAYER2 && board[2][0] == PLAYER2 ||
            board[0][1] == PLAYER2 && board[1][1] == PLAYER2 && board[2][1] == PLAYER2 ||
            board[0][2] == PLAYER2 && board[1][2] == PLAYER2 && board[2][2] == PLAYER2 ||
            board[0][0] == PLAYER2 && board[1][1] == PLAYER2 && board[2][2] == PLAYER2 ||
            board[0][2] == PLAYER2 && board[1][1] == PLAYER2 && board[2][0] == PLAYER2) {
            return PLAYER2;
        } else {
            return BLANK;
        }

    }
}

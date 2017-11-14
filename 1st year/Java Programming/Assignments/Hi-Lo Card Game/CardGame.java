/* SELF ASSESSMENT
   1. Did I use appropriate CONSTANTS instead of numbers within the code?
       Mark out of 5: 5
       Comment: I userd a constant for the number of guesses so it can be altered eassily. Also for the faced cards
       as it is easier to read
   2. Did I use easy-to-understand, meaningful CONSTANT names formatted correctly in UPPERCASE?
       Mark out of 5: 5
       Comment: I named all constants with words correlating to their meanings
   3. Did I use easy-to-understand meaningful variable names?
       Mark out of 10: 10
       Comment: All variables are named what they are in simple english
   4. Did I format the variable names properly (in lowerCamelCase)?
       Mark out of 5: 5
       Comment: I did format them like this
   5. Did I indent the code appropriately?
       Mark out of 10: 10
       Comment: I indented every statement appropriately
   6. Did I use an appropriate loop to allow the user to enter their guesses until they win or lose?
       Mark out of 20: 20
       Comment: The loop implemented allows users to guess until they get it wrong or reach the correct amount of guesses
   7. Did I check the input to ensure that invalid input was handled appropriately?
       Mark out of 10: 10
       Comment: All input other then 'higher','lower', and 'equal' will exit the application with a message to the user that we
       couldn't interpret it
   8. Did I generate the cards properly using random number generation (assuming all cards are equally likely each time)?
       Mark out of 10: 10
       Comment: Each card is a new random number
   9. Did I output the cards correctly as 2, 3, 4, ... 9, 10, Jack, Queen, King?
       Mark out of 10: 10
       Comment: Named cards print their name instead of the number
   10. Did I report whether the user won or lost the game before the program finished?
       Mark out of 10: 10
       Comment: I have a win, lose and invalid input message
   11. How well did I complete this self-assessment?
       Mark out of 5: 5
       Comment: I justified all my marks
   Total Mark out of 100 (Add all the previous marks): 100
*/

import java.util.Scanner;
import java.util.Objects;
import java.util.Random;

public class CardGame {

    public static final int GUESS_NUM = 4;

    public static final int ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;

    public static void main(String[] args){

    	boolean quit = false;

    	Scanner input = new Scanner(System.in);

    	while(!quit) {
    		Random generator = new Random();

        	int count = 0;
            int lastCard = 0;
            int cardNum = generator.nextInt(13) + 1;
            String guess = " ";

            boolean badInput = false;

            while(count < GUESS_NUM && !quit){
            	lastCard = cardNum;
                cardNum = generator.nextInt(13) + 1;

                printCard(lastCard);
                System.out.print("Do you think the next card will be higher, lower or equal? ");
                guess = input.next();

                if(lastCard < cardNum && Objects.equals(guess, "higher")){
                    count++;
                }
                else if(lastCard > cardNum && Objects.equals(guess, "lower")){
                    count++;
                }
                else if(lastCard == cardNum && Objects.equals(guess, "equal")){
                    count++;
                }
                else if(Objects.equals(guess, "quit")){
                    quit = true;
                }
                else if (!Objects.equals(guess, "higher") && !Objects.equals(guess, "lower") && !Objects.equals(guess, "equal")){
                    System.out.println("Sorry, we could not understand your input.");
                    badInput = true;
                    count = 1000;
                }
                else {
                    count = 1000;
                }
            }

            if(!quit) {
	            if(count == GUESS_NUM) {
	            	System.out.println("Congratulations. You got them all correct.");
	            }
	            else if (!badInput){
	            	System.out.println("You lost the game.");
	            }
	            System.out.print("Do you want to play again(y/n): ");
                String again = input.next();
                if(!Objects.equals(again, "y") || !Objects.equals(again, "Y")){
                    quit = true;

                    if(!Objects.equals(again, "n") || !Objects.equals(again, "N")){
                        System.out.println("Sorry didn't get that but guessing you quit, Thanks for playing!");
                    }
                    else {
                        System.out.println("Thanks for playing!");
                    }
                }
            }
    	}
        input.close();
    }

    public static void printCard(int cardNum){
        if(cardNum != 1 && cardNum <= 10) {
            System.out.println("The card is a " + cardNum);
        }
        else if(cardNum == ACE){
            System.out.println("The card is an Ace");
        }
        else if(cardNum == JACK){
            System.out.println("The card is a Jack");
        }
        else if(cardNum == QUEEN){
            System.out.println("The card is a Queen");
        }
        else if(cardNum == KING){
            System.out.println("The card is a King");
        }
    }
}

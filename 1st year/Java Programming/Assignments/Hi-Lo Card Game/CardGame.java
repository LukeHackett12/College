import java.util.Scanner;
import java.util.Objects;
import java.util.Random;

public class CardGame {
    public static void main(String[] args){
        //Initial guess
        Random generator = new Random();
        Scanner input = new Scanner(System.in);
        int cardNum = generator.nextInt(13) + 1;

        printCard(cardNum);
        int cardGuess = takeInput(input);

        boolean quit = false;
        int count = 0;

        while(count < 3 && !quit){
        	int lastCard = cardNum;
            cardNum = generator.nextInt(13) + 1;

            if(lastCard < cardNum && cardGuess == 1){
                count++;
            }
            else if(lastCard > cardNum && cardGuess == 2){
                count++;
            }
            else if(lastCard == cardNum && cardGuess == 3){
                count++;
            }
            else {
                quit = true;

                if(cardGuess == 0)
                {
                    System.out.println("Sorry, we could not understand your input :/");
                }
            }

            if(!quit){
                printCard(cardNum);
                cardGuess = takeInput(input);
            }
        }

        if(count == 3) {
        	System.out.println("You have won :)!!!!!!!!");
        }
        else if (cardGuess != 0){
        	System.out.println("You lost the game :'(");
        }

        input.close();
    }

    public static int takeInput(Scanner input){
    	System.out.print("Do you think the next card will be higher, lower or equal? ");

        int cardGuess = 0;
        String guess = input.next();

        if(Objects.equals(guess, "higher")) {
        	cardGuess = 1;
        }
        else  if(Objects.equals(guess, "lower")) {
        	cardGuess = 2;
        }
        else if(Objects.equals(guess, "equal")) {
        	cardGuess = 3;
        }

        return cardGuess;
    }

    public static void printCard(int cardNum){
        if(cardNum != 1 && cardNum <= 10) {
            System.out.println("The card is a " + cardNum);
        }
        else if(cardNum == 1){
            System.out.println("The card is an Ace");
        }
        else if(cardNum == 11){
            System.out.println("The card is a Jack");
        }
        else if(cardNum == 12){
            System.out.println("The card is a Queen");
        }
        else if(cardNum == 13){
            System.out.println("The card is a King");
        }
    }
}

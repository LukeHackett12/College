import java.util.Scanner;
import java.util.Random;

public class LukesLottery {
	public static void main(String[] args){
		//Start game
		boolean quit = false;
		Scanner input = new Scanner(System.in);

		while(!quit){
			//Start Input
			boolean valid = false;
			int[] guess = new int[3];
			while(!valid){
				boolean withinBounds = true;
				boolean differentNums = true;
				System.out.print("Please select three numbers between 1 and 45 for your ticket: ");
				for(int i = 0; i < 3; i++){
					if(input.hasNextInt()){
						guess[i] = input.nextInt();
						if(guess[i] >= 1 && guess[i] <= 45){
							valid = true;
							for(int j = 0; j < i; j++){
								if(guess[i] == guess[j]){
									differentNums = false;
								}
							}
						}
						else if(guess[i] < 1 || guess[i] > 45){
							withinBounds = false;
						}
						else {
							valid = false;
						}
					}
					else if(input.hasNext("quit")){
						System.out.print("Thank you for playing");
						System.exit(0);
					}

					if(!withinBounds){
						System.out.println("Sorry one of your numbers was not within 1 and 45, please try again");
						valid = false;
						i = 3;
					}
					else if(!valid){
						System.out.println("Sorry one of your numbers was wrong, please try again");
						valid = false;
						i = 3;
					}
					else if(!differentNums){
						System.out.println("Sorry some of your numbers appeared to be the same, please try again");
						valid = false;
						i = 3;
					}
				}
			}
			//End input

			//Start ticket generation
			Random generator = new Random();
			int[] ticket = new int[3];
			for(int i = 0; i < 3; i++){
				ticket[i] = generator.nextInt(45) + 1;
				for(int j = 0; j < i; j++){
					if(ticket[j] == ticket [i]){
						ticket[i] = generator.nextInt(45) + 1;
					}
				}
			}
			//End ticket generation

			//Start comparison
			int correctGuesses = 0;
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					if(guess[i] == ticket[j]){
						correctGuesses++;
					}
				}
			}
			//End comparison

			//Print outcome
			if(correctGuesses == 3){
				System.out.print("You have won!! Do you want to play again(yes/no): ");

				String again= input.next();
				if(!again.equals("yes")){
					System.out.println("Ok, thanks for playing");
					System.exit(0);
				}

			}
			else{
				System.out.print("You had " + correctGuesses + " correct guesses. Do you want to play again(yes/no): ");

				String again= input.next();
				if(!again.equals("yes")){
					System.out.println("Ok, thanks for playing");
					System.exit(0);
				}	
			}
		}
		input.close();
	}
}

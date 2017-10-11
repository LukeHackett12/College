import java.util.Scanner;

public class Lottery {
	public static void main(String[] args) {
		
		System.out.print("Please pick three numbers that you would like to enter the loterry with: ");
		
		Scanner inputScanner = new Scanner(System.in);
		int guess1 = inputScanner.nextInt();
		while(guess1 > 45 || guess1 < 1) {
			System.out.print("You already have that number or it is not within our range, pick another: ");
			//inputScanner = new Scanner(System.in);
			guess1 = inputScanner.nextInt();
		}
		int guess2 = inputScanner.nextInt();
		while (guess1 == guess2 || guess2 > 45 || guess2 < 1) {			
			System.out.print("You already have that number or it is not within our range, pick another: ");
			//inputScanner = new Scanner(System.in);
			guess2 = inputScanner.nextInt();
		}
		int guess3 = inputScanner.nextInt();	
		while (guess1 == guess3 || guess2 == guess3 || guess3 > 45 || guess2 < 1) {
			System.out.print("You already have that number or it is not within our range, pick another: ");
			//inputScanner = new Scanner(System.in);
			guess2 = inputScanner.nextInt();
		}
		inputScanner.close();
		
		int guessNums[] = {guess1, guess2, guess3};
		//Calculate random numbers
		int winNums[] = {0, 0, 0};
		
		for(int i = 0; i < 3; i++) {
			
			boolean _isValid = true;
			int tempRandom = (int)(Math.random() * 45 + 1);
			
			for(int j = 0; j < 3; j++) {
				if(tempRandom != winNums[j])
					_isValid = true;
				else {
					_isValid = false;
					j = 3;
				}
			}
			
			if(_isValid)
				winNums[i] = tempRandom;
			else
				i--;
		}
		
		int winMatch[] = {0, 0, 0};
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(guessNums[i] == winNums[j])
					winMatch[i] = guessNums[i];
			}
		}
		
		if(winMatch[0] != 0 && winMatch[1] != 0 && winMatch[2] != 0)
			System.out.println("You have won! The numbers that matched were: " + winMatch[0] + "," + winMatch[1] + "," + winMatch[2]);
		else {
			System.out.print("You have lost, the numbers that matched were ");
			if(winMatch[0] != 0) {
				System.out.print(winMatch[0] + ",");
			}
			if(winMatch[1] != 0) {
				System.out.print(winMatch[1]  + ",");
			}
			if(winMatch[2] != 0) {
				System.out.print(winMatch[2]);
			}
		}
	}
}

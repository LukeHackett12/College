import java.util.Scanner;

public class etest1 {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.print("What number would you like the closest Fibonacci number for: ");
		double number = 0;
		if(input.hasNextDouble()){
			number = input.nextDouble();
			
			int sequenceNum = 1;
			int previousSequence = 0;
			
			if(number >= 0){
				boolean closest = false;
				while(!closest){
					if(sequenceNum == number){
						System.out.println("The closest Fibonacci number to " + number + " is " + sequenceNum);
						closest = true;
					}
					else if(previousSequence == number){
						System.out.println("The closest Fibonacci number to " + number + " is " + previousSequence);
						closest = true;
					}
					else {
						int nextNumber = sequenceNum + previousSequence;
						
						if(Math.abs(nextNumber - number) < Math.abs(sequenceNum - number) || Math.abs(nextNumber - number) < Math.abs(previousSequence - number)){
							previousSequence = sequenceNum;
							sequenceNum = nextNumber;
						}
						else if (Math.abs(sequenceNum - number) > Math.abs(previousSequence - number)){
							System.out.println("The closest Fibonacci number to " + number + " is " + previousSequence);
							closest = true;
						}
						else if (Math.abs(sequenceNum - number) < Math.abs(previousSequence - number)){
							System.out.println("The closest Fibonacci number to " + number + " is " + sequenceNum);
							closest = true;
						}
						else if (Math.abs(sequenceNum - number) == Math.abs(previousSequence - number)){
							System.out.println("The closest Fibonacci numbers to " + number + " are " + previousSequence + " and " + sequenceNum);
							closest = true;
						}
					}
				}
			}
			else {
				System.out.println("The closest Fibonacci number to " + number + " is " + 0);
			}
		}
		else {
			System.out.println("That was not a valid input, please start the program again to retry.");
		}
		input.close();
	}
}

import java.util.Scanner;

public class Fibonacci {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.print("How many numbers in the Fibonacci sequence would you like calculated: ");
		int ammount = 0;
		if(input.hasNextInt()){
			ammount = input.nextInt();
		}
		
		int sequenceNum = 1;
		int previousSequence = 0;
		
		System.out.println(previousSequence);
		
		for(int count = 0; count < ammount; count++){
			int nextNumber = sequenceNum + previousSequence;
			previousSequence = sequenceNum;
			sequenceNum = nextNumber;
			System.out.println(previousSequence);
		}
		
		input.close();
	}
}

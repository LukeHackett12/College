import java.util.Scanner;

public class temp
{
  public static void main(String[] args){

      int compareNum = 0;
      int highestNum = Integer.MIN_VALUE;
      int lowestNum = Integer.MAX_VALUE;

      Scanner input = new Scanner(System.in);
	  String userInput = input.nextLine();
	  Scanner parseInput = new Scanner(userInput);

      while(parseInput.hasNextInt()){
    	  	compareNum = parseInput.nextInt();
	        if(compareNum > highestNum) {
	        	highestNum = compareNum;
	        }
	        if(compareNum < lowestNum) {
	        	lowestNum = compareNum;
	        }
      }
      input.close();
      parseInput.close();

      System.out.println("The lowest number is " + lowestNum + " " + "and the highest number is " + highestNum);
  }
}

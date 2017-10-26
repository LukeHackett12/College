import java.util.Scanner;

public class IncrementalStatistics {
  public static void main(String[] args){

    Scanner input = new Scanner(System.in);
    System.out.print("Enter a number(or type 'exit'): ");
    double number = 0;
    //Check if number is valid as an integer
    if(input.hasNextInt()){
      number = input.nextInt();
    }
    else if(input.hasNext("exit") || input.hasNext("quit")){
      input.close();
      System.exit(0);
    }
    else {
      System.out.println("Sorry that was not a valid number, please start again.");
      input.close();
      System.exit(0);
    }
    //Average is first number and no variance, easier to not compute.
    double lastAvg = 0;
    double average = number;
    double variance = 0;

    //Print out initial values
    System.out.println("So far the average is " + average + " and the variance is " + variance);

    //Add a count of the numbers as needed in the formula
    int numCount = 1;

    //Start while loop to compute additions to beginning
    boolean exit = false;
    while(!exit){
      System.out.print("Enter another number (or type 'exit'): ");

      if(input.hasNextDouble()){
        number = input.nextInt();
        numCount++;
      }
      else if(input.hasNext("exit") || input.hasNext("quit")){
        exit = true;
        input.close();
      }
      else {
        System.out.print("Sorry that was not a valid number, please start again");
        exit = true;
        input.close();
      }

      //Compute new average and variance
      lastAvg = average;
      average = average + (number - average)/numCount;
      variance = ((variance * (numCount - 1)) + (number - lastAvg) * (number - average)) / numCount;

      //Print out computed values or a goodbye
      if(!exit) {
      	System.out.println("So far the average is " + average + " and the variance is " + variance);
      }
      else {
    	System.out.println("Goodbye");
      }
    }
  }
}

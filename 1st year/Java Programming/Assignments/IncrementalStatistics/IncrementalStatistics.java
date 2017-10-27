import java.util.Scanner;

public class IncrementalStatistics {
  public static void main(String[] args){

    Scanner input = new Scanner(System.in);
    System.out.print("Enter a number(or type 'exit'): ");
    double number = 0;
    int numCount = 0;

    if(input.hasNextInt()){
      number = input.nextInt();
      numCount++;
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

    double lastAvg = 0;
    double average = number;
    double variance = 0;

    System.out.println("So far the average is " + average + " and the variance is " + variance);

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

      lastAvg = average;
      average = average + (number - average)/numCount;
      variance = ((variance * (numCount - 1)) + (number - lastAvg) * (number - average)) / numCount;

      if(!exit) {
      	System.out.println("So far the average is " + average + " and the variance is " + variance);
      }
      else {
    	System.out.println("Goodbye");
      }
    }
  }
}

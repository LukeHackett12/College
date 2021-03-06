/* SELF ASSESSMENT
   1. Did I use easy-to-understand meaningful variable names?
       Mark out of 10: 10 - All my variable names are easy to understand and have meaning
   2. Did I format the variable names properly (in lowerCamelCase)?
       Mark out of 5: 5 - They have been formatted properly
   3. Did I indent the code appropriately?
       Mark out of 10: 10 - I have indented the main function and any statement blocks in if/while statements
   4. Did I input the numbers one at a time from the command line?
       Mark out of 10: 10 - The numbers are input one at a time
   5. Did I check the input to ensure that invalid input was handled appropriately?
       Mark out of 10: 10 - Used the hasNextInt() function from scanner to ensure it was valid and if not quit
   6. Did I use an appropriate while or do-while loop to allow the user to enter numbers until they entered exit/quit?
       Mark out of 20: 20 - My while loop will go until they type quit or exit
   7. Did I implement the loop body correctly so that the average and variance were updated and output appropriately?
       Mark out of 30: 30 - I get correct answers and print out like the example case
   8. How well did I complete this self-assessment?
       Mark out of 5: 5 - I commented on everything
   Total Mark out of 100 (Add all the previous marks): 100
*/

import java.util.Scanner;

public class IncrementalStatistics {
  public static void main(String[] args){

    Scanner input = new Scanner(System.in);
    System.out.print("Enter a number(or type 'exit'): ");
    double number = 0;
    int numCount = 0;

    if(input.hasNextDouble()){
      number = input.nextDouble();
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
        number = input.nextDouble();
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

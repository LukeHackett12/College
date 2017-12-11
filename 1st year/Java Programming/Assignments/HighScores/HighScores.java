/* SELF ASSESSMENT
 1. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?
        Mark out of 5: 5

 2. Did I indent the code appropriately?
        Mark out of 5: 5

 3. Did I write the initialiseHighScores function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 15: 15

 4. Did I write the printHighScores function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 15: 15

 5. Did I write the higherThan function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 15: 15

 6. Did I write the insertScore function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 20: 20

 7. Did I write the main function body correctly (first asking for the number of scores to be maintained and then repeatedly asking for scores)?
       Mark out of 20: 20

 8. How well did I complete this self-assessment?
        Mark out of 5: 5
        
 Total Mark out of 100 (Add all the previous marks): 100
*/

import java.util.Scanner;

public class HighScores{
    public static void main(String[] Args){
        Scanner input = new Scanner(System.in);

        System.out.print("How many scores are you maintaining? ");
        int arrayLength = 0;
        if(input.hasNextInt()){
            arrayLength = input.nextInt();
        }

        int[] highScores = initialiseHighScores(arrayLength);
        printHighScores(highScores);

        boolean quit = false;
        while(!quit){
            System.out.print("Input the high score: ");
            int highScore = 0;
            if(input.hasNextInt()){
                highScore = input.nextInt();
            }
            else {
                quit = true;
            }

            if(higherThan(highScores, highScore) && !quit){
                highScores = insertScore(highScores, highScore);
            }
            printHighScores(highScores);
        }
    }

    public static int[] initialiseHighScores(int arrayLength){
        int[] tempHighScores = new int[arrayLength];
        for(int count = 0; count < arrayLength; count++){
            tempHighScores[count] = 0;
        }
        return tempHighScores;
    }

    public static void printHighScores(int[] highScores){
        System.out.print("The high scores are ");
        for(int count = 0; count < highScores.length-1; count++){
            System.out.print(highScores[count] + ", ");
        }
        System.out.print(highScores[highScores.length - 1]);
        System.out.println();
    }

    public static boolean higherThan(int[] highScores, int highScore){
        for(int count = 0; count < highScores.length; count++){
            if(highScore > highScores[count]){
                return true;
            }
        }
        return false;
    }

    public static int[] insertScore(int[] highScores, int highScore){
        for(int count = highScores.length - 1; count >= 0; count--){
            if(highScore > highScores[count]){
                for(int shift = 1; shift <= count; shift++){
                    highScores[shift-1]=highScores[shift];
                }
                highScores[count] = highScore;
                count = -1;
            }
        }
        return highScores;
    }
}

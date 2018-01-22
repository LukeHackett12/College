/* SELF ASSESSMENT
   1. createSequence:
Did I use the correct method definition?
Mark out of 5:5
Comment: I defined the method as an int[] named correctly
Did I create an array of size n (passed as the parameter) and initialise it?
Mark out of 5:5
Comment:Created and initialised an n sized array.
Did I return the correct item?
Mark out of 5:5
Comment: returned iitialised array
   2. crossOutMultiples
Did I use the correct method definition?
Mark out of 5:5
Comment:Defined as int[] with correct name
Did I ensure the parameters are not null and one of them is a valid index into the array
Mark out of 2:2
Comment:Sequence is ensured to be not null
Did I loop through the array using the correct multiple?
Mark out of 5:5
Comment: looped through array using increasing correct multiple
Did I cross out correct items in the array that were not already crossed out?
Mark out of 3:3
Comment:Crosses out all correct numbers in sequence
   3. sieve
Did I have the correct function definition?
Mark out of 5:5
Comment:Sieve takes a value n and is an int[]
Did I make calls to other methods?
Mark out of 5:5
Comment:Called all necessary methods
Did I return an array with all non-prime numbers are crossed out?
Mark out of 2:2
Comment:Returned correct array
   4. sequenceTostring
Did I have the correct function definition?
Mark out of 5:5
Comment:Took in sequence of type int[] and returned string
Did I ensure the parameter to be used is not null?
Mark out of 3:3
Comment:If sequence is null code will return empty string
Did I Loop through the array updating the String variable with the non-crossed out numbers and the crossed numbers in brackets?
Mark out of 10:10
Comment:Looped through array to produce correct output
   5. nonCrossedOutSubseqToString
Did I have the correct function definition
Mark out of 5:5
Comment:Took in sequence of type int[] and returned string
Did I ensure the parameter to be used is not null?
Mark out of 3:3
Comment:If sequence is null code will return empty string
Did I loop through the array updating the String variable with just the non-crossed out numbers?
Mark out of 5:5
Comment:Looped through array to produce correct output
   6. main
Did I ask  the user for input n and handles input errors?
Mark out of 5:5
Comments:Input will be asked for again if incorrect
Did I make calls to other methods (at least one)?
Mark out of 5:5
Comment: Called Sieve and nonCrossedOutSubseqToString
Did I print the output as shown in the question?
Mark out of 5:3
Comment:Couldn't get the comma off the final value
   7. Overall
Is my code indented correctly?
Mark out of 4:4
Comments:Indented correctly
Do my variable names make sense?
Mark out of 4:4
Comments:All names correlate to their value
Do my variable names, method names and class name follow the Java coding standard
Mark out of 4:4
Comments:Follow the standard
      Total Mark out of 100 (Add all the previous marks): 98
*/

import java.util.Scanner;

public class SieveOfEratosthenes{

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        boolean quit = false;

        while(!quit)
        {
            System.out.print("Enter int >= 2: ");
            int number = 0;
            if(in.hasNextInt()){
                number = in.nextInt();
                if(number >= 2){
                    int[] primedSequence = sieve(number);
                    System.out.println(nonCrossedOutSubseqToString(primedSequence));
                }
                else if(in.hasNext("q")){
                    quit = true;
                }
                else{
                    System.out.println("Eww, try again.");
                    in.next();
                }
            }
            else{
                System.out.println("Eww, try again.");
                in.next();
            }
        }
        in.close();
    }

    public static int[] createSequence(int n){
        int[] numberSequence = new int[n-1];
        int tempN = 2;
        for(int i = 0; i < numberSequence.length; i++){
            numberSequence[i] = tempN;
            tempN++;
        }
        return numberSequence;
    }

    public static int[] crossOutHigherMultiples(int[] sequence){
        if(sequence != null){
            int multiple = 2;
            while(multiple <= Math.sqrt(sequence.length+2)){
                for(int i = multiple; i < sequence.length; i++){
                    if(sequence[i] % multiple == 0 && sequence[i] > 0)
                        sequence[i] *= -1;
                }
                System.out.println(sequenceToString(sequence));
                multiple++;
            }
        }
        return sequence;
    }

    public static int[] sieve(int n){
        int[] numberSequence = createSequence(n);
        System.out.println(sequenceToString(numberSequence));
        numberSequence = crossOutHigherMultiples(numberSequence);
        return numberSequence;
    }

    public static String sequenceToString(int[] sequence){
        String sequenceString = "";
        for(int i : sequence){
            if(i > 0 && i != sequence[sequence.length-1]){
                sequenceString += i + ", ";
            } else if(i < 0 && i != sequence[sequence.length-1]){
                sequenceString += "[" + Math.abs(i) + "], ";
            } else if(i > 0 && i == sequence[sequence.length-1]){
                sequenceString += i;
            } else{
                sequenceString += "[" + Math.abs(i) + "]";
            }
        }
        return sequenceString;
    }

    public static String nonCrossedOutSubseqToString(int[] sequence){
        String sequenceString = "";
        for(int i : sequence){
            if(i > 0){
                sequenceString += i + ", ";
            }
        }
        return sequenceString;
    }
}

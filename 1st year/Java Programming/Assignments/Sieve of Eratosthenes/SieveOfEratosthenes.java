import java.util.Scanner;

public class SieveOfEratosthenes{

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter int >= 2: ");
        int number = 0;
        if(in.hasNextInt()){
            number = in.nextInt();
        }
        if(number >= 2){
            int[] primedSequence = sieve(number);
            System.out.println(nonCrossedOutSubseqToString(primedSequence));
        }
        else{
            System.out.println("Eww");
        }
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
        int multiple = 2;
        while(multiple <= Math.sqrt(sequence.length+2)){
            for(int i = multiple; i < sequence.length; i++){
                if(sequence[i] % multiple == 0 && sequence[i] > 0)
                    sequence[i] *= -1;
            }
            System.out.println(sequenceToString(sequence));
            multiple++;
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
            if(i > 0){
                sequenceString += i + ", ";
            } else {
                sequenceString += "[" + Math.abs(i) + "], ";
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

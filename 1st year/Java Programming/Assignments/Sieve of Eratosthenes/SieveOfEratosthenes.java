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
            int[][] primedSequence = sieve(number);
            System.out.println(nonCrossedOutSubseqToString(primedSequence, number));
        }
        else{
            System.out.println("Eww");
        }
    }

    public static int[][] createSequence(int n){
        int[][] numberSequence = new int[n-1][2];
        int tempN = 2;
        for(int i = 0; i <= n-2; i++){
            numberSequence[i][0] = tempN;
            numberSequence[i][1] = 0;
            tempN++;
        }
        return numberSequence;
    }

    public static int[][] crossOutHigherMultiples(int[][] sequence, int n){
        int multiple = 2;
        while(multiple <= Math.sqrt(n)){
            for(int i = multiple; i <= n-2; i++){
                if(sequence[i][0] % multiple == 0)
                    sequence[i][1] = 1;
            }
            System.out.println(sequenceToString(sequence, n));
            multiple++;
        }
        return sequence;
    }

    public static int[][] sieve(int n){
        int[][] numberSequence = createSequence(n);
        System.out.println(sequenceToString(numberSequence, n));
        numberSequence = crossOutHigherMultiples(numberSequence, n);
        return numberSequence;
    }

    public static String sequenceToString(int[][] sequence, int n){
        String sequenceString = "";
        for(int i = 0; i <= n-2; i++){
            if(sequence[i][1] == 0){
                sequenceString += sequence[i][0] + ", ";
            } else {
                sequenceString += "[" + sequence[i][0] + "], ";
            }
        }
        return sequenceString;
    }

    public static String nonCrossedOutSubseqToString(int[][] sequence, int n){
        String sequenceString = "";
        for(int i = 0; i <= n-2; i++){
            if(sequence[i][1] == 0){
                sequenceString += sequence[i][0] + ", ";
            }
        }
        return sequenceString;
    }
}

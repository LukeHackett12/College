import java.util.Scanner;

public class ChuckALuck{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        Wallet playerWallet = new Wallet();

        System.out.println("How much do you have in your wallet? ");
        if(in.hasNextInt()) playerWallet.put(in.hasNextInt);

        boolean finish = false;
        while(!finish){
            if(playerWallet.check() <= 0){
                finish = true;
            }
            else {
                System.out.println("What do you want to bet on: ");
                if(){

                }
            }
        }
    }

    public static void ResolveBet(String bet, Wallet playerWallet){
        System.out.println("How much do you want to bet: ");
        if(in.hasNextInt()){
            double bet = in.hasNextInt;
            if(playerWallet.get(bet)){
                Dice[] dice = new Dice[3];
                int[] rolls = new int[3];
                for(int i = 0; i < dice.length; i++){
                    rolls[i] = dice[i].topFace();
                }

                if(rolls[0] == rolls[1] == roll[2] && bet.equals("Triple")){
                    //Triple
                }
                else {
                    int count = rolls[0] + rolls[1] + roll[2];
                    if(count > 8 && count < 12 && bet.equals("Field")){
                        //Field
                    }
                    else if(count > 10 && bet.equals("High")){
                        //High
                    }
                    else if(count < 11 && bet.equals("Low")){
                        //Low
                    }
                }
            }
            else {
                //Didn't has the cash
            }
        }
        else{
            //Didn't has the int
        }
    }
}

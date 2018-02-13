import java.util.Scanner;

/* SELF ASSESSMENT

1. ResolveBet

    I have correctly defined ResolveBet which takes the bet type (String) and the Wallet object, and a void return type [Mark out of 7: 7].
    Comment:Takes a string and wallet and has no return type.
    My program presents the amount of cash in the wallet and asks the user how much he/she would like to bet [Mark out of 8: 8].
    Comment:Asks for bet amount and says how mcuh they have
    My program ensures the bet amount is not greater than the cash in the wallet [Mark out of 5: 5].
    Comment:checks that they have the amount of money
    My program creates three Dice objects, rolls them and creates a total variable with a summation of the roll values returned [Mark out of 15: 15]..
    Comment:rolls and creates a count of the three dice
    My program determines the winnings by comparing the bet type with the total and comparing the bet type with the dice faces for the triple bet [Mark out of 20: 20].
    Comment:compares the bet type with count to determine result
    My program outputs the results (win or loss) and adds the winnings to the wallet if user wins or removes the bet amount from the wallet if the user loses [Mark out of 10: 10].
    Comment:outputs result and alters wallet


2. Main

    I ask the user for the amount of cash he/she has, create a Wallet object and put this cash into it [Mark out of 15: 15]
    Comment:Take cash input, create wallet and fund it
    My program loops continuously until the user either enters quit or the cash in the wallet is 0 [Mark out of 5:5 ]
    Comment:loops until quit or empty wallet
    I ask the user to enter any of the four bet types or quit [Mark out of 5: 5].
    Comment:Ask for a bet or say that they can quit
    My program calls resolveBet for each bet type entered [Mark out of 5: 5].
    Comment:Calls resolve bet for each input guess
    At the end of the game my program presents a summary message regarding winnings and losses [Mark out of 5: 5]
    Comment:Says how much money you won or lost

 Total Mark out of 100 (Add all the previous marks):100
*/

public class ChuckALuck{

    static double initialValue;
    static doulbe finalValue;

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        Wallet playerWallet = new Wallet();

        System.out.println("How much do you have in your wallet? ");
        if(in.hasNextInt()) {
            initialValue = in.nextInt();
            playerWallet.put(initialValue);
        }

        boolean finish = false;
        while(!finish){
            if(playerWallet.check() <= 0){
                finish = true;
                finalValue = playerWallet.check();
            }
            else{
                System.out.println("What do you bet it will be(or quit): ");
                if(in.hasNext("quit")) finish = true;
                else{
                    String bet = in.next();
                    System.out.println("How much do you want to bet: ");
                    if(in.hasNextInt()){
                        double moneyBet = in.nextInt();
                        ResolveBet(bet, moneyBet, playerWallet);
                    }
                    else{
                        //Didn't has the int
                        System.out.println("Your maths is way too complex that's not a even a number.");
                    }
                }
            }
        }
        result();
    }

    public static void ResolveBet(String bet, double moneyBet, Wallet playerWallet){
        if(playerWallet.get(moneyBet)){
            Dice[] dice = new Dice[3];
            int[] rolls = new int[3];
            for(int i = 0; i < dice.length; i++){
                dice[i] = new Dice();
                rolls[i] = dice[i].roll();
            }

            if(rolls[0] == rolls[1] && rolls[1] == rolls[2] && bet.equals("Triple")){
                //Triple
                if(rolls[0] != 1 && rolls[0] != 6){
                    playerWallet.put(31*moneyBet);
                    System.out.println("You won!!");
                    System.out.println(playerWallet.toString());
                }
                else {
                    System.out.println("You lost :'(");
                    System.out.println(playerWallet.toString());
                }
            }
            else {
                int count = rolls[0] + rolls[1] + rolls[2];
                if(count > 8 && count < 12 && bet.equals("Field")){
                    //Field
                    playerWallet.put(2*moneyBet);
                    System.out.println("You won!!");
                    System.out.println(playerWallet.toString());
                }
                else if(count > 10 && bet.equals("High")){
                    //High
                    playerWallet.put(2*moneyBet);
                    System.out.println("You won!!");
                    System.out.println(playerWallet.toString());
                }
                else if(count < 11 && bet.equals("Low")){
                    //Low
                    playerWallet.put(2*moneyBet);
                    System.out.println("You won!!");
                    System.out.println(playerWallet.toString());
                }
                else{
                    System.out.println("You lost :'(");
                    System.out.println(playerWallet.toString());
                }
            }
        }
        else {
            //Didn't has the cash
            System.out.println("You can't afford this go home");
            System.out.println("You lost :'(");
        }
    }

    public static void result(){
        if(initialValue > finalValue){
            System.out.println("You lost " + initialValue-finalValue + "dollars, damn");
        }
        else if(initialValue < finalValue){
            System.out.println("You won " + finalValue-initialValue + "dollars, damn");        }
        else {
            System.out.println("You broke even");
        }
    }
}

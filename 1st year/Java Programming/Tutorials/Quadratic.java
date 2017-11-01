import java.util.Scanner;

public class Quadratic {
    public static void main(String[] args){
    	Scanner input = new Scanner(System.in);

    	boolean quit = false;

        while(!quit){
            System.out.print("Enter the coefficients of your second order polynomial seperated by spaces(or enter quit): ");

            double values[] = new double[3];

            for(int count = 0; count < 3; count++){
                if(input.hasNextDouble() && !quit){
                    values[count] = input.nextDouble();
                }
                else if(input.hasNext("quit")){
                    quit = true;
                }
                else if(!quit){
                	System.out.println("That isn't a number you fool, I'm not going to justify this by running again");
                	quit = true;
                }
            }

            if(!quit){
                double answers[] = new double[2];

                if(values[0] != 0) {
                	double inRoot = values[1]*values[1] - 4*(values[0]*values[2]);
	                if(inRoot > 0) {
	                	answers[0] = ((-1 * (values[1])) + Math.sqrt(values[1]*values[1] - 4*(values[0]*values[2]))) / (2 * values[0]);
	                	answers[1] = ((-1 * (values[1])) - Math.sqrt(values[1]*values[1] - 4*(values[0]*values[2]))) / (2 * values[0]);

	                	System.out.println("The roots to this equation are x=" + answers[0] + " and x=" + answers[1]);
	                }
	                else {
	                	System.out.println("There are no real roots for this equation");
	                }
                }
                else {
                	System.out.println("This cannot be done as 'a' is equal to zero");
                }
            }
        }

        input.close();
    }
}

import java.util.*;
import java.text.*;

/*  SELF ASSESSMENT
   1. Did I use appropriate CONSTANTS instead of numbers within the code?
       Mark out of 10:  10
   2. Did I use easy-to-understand, meaningful CONSTANT names?
       Mark out of 5:  4
    3. Did I format the CONSTANT names properly (in UPPERCASE)?
       Mark out of 5:  5
   4. Did I use easy-to-understand meaningful variable names?
       Mark out of 10:  10
   5. Did I format the variable names properly (in lowerCamelCase)?
       Mark out of 10:  10
   6. Did I indent the code appropriately?
       Mark out of 10:  10
   7. Did I read the input correctly from the user using (an) appropriate question(s)?
       Mark out of 10:  10
   8. Did I compute the answer correctly for all cases?
       Mark out of 20:  
   9. Did I output the correct answer in the correct format (as shown in the examples)?
       Mark out of 10:  10
   10. How well did I complete this self-assessment?
       Mark out of 10:  10
   Total Mark out of 100 (Add all the previous marks):  
*/

public class InflationCalculatorHandling{
	
	public static final int	OLD_PENCE_IN_NEW_PENCE = 67; 
	public static final int OLD_PENCE_IN_OLD_SHILLINGS = 12;	
	public static final int OLD_SHILLINGS_IN_OLD_POUNDS = 20;
	public static final int NEW_PENCE_IN_NEW_POUND = 100;

	public static void main(String[] args){
		 
		/* OK so... the deal is that we have to scan the 
		   scanners input to see if there are any 
		   fucking random letters or shit in my integer
		   values that I dont want. Use a for statement 
		   or some shite to do it for each input.
		*/
		
		System.out.print("Plese enter the amount of money you want to convert (pounds shillings pence): ");
		Scanner inputScanner = new Scanner (System.in);
		String oldPounds = inputScanner.next();
		String oldShillings = inputScanner.next();
		String oldPence = inputScanner.next();
		inputScanner.close();
		
		//Sanitise input
		String sanatiseDoubles[] = {oldPounds, oldShillings, oldPence};
		for(int i = 0; i < 3; i++){
			String doubleToTest = sanatiseDoubles[i];

			for(int j = 0; j < sanatiseDoubles[i].length(); j++){
				if (Character.isDigit(doubleToTest.charAt(j)) == false) { 
					System.out.println("You have tried to trick me but i caught you this time...");
					System.exit(0);
				}
			}
		}

		double oldPenceInNewPence = Double.parseDouble(oldPence) * OLD_PENCE_IN_NEW_PENCE;
		double oldShillingsInNewPence = Double.parseDouble(oldShillings) * OLD_PENCE_IN_OLD_SHILLINGS * OLD_PENCE_IN_NEW_PENCE;
		double oldPoundsInNewPence = Double.parseDouble(oldPounds) * OLD_SHILLINGS_IN_OLD_POUNDS * OLD_PENCE_IN_OLD_SHILLINGS * OLD_PENCE_IN_NEW_PENCE;
		double newPennySum = oldPenceInNewPence + oldShillingsInNewPence + oldPoundsInNewPence;

		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.UK);	
		String finalConversion = currencyFormat.format(newPennySum / NEW_PENCE_IN_NEW_POUND);
		System.out.println(oldPounds + " old pound, " + oldShillings + " old shilling and " + oldPence + " old pence = " + finalConversion);

	}
}


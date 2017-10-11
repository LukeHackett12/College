import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;

/*  SELF ASSESSMENT
   1. Did I use appropriate CONSTANTS instead of numbers within the code?
       Mark out of 10:  10
   2. Did I use easy-to-understand, meaningful CONSTANT names?
       Mark out of 5:  4
    3. Did I format the CONSTANT names properly (in UPPERCASE)?
       Mark out of 5:  5
   4. Did I use easy-to-understand meaningful variable names?
       Mark out of 10:  8
   5. Did I format the variable names properly (in lowerCamelCase)?
       Mark out of 10:  10
   6. Did I indent the code appropriately?
       Mark out of 10:  9
   7. Did I read the input correctly from the user using (an) appropriate question(s)?
       Mark out of 10:  8
   8. Did I compute the answer correctly for all cases?
       Mark out of 20:  19
   9. Did I output the correct answer in the correct format (as shown in the examples)?
       Mark out of 10:  10
   10. How well did I complete this self-assessment?
       Mark out of 10:  8
   Total Mark out of 100 (Add all the previous marks):  91
*/

public class InflationCalculator{
	
	public static final NumberFormat POUND_FORMAT = NumberFormat.getCurrencyInstance(Locale.UK);
	public static final int	OLD_PENCE_IN_NEW_PENCE = 67; 
	public static final int OLD_PENCE_IN_OLD_SHILLINGS = 12;	
	public static final int OLD_SHILLINGS_IN_OLD_POUNDS = 20;
	public static final int NEW_PENCE_IN_NEW_POUND = 100;

	public static void main(String[] args){
		 
		System.out.print("Plese enter the amount of money you want to convert (pounds shillings pence): ");
		Scanner inputScanner = new Scanner (System.in);
		int oldPounds = inputScanner.nextInt();
		int oldShillings = inputScanner.nextInt();
		int oldPence = inputScanner.nextInt();
		inputScanner.close();
			
		int oldPenceInNewPence = oldPence * OLD_PENCE_IN_NEW_PENCE;
		int oldShillingsInNewPence = oldShillings * OLD_PENCE_IN_OLD_SHILLINGS * OLD_PENCE_IN_NEW_PENCE;
		int oldPoundsInNewPence = oldPounds * OLD_SHILLINGS_IN_OLD_POUNDS * OLD_PENCE_IN_OLD_SHILLINGS * OLD_PENCE_IN_NEW_PENCE;
		double newPennySum = oldPenceInNewPence + oldShillingsInNewPence + oldPoundsInNewPence;
	
		String finalConversion = POUND_FORMAT.format(newPennySum / 100);

		System.out.println(oldPounds + " old pound, " + oldShillings + " old shilling and " + oldPence + " old pence = " + finalConversion);
	}	
}

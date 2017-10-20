/* SELF ASSESSMENT
   1. Did I use appropriate CONSTANTS instead of numbers within the code?
       Mark out of 5: 5
   2. Did I use easy-to-understand, meaningful CONSTANT names?
       Mark out of 5: 5
   3. Did I format the CONSTANT names properly (in UPPERCASE)?
       Mark out of 5: 5
   4. Did I use easy-to-understand meaningful variable names?
       Mark out of 10: 5
   5. Did I format the variable names properly (in lowerCamelCase)?
       Mark out of 10: 10
   6. Did I indent the code appropriately?
       Mark out of 10: 10
   7. Did I use an appropriate for loop to test all possibilities?  There should be only one.
       Mark out of 20:  20
   8. Did I correctly check if people alive today were or could be alive in a year that is the square of their age in that year.
       Mark out of 30:  30
   9. How well did I complete this self-assessment?
       Mark out of 5: 5
   Total Mark out of 100 (Add all the previous marks): 100
*/

import javax.swing.JOptionPane;

public class SquareAges
{
    public static final int CURRENT_YEAR = 2017;
    public static final int MAX_AGE = 123;

    public static void main(String[] args)
    {
    	for(int age = 0; age <= MAX_AGE; age++)
    	{
    		int squaredAge = age * age;
    		if(age + Math.abs(squaredAge - CURRENT_YEAR) <= MAX_AGE  && age + Math.abs(squaredAge - CURRENT_YEAR) >= 0)
    		{
    			System.out.println(age + " in " + squaredAge);
    		}
    	}
    }
}

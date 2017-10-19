//import java.util.Scanner;
import javax.swing.JOptionPane;

public class SquareAges
{
	public static final int CURRENT_YEAR = 2017;
    public static final int MAX_AGE = 123;

    public static void main(String[] args)
    {
    	int maxYear = CURRENT_YEAR + MAX_AGE;
    	int minYear = CURRENT_YEAR - MAX_AGE;
    	
        for(int age = 1; age <= MAX_AGE; age++)
        {
            int squaredAge = age * age;
            if(squaredAge <= (maxYear - age) && squaredAge >= (minYear + age))
            {
              JOptionPane.showMessageDialog(null, "If you are age " + age + ", it is possible you will be alive in the year "
              														+ "that is your age squared. This year is " + squaredAge);
            }
        }
    }
}

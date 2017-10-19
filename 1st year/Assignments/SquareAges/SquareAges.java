//import java.util.Scanner;
import javax.swing.JOptionPane;

public class SquareAges
{
    public static final int MIN_YEAR = 1894;
    public static final int MAX_YEAR = 2140;

    public static void main(String[] args)
    {
        for(int age = 1; age <= 123; age++)
        {
            int squaredAge = age * age;
            if(squaredAge <= (MAX_YEAR - age) && squaredAge >= (MIN_YEAR + age))
            {
              JOptionPane.showMessageDialog(null, "If you are age " + age + ", it is possible you will be alive in the year that is your age squared. This year is " + squaredAge);
            }
        }
    }
}

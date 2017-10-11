import java.util.Scanner;

public class BMI {
	public static void main(String[] Args) {
		System.out.println("Wlcome, This is a BMI calculator. Please enter your weight: ");
		Scanner inputScanner = new Scanner(System.in);
		double weight = inputScanner.nextDouble();
		System.out.println("Now enter your height: ");
		double height = inputScanner.nextDouble();
		inputScanner.close();
		
		double bmi = weight / (height * height);
		
		System.out.println("Your BMI is " + bmi);
	}
}

import javax.swing.JOptionPane;
import java.util.Scanner;

public class BMIGraphical {
	public static void main(String[] Args) {
		String weightInput = JOptionPane.showInputDialog("What is your weight:");
		Scanner weightScanner = new Scanner(weightInput);
		double weight = weightScanner.nextDouble();
		
		String heightInput = JOptionPane.showInputDialog("What is your height:");
		Scanner heightScanner = new Scanner(heightInput);
		double height = heightScanner.nextDouble();
		
		weightScanner.close();
		heightScanner.close();
		
		double bmi = weight / (height * height);
		
		JOptionPane.showMessageDialog(null, "Your BMI is " + bmi);
	}
}

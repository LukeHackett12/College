import javax.swing.JOptionPane;

/* SELF ASSESSMENT 
1. Did I use easy-to-understand meaningful variable names? 
    Mark out of 10: 10
2. Did I format the variable names properly (in lowerCamelCase)? 
    Mark out of 10: 10
3. Did I indent the code appropriately? 
    Mark out of 10: 10
4. Did I read the input correctly from the user using appropriate questions? 
    Mark out of 20: 20
5. Did I use an appropriate (i.e. not more than necessary) series of if statements? 
    Mark out of 30: 30
6. Did I output the correct answer for each possibility in an easy to read format? 
    Mark out of 15: 15
7. How well did I complete this self-assessment? 
    Mark out of 5: 5
Total Mark out of 100 (Add all the previous marks): 100
*/

public class Umbrella {
	public static void main(String[] args) {
		
		int answer = JOptionPane.showConfirmDialog(null, "Is it raining or does it look like it is going to rain? ");
		boolean rainInbound = (answer == JOptionPane.YES_OPTION);
		
		if(rainInbound) {
			answer = JOptionPane.showConfirmDialog(null, "OK you should bring an umbrella, is it currently raining? ");
			boolean raining = (answer == JOptionPane.YES_OPTION);
			
			if(raining)
				JOptionPane.showMessageDialog(null, "Open your umbrella!");
			else
				JOptionPane.showMessageDialog(null, "You don't need to open your umbrella just yet.");
		} else 
			JOptionPane.showMessageDialog(null, "You don't need to bring an umbrella.");
	}
}

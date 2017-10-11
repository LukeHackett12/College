import javax.swing.JOptionPane;

public class multipleChoice {
	public static void main(String[] args){
		String vertebrate = "";
		
		int answer = JOptionPane.showConfirmDialog(null, "Is the vertebrate warm blooded? ");
		boolean warmBlooded = (answer == JOptionPane.YES_OPTION);

		if(warmBlooded){
			answer = JOptionPane.showConfirmDialog(null, "Is the vertebrate hairy/furry? ");
			boolean hairFur = (answer == JOptionPane.YES_OPTION);

			if(hairFur)
				vertebrate = "mammal";
			else
				vertebrate = "bird";
		} else {
			answer = JOptionPane.showConfirmDialog(null, "Does it have fins? ");
			boolean finned = (answer == JOptionPane.YES_OPTION);
			
			if(finned)
				vertebrate = "fish";
			else {
				answer = JOptionPane.showConfirmDialog(null, "Is it scaled? ");
				boolean scaled = (answer == JOptionPane.YES_OPTION);
				
				if(scaled)
					vertebrate = "reptile";
				else
					vertebrate = "amphibian";
			}
		}

		JOptionPane.showDialog(null, "The vertebrate is a " + vertebrate);
	}
}

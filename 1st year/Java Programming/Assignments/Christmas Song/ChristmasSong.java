/* SELF ASSESSMENT
   1. Did I use appropriate CONSTANTS instead of numbers within the code?
       Mark out of 5:5
       I made a constant for each constant value as stated in the coding standard
   2. Did I use easy-to-understand, meaningful CONSTANT names formatted correctly in UPPERCASE?
       Mark out of 5:5
       Constant values are all named indicating exactly what they are
   3. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?
       Mark out of 10:10
       All variable names are meaningful and formatted properly
  4. Did I indent the code appropriately?
       Mark out of 10:10
       All code indented when appropriate
   5. Did I use an appropriate loop (or loops) to produce the different verses?
       Mark out of 20:20
       There is a nested loop that produces the verses in the correct order
   6. Did I use a switch to build up the verses?
       Mark out of 25:25
       I use switch statements to determine the day and teh presents that should be printed
   7. Did I avoid duplication of code and of the lines which make up the verses (each line should be referred to in the code only once (or twice))?
       Mark out of 10:10
       I only typed each line of the song once.
   8. Does the program produce the correct output?
       Mark out of 10:10
       The program produces the song
   9. How well did I complete this self-assessment?
       Mark out of 5:5
       I gave a rational argument for each point
   Total Mark out of 100 (Add all the previous marks):100
*/

public class ChristmasSong {

    public static final String FIRST = " first ";
    public static final String SECOND = " second ";
    public static final String THIRD = " third ";
    public static final String FOURTH = " fourth ";
    public static final String FIFTH = " fifth ";
    public static final String SIXTH = " sixth ";
    public static final String SEVENTH = " seventh ";
    public static final String EIGHTH = " eighth ";
    public static final String NINTH = " ninth ";
    public static final String TENTH = " tenth ";
    public static final String ELEVENTH = " eleventh ";
    public static final String TWELVTH = " twelvth ";

    public static final String DAY1 = "a Partridge in a Pear Tree";
    public static final String DAY2 = "Two Turtle Doves";
    public static final String DAY3 = "Three French Hens,";
    public static final String DAY4 = "Four Calling Birds,";
    public static final String DAY5 = "Five Gold Rings,";
    public static final String DAY6 = "Six Geese a-Laying,";
    public static final String DAY7 = "Seven Swans a-Swimming,";
    public static final String DAY8 = "Eight Maids a-Milking,";
    public static final String DAY9 = "Nine Ladies Dancing,";
    public static final String DAY10 = "Ten Lords a-Leaping,";
    public static final String DAY11 = "Eleven Pipers Piping,";
    public static final String DAY12 = "Twelve Drummers Drumming,";

    public static void main(String[] args){
        for(int dayCount = 0; dayCount < 12; dayCount++){
            String day = " ";
            switch(dayCount){
                case 0: day = FIRST;
                        break;
                case 1: day = SECOND;
                        break;
                case 2: day = THIRD;
                        break;
                case 3: day = FOURTH;
                        break;
                case 4: day = FIFTH;
                        break;
                case 5: day = SIXTH;
                        break;
                case 6: day = SEVENTH;
                        break;
                case 7: day = EIGHTH;
                        break;
                case 8: day = NINTH;
                        break;
                case 9: day = TENTH;
                        break;
                case 10: day = ELEVENTH;
                        break;
                case 11: day = TWELVTH;
                        break;
            }

            System.out.println("On the" + day  + "day of Christmas");
            System.out.println("my true love sent to me: ");
            for(int presentCount = dayCount; presentCount >= 0; presentCount--){
                String present = " ";
                switch(presentCount){
                    case 11:present = DAY12;
                            break;
                    case 10: present = DAY11;
                            break;
                    case 9: present = DAY10;
                            break;
                    case 8: present = DAY9;
                            break;
                    case 7: present = DAY8;
                            break;
                    case 6: present = DAY7;
                            break;
                    case 5: present = DAY6;
                            break;
                    case 4: present = DAY5;
                            break;
                    case 3: present = DAY4;
                            break;
                    case 2: present = DAY3;
                            break;
                    case 1: present = DAY2;
                            break;
                    case 0: if(dayCount == 0)
                                present = DAY1;
                            else
                                present = "and " + DAY1;

                            break;
                }
                System.out.println(present);
            }
            System.out.println();
        }
    }
}

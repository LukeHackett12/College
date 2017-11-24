/* SELF ASSESSMENT
 1. Did I use appropriate, easy-to-understand, meaningful CONSTANT names formatted correctly in UPPERCASE?
        Mark out of 5: 5
        All the constants I used were taken from the demo code, so were named and formatted.
 2. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?
        Mark out of 5: 5
        I named my variables meaningfully and formatted correctly.
 3. Did I indent the code appropriately?
        Mark out of 5: 5
        Indented every statement appropriately
 4. Did I define the required function correctly (names, parameters & return type) and invoke them correctly?
       Mark out of 20: 20
       Defined and invoked functions in appropriate places.
 5. Did I implement the dayOfTheWeek function correctly and in a manner that can be understood?
       Mark out of 20: 20
       dayOfTheWeek is easy to understand and works correctly
 6. Did I implement the other functions correctly, giving credit for any code that you take from elsewhere?
       Mark out of 20: 20
       Gave credit for code that finds if the date is valid, also implemented numberEnding and monthName correctly
 7. Did I obtain (and process) the input from the user in the correct format (dd/mm/yyyy), and deal with any invalid input properly?
       Mark out of 10: 10
       Input is thouroughly checked and dealt with appropriately
 8. Does the program produce the output in the correct format (e.g. Monday, 25th December 2017)?
       Mark out of 10: 10
       Program prints out the date in words as asked for.
 9. How well did I complete this self-assessment?
        Mark out of 5: 5
        Commented on all parts with rational
 Total Mark out of 100 (Add all the previous marks): 100
*/

import java.util.Scanner;

public class DayOfTheWeek{

    public static final int DAYS_IN_APRIL_JUNE_SEPT_NOV = 30;
	public static final int DAYS_IN_FEBRUARY_NORMALLY = 28;
	public static final int DAYS_IN_FEBRUARY_IN_LEAP_YEAR = 29;
	public static final int DAYS_IN_MOST_MONTHS = 31;
	public static final int NUMBER_OF_MONTHS = 12;

    public static void main(String[] args){

        boolean quit = false;
        Scanner input = new Scanner(System.in);

        while(!quit){

            String date = " ";
            boolean isValid = false;
            while(!isValid){
                System.out.print("What date would you like to wordify(dd/mm/yyyy or 'quit' to exit)? ");
                date = input.nextLine();

                char[] dateChars = date.toCharArray();
                if(dateChars[0] == 'q' || dateChars[0] == 'e'){
                    quit = true;
                    isValid = true;
                }
                else if(dateChars[2] == '/' && dateChars[5] == '/' && dateChars.length > 6){
                    if(Character.isDigit(dateChars[0]) && Character.isDigit(dateChars[1])){
                        if(Character.isDigit(dateChars[3]) && Character.isDigit(dateChars[4])){
                            for(int count = 6; count < dateChars.length; count++){
                                if(!Character.isDigit(dateChars[count])){
                                    isValid = false;
                                    count = dateChars.length;
                                }
                                else {
                                    isValid = true;
                                }
                            }
                        }
                    }
                }
                else{
                    System.out.println("Sorry that was not in the correct format, please try again.");
                }
            }

            Scanner dateScanner = new Scanner(date);
            dateScanner.useDelimiter("/");

            int day = 0;
            int month = 0;
            int year = 0;

            if(dateScanner.hasNextInt()){
                day = dateScanner.nextInt();
            }

            if(dateScanner.hasNextInt()){
                month = dateScanner.nextInt();
            }

            if(dateScanner.hasNextInt()){
                year = dateScanner.nextInt();
            }
            dateScanner.close();

            if(quit){
                System.out.println("Goodbye!");
            }
            else if(validDate(day, month, year)){
                String numberEnding = numberEnding(day);
                String monthName = monthName(month);
                String dayOfTheWeek = dayOfTheWeek(day, month, year);

                System.out.println(dayOfTheWeek + ", " + day + numberEnding + " "  + monthName + " " + year);
            }
            else {
                System.out.println("Sorry that was not a valid date, please try again.");
            }
        }

        input.close();
    }

    public static String numberEnding(int dayNumber){
    	int dayNumberEnding = dayNumber % 10;

    	String numberEnding = " ";

        if(dayNumberEnding == 1 && dayNumber != 11) {
        	numberEnding = "st";
        }
        else if(dayNumber == 2) {
        	numberEnding = "nd";
        }
        else if(dayNumber == 3) {
        	numberEnding = "rd";
        }
        else {
        	numberEnding = "th";
        }

        return numberEnding;
    }

    public static String monthName(int monthNumber){
        String monthName = " ";

        if(monthNumber == 1) {
        	monthName = "January";
        }
        else if(monthNumber == 2) {
        	monthName = "February";
        }
		else if(monthNumber == 3) {
			monthName = "March";
    	}
		else if(monthNumber == 4) {
			monthName = "April";
		}
		else if(monthNumber == 5) {
			monthName = "May";
		}
		else if(monthNumber == 6) {
			monthName = "June";
		}
		else if(monthNumber == 7) {
			monthName = "July";
		}
		else if(monthNumber == 8) {
			monthName = "August";
		}
		else if(monthNumber == 9) {
			monthName = "September";
		}
		else if(monthNumber == 10) {
			monthName = "October";
		}
		else if(monthNumber == 11) {
			monthName = "November";
		}
		else if(monthNumber == 12) {
			monthName = "December";
		}

        return monthName;
    }

    public static String dayOfTheWeek(int dayNumber, int monthNumber, int yearNumber){
        if(monthNumber == 1 || monthNumber == 2)
            yearNumber--;

        int yearHalfOne = 0;
        int yearHalfTwo = 0;

        if(yearNumber <= 99){
            yearHalfOne = 0;
            yearHalfTwo = yearNumber;
        }
        else{
            yearHalfOne = (int)Math.floor(yearNumber / 100);
            yearHalfTwo = yearNumber % 100;
        }

        int day = (int) Math.abs((dayNumber + Math.floor(2.6 * (((monthNumber + 9) % 12) + 1) - 0.2) + yearHalfTwo + Math.floor(yearHalfTwo/4) + Math.floor(yearHalfOne/4) - 2*yearHalfOne) % 7);

        String dayOfTheWeek = " ";
        if(day == 1){
            dayOfTheWeek = "Monday";
        }
        else if(day == 2){
            dayOfTheWeek = "Tuesday";
        }
        else if(day == 3){
            dayOfTheWeek = "Wednesday";
        }
        else if(day == 4){
            dayOfTheWeek = "Thursday";
        }
        else if(day == 5){
            dayOfTheWeek = "Friday";
        }
        else if(day == 6){
            dayOfTheWeek = "Saturday";
        }
        else if(day == 0){
            dayOfTheWeek = "Sunday";
        }
        else{
            dayOfTheWeek = ":(";
        }

        return dayOfTheWeek;
    }

//Start Demo Code - Obtained from ValidDate.java on Blackboard
    public static boolean validDate( int day, int month, int year) {
    	return ((year >= 0) && (month >= 1) && (month <= NUMBER_OF_MONTHS) &&
    			(day >= 1) && (day <= daysInMonth( month, year )));
    }

    public static int daysInMonth( int month, int year ) {
    	int numberOfDaysInMonth = DAYS_IN_MOST_MONTHS;
    	switch (month)
    	{
    	case 2:
    		numberOfDaysInMonth = isLeapYear( year ) ? DAYS_IN_FEBRUARY_IN_LEAP_YEAR :
    			                                       DAYS_IN_FEBRUARY_NORMALLY;
    		break;
    	case 4:
    	case 6:
    	case 9:
    	case 11:
    		numberOfDaysInMonth = DAYS_IN_APRIL_JUNE_SEPT_NOV;
    		break;
    	default:
    		numberOfDaysInMonth = DAYS_IN_MOST_MONTHS;
    		break;
    	}
    	return numberOfDaysInMonth;
    }

    public static boolean isLeapYear( int year ) {
		return (((year%4 == 0) && (year%100 != 0)) || (year%400 == 0));
	}
//End Demo Code
}

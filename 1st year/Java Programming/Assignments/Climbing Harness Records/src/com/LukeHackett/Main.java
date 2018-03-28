/* SELF ASSESSMENT

Harness Class: Member variables (8 marks) 8
All my data members are declared, private and the ones that don't change are marked as private. I also have a constant for the maximum number of uses of a harness.
Comment:All data is declared private and are accessed with getters and setters. Have a constant with a max of 10 uses defined.

Harness Class: Harness constructor 1 & constructor 2 (6 marks) 6
I initialise all the variables using the parameters given and set the other members to reasonable default values.
Comment: Define all variables as asked in constructor 1 and constructor two takes the required arguments. Initialise all values

Harness Class: checkHarness method (5 marks) 5
My method takes an instructor's name as a parameter, and if the harness is not on loan sets the instructor member variable to the given parameter value (assuming the instructor's name is not null/empty). It also resets the number of times the harness was used.
Comment: Takes an instructor and the instructor checks the harness not on loan.

Harness Class: isHarnessOnLoan method (2 marks) 2
My method has no parameters and returns the value of the loan status variable.
Comment: returns if the harness is on loan or not

Harness Class: canHarnessBeLoaned method (4 marks) 4
My method has no parameters and returns true if the harness is not on loan and if the number of times it was used is less than the maximum allowed number of times.
Comment: says if the harness can be loaned based on the number of uses and availability;

Harness Class: loanHarness method (6 marks) 6
My method has a member name as a parameter and it checks if harness can be loaned by using the canHarnessBeLoaned method. If true, it sets the club member value to the parameter value, sets the on loan status to true and increments the number of times used variable.
Comment: loans the harness if it can be loaned

Harness Class: returnHarness method (5 marks) 5
My method has no parameters, checks if the harness is on loan, and if so, changes its on-loan status to false, and resets the club member value.
Comment: returns harness if it is on loan

Harness Class: toString method (3 marks) 3
My method returns a String representation of all the member variables.
Comment: returns a string of all the variables

HarnessRecords Class: member variables (3 marks) 3
I declare the member variable as a private collection of Harnesses
Comment: declared a private arraylist of harnesses

HarnessRecords Class: HarnessRecords constructor 1 & 2 (9 marks) 9
In the first constructor, I set the member variable to null [1 mark]. In the second constructor, I use the Java I/O to read data from a text file I created containing sets of Harness characteristics. I use these set of characteristics to create Harness objects and add them to the collection.
Comment: constructor one makes an empty list and read from a file with space delimiters for constructor two.

HarnessRecords Class: isEmpty method (1 marks) 1
I return true if the collection is null/empty and, false otherwise.
Comment: returns whether the arraylist is empty or not.

HarnessRecords Class: addHarness method (5 marks) 5
My method takes a Harness object as a parameter and adds the harness to the collection.
Comment: adds a harness to the arraylist with whatever constructor you want

HarnessRecords Class: findHarness method (6 marks) 6
My method takes a make and model number as parameters. It checks if a harness with such properties exists and if it does it returns harness object, otherwise returns null.
Comment: finds a harness of the model number and make requested.

HarnessRecords Class: checkHarness method (6 marks) 6
must take instructor name, make and model number as parameters and return a Harness. If a harness with the make and model number exists by using the findHarness method and is not on loan, the Harness method checkHarness is called with the instructor name as a parameter and the updated Harness object is returned. If the harness is not available returns null.
Comment: checks a harness if it exists and not loaned and resets the uses.

HarnessRecords Class: loanHarness method (7 marks) 7
My method takes a club member name as a parameter and looks for an available harness by calling the method canHarnessBeLoaned be loaned. If an available harness is found it is loaned by using the Harness method loanHarness with the club member as a parameter, returning the harness. If there's no available harness null is returned.
Comment: loans a harness to a club member based on if any are available.

HarnessRecords Class: returnHarness method (7 marks) 7
My method takes a make and model number as parameters. It checks if a harness with those properties exists by using the findHarness method. If the found harness is not null, it returns the harness object by using Harness method returnHarness, otherwise returns null.
Comment: returns a harness with the make and model if it is marked as being on loan.

HarnessRecords Class: removeHarness method (8 marks) 8
My method takes a make and model number as parameters and check the collection for a harness with those properties and removes it. It returns the harness object if it is found, otherwise returns null.
Comment: removes a harness of given make and modelNo

GUI (Java main line) (5 marks) 5
My test class has a menu which implements at least the five points specified using the HarnessRecords class methods.
Comment: Implements the 5 sample points in a JOption pane GUI

*/

package com.LukeHackett;

import javax.swing.*;

public class Main {

    public static final int HOME_SCREEN = 0;
    public static final int ADD_SCREEN = 1;
    public static final int REMOVE_SCREEN = 2;
    public static final int RECORD_SCREEN = 3;
    public static final int LOAN_SCREEN = 4;
    public static final int RETURN_SCREEN = 5;

    public static JFrame frame;
    public static int currentScreen = HOME_SCREEN;
    public static HarnessRecords harnessRecords;

    public static void main(String[] args) {
        harnessRecords = new HarnessRecords();

        boolean done = false;
        draw(currentScreen);
    }

    private static void draw(int screen) {
        switch (screen) {
            case HOME_SCREEN:
                homeScreen();
                break;
            case ADD_SCREEN:
                addScreen();
                break;
            case REMOVE_SCREEN:
                removeScreen();
                break;
            case RECORD_SCREEN:
                recordScreen();
                break;
            case LOAN_SCREEN:
                loanScreen();
                break;
            case RETURN_SCREEN:
                returnScreen();
        }
    }

    private static void homeScreen() {
        String[] possibilities = {"Add a Harness", "Remove a Harness", "Check safety", "Loan a Harness", "Return a Harness"};
        String s = (String)JOptionPane.showInputDialog(
                frame,
                "What would you like to do: ",
                "Climbing Harness Record System",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "Add a Harness");

        int count = 1;
        for(String p : possibilities){
            if(p.equals(s)) currentScreen = count;
            count++;
        }
        if(currentScreen != HOME_SCREEN)
            draw(currentScreen);
    }

    private static void addScreen() {
        System.out.println("--- ADD HARNESS ---");

        JTextField make = new JTextField(5);
        JTextField model = new JTextField(5);
        JTextField instructor = new JTextField(5);
        Object[] fields = {
                "Harness make:", make,
                "Harness model No:", model,
                "Last Instructor:", instructor
        };

        int result = JOptionPane.showConfirmDialog(null, fields,
                "Add Harness", JOptionPane.OK_CANCEL_OPTION);

        if(result == JOptionPane.OK_OPTION){
            harnessRecords.addHarness(new Harness(make.getText(), Integer.valueOf(model.getText()), instructor.getText()));
        }

        JOptionPane.showMessageDialog(null, "Added Harnesss Successfully!");
        currentScreen = HOME_SCREEN;
        draw(currentScreen);
    }

    private static void removeScreen() {
        System.out.println("--- REMOVE CHECK ---");

        JTextField make = new JTextField(5);
        JTextField model = new JTextField(5);
        Object[] fields = {
                "Harness make:", make,
                "Harness model No:", model,
        };

        int result = JOptionPane.showConfirmDialog(null, fields,
                "Add Harness", JOptionPane.OK_CANCEL_OPTION);

        boolean success = false;
        if(result == JOptionPane.OK_OPTION){
            success = harnessRecords.removeHarness(make.getText(), Integer.valueOf(model.getText()));
        }

        if(success){
            JOptionPane.showMessageDialog(null, "Successfully Removed Harness!");
        } else {
            JOptionPane.showMessageDialog(null, "Couldn't Remove Harness!");
        }
        currentScreen = HOME_SCREEN;
        draw(currentScreen);
    }

    private static void recordScreen() {
        System.out.println("--- RECORD HARNESS CHECK ---");

        JTextField make = new JTextField(5);
        JTextField model = new JTextField(5);
        JTextField instructor = new JTextField(5);
        Object[] fields = {
                "Harness make:", make,
                "Harness model No:", model,
                "Instructor that checked safety:", instructor
        };

        int result = JOptionPane.showConfirmDialog(null, fields,
                "Record Harness Safety", JOptionPane.OK_CANCEL_OPTION);

        boolean success = false;
        if(result == JOptionPane.OK_OPTION){
            success = harnessRecords.checkHarness(instructor.getText(), make.getText(), Integer.valueOf(model.getText()));
        }

        if(success){
            JOptionPane.showMessageDialog(null, "Successfully Recorded Check!");
        } else {
            JOptionPane.showMessageDialog(null, "Couldn't add Check!");
        }
        currentScreen = HOME_SCREEN;
        draw(currentScreen);
    }

    private static void loanScreen(){
        System.out.println("--- RECORD HARNESS CHECK ---");

        JTextField user = new JTextField(5);
        Object[] fields = {
                "Who is loaning the harness:", user
        };

        int result = JOptionPane.showConfirmDialog(null, fields,
                "Record Harness Safety", JOptionPane.OK_CANCEL_OPTION);

        boolean success = false;
        if(result == JOptionPane.OK_OPTION){
            success = harnessRecords.loanHarness(user.getText());
        }

        if(success){
            JOptionPane.showMessageDialog(null, "Successfully Loaned to user!");
        } else {
            JOptionPane.showMessageDialog(null, "Couldn't Loan!");
        }
        currentScreen = HOME_SCREEN;
        draw(currentScreen);
    }

    private static void returnScreen() {
        System.out.println("--- RETURNED HARNESS ---");

        JTextField make = new JTextField(5);
        JTextField model = new JTextField(5);
        Object[] fields = {
                "Harness make:", make,
                "Harness model No:", model,
        };

        int result = JOptionPane.showConfirmDialog(null, fields,
                "Add Harness", JOptionPane.OK_CANCEL_OPTION);

        boolean success = false;
        if(result == JOptionPane.OK_OPTION){
            success = harnessRecords.returnHarness(make.getText(), Integer.valueOf(model.getText()));
        }

        if(success){
            JOptionPane.showMessageDialog(null, "Successfully Returned Harness!");
        } else {
            JOptionPane.showMessageDialog(null, "Couldn't Returned Harness!");
        }
        currentScreen = HOME_SCREEN;
        draw(currentScreen);
    }
}

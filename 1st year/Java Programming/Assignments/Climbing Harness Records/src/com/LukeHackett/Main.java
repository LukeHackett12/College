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

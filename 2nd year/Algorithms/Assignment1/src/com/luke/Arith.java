package com.luke;
// -------------------------------------------------------------------------

import java.util.Stack;

/**
 * Utility class containing validation/evaluation/conversion operations
 * for prefix and postfix arithmetic expressions.
 *
 * @author
 * @version 1/12/15 13:03:48
 */

public class Arith {
    //~ Validation methods ..........................................................

    /**
     * Validation method for prefix notation.
     *
     * @param prefixLiterals : an array containing the string literals hopefully in prefix order.
     *                       The method assumes that each of these literals can be one of:
     *                       - "+", "-", "*", or "/"
     *                       - or a valid string representation of an integer.
     * @return true if the parameter is indeed in prefix notation, and false otherwise.
     **/
    public static boolean validatePrefixOrder(String prefixLiterals[]) {
        boolean atNumbers = false;
        int count = 0;
        for (String string : prefixLiterals) {
            if (!string.matches("-?\\d+")) {
                if (isOperator(string)) {
                    count++;
                } else {
                    return false;
                }
                if (atNumbers) return false;
            } else {
                atNumbers = true;
                count--;

            }
        }

        return (count == -1);
    }

    /**
     * Validation method for postfix notation.
     *
     * @param postfixLiterals : an array containing the string literals hopefully in postfix order.
     *                        The method assumes that each of these literals can be one of:
     *                        - "+", "-", "*", or "/"
     *                        - or a valid string representation of an integer.
     * @return true if the parameter is indeed in postfix notation, and false otherwise.
     **/
    public static boolean validatePostfixOrder(String postfixLiterals[]) {
        Integer count = 0;
        for (String string : postfixLiterals) {
            if (string.matches("-?\\d+")) {
                count++;
            } else {
                if (isOperator(string)) {
                    count--;
                } else {
                    return false;
                }
            }

            if (count < 0) return false;
        }

        return (count == 1);
    }

    private static boolean isOperator(String string) {
        if (string == "/" ||
                string == "*" ||
                string == "^" ||
                string == "+" ||
                string == "-") {
            return true;
        }

        return false;
    }

    //~ Evaluation  methods ..........................................................

    /**
     * Evaluation method for prefix notation.
     *
     * @param prefixLiterals : an array containing the string literals in prefix order.
     *                       The method assumes that each of these literals can be one of:
     *                       - "+", "-", "*", or "/"
     *                       - or a valid string representation of an integer.
     * @return the integer result of evaluating the expression
     **/
    public static int evaluatePrefixOrder(String prefixLiterals[]) {
        if (validatePrefixOrder(prefixLiterals)) {
            Stack<String> operators = new Stack<>();

            String trailingString = null;

            for (String string : prefixLiterals) {
                if (isOperator(string)) {
                    operators.push(string);
                } else {
                    if (trailingString == null) {
                        trailingString = string;
                    } else {
                        trailingString = String.valueOf(performOperation(operators.pop(), Integer.parseInt(string), Integer.parseInt(trailingString)));
                    }
                }
            }

            assert trailingString != null;
            return Integer.parseInt(trailingString);
        }
        return 0;
    }

    /**
     * Evaluation method for postfix notation.
     *
     * @param postfixLiterals : an array containing the string literals in postfix order.
     *                        The method assumes that each of these literals can be one of:
     *                        - "+", "-", "*", or "/"
     *                        - or a valid string representation of an integer.
     * @return the integer result of evaluating the expression
     **/
    public static int evaluatePostfixOrder(String postfixLiterals[]) {
        if (validatePostfixOrder(postfixLiterals)) {

            Stack<String> stack = new Stack<>();
            for (String string : postfixLiterals) {
                if (string.matches("-?\\d+")) {
                    stack.push(string);
                } else if (isOperator(string)) {
                    int one = Integer.parseInt(stack.pop());
                    int two = Integer.parseInt(stack.pop());

                    int result = performOperation(string, one, two);
                    stack.push(String.valueOf(result));
                }
            }

            return Integer.parseInt(stack.pop());
        }
        return -1;
    }

    private static int performOperation(String string, int one, int two) {
        switch (string) {
            case "^":
                return ((int) Math.pow(two, one));
            case "*":
                return (one * two);
            case "/":
                return (two / one);
            case "+":
                return (one + two);
            case "-":
                return (two - one);
        }

        return 0;
    }

    //~ Conversion  methods ..........................................................

    /**
     * Converts prefix to postfix.
     *
     * @param prefixLiterals : an array containing the string literals in prefix order.
     *                       The method assumes that each of these literals can be one of:
     *                       - "+", "-", "*", or "/"
     *                       - or a valid string representation of an integer.
     * @return the expression in postfix order.
     **/
    public static String[] convertPrefixToPostfix(String prefixLiterals[]) {
        
        return new String[0];
    }

    /**
     * Converts postfix to prefix.
     *
     * @param postfixLiterals : an array containing the string literals in postfix order.
     *                       The method assumes that each of these literals can be one of:
     *                       - "+", "-", "*", or "/"
     *                       - or a valid string representation of an integer.
     * @return the expression in prefix order.
     **/
    public static String[] convertPostfixToPrefix(String postfixLiterals[]) {
        return new String[2];
        //TODO
    }

    /**
     * Converts prefix to infix.
     *
     * @param prefixLiterals : an array containing the string literals in prefix order.
     *                      The method assumes that each of these literals can be one of:
     *                      - "+", "-", "*", or "/"
     *                      - or a valid string representation of an integer.
     * @return the expression in infix order.
     **/
    public static String[] convertPrefixToInfix(String prefixLiterals[]) {
        return new String[2];
        //TODOargs
    }

    /**
     * Converts postfix to infix.
     *
     * @param postfixLiterals : an array containing the string literals in postfix order.
     *                      The method assumes that each of these literals can be one of:
     *                      - "+", "-", "*", or "/"
     *                      - or a valid string representation of an integer.
     * @return the expression in infix order.
     **/
    public static String[] convertPostfixToInfix(String postfixLiterals[]) {
        return new String[2];
        //TODO
    }
}

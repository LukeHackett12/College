package com.luke;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.*;

public class ArithTest{

    @Test
    public void testPostfixValidation(){
        String args[] = {"1", "2", "-", "2", "+"};
        assertTrue("Valid postfix", Arith.validatePostfixOrder(args));

        args = new String[]{"1", "asddas", "-", "2", "+"};
        assertFalse("Valid postfix", Arith.validatePostfixOrder(args));

        args = new String[]{"1", "2", "-", "2", "2"};
        assertFalse("Valid postfix", Arith.validatePostfixOrder(args));

        args = new String[]{"3", "1", "-", "asd", "2"};
        assertFalse("Valid prefix", Arith.validatePrefixOrder(args));
    }

    @Test
    public void testPrefixValidation(){
        String args[] = {"-", "5", "*", "6", "7"};
        assertTrue("Valid prefix", Arith.validatePrefixOrder(args));

        args = new String[]{"*", "-", "1", "2", "3"};
        assertTrue("Valid prefix", Arith.validatePrefixOrder(args));

        args = new String[]{"-", "9", "2", "-", "2"};
        assertFalse("Invalid prefix", Arith.validatePrefixOrder(args));

        args = new String[]{"-", "9", "9", "2", "2"};
        assertFalse("Invalid prefix", Arith.validatePrefixOrder(args));

        args = new String[]{"-", "-", "9", "asd", "2"};
        assertFalse("Invalid prefix", Arith.validatePrefixOrder(args));
    }

    @Test
    public void testPostfixEvaluate(){
        String args[] = {"2", "2", "+", "6", "-"};
        int result = Arith.evaluatePostfixOrder(args);
        assertEquals("Postfix simple case", -2, result);

        args = new String[]{"2", "2", "1", "6", "-"};
        result = Arith.evaluatePostfixOrder(args);
        assertEquals("Postfix simple case", -1, result);

        args = new String[]{"2", "6", "+", "6", "/"};
        result = Arith.evaluatePostfixOrder(args);
        assertEquals("Postfix division rounding case", 1, result);
    }

    @Test
    public void testPrefixEvaluate(){
        String args[] = {"-", "-", "2", "2", "2"};
        assertEquals("Added prefix", -2,Arith.evaluatePrefixOrder(args));

        args = new String[]{"-", "234", "2", "+", "2"};
        assertEquals("Added prefix", 0,Arith.evaluatePrefixOrder(args));

        args = new String[]{"*", "-", "1", "2", "3"};
        assertEquals("Added prefix", 5,Arith.evaluatePrefixOrder(args));

        args = new String[]{"-", "5", "*", "6", "7"};
        assertEquals("Added prefix", -37,Arith.evaluatePrefixOrder(args));
    }

    @Test
    public void testConvertPrefixToPostfix(){
        String args[] = {"-", "-", "2", "2", "2"};
        String expected[] = {"2", "2", "-", "2", "-"};
        assertEquals("Converting prefix to postfix", Arrays.toString(expected), Arrays.toString(Arith.convertPrefixToPostfix(args)));

        args[0] = "12";
        assertNull(Arith.convertPrefixToPostfix(args));
    }

    @Test
    public void testConvertPostfixToPrefix(){
        String args[] = {"2", "2", "-", "2", "-"};
        String expected[] = {"-", "-", "2", "2", "2"};
        assertEquals("Converting postfix to prefix", Arrays.toString(expected), Arrays.toString(Arith.convertPostfixToPrefix(args)));

        args[2] = "12";
        assertNull(Arith.convertPostfixToPrefix(args));
    }

    @Test
    public void testConvertPrefixToInfix(){
        String args[] = {"-", "-", "2", "2", "2"};
        String expected[] = {"(", "(", "2", "-", "2", ")","-", "2", ")"};

        assertEquals("Converting prefix to infix", Arrays.toString(expected), Arrays.toString(Arith.convertPrefixToInfix(args)));

        args[0] = "12";
        assertNull(Arith.convertPrefixToInfix(args));
    }

    @Test
    public void testConvertPostfixToInfix(){
        String args[] = {"2", "2", "-", "2", "-"};
        String expected[] = {"(", "(", "2", "-", "2", ")","-", "2", ")"};

        assertEquals("Converting postfix to infix", Arrays.toString(expected), Arrays.toString(Arith.convertPostfixToInfix(args)));

        args[2] = "12";
        assertNull(Arith.convertPostfixToInfix(args));
    }
}

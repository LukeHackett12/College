package com.luke;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ArithTest{

    //TODO empty array case
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
        String args[] = {"-", "-", "2", "2", "2"};
        assertTrue("Valid prefix", Arith.validatePrefixOrder(args));

        args = new String[]{"-", "9", "2", "-", "2"};
        assertFalse("Valid prefix", Arith.validatePrefixOrder(args));

        args = new String[]{"-", "9", "9", "2", "2"};
        assertFalse("Valid prefix", Arith.validatePrefixOrder(args));

        args = new String[]{"-", "-", "9", "asd", "2"};
        assertFalse("Valid prefix", Arith.validatePrefixOrder(args));
    }

    @Test
    public void testPostfixEvaluate(){
        String args[] = {"2", "2", "+", "6", "-"};
        int result = Arith.evaluatePostfixOrder(args);
        assertEquals("Postfix simple case", -2, result);

        args = new String[]{"2", "2", "1", "6", "-"};
        result = Arith.evaluatePostfixOrder(args);
        assertEquals("Postfix simple case", -1, result);

        args = new String[]{"2", "2", "^", "2", "+", "6", "/"};
        result = Arith.evaluatePostfixOrder(args);
        assertEquals("Postfix simple case", 1, result);
    }

    @Test
    public void testPrefixEvaluate(){
        String args[] = {"-", "-", "2", "2", "2"};
        assertEquals("Added prefix", -2,Arith.evaluatePrefixOrder(args));

        args = new String[]{"-", "234", "2", "+", "2"};
        assertEquals("Added prefix", 0,Arith.evaluatePrefixOrder(args));

        args = new String[]{"-", "+", "2", "2", "2"};
        assertEquals("Added prefix", 2,Arith.evaluatePrefixOrder(args));
    }

    @Test
    public void testConvertPrefixToPostfix(){
        String args[] = {"-", "-", "2", "2", "2"};
        String expected[] = {"2", "2", "-", "2", "-"};
        assertEquals("Converting prefix to postfix", expected, Arith.convertPrefixToPostfix(args));
    }

    @Test
    public void testConvertPostfixToPrefix(){

    }

    @Test
    public void testConvertPrefixToInfix(){

    }

    @Test
    public void testConvertPostfixToInfix(){

    }
}

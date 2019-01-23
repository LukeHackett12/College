import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ArithTest {

    @org.junit.Test
    public void validatePrefixOrder() {
        String prefixLiterals [] = {"8","8","8","/","6","3","2"};
        assertEquals(false, Arith.validatePrefixOrder(prefixLiterals));

        String prefixLiterals2 [] = {"-","+","7","*","4","5","+","2","0"};
        assertEquals(true, Arith.validatePrefixOrder(prefixLiterals2));

        String prefixLiterals3 [] = {"-","+","8","/","+","3"};
        assertEquals(false, Arith.validatePrefixOrder(prefixLiterals3));


        String prefixLiterals4 [] = {"-","+","8","67","+","3","298"};
        assertEquals(true, Arith.validatePrefixOrder(prefixLiterals4));

    }

    @Test
    public void validatePostfixOrder(){
        String postfixLiterals[] = {"8", "67", "+", "3", "298","+", "*"};
        assertEquals(true, Arith.validatePostfixOrder(postfixLiterals));

        String postfixLiterals2[] = {"+", "+", "+", "3", "298","7", "*"};
        assertEquals(false, Arith.validatePostfixOrder(postfixLiterals2));

        String postfixLiterals3[] = {"2", "2","2","2"};
        assertEquals(false, Arith.validatePostfixOrder(postfixLiterals3));


    }

    @Test
    public void evaluatePostfixOrder(){
        String postfixLiterals[] = {"8", "67", "+", "3", "298","+", "*"};
        assertEquals(22575,Arith.evaluatePostfixOrder(postfixLiterals));

        String postfixLiterals2[] = {"67", "8", "-"};
        assertEquals(59,Arith.evaluatePostfixOrder(postfixLiterals2));


        String postfixLiterals3[] = {"6", "3", "/"};
        assertEquals(2,Arith.evaluatePostfixOrder(postfixLiterals3));

        String postfixLiterals4[] = {"3", "3", "/"};
        assertEquals(1,Arith.evaluatePostfixOrder(postfixLiterals4));
    }

    @Test
    public void evaluatePrefixOrder(){
        String prefixLiterals [] = {"*","+","8","67","+","3","298"};
        assertEquals(22575, Arith.evaluatePrefixOrder(prefixLiterals));

        String prefixLiterals2[]={"*", "-", "1", "2", "3"};
        assertEquals(-3, Arith.evaluatePrefixOrder(prefixLiterals2));

        String prefixLiterals3[]={"/", "3", "3"};
        assertEquals(1, Arith.evaluatePrefixOrder(prefixLiterals3));


    }

    @org.junit.Test
    public void isNumber() {
        assertEquals(true, Arith.isNumber("4"));
        assertEquals(true, Arith.isNumber("10"));
        assertEquals(false, Arith.isNumber("12"));

    }

    @org.junit.Test
    public void isOperator() {
        assertEquals(true, Arith.isOperator("*"));
        assertEquals(true, Arith.isOperator("-"));
        assertEquals(true, Arith.isOperator("/"));
        assertEquals(true, Arith.isOperator("+"));
        assertEquals(false, Arith.isOperator("9"));
        assertEquals(false, Arith.isOperator("g"));
    }

    @Test
    public void convertPostfixToPrefix() {
        String prefixLiterals [] = {"*","+","8","67","+","3","298"};
        String postfixLiterals[] = {"8", "67", "+", "3", "298","+", "*"};

        assertArrayEquals(prefixLiterals, Arith.convertPostfixToPrefix(postfixLiterals));
    }
    @Test
    public void convertPrefixToPostfix(){
        String prefixLiterals [] = {"*","+","8","67","+","3","298"};
        String postfixLiterals[] = {"8", "67", "+", "3", "298","+", "*"};

        assertArrayEquals(postfixLiterals, Arith.convertPrefixToPostfix(prefixLiterals));
    }

    @Test
    public void convertPrefixToInfix(){
        String prefixLiterals [] = {"*","+","8","67","+","3","298"};
        String infixLiterals[] = {"(","(","8","+","67",")", "*", "(","3", "+","298",")",")"};

        assertArrayEquals(infixLiterals, Arith.convertPrefixToInfix(prefixLiterals));
    }

    @Test
    public void convertPostfixToInfix(){

        String infixLiterals[] = {"(","(","8","+","67",")", "*", "(","3", "+","298",")",")"};
        String postfixLiterals[] = {"8", "67", "+", "3", "298","+", "*"};

        assertArrayEquals(infixLiterals, Arith.convertPostfixToInfix(postfixLiterals));
    }

}
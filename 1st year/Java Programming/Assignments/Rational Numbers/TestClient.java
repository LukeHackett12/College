/* SELF ASSESSMENT

Class Retional
I declared two member variables: numerator and denominator (marks out of 4: 4).
Comment: Have numerator and denominator variables in class

Constructor 1
My program takes take two integers as parameters (for numerator and denominator) and initialises the member variables with the corresponding values . If the denominator is equal to 0 I throw an exception (marks out of 5: 5).
Comment: Takes two int parameters and passes them to the class variables, throws an arithmetic exception if 0

Constructor 2
My program takes only one integer as parameter (numerator), and set the numerator to this value . I set the denominator to 1 in this case, as the resulting rational number in this case is an integer (marks out of 3: 3).
Comment: constructor takes one parameter and will return a rational with denominator 1

Add Method
My program takes only a rational number as a parameter and returns a new rational number which has a numerator and denominator which the addition of the two objects - this and the parameter. My program does not overwrite any of the other two rational numbers (marks out of 8: 8).
Comment: adds rational number to current object without overwriting either

Subtract Method
I have implemented this the same as add method, except it implements subtraction (marks out of 8: 8).
Comment: same as add

Multiply Method
I have implemented this the same as add method, except it implements multiplication (marks out of 8: 8).
Comment: same as add

Divide Method
I have implemented this the same as add method, except it implements divide (marks out of 8: 8).
Comment: same as add

Equals Method
My program takes a rational number as a parameter and compares it to the reference object. I only use multiplication between numerators/denominators for the purpose of comparison, as integer division will lead to incorrect results. I return a boolean value ((marks out of 8: 8).
Comment: compares a rational to itself and states if its equal using cross multiplication, returns boolean

isLessThan
My program takes a rational number as a parameter and compares it to the reference object. I only use multiplication as integer division will lead to incorrect results. I return a boolean value (marks out of 8: 8).
Comment: uses cross multiplication to check if the object is less than the passed rational, returns boolean

Simplify Method
My program returns a rational number but not a new rational number, instead it returns the current reference which is this. It doesn't take any parameters as it works only with the reference object. I first find the greatest common divisor (GCD) between the numerator and denominator, and then obtain the new numerator and denominator by dividing to the GCD (marks out of 8: 8).
Comment: returns the rational object in its simplest form

gcd function
My program returns the greatest common divider of two integers: the numerator and the denominator (marks out of 6: 6).
Comment: returns the gcd of the numerator and the denominator

toString Method
My program returns a string showing the fraction representation of the number, eg. "1/2". It takes no parameters (marks out of 4: 4).
Comment: converts the rational and returns a string of the number

Test Client Class
My program asks the user for two rational numbers, creates two rational objects using the constructor and passing in the provided values, calls addition, subtraction, multiplication, division, comparison and simplification and prints out the results (marks out of 22: 22).
Comment: creates two rationals using both constructors and uses all the functions on them
*/

import java.util.Scanner;

class TestClient{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        System.out.print("Input a numerator: ");
        int numerator = in.nextInt();
        System.out.print("Input a divisor: ");
        int divisor = in.nextInt();
        Rational rationalOne = new Rational(numerator, divisor);

        System.out.print("Input another numerator: ");
        numerator = in.nextInt();
        Rational rationalTwo = new Rational(numerator);

        Rational add = rationalOne.add(rationalTwo);
        Rational sub = rationalOne.subtract(rationalTwo);
        Rational mul = rationalOne.multiply(rationalTwo);
        Rational div = rationalOne.divide(rationalTwo);
        boolean eq = rationalOne.equals(rationalTwo);
        boolean less = rationalOne.isLessThan(rationalTwo);
        add.simplify();
        sub.simplify();
        mul.simplify();
        div.simplify();
        rationalOne.simplify();

        System.out.println("Added you get: " + add.toStringR());
        System.out.println("Subtracted you get: " + sub.toStringR());
        System.out.println("Multiplied you get: " + mul.toStringR());
        System.out.println("Divided you get: " + div.toStringR());
        System.out.println("Are they equal? " + eq);
        System.out.println("Is the first less than the second? " + less);
        System.out.println("Simplified the first rational is " + rationalOne.toStringR());
    }
}

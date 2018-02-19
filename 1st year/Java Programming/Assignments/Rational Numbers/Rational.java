class Rational{
    private int numerator;
    private int denominator;

    Rational(int numerator, int denominator){
        this.numerator = numerator;
        if(denominator != 0){
            this.denominator = denominator;
        } else {
            throw new ArithmeticException();
        }
    }

    Rational(int numerator){
        this.numerator = numerator;
        this.denominator = 1;
    }

    Rational add(Rational rational){
        int tempNumerator = numerator * rational.getDenominator() + rational.getNumerator() * denominator;
        int tempDivisor = denominator * rational.getDenominator();
        return new Rational(tempNumerator, tempDivisor);
    }

    Rational subtract(Rational rational){
        int tempNumerator = numerator * rational.getDenominator() - denominator * rational.getNumerator();
        int tempDivisor = denominator * rational.getDenominator();
        return new Rational(tempNumerator, tempDivisor);
    }

    Rational multiply(Rational rational){
        int tempNumerator = numerator * rational.getNumerator();
        int tempDivisor = denominator * rational.getDenominator();
        return new Rational(tempNumerator, tempDivisor);
    }

    Rational divide(Rational rational){
        int tempNumerator = numerator * rational.getDenominator();
        int tempDivisor = denominator * rational.getNumerator();
        return new Rational(tempNumerator, tempDivisor);
    }

    boolean equals(Rational rational){
        if(numerator*rational.denominator == rational.numerator*denominator){
            return true;
        }
        else{
            return false;
        }
    }

    boolean isLessThan(Rational rational){
        if(numerator*rational.getDenominator() < denominator*rational.getNumerator()){
            return true;
        }
        return false;
    }

    void simplify(){
        int gcd = gcd(numerator, denominator);
        numerator = numerator/gcd;
        denominator = denominator/gcd;
    }

    String toStringR(){
        String rational = "";
        if(denominator != 1){
            rational = numerator + "/" + denominator;
        }
        else{
            rational = Integer.toString(numerator);
        }
        return rational;
    }

    private int getNumerator(){
        return numerator;
    }

    private int getDenominator(){
        return denominator;
    }

    private int gcd(int numerator, int denominator){
        return (denominator == 0) ? numerator : gcd(denominator, numerator % denominator);
    }
}

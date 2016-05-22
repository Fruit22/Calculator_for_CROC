/**
 * Created by Яковенко Михаил
 */
public class Fraction {
    private int numerator;       // числитель
    private int denominator;     // знаменатель

    protected Fraction(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
    }

    protected Fraction multiplication(Fraction operand){
        Fraction result = new Fraction(0,0);
        result.numerator = this.numerator * operand.numerator;
        result.denominator = this.denominator * operand.denominator;
        return result;
    }

    protected Fraction division(Fraction operand) throws ArithmeticException{
        Fraction result = new Fraction(0,0);
        if (operand.numerator==0){
            throw new ArithmeticException();
        }
        result.numerator = this.numerator * operand.denominator;
        result.denominator = this.denominator * operand.numerator;
        return result;
    }

    protected Fraction subtraction(Fraction operand){
        Fraction result = new Fraction(0,0);
        result.denominator = lcm (this.denominator,operand.denominator);
        result.numerator = ((result.denominator/this.denominator)*this.numerator)-((result.denominator/operand.denominator)*operand.numerator);
        return result;
    }

    protected Fraction addition(Fraction operand){
        Fraction result = new Fraction(0,0);
        result.denominator = lcm (this.denominator,operand.denominator);
        result.numerator = ((result.denominator/this.denominator)*this.numerator)+((result.denominator/operand.denominator)*operand.numerator);
        return result;
    }

    // наибольший общий делить
    protected int gcd(int a,int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    // наименьшее общее кратное
    protected int lcm(int a, int b){
        return a / gcd(a,b) * b;
    }

    @Override
    public String toString(){
        return this.numerator + "/" + this.denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }
}

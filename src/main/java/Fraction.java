/**
 * Класс для представления простой дроби.
 * Реализует арифметические операции над дробями (умножение, деление, сложение, вычитание)
 * @author  Yakovenko M.
 */
public class Fraction {
    private int numerator;       // числитель
    private int denominator;     // знаменатель

    protected Fraction(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
    * Метод, реализующий умножение.
    * @param operand - второй операнд
    * @return Возвращает произведение
    */
    protected Fraction multiplication(Fraction operand){
        Fraction result = new Fraction(0,0);
        result.numerator = this.numerator * operand.numerator;
        result.denominator = this.denominator * operand.denominator;
        return result;
    }

    /**
    * Метод, реализующий деление.
    * @param operand - второй операнд
    * @return - возвращает частное
    * @exception ArithmeticException - деление на 0
    */
    protected Fraction division(Fraction operand) throws ArithmeticException{
        Fraction result = new Fraction(0,0);
        if (operand.numerator==0){
            throw new ArithmeticException();
        }
        result.numerator = this.numerator * operand.denominator;
        result.denominator = this.denominator * operand.numerator;
        return result;
    }

    /**
     * Метод, реализующий разность.
     * @param operand - второй операнд
     * @return - возвращает разность
     */
    protected Fraction subtraction(Fraction operand){
        Fraction result = new Fraction(0,0);
        result.denominator = lcm (this.denominator,operand.denominator);
        result.numerator = ((result.denominator/this.denominator)*this.numerator)-((result.denominator/operand.denominator)*operand.numerator);
        return result;
    }

    /**
     * Метод, реализующий деление.
     * @param operand - второй операнд
     * @return - возвращает частное
     */
    protected Fraction addition(Fraction operand){
        Fraction result = new Fraction(0,0);
        result.denominator = lcm (this.denominator,operand.denominator);
        result.numerator = ((result.denominator/this.denominator)*this.numerator)+((result.denominator/operand.denominator)*operand.numerator);
        return result;
    }

    /**
     * Метод, реализующий поиск наибольшего общего делителя(НОД) для двух чисел.
     * @param  a - первое число
     * @param b - второе число
     * @return - возвращает НОД
     */
    protected int gcd(int a,int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    /**
     * Метод, реализующий поиск наименьшего общего кратного(НОК) для двух чисел.
     * @param a - первое число
     * @param b - второе число
     * @return - возвращает НОК
     */
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

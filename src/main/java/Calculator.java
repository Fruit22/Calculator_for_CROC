import java.io.*;
import java.util.Date;

/**
 * Главный класс.
 * Отвечает за последовательность действий и получение итогового результата.
 * @author  Yakovenko M.
 */

public class Calculator {

    private static Fraction operandOne;        // операнд 1
    private static Fraction operandTwo;        // операнд 2
    private static Parser parser = new Parser();
    private static RW rw = new RW();

    public static void main(String[] args) {
        if (args.length!=0) rw.setPath(args[0]);
        start();
    }

    /**
    * Метод, определяющий способ ввода данных (на основе файла или через консоль).
    * Выполняет необходимую последовательность действий для обоих случаев.
    * Отлавливает возможные исключения.
    */
    protected static void start(){
        if (!rw.getPath().equals("")){
            try {
                rw.readFile();
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла");
                rw.setPath("");
                start();
                return;
            }
            rw.writeFile("----- " + String.valueOf(new Date()) + " -----");
            for (int i = 0; i < rw.getExamples().size() ; i++) {
                int number = 1 + i;
                try {
                    parser.parsingExampleAndCheck(rw.getExamples().get(i));
                    Fraction result = makeCalculation();
                    rw.writeFile(operandOne+" "+parser.getSign()+" "+operandTwo + " = " + result);
                } catch (ArithmeticException e) {
                    rw.writeFile("Ошибка! Деление на ноль в строке №"+number);
                } catch (Exception e) {
                    rw.writeFile("Неверный формат входных данных в строке №"+number);
                }
            }
            rw.writeFile("----------------------------------------");
            System.out.println("Решения записаны в файл");
        }
        else {
            System.out.println("Введите свое выражение:");
            rw.readConsole();
            try {
                parser.parsingExampleAndCheck(rw.getExample());
                Fraction result = makeCalculation();
                System.out.println((operandOne+" "+parser.getSign()+" "+operandTwo + " = " + result));
            } catch (ArithmeticException e) {
                System.out.println("Ошибка! Деление на ноль");
                start();
            }catch (Exception e) {
                System.out.println("Неверный формат входных данный");
                start();
            }
        }
    }

    /**
     * Метод для создания операндов (на основе данных парсинга),
     * и получения результата операции.
     * @return Возвращает результат вычислений в виде дроби (Fraction).
     */
    protected static Fraction makeCalculation() throws ArithmeticException{
        operandOne = new Fraction(parser.getNumeratorOne(),parser.getDenominatorOne());
        operandTwo = new Fraction(parser.getNumeratorTwo(),parser.getDenominatorTwo());
        switch (parser.getSign()){
            case ("*"):
                return operandOne.multiplication(operandTwo);
            case ("/"):
                return operandOne.division(operandTwo);
            case ("-"):
                return operandOne.subtraction(operandTwo);
            case ("+"):
                return operandOne.addition(operandTwo);
            default:
                return null;
        }
    }
}
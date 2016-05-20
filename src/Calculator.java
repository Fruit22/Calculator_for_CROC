import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Яковенко Михаил
 */

public class Calculator {
    private static String example;             // входное выражение (из консоли)
    private static List<String> examples;      // входные выражения (из файла)
    private static int numeratorOne;           // числитель первой дроби
    private static int denominatorOne;         // знаменатель первой дроби
    private static int numeratorTwo;           // числитель второй дроби
    private static int denominatorTwo;         // знаменатель второй дроби
    private static String sign;                // знак операции
    private static String path = "";           // путь к файлу (чтение)
    private static String pathSolutions = "";  // путь к файлу (запись)
    private static Fraction operandOne;        // операнд 1
    private static Fraction operandTwo;        // операнд 2

    public static void main(String[] args) {
        if (args.length!=0) path = args[0];
        start();
    }

    protected static void start(){
        if (!path.equals("")){
            // При ошибке чтения файла, предложить пользователю ввести выражение в консоли.
            try {
                readFile();
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла");
                path = "";
                start();
                return;
            }
            pathSolutions = path.substring(0,path.length()-4)+"SOLUTIONS.xml";
            writeFile("----- " + String.valueOf(new Date()) + " -----");
            for (int i = 0; i < examples.size() ; i++) {
                int number = 1 + i;
                try {
                    parsingExampleAndCheck(examples.get(i));
                    Fraction result = makeCalculation();
                    writeFile(operandOne+" "+sign+" "+operandTwo + " = " + result);
                } catch (ArithmeticException e) {
                    writeFile("Ошибка! Деление на ноль в строке №"+number);
                    continue;
                } catch (Exception e) {
                    writeFile("Неверный формат входных данных в строке №"+number);
                    continue;
                }
            }
            writeFile("----------------------------------------");
            System.out.println("Решения записаны в файл");
        }
        else {
            readConsole();
            try {
                parsingExampleAndCheck(example);
                Fraction result = makeCalculation();
                System.out.println((operandOne+" "+sign+" "+operandTwo + " = " + result));
            } catch (ArithmeticException e) {
                System.out.println("Ошибка! Деление на ноль");
                start();
                return;
            }catch (Exception e) {
                System.out.println("Неверный формат входных данный");
                start();
                return;
            }
        }
    }

    protected static void readFile() throws IOException {
        String line;
        examples = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        while ((line = reader.readLine()) != null) {
                examples.add(line);
        }
    }

    protected static void writeFile(String str){
        try {
            FileWriter writer = new FileWriter(pathSolutions,true);
            writer.write(str+"\r\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");
            start();
        }
    }

    protected static void readConsole(){
        System.out.println("Введите свое выражение:");
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        try {
            example = buf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void parsingExampleAndCheck(String example) throws Exception {
        String [] splitExample = example.split("\\D+");
        String exampleWithoutSpaces = example.replace(" ","");
        String [] characters = exampleWithoutSpaces.split("\\d+");
        if (splitExample.length>4){
            throw new Exception();
        }
        numeratorOne = Integer.parseInt(splitExample[0]);
        denominatorOne = Integer.parseInt(splitExample[1]);
        numeratorTwo = Integer.parseInt(splitExample[2]);
        denominatorTwo = Integer.parseInt(splitExample[3]);
        if (!characters[1].equals("/") || !characters[3].equals("/")){
            throw new Exception();
        }
        if (denominatorOne==0 || denominatorTwo==0){
            throw new Exception();
        }
        sign = characters[2];
    }

    protected static Fraction makeCalculation() throws ArithmeticException{
        operandOne = new Fraction(numeratorOne,denominatorOne);
        operandTwo = new Fraction(numeratorTwo,denominatorTwo);
        switch (sign){
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
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для операций считывания входных данных и записи результатов вычислений.
 * @author  Yakovenko M.
 */
public class RW {
    private String example;             // входное выражение (из консоли)
    private List<String> examples;      // входные выражения (из файла)
    private String path = "";           // путь к файлу (чтение)

    /**
     * Метод, реализующий чтение данных из файла.
     * @exception IOException - при ошибки чтения
     */
    protected void readFile() throws IOException {
        String line;
        examples = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        while ((line = reader.readLine()) != null) {
            examples.add(line);
        }
    }

    /**
     * Метод, реализующий запись вычислений в файл.
     */
    protected void writeFile(String str){
        try {
            FileWriter writer = new FileWriter(path.substring(0,path.length()-4)+"SOLUTIONS.xml",true);
            writer.write(str+"\r\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");
        }
    }

    /**
     * Метод, реализующий чтений входных данных из консоли.
     */
    protected void readConsole(){
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        try {
            example = buf.readLine();
        } catch (IOException e) {
            System.out.println("Ошибка чтения из консоли");
        }
    }

    public String getPath() {
        return path;
    }

    public List<String> getExamples() {
        return examples;
    }

    public String getExample() {
        return example;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

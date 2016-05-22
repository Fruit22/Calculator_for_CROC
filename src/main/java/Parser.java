/**
 * Created by Яковенко Михаил
 */

public class Parser {
    private int numeratorOne;
    private int denominatorOne;
    private int numeratorTwo;
    private int denominatorTwo;
    private String sign;

    protected void  parsingExampleAndCheck(String example) throws Exception {
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

    public int getDenominatorOne() {
        return denominatorOne;
    }

    public int getNumeratorOne() {
        return numeratorOne;
    }

    public int getNumeratorTwo() {
        return numeratorTwo;
    }

    public int getDenominatorTwo() {
        return denominatorTwo;
    }

    public String getSign() {
        return sign;
    }
}

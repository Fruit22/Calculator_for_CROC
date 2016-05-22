import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Яковенко Михаил
 */

public class FractionTest {
    Fraction operand1 = new Fraction(3,10);
    Fraction operand2 = new Fraction(4,15);

    @Test
    public void testMultiplication() throws Exception {
    assertEquals(12, operand1.multiplication(operand2).getNumerator());
    assertEquals(150, operand1.multiplication(operand2).getDenominator());
    }

    @Test
    public void testDivision() throws Exception {
    assertEquals(45, operand1.division(operand2).getNumerator());
    assertEquals(40, operand1.division(operand2).getDenominator());
    }

    @Test
    public void testSubtraction() throws Exception {
    assertEquals(1, operand1.subtraction(operand2).getNumerator());
    assertEquals(30, operand1.subtraction(operand2).getDenominator());
    }

    @Test
    public void testAddition() throws Exception {
    assertEquals(17, operand1.addition(operand2).getNumerator());
    assertEquals(30, operand1.addition(operand2).getDenominator());
    }

    @Test
    public void testGcd() throws Exception {
    assertEquals(5, operand1.gcd(35,40));
    assertEquals(8, operand1.gcd(24,16));
    }

    @Test
    public void testLcm() throws Exception {
    assertEquals(35, operand1.lcm(7,5));
    assertEquals(24, operand1.lcm(8,6));
    }
}
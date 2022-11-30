package Calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Before
    public void prepare(){
        Calculator calculator = new Calculator();
    }

    @Test
    public void testSum(){
        Calculator calculator = new Calculator();
        int sum = calculator.sum(5, 4);
        Assert.assertEquals(9, sum);
    }

    @Test
    public void testMultiplication(){
        Calculator calculator = new Calculator();
        int multiplication = calculator.multiply(5, 4);
        Assert.assertEquals(20, multiplication);
    }

}
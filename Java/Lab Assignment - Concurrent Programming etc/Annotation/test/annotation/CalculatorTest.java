
package annotation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CalculatorTest {
        
    public CalculatorTest() {
    }

    public double add(double num1, double num2) {
        return num1 + num2;
    }

    public double subtract(double num1, double num2) {
        return num1 - num2;
    }

    public double divide(double num1, double num2) {
        return num1/num2;
    }
    
    public double multiply(double num1, double num2) {
        return num1 * num2;
    }   
    
}

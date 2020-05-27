
package annotation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.PostConstruct;

public class CalculatorUnitTesterTest {
    
    private static int steps = 0;
    
    public CalculatorUnitTesterTest() {

    }
    
    @initialize
    public @interface initialize {   
        
    }
    
    public @interface cleanup {        
    }
    
    @cleanup
    public void cleanup() {
        System.out.println("cleanup: CLEANING UP PROGRAM");
    }
    
    @BeforeClass
    @initialize
    public static void initialize() {

        System.out.println("initialize: STARTING UP PROGRAM");

    }

    @Before
    public void beforeTest() {
        System.out.println("before: TEST STARTING");
    }
    
    @After
    public void afterTest() {
        System.out.println("after: TEST FINISHED");
    }

    @Test
    public void testAdd() {
        CalculatorTest c = new CalculatorTest();
        double expected = 15;
        double result = c.add(5,10);
        if (Math.abs(expected - result) < 0.000000001) {
            System.out.println("testAdd succeeded");
        } else {
            System.out.println("testAdd failed");
        }
    }
    
    @Test
    public void testSubtract() {
        CalculatorTest c = new CalculatorTest();
        double expected = 15;
        double result = c.subtract(20,5);
        if (Math.abs(expected - result) < 0.000000001) {
            System.out.println("testSubtract succeeded");
        } else {
            System.out.println("testSubtract failed");
        }
    }
    
    @Test
    public void testMultiply() {
        CalculatorTest c = new CalculatorTest();
        double expected = 15;
        double result = c.multiply(5,3);
        if (Math.abs(expected - result) < 0.000000001) {
            System.out.println("testMultiply succeeded");
        } else {
            System.out.println("testMultiply failed");
        }
    }
    
    @Test
    public void testDivide() {
        CalculatorTest c = new CalculatorTest();
        double expected = 5;
        double result = c.add(10,2);
        if (Math.abs(expected - result) < 0.000000001) {
            System.out.println("testDivide succeeded");
        } else {
            System.out.println("testDivide failed");
        }
    }    
    
}

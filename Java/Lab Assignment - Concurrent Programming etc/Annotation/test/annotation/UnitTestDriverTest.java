
package annotation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class UnitTestDriverTest {
    
    public UnitTestDriverTest() {
    }
    
    @Test
    public void testMain() {
        CalculatorUnitTesterTest cunit = new CalculatorUnitTesterTest();
        cunit.cleanup();
        String[] args = null;
        UnitTestDriver.main(args);        
    }
    
}

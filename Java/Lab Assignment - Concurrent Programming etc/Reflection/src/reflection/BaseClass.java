
package reflection;

import java.lang.*;

public class BaseClass {
    public int baseInt;
    
    private static void method3() {
        System.out.println("Method3");
    }
    
    public int method4() {
        System.out.println("Method4");
        return 0;
    }
    
    public static int method5() {
        System.out.println("Method5");
        return 0;
    }
    
    public void method6() {
        System.out.println("Method6");        
    }
    
    // inner public class
    public class BaseClassInnerClass{
        
    }
    
    // member public enum
    public enum BaseClassMemberEnum{
        
    }
}

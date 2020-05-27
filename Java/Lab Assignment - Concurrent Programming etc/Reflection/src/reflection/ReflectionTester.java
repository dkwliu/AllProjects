
package reflection;

import java.lang.reflect.*;
import java.lang.annotation.Annotation;

public class ReflectionTester {


    public static void main(String[] args) {

        Class ccobj = ConcreteClass.class;
        //get canonical name of the class
        String ccobjName = ccobj.getCanonicalName();        
        System.out.println("Canonical Name of ConcreteClass: " + ccobjName);
        System.out.println("");
        
        // get the superclasses of the class
        Class ccobjsc = ccobj.getSuperclass();
        System.out.println("Super class of ConcreteClass: " + ccobjsc);
        System.out.println("");
        
        // get public interface members inherited from super classes and the
        // public class and interface members declared by the class       
        Class[] ccobjmi = ccobj.getInterfaces();
        for (int i = 0; i < ccobjmi.length; i++) {
        System.out.println("Public interface members inherited from super " +
                "classes and interface members declared by class: " 
                + ccobjmi[i]);
        }       
        System.out.println("");        
        
        // get classes and interfaces declared as members of the class
        Class[] ccobjcm = ccobj.getDeclaredClasses();
        for (int i = 0; i < ccobjcm.length; i++) {
            System.out.println("Classes and interfaces "
                    + "declared as members of class: "
                    + ccobjcm[i]);
        }
        System.out.println("");        

        // get class modifiers
        Class[] ccobjac = ccobj.getDeclaredClasses();
        int ccobjm = ccobj.getModifiers();
        for (int i = 0; i < ccobjac.length; i++) {
            System.out.println("Modifiers of ConcreteClass's classes: " + 
                    Modifier.toString(ccobjac[i].getModifiers()));       
        }
        System.out.println("");         
        
        // get implemented interfaces
        Class[] ccobjii = ccobj.getInterfaces();
        for (int i = 0; i < ccobjii.length; i++) {
        System.out.println("Implemented interfaces: " 
                + ccobjii[i]);
        }       
        System.out.println(""); 
        
        // get public methods
        Method[] ccobjpm = ccobj.getMethods();
        for (int i = 0; i < ccobjpm.length; i++) {
        System.out.println("Public methods: " 
                + ccobjpm[i]);
        }       
        System.out.println("");     
        
        // get public constructors
        Constructor[] ccobjpc = ccobj.getConstructors();
        for (int i = 0; i < ccobjpc.length; i++) {
        System.out.println("Public Constructors: " 
                + ccobjpc[i]);
        }       
        System.out.println("");         
        
        // get public fields
        Field[] ccobjf = ccobj.getFields();
        for (int i = 0; i < ccobjf.length; i++) {
            System.out.println("Public Fields: "
                    + ccobjf[i]);
        }
        System.out.println("");

        // class annotations
        Annotation[] ccobja = ccobj.getClass().getAnnotations();
        for (int i = 0; i < ccobja.length; i++) {
            System.out.println("Class annotations: " + ccobja[i]);
        }
        System.out.println("");

        //get constructor and use it to create a new instanced object
        try {
            Constructor ccobjpic = ccobj.getConstructor(int.class);
            Object conInstance = ccobjpic.newInstance(1);
            System.out.println("Concrete class instanced through " +
                    "reflection: " + conInstance);           
        } catch (Exception e){
            System.out.println("Something went wrong");
        }
        System.out.println("");
        
        //get public fields and use Field classs to get and set them
        try {        
            ConcreteClass newinst = new ConcreteClass(1);
            Class c = newinst.getClass();            
            Field ccobjfi1 = c.getDeclaredField("publicInt");           
            ccobjfi1.set(newinst, 3);
            int getsetint = (int) ccobjfi1.get(newinst);
            System.out.println("Get set number for publicInt: " 
                    + getsetint);
            
            Field ccobjfi2 = c.getDeclaredField("privateString");  
            ccobjfi2.setAccessible(true);
            ccobjfi2.set(newinst, "setstring");
            String getsetstring = (String) ccobjfi2.get(newinst);
            System.out.println("Get set number for privateString: " 
                    + getsetstring);
            
            Field ccobjfi3 = c.getDeclaredField("protectedBoolean");          
            ccobjfi3.setAccessible(true);
            ccobjfi3.set(newinst, true);
            boolean getsetint3 = (boolean) ccobjfi3.get(newinst);
            System.out.println("Get set boolean for protectedBoolean: " 
                    + getsetint3);        
            
            Field ccobjfi4 = c.getDeclaredField("defaultObject");          
            ccobjfi4.set(newinst, new Object());
            Object getsetint4 = (Object) ccobjfi4.get(newinst);
            System.out.println("Get set number for defaultObject: " 
                    + getsetint4);    
        } catch (Exception e){
            System.out.println("Something went wrong");
        }        
        System.out.println("");
        
        //get public methods and use Method class to invoke public methods
        ConcreteClass newinst = new ConcreteClass(1);
        
        try {
            Class inst = newinst.getClass();
            
            Method method1 = inst.getMethod("method1");
            method1.invoke(newinst);
            
            Method method2 = inst.getMethod("method2", String.class);
            method2.invoke(newinst, "arg");
            
            Method method4 = inst.getMethod("method4");
            method4.invoke(newinst);
            
            Method method5 = inst.getMethod("method5", int.class);
            method5.invoke(newinst, 1);

        } catch (Exception e){
            System.out.println("Something went wrong");
        }
    }
}

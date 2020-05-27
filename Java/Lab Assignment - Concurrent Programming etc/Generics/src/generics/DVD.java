
package generics;

import java.util.*;

public class DVD extends LibraryItem implements Borrowable {
 
    // At least 3 fields    
    private String title;
    private int uniqueID;
    private GregorianCalendar checkoutDate;
    private GregorianCalendar dueDate;
    private String producer;
    
    public DVD (GregorianCalendar cd, GregorianCalendar dd, 
            String t, String desc, String p) {
        uniqueID = id;
        id = id + 1;
        title = t;
        checkoutDate = cd;
        dueDate = dd;
        description = desc;
        producer = p;
    }
 
    // get set methods    
    public GregorianCalendar getCheckoutDate() {
        return checkoutDate;
    }
    
    public GregorianCalendar getDueDate() {
        return dueDate;
    }
    
    public void setCheckoutDate(GregorianCalendar d) {
        checkoutDate = d;
    }
    
    public void setDueDate(GregorianCalendar d) {
        dueDate = d;
    }  
    
    public void setTitle(String s) {
        title = s;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setProducer(String p) {
        producer = p;
    }
    
    public String getProducer() {
        return producer;
    }  
    
    public int getID() {
        return uniqueID;
    }        
}

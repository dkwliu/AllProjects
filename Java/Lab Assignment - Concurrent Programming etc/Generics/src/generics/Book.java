
package generics;

import java.util.*;

public class Book extends LibraryItem implements Borrowable {
    
    // At least 3 fields    
    private String title;
    private int uniqueID;
    private GregorianCalendar checkoutDate;
    private GregorianCalendar dueDate;
    private String author;
    
    public Book (GregorianCalendar cd, GregorianCalendar dd, 
            String t, String desc, String a) {
        uniqueID = id;
        id = id + 1;
        title = t;
        checkoutDate = cd;
        dueDate = dd;
        description = desc;
        author = a;
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
    
    public void setAuthor(String a) {
        author = a;
    }
    
    public String getProducer() {
        return author;
    }  
    
    public int getID() {
        return uniqueID;
    }      
}

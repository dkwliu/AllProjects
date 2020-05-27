
package generics;

import java.util.*;

public class Magazine extends LibraryItem {
    
    // At least 3 fields
    private String title;    
    private int uniqueID;
    private String author;
    private GregorianCalendar checkoutDate;

    public Magazine (String t, String a) {
        uniqueID = id;
        id = id + 1;
        title = t;
        author = a;
    }    
    
    // get set methods      
    
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

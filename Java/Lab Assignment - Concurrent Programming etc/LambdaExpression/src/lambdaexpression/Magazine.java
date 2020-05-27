
package lambdaexpression;

import java.util.GregorianCalendar;

public class Magazine extends LibraryItems{

    public Magazine(String t, String desc, GregorianCalendar dd) {
        title = t;
        description = desc;
        dueDate = dd;
        book = false;
    }

    // accessor mutator methods
    public String getDescription() {
        return description;
    }

    public void setDescription(String d) {
        description = d;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String t) {
        title = t;
    }
    
    public void setDueDate(GregorianCalendar dd) {
        dueDate = dd;
    }
    
    public GregorianCalendar getDueDate() {
        return dueDate;
    }
}

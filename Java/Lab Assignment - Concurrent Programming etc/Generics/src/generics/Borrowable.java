
package generics;
import java.util.*;


// borrowable interface
public interface Borrowable {
    public GregorianCalendar getCheckoutDate();
    public GregorianCalendar getDueDate();
    public void setCheckoutDate(GregorianCalendar d);
    public void setDueDate(GregorianCalendar d);
    public void setTitle(String s);
    public String getTitle();    
}

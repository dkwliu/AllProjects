
package generics;

import java.util.*;

public class CheckoutCart<E extends Borrowable> 
    extends LibraryCollection<E> {
    
    ArrayList<E> checkoutArray = new ArrayList<E>();
    
    public CheckoutCart(ArrayList al) {
        checkoutArray = al;
    }
    
        // Get an iterator that iterates based on date
    public Iterator<E> getDueDateIterator() {
        ArrayList<E> items = new ArrayList<E>();      
        
        for (int i = 0; i < checkoutArray.size(); i++) {
            if (checkoutArray.get(i).getDueDate() != null) {
                items.add(checkoutArray.get(i));  
            }
        }
        return items.iterator();
    }
    
    public ArrayList<String> getOverDueBook(GregorianCalendar d) {
        
        ArrayList<String> tempList = new ArrayList<String>();
        
        Iterator<E> it = getDueDateIterator();
        
        Iterator godbIt = it;
        
        while (it.hasNext()) {
            E ititem = it.next();
            
            if (d.before(ititem.getDueDate())) {
                tempList.add(ititem.getTitle());
            }
        }
        
        return tempList;
    }    
                
}


package generics;
import java.util.*;

public class LibraryCollection<E>{
    
    public ArrayList<E> LC = new ArrayList();
    protected Object[] LCarrayObject;
    protected E[] LCarrayE;
    
    //Add the element E
    public void addItem(E item) {
        LC.add(item);
    }
    
    //Add the contents of c
    public void addAllItem(ArrayList c) {
        LC.addAll(c);
    }
    
    //Remove the element item
    public void removeItem(E item) {
        LC.remove(item);
    }
    
    //Remove all elements
    public void clearItems() {
        LC.clear();
    }
    
    //Remove the elements in c
    public void removeAllItems(ArrayList c) {
        LC.removeAll(c);
    }
    
    //Remove the elements not in c
    public void retainItems(ArrayList c) {
        LC.retainAll(c);
    }
    
    //true if element is present
    public boolean collectionContains(E item) {
        return LC.contains(item);
    }
    
    //True if all elements of c are present
    public boolean collectionContainsAll(ArrayList c) {
        return LC.containsAll(c);
    }
    
    // True if no elements are present
    public boolean isEmpty() {
        return LC.isEmpty();
    }
    
    // Number of elements in collection
    public int collectionSize() {
        return LC.size();
    }
    
    // copy contents to an Object[]
    public void addToArrayObject(ArrayList c) {
        LCarrayObject = new Object[c.size()];
        LCarrayObject = c.toArray(LCarrayObject);
    }

    // Get an iterator over the elements
    public Iterator<E> getIterator () {
        Iterator<E> libItems = LC.iterator();

        return libItems;
    }
    
    public void OverDueBooks(GregorianCalendar gc) {
        CheckoutCart coCart = new CheckoutCart(LC);
        ArrayList<String> tempList = new ArrayList<String>();
        tempList = coCart.getOverDueBook(gc);
        
        for (int i = 0; i < tempList.size(); i++) {
            System.out.println(tempList.get(i) +
                    " is overdue.");    
        }
        
    }
}
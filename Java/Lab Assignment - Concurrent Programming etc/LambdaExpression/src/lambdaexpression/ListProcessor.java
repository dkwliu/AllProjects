
package lambdaexpression;
import java.util.*;
import java.util.function.*;

public class ListProcessor {

    public static List<LibraryItems> filter(List<LibraryItems> l, Predicate<LibraryItems> p) {
        ArrayList<LibraryItems> tempList = new ArrayList<LibraryItems>();
        
        for (LibraryItems item: l) {
            if (p.test(item)) {
                tempList.add(item);
            }
        }
        
        return tempList;
    }
    
    public static void forEach(List<LibraryItems> l, Consumer<LibraryItems> c) {
        for (LibraryItems item: l) {
            c.accept(item);
        }
    }
    
    public static List<String> map(List<LibraryItems> list, 
            Function <LibraryItems, String> f) {
        
        List<String> listTemp = new ArrayList<String>();
        for (LibraryItems item: list) {
            listTemp.add(f.apply(item));
        }      
        
        return listTemp;
    }
    
}

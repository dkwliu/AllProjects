
package lambdaexpression;

import java.util.*;
import java.text.SimpleDateFormat;

public class ListProcessorTest {
    
    public static void main(String[] args) {
        
        List<LibraryItems> libItems = new ArrayList<LibraryItems>();
        List<LibraryItems> tempList = new ArrayList<LibraryItems>();
        List<LibraryItems> tempList2 = new ArrayList<LibraryItems>();
        List<String> tempListString = new ArrayList<String>();
        
        // adding library items into arraylist
        Book book1 = new Book("book title 1", "Book description 1", 
                new GregorianCalendar(2017,4,28));
        libItems.add(book1);
        Book book2 = new Book("book title 2", "Book description 2", 
                new GregorianCalendar(2016,2,19)); 
        libItems.add(book2);
        
        DVD dvd1 = new DVD("DVD title 1", "DVD description 1", 
                new GregorianCalendar(2017,6,18));
        libItems.add(dvd1);
        DVD dvd2 = new DVD("DVD title 2", "DVD description 2", 
                new GregorianCalendar(2018,3,15));
        libItems.add(dvd2);
        
        Magazine mag1 = new Magazine("Mag title 1", "Mag description 1", 
                new GregorianCalendar(2013,1,1));
        libItems.add(mag1);
        Magazine mag2 = new Magazine("Mag title 2", "Mag description 2", 
                new GregorianCalendar(2017,4,24));
        libItems.add(mag2);
        
        
        ListProcessor lpTest = new ListProcessor();

        // Print the list of library items
        System.out.println("Print all library items using Consumer:");
        lpTest.forEach(libItems, item -> System.out.println(item.title));
        
        // Only prints books
        System.out.println("\n\nPrint all book items using Predicate:");
        tempList = lpTest.filter(libItems, item -> item.book == true);        
        for (int i = 0; i < tempList.size(); i++) {
            System.out.println(tempList.get(i).title);
        }
        
        // Print list of library item descriptions using Function
        System.out.println("\n\nPrint list of library "
                + "item descriptions using Function:");
        tempListString = lpTest.map(libItems, item -> item.description);
        for (int i = 0; i < tempListString.size(); i++) {
            System.out.println(tempListString.get(i));
        }        
        
        System.out.println("\n\nPrint list of library items that "
                + "are overdue using Predicate:");
        tempList = lpTest.filter(libItems, item -> 
                item.dueDate.before(new GregorianCalendar(2017,4,26)));
        for (int i = 0; i < tempList.size(); i++) {
            System.out.println("The following items are past due: " 
                    + tempList.get(i).title);
        } 

        System.out.println("\n\nPrint list of books that "
                + "are overdue using Predicate:");
        tempList = lpTest.filter(libItems, item -> 
                item.dueDate.before(new GregorianCalendar(2017,4,26)));
        tempList2 = lpTest.filter(tempList, item -> 
                item.book == true);
        for (int i = 0; i < tempList2.size(); i++) {
            System.out.println("The following books are past due: " 
                    + tempList2.get(i).title);
        }         
        
    }
    
}

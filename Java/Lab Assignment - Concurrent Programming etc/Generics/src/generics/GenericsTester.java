
package generics;
import java.util.*;

public class GenericsTester {

    public static void main(String[] args) {
        
        LibraryCollection<Book> LCBook = 
                new LibraryCollection<Book>();
        LibraryCollection<DVD> LCDVD =
                new LibraryCollection<DVD>();
        LibraryCollection<Magazine> LCMag =
                new LibraryCollection<Magazine>();
        LibraryCollection<Magazine> LCMag2 =
                new LibraryCollection<Magazine>();

        // Add generic objects into Library Collection using add() method
        LCBook.addItem(new Book(new GregorianCalendar(2017, 4, 8),
                new GregorianCalendar(2017, 5, 8),
                "title1", "book1", "author1"));
        LCBook.addItem(new Book(new GregorianCalendar(2017, 4, 10),
                new GregorianCalendar(2017, 5, 10),
                "title2", "book2", "author2"));
        LCBook.addItem(new Book(new GregorianCalendar(2017, 3, 10),
                new GregorianCalendar(2017, 4, 10),
                "title3", "book3", "author3"));
             
        //
        LCDVD.addItem(new DVD(new GregorianCalendar(2016, 5, 7), 
                new GregorianCalendar(2016, 4, 8), "title1",
                "desc1", "producer1"));
        LCDVD.addItem(new DVD(new GregorianCalendar(2016, 7, 10),
                new GregorianCalendar(2016, 12, 12), "title2",
                "desc2", "producer2"));
        LCDVD.addItem(new DVD(new GregorianCalendar(2015, 3, 12),
                new GregorianCalendar(2015, 4, 18), "title3",
                "desc3", "producer3"));
        
        LCMag.addItem(new Magazine("title1", "producer1"));
        LCMag.addItem(new Magazine("title2", "producer2"));
        LCMag.addItem(new Magazine("title3", "producer3"));
        
        LCMag2.addItem(new Magazine("title4", "producer4"));
        LCMag2.addItem(new Magazine("title5", "producer5"));
        LCMag2.addItem(new Magazine("title6", "producer6"));

        
        // Checking if collection is empty
        System.out.println("Checking if collection of magazines is empty: "
                + LCMag.isEmpty());
               
        // Checking if collection of magazine is empty after clear method
        LCMag.clearItems();
        System.out.println("Checking if collection of magazines is empty "
                + "after using clear method: "
                + LCMag.isEmpty());        

        // Checking if collection of magazine is empty after using
        // addAll method to add LCMag2 collection into LCMag
        LCMag.addAllItem(LCMag2.LC);
        System.out.println("Checking if collection of magazines is empty "
                + "after using addAll method to add LCMag2 " +
                "collection into LCMag: "
                + LCMag.isEmpty());
        
        // Checking if collection contains object with
        // "new Magazine("title5", "producer5")" using contains() method    
        System.out.println("Checking if collection of magazines " +
                "contain object: new Magazine(\"title5\", \"producer5\") :"
                + LCMag.collectionContains(
                        new Magazine("title4", "producer5")));
        
        // Checking collection size of LCMag
        System.out.println("Checking collection size of LCMag: " +
                LCMag.collectionSize());
        
        // Checking if collection of magazine is empty after using
        // removeAll method on LCMag collection 
        LCMag.removeAllItems(LCMag.LC);
        System.out.println("Checking if collection of magazines is empty "
                + "after using removeAll method on LCMag collection: " 
                + LCMag.isEmpty());    
        
        // Checking collection size of LCMag again after removeAll
        System.out.println("Checking collection size of LCMag: " +
                LCMag.collectionSize());        
        
        // Using the getOverDueBook method in CheckOutCart class to see
        // which library items are overdue pass 4/12/2017
        System.out.println("\nChecking for books that are overdue " +
                "pass the date of 4/12/2017.");
        LCBook.OverDueBooks(new GregorianCalendar(2017,4,12));
              
        System.out.println("\nChecking for DVDs that are overdue " +
                "pass the date of 6/1/2016.");
        LCDVD.OverDueBooks(new GregorianCalendar(2016,6,1));
         
    }

}

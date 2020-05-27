/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarday;
import java.util.*;
/**
 *
 * @author doger
 */
public class CalendarTester {
    
    public static void main(String[] args){
        
        //Demonstration of Array.sort()
        System.out.println("Demonstration of Array.sort()");
        
        CalendarDay cal1 = new CalendarDay("12/6/2014");
        CalendarDay cal2 = new CalendarDay(9,6,2016);
        CalendarDay cal3 = new CalendarDay("8/15/1900");
        CalendarDay cal4 = new CalendarDay(5,6,1180);
        
        CalendarDay CDArray[]= {cal1, cal2, cal3, cal4};     
        
        Arrays.sort(CDArray);
        
        for (CalendarDay dateObj : CDArray) {
            System.out.println(dateObj);
        }
        //Demonstration of mutators and accessors, as well as
        // exception handling
        
        System.out.println("\n\n\nDemonstration of accessors, "
                + "and the toString() method");
        System.out.println(cal1.getDay());
        System.out.println(cal1.getMonth());
        System.out.println(cal1.getYear());
        System.out.println(cal1.toString());
        
        
        System.out.println("\n" + cal2.getDay());
        System.out.println(cal2.getMonth());
        System.out.println(cal2.getYear());
        System.out.println(cal2.toString());
        
        
        // Demonstration of mutators and exceptions
        System.out.println("\n\n\nDemonstration of mutators "
                + "and exceptions"); 
        System.out.println("Initializing calendarday object with day: 3, month: 13, " +
                "and year 999. Year 999 and month 13 are not allowed so it should throw an exception");
        

        CalendarDay cal6 = new CalendarDay(13,3,999);

        System.out.println("\n\nInitializing a number of mutators with invalid inputs." +
                "This will allow multiple chances to input a valid number.");
        try {
            cal6.setMonth(15);
        } catch (MonthException m) {
            m.printExc();
            m.reinput(cal6);
        }

        try {
            cal6.setDay(45);
        } catch (DayException d) {
            d.printExc();
            d.reinput(cal6);
        }
        
        try {
            cal6.setYear(999);
        } catch (YearException y) {
            y.printExc();
            y.reinput(cal6);
        }
        
        
        // demonstration of equal method
        System.out.println("\n\n\nDemonstration of equals()"); 
        System.out.println("cal1: " + cal1.toString());
        
        CalendarDay cal5 = new CalendarDay(cal1);
        
        System.out.println("cal5: " + cal5.toString());
        System.out.println(cal5.equals(cal1));
        
        
        
        // demonstration of comparable and compareTo()
        System.out.println("\n\n\nDemonstration of Comparable interface" +
                "and the compareTo() method"); 
        
        System.out.println("\nComparing cal2 to cal1 object");
        System.out.println("cal1: " + cal1.toString());
        System.out.println("cal2: " + cal2.toString());
        int test = cal2.compareTo(cal1);
        System.out.println(test);
                
        System.out.println("\nComparing cal5 to cal1 object");        
        System.out.println("cal1: " + cal1.toString());
        System.out.println("cal5: " + cal5.toString()); 
        int test2 = cal5.compareTo(cal1);
        System.out.println(test2);
    }
}

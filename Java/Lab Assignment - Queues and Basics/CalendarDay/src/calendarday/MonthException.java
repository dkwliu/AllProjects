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
public class MonthException extends Exception {
    
    int month;
    CalendarDay obj;
    
    public MonthException(int m, CalendarDay t) {
        month = m;
        obj = t;
    }
    
    public void printExc () {
        System.out.println("Month, " + month + ", is invalid");
    }
    
    public void reinput (CalendarDay o) {
        try {
            Scanner scan = new Scanner(System.in);
            int i = scan.nextInt();
            month = i;
            o.setMonth(i);
        } catch (MonthException m) {
            printExc();
            reinput(o);
        }
    }
}

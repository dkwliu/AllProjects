/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarday;

import java.util.Scanner;

/**
 *
 * @author doger
 */
public class YearException extends Exception {
    
    int year;
    CalendarDay obj;
    
    public YearException(int y, CalendarDay t) {
        year = y;
        obj = t;
    }
    
    public void printExc () {
        System.out.println("Year, " + year + ", is invalid");
    }
    
    public void reinput(CalendarDay o) {
        try {
            Scanner scan = new Scanner(System.in);
            int i = scan.nextInt();
            year = i;
            o.setYear(i);
        } catch (YearException y) {
            printExc();
            reinput(o);
        }
    }
}

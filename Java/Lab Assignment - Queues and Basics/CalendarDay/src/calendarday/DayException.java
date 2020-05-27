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
public class DayException extends Exception {
    
        int day;
        CalendarDay obj;
    
    public DayException(int d, CalendarDay t) {
        day = d;
        obj = t;
    }
    
    public void printExc () {
        System.out.println("Day, " + day + ", is invalid");
    }

    public void reinput(CalendarDay o) {
        try {
            Scanner scan = new Scanner(System.in);
            int i = scan.nextInt();
            day = i;
            o.setDay(i);
        } catch (DayException d) {
            printExc();
            reinput(o);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarday;

/**
 *
 * @author doger
 */

// implemented comparable interface
public class CalendarDay implements Comparable{

    private int day;
    private int month;
    private int year;
       
    // Constructor that takes a **/**/**** string
    public CalendarDay(String date) {
        splitDate(date);
    }
    
    // Constructor that takes a day, month, and year int
    public CalendarDay(int m, int d, int y) {
        try {
            setMonth(m);
        } catch (MonthException me){
            me.printExc();
            me.reinput(this);
        }
        
        try {
            setDay(d);
        } catch (DayException de) {
            de.printExc();
            de.reinput(this);
        }
        
        try {
            setYear(y);
        } catch (YearException ye) {
            ye.printExc();
            ye.reinput(this);
        }
    }
    
    // Split string date into day, month, and year
    public void splitDate(String cDate) {
        String delims = "[/]";
        String[] num = cDate.split(delims);
        try {
            setMonth(Integer.parseInt(num[0]));
        } catch (MonthException me){
            me.printExc();
            me.reinput(this);
        }
        
        try {
            setDay(Integer.parseInt(num[1]));
        } catch (DayException de) {
            de.printExc();
            de.reinput(this);
        }
        
        try {
            setYear(Integer.parseInt(num[2]));
        } catch (YearException ye) {
            ye.printExc();
            ye.reinput(this);
        }
    }
    
    // Copy constructor
    public CalendarDay(CalendarDay CD) {
        month = CD.month;
        day = CD.day;
        year = CD.year;
    }
    
    // mutator methods
    public void setDay(int day) throws DayException {
        if (month == 1 || month == 3 || month == 5 ||
                month == 7 || month == 8 || month ==10 ||
                month == 12) {
            if (day <= 31 && day >= 1) {
                this.day = day;
            } else {
                throw new DayException(day, this);
            }
        } else if (month == 4 || month == 6 || month == 9 ||
                month == 11) {
            if (day <= 30 && day >= 1) {
                this.day = day;
            } else {
                throw new DayException(day, this);
            }
        } else if (month == 2) {
            if (year % 4 == 0 ) {
                if (day <= 29 && day >= 1) {
                    this.day = day;
                } else {
                    throw new DayException(day, this);
                }
            } else {
                if (day <= 28 && day >= 1) {
                   this.day = day;
                } else {
                    throw new DayException(day, this);
                }
            }           
        } else {
            throw new DayException(day, this);        
        }
    }
    
    public void setMonth(int month) throws MonthException {
        if (month <= 12 && month >= 1) {
            this.month = month;
        } else {
            throw new MonthException(month, this);
        }
    }
    
    public void setYear(int year) throws YearException {
        if (year <=3000 && year >= 1000) {
            this.year = year;
        } else {
            throw new YearException(year, this);
        }
    }
    
    // Accessor methods
    public int getDay(){
        return day;
    }
    
    public int getMonth() {
        return month;
    }
    
    public int getYear() {
        return year;       
    }
    
    // ToString method
    public String toString() {
        return "day: " + day + " | month: " + month + " | year: " + year;
    }
    
    // Equals method override
    public boolean equals(Object o) {

        CalendarDay cd = (CalendarDay) o;
        
        if (toString().equals(cd.toString())) {
            return true;
        } else {
            return false;
        }
    }
    
    // compareTo method
    public int compareTo(Object o) {      
        
        CalendarDay cd = (CalendarDay) o;
        
        if (year < cd.year) {
            return -1;           
        } else if (year > cd.year){
            return 1;
        } else {
            if (month < cd.month) {
                return -1;
            } else if (month > cd.month) {
                return 1;
            } else {
                if (day < cd.day) {
                    return -1;
                } else if (day > cd.day) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }           
    }
}

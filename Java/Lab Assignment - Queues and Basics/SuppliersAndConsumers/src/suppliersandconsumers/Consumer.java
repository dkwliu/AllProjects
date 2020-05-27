/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suppliersandconsumers;
import java.util.*;
/**
 *
 * @author doger
 */
public class Consumer {
    private static int ID = 1;
    private double sleep;
    private int uniqueID;
    private double data;
    
    public Consumer() {
        uniqueID = ID; 
        ID +=1;
        execute(1);
    }
    
    public void execute(int i) {
        
        if (i == 1) {
            sleep();
        } else if (i == 2) {
            enter();
        }
    }
    
    // enters the meeting room and collects data or go into waiting
    public void enter() {
        MeetingRoom mr = new MeetingRoom();
        
        if (mr.getConsumerVacancy() == true) {
            mr.setConsumerVacancy(false);
            System.out.println("Consumer " + uniqueID + " enters meeting room");
            if (uniqueID == mr.getDataId()) {
                mr.setData(0);
                mr.setDataId(0);
                data = mr.getData();
                mr.setConsumerVacancy(true);
                System.out.println("Consumer " + uniqueID + " collects data");
                execute(1);
            } else {
                mr.setConsumerVacancy(true);
                System.out.println("Consumer " + uniqueID + " enters waiting room");
                waitRoom(this);
            }
        } else {
            execute(1);
            System.out.println("Consumer " + uniqueID + " could not enter room");
        }
    }
    
    //sleep method for consumer
    public void sleep() {
        sleep = Math.random() * 1000 + 1;
        long sleeplong = (new Double(sleep)).longValue();
        try {
            System.out.println("Consumer " + uniqueID + " will sleep " +
                    "for " + sleeplong + " milliseconds");
            Thread.sleep(sleeplong);           
        }catch(Exception e) {
            System.out.println("sleep interrupted");
        }
        execute(2);
    }
    
    // wait method
    public void waitRoom(Consumer c) {
        ConsumerWaitingRoom pwr = new ConsumerWaitingRoom(c);
        execute(1);
    }
    
    public int getId() {
        return uniqueID;
    }
    
    public double getData() {
        return data;
    }    
    
    public void setData(double d) {
        data = d;
    }
    
}

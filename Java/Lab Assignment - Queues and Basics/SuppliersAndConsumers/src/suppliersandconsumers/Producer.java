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
public class Producer {    
    private static int ID = 1;
    private double sleep;
    private int uniqueID;
    private double data;
    
    public Producer() {
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
 
    // enters the meeting room and unloads data for consumers to take
    public void enter() {
        MeetingRoom mr = new MeetingRoom();

        if (mr.getProducerVacancy() == true) {
            mr.setProducerVacancy(false);
            System.out.println("Producer " + uniqueID + " enters meeting room");
            if (mr.getData() > 0) {
                mr.setProducerVacancy(true);
                System.out.println("Producer " + uniqueID + " enters waiting room");
                waitRoom(this);
            } else {
                mr.setDataId(uniqueID);
                mr.setData(data);
                System.out.println("Producer " + uniqueID + " set data");
                mr.setProducerVacancy(true);
                execute(1);
            }
        } else {
            System.out.println("Consumer " + uniqueID + " could not enter room");
            mr.setProducerVacancy(true);
            execute(1);
        }
    }
    
    // sleep method for producer
    public void sleep() {
        data = Math.random() * 2000 + 2;
        sleep = Math.random() * 1000 + 1;
        long sleeplong = (new Double(sleep)).longValue();
        try {
            System.out.println("Producer " + uniqueID + " will sleep " +
                    "for " + sleeplong + " milliseconds");
            Thread.sleep(sleeplong);           
        }catch(Exception e) {
            System.out.println("sleep interrupted");
        }
        execute(2);
    }
    
    // wait method
    public void waitRoom(Producer p) {
        ProducerWaitingRoom pwr = new ProducerWaitingRoom(p);
        execute(1);
    }
    
    public int getId() {
        return uniqueID;
    }
    
    public double getData() {
        return data;
    }
}

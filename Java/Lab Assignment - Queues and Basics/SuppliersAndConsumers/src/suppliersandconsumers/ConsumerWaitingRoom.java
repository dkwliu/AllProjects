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
public class ConsumerWaitingRoom {
    
    private static ArrayList<Consumer> conList = new ArrayList<Consumer>();
    
    public ConsumerWaitingRoom(Consumer c) {
        addConsumer(c);
        waitingToCollect(c);
    }

    public void waitingToCollect(Consumer c) {
        MeetingRoom mr = new MeetingRoom();

        if (c.getId() == mr.getDataId()) {
            mr.setData(0);
            mr.setDataId(0);
            c.setData(mr.getData());
            mr.setConsumerVacancy(true);
            System.out.println("Consumer " + c.getId() + " collects data");
            removeConsumer();
        }

    }
    
    public void addConsumer(Consumer c) {
        conList.add(c);
    }
    
    public void removeConsumer() {
        conList.remove(0);
    }    
}

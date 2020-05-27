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
public class ProducerWaitingRoom {
    
    private static ArrayList<Producer> prodList = new ArrayList<Producer>();
    
    public ProducerWaitingRoom(Producer p) {
        addProducer(p);
        waitingToSet(p);
    }
    
    public void waitingToSet(Producer p) {
        MeetingRoom mr = new MeetingRoom();
        
        if (mr.getData() == 0 && mr.getDataId() == 0) {
            mr.setDataId(p.getId());
            mr.setData(p.getData());
            System.out.println("Producer " + p.getId() + " set data");
            mr.setProducerVacancy(true);
            removeProducer();
        }
        
    }
    
    public void addProducer(Producer p) {
        prodList.add(p);
    }
    
    public void removeProducer() {
        prodList.remove(0);
    }
}

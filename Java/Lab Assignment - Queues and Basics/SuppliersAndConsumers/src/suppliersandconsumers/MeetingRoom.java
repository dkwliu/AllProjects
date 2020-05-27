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
public class MeetingRoom {
    
    private static boolean consumerVacant = true;
    private static boolean producerVacant = true;
    private static double Data = 0;
    private static int DataId = 0;
    
    public MeetingRoom() {

    }
    
    public void setConsumerVacancy(boolean v) {
        consumerVacant = v;
    }
    
    public void setProducerVacancy(boolean v) {
        producerVacant = v;
    }
    
    public boolean getConsumerVacancy() {
        return consumerVacant;
    }
    
    public boolean getProducerVacancy() {
        return producerVacant;
    }
    
    public void setData(double s) {
        Data = s;
    }
    
    public void setDataId(int i) {
        DataId = i;
    }
    
    public double getData() {
        return Data;
    }
    
    public int getDataId() {
        return DataId;
    }
}

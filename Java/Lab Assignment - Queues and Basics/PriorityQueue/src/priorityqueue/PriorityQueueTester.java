/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priorityqueue;
import java.util.*;

/**
 *
 * @author doger
 */
public class PriorityQueueTester {

    public static void main(String[] args) {  
                 
        PriorityQueue pq = new PriorityQueue();
        ListIterator pqit = pq.getIterator();        
        
        // added T objects into arraylist
        System.out.println("List of T objects before calling the Remove() method: \n");
        pq.Add(1);
        pq.Add(3);
        pq.Add(9);
        pq.Add(6);
        pq.Add(10);
        
        for (int i = 0; i < pq.getArray().size(); i++) {
            System.out.println(pq.getArray().get(i));
        }
        
        // calling the Remove() method. It removes the highest priority object and returns it.

        System.out.println("\n\n\nList of T objects after calling the Remove() method: \n");        
        
        System.out.println("The highest priority object is " + pq.Remove());
        
        for (int i = 0; i < pq.getArray().size(); i++) {
            System.out.println(pq.getArray().get(i));
        }        
    }    
}

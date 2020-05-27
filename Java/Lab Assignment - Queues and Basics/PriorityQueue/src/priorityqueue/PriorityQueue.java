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
public class PriorityQueue <T extends Comparable<T>> {

    private ArrayList<T> PriorityQueue = new ArrayList<T>();
    
    // method to add a new object and prioritize it
    public void Add(T obj) {
        PriorityQueue.add(obj);
 
        T temp;
        int startit;

        for (int i = 0; i < PriorityQueue.size(); i++) {
            startit = i + 1;
            for (int x = startit; x < PriorityQueue.size(); x++) {
                if (compare(PriorityQueue.get(i), PriorityQueue.get(x)) == -1) {
                    temp = PriorityQueue.get(x);
                    PriorityQueue.set(x, PriorityQueue.get(i));
                    PriorityQueue.set(i, temp);
                }
            }
        }
    }
    
    // method to remove the highest priorty object and then return it
    public T Remove() {
        T obj = PriorityQueue.get(0);
        PriorityQueue.remove(0);
        return obj;
    }
    
    // getIterator method that returns an iterator
    public ListIterator<T> getIterator() {
        ListIterator<T> pobj = PriorityQueue.listIterator();
        
        return pobj;
    }
    
    // prints the entire arraylist with an iterator
    public void showArray () {
        ListIterator<T> objit = getIterator();
        
        while (objit.hasNext()) {
            T obj = objit.next();
            System.out.println(obj);
        }
    }
    
    // accessor for the arrraylist
    public ArrayList<T> getArray() {
        return PriorityQueue;
    }
    
    // uses the compareTo() method to compare to compare priority of objects
    public int compare(T obj1, T obj2) {
        return obj1.compareTo(obj2);
    }
    
}

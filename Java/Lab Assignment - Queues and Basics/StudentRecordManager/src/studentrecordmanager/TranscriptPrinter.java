/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentrecordmanager;

import java.util.*;
/**
 *
 * @author doger
 */
public class TranscriptPrinter {
//
//    StudentRecordManager srm = new StudentRecordManager();
//    ListIterator<Student> it = srm.getIterator();

    
    public void printTranscript(ListIterator<Student> li, int id) {
        while (li.hasNext()) {
            Student stud = li.next();
            
            if (stud.getID() == id) {
                System.out.println("\n" + stud.getfName() + " " + 
                        stud.getlName() + "'s transcript:" );                
                for (int i = 0; i < stud.getCourse().size(); i++) {
                    System.out.println(stud.getCourse().get(i).getSubject());                    
                }
            }
        }
    }
    
    public void printTranscript(ListIterator<Student> li, ArrayList<Integer> id) {
        while (li.hasNext()) {
            Student stud = li.next();
            
            for (int i = 0; i < id.size(); i++) {
                if (id.get(i) == stud.getID()) {
                    System.out.println("\n" + stud.getfName() + " "
                            + stud.getlName() + "'s transcript:");
                    for (int x = 0; x < stud.getCourse().size(); x++) {
                        System.out.println(stud.getCourse().get(x).getSubject());
                    }
                }
            }
            
            
        }        
    }
}

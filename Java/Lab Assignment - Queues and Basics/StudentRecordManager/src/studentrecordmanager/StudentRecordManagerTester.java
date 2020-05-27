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
public class StudentRecordManagerTester {
    
    public static void main(String[] args) {
        
        StudentRecordManager srm1 = new StudentRecordManager();
        TranscriptPrinter tp1 = new TranscriptPrinter();
        Student s1 = new Student("John", "Doe", "John@gmail.com");
        Student s2 = new Student("Jane", "Doe", "Jane@gmail.com");
        Student s3 = new Student("Bob", "Dole", "Bob@gmail.com");
        Student s4 = new Student("Pope", "John", "John@gmail.com");
        Student s5 = new Student("Random", "Guy", "asdf@gmail.com");
        Student s6 = new Student("Another", "Doe", "Doe@gmail.com");
        Student s7 = new Student("David", "Liu", "dav@gmail.com");

        srm1.addCourse(s1, "math");
        srm1.addCourse(s1, "science");
        
        srm1.addCourse(s2, "history");
        srm1.addCourse(s2, "science");
        
        
        srm1.addStudent(s1);
        srm1.addStudent(s2);
        srm1.addStudent(s3);
        srm1.addStudent(s4);
        srm1.addStudent(s5);
        srm1.addStudent(s6);
        srm1.addStudent(s7); 
        
        ListIterator<Student> it = srm1.getIterator();
         
        // Search student with the searchStudentID method
        System.out.println("Search student with the searchStudentID() method."
                + " Searching for student with ID 1002");
        srm1.searchStudentID(1002);

        // Search student with searchStudentName() method
        System.out.println("\n\n\nSearch student with the searchStudentName() method."
                + " Searching for student with John in name");
        srm1.searchStudentName("John");

        // Demonstrating the transcript printing methods        
        System.out.println("\n\n\nDemonstration of transcript print");   
        
        ArrayList<Integer> scl = new ArrayList<Integer>();
        scl.add(1000);
        scl.add(1001);
        System.out.println("This printer accepts an iterator and a list of student ids as arguments");
        tp1.printTranscript(it, scl);

        System.out.println("\n\nThis printer accepts an iterator and a specific student id as arguments");
        tp1.printTranscript(it, 1001);
    }

}

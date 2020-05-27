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
public class Student{
    private static int newID = 1000;
    private String firstName;
    private String lastName;
    private int ID;
    private String emailAddress;
    private ArrayList<Course> courseList = new ArrayList<Course>();

    public Student(String fN, String lN, String email) {
        firstName = fN;
        lastName = lN;
        emailAddress = email;
        ID = newID;
        newID += 1;
    }
    
    // copy constructor for Student class
    public Student(Student sd) {
        firstName = sd.firstName;
        lastName = sd.lastName;
        emailAddress = sd.emailAddress;
        ID = sd.ID;
        courseList = sd.courseList;
    }
    
    // accessors to get student info
    public String getfName() {
        return firstName;
    }
    
    public String getlName() {
        return lastName;
    }
    
    public int getID() {
        return ID;
    }
    
    public String getEmail() {
        return emailAddress;
    }
    
    public ArrayList<Course> getCourse() {
        return courseList;
    }
    
    //adds a course
    public void createCourse (String subj) {
        Course newCourse = new Course(subj);
        courseList.add(newCourse);
    }
       
    // iterates through the student's courses
    public void courseIterator () {
        Iterator<Course> courseIt = courseList.iterator();

        while(courseIt.hasNext()) {           
            Course course = courseIt.next();
            System.out.println(course.getSubject());
        }        
    }    
    
    
    // Inner course class to keep course details for students
    class Course {
        private String subject;
        
        public Course (String subj) {
            subject = subj;
        }

        // copy constructor for Course class
        public Course(Course c) {
            subject = c.subject;
        }
        
        public String getSubject() {
            return subject;
        }
    }
}

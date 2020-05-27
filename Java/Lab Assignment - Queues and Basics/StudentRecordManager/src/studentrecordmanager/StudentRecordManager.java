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
public class StudentRecordManager {
    
    private ArrayList<Student> studentList = new ArrayList<Student>();
    
    // add a student to the list
    public void addStudent(Student s) {
        getIterator().add(s);
    }
    
    // remove a student from the list
    public void removeStudent(Student s) {
        
        while (getIterator().hasNext()) {
            Student removable = (Student)getIterator().next();
            if (removable.getID() == s.getID()) {
                getIterator().remove();
            }
        }
    }
    
    // method to search students by ID
    public void searchStudentID(int id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getID() == id) {
                System.out.println("ID: "+ studentList.get(i).getID() 
                        + " || Name: " + studentList.get(i).getfName() +
                        " " + studentList.get(i).getlName() + 
                        " || email: " + studentList.get(i).getEmail());
            }
        }
    }
    
    // method to search for student by last name
    public void searchStudentName(String N) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getfName() == N || 
                    studentList.get(i).getlName() == N) {
                System.out.println("ID: "+ studentList.get(i).getID() 
                        + " || Name: " + studentList.get(i).getfName() +
                        " " + studentList.get(i).getlName());
            }
        }
    }
    
    // method to add course for a student
    public void addCourse(Student st, String subj) {
        st.createCourse(subj);
    }
    
    public ArrayList<Student> getStudentList() {
        return studentList;
    }
    
    // return method of iterator
    public ListIterator<Student> getIterator() {
        ListIterator<Student> studentIt = getStudentList().listIterator();
        
        return studentIt;
    }
    
}

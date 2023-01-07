/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */

import java.util.ArrayList;

public class Course {
    
    private static ArrayList<Student> students;
    private static Course instance = new Course();
    private Course(){
        students = new ArrayList<>();
    }
    
    public static ArrayList<Student> getStudents(){
        return students;
    }
    
}

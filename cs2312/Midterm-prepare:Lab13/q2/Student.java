
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jerrykwok
 */
public class Student {

    private String name;
    private char gender;
    private String programme;
    private ArrayList<Rule> rules;

    public Student(String name, char gender, String programme) {
        this.name = name;
        this.gender = gender;
        this.programme = programme;
        rules = new ArrayList<Rule>();
        addToCourse();
    }

    public String getName() {
        return name;
    }
    

    public char getGender() {
        return gender;
    }

    public String getProgramme() {
        return programme;
    }

    public void addToCourse() {
        Course.getStudents().add(this);
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public void listTargets() {
        System.out.print(name + ":");
        for (Student student : Course.getStudents()) {
            if (student == this) continue;
            if (rules.isEmpty()) {
                    System.out.print(" " + student.getName());
            }else{
                ArrayList<Boolean> chkResult = new ArrayList(rules.size());
                for(Rule rule: rules){
                    if(!rule.matchRule(this, student)){
                        chkResult.add(Boolean.FALSE);
                        
                    }
                }
                if(chkResult.contains(Boolean.FALSE))
                    continue;
                System.out.print(" " + student.getName());
            }
        }
        System.out.println();
    }

}

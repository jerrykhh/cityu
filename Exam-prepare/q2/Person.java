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
public class Person {
    private String name;
    private ArrayList<Group> groups;
    private static ArrayList<Person> persons = new ArrayList<Person>();

    public Person(String name, Group group){
        this.name = name;
        groups = new ArrayList<Group>();
        addGroup(group);
    }

    public String getName(){
        return name;
    }

    public void addGroup(Group group){
        groups.add(group);
    }

    public ArrayList<Group> getGroup(){
        return groups;
    } 

    public static void addPerson(Person person){
        persons.add(person);
    }

    public static void showHistory(String name){
        Person foundPerson = null;
        for (Person person : persons) {
            if(person.getName().equals(name)){
                foundPerson = person;
            }
        }

        System.out.print("[");
        for (int i = 0; i < foundPerson.getGroup().size(); i++) {
            System.out.print(foundPerson.getGroup().get(i).getTeamName());
            if(i != foundPerson.getGroup().size() - 1)
                System.out.print(" => ");
        }
        System.out.print("]");
    }
}


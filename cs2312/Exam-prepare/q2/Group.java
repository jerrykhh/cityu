import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Group {
    private String name;
    private String filePath;
    private ArrayList<Person> members;

    public Group(String name, String filePath) throws FileNotFoundException {
        this.name = name;
        this.filePath = filePath;
        members = new ArrayList<Person>();
        readTeamMember(filePath);
    }

    public String getTeamName(){
        return name;
    }

    private void readTeamMember(String filePath) throws FileNotFoundException {
        Scanner in = new Scanner(new File(filePath));
        int memberNumber = Integer.parseInt(in.nextLine());
        for(int i = 0; i < memberNumber; i++){
            Person person = new Person(in.nextLine(), this);
            members.add(person);
            Person.addPerson(person);
        }
    }

    public boolean existMember(String memberName){
        for (Person person : members) {
            if(person.getName().equals(memberName))
                return true;
        }
        return false;
    }

    public void show(){
        System.out.print("[" + name + ": " + members.get(0).getName() + "(leader), ");
        for(int i = 0; i < members.size() - 1; i++){
            System.out.print(members.get(i + 1).getName());
            if(i != (members.size()-2)) System.out.print(", ");
        }
        System.out.println("]");
        
    }

    private boolean existLeader(String memberName){
        if(members.get(0).getName().equals(memberName)){
            return true;
        }
        return false;
    }

    public Person removeMember(String mName) {
        Iterator<Person> itr = members.iterator();
        while (itr.hasNext()) {
            Person person = itr.next();
            if (person.getName().equals(mName)) {
                itr.remove();
                return person;
            }
        }
        return null;
    }

    public void addMember(Person person){
        members.add(person);
    }

    public static void changeTeam(String mName, Group currTeam, Group targetTeam) {
        if (!currTeam.existMember(mName)) {
            System.out.println("[No. " + mName + " is not found in " + currTeam.getTeamName() + "]");
            return;
        }
        if (currTeam.existLeader(mName)) {
            System.out.println("[No. " + mName + " is the leader in " + currTeam.getTeamName() + "]");
            return;
        }
        Person person = currTeam.removeMember(mName);
        person.addGroup(targetTeam);
        targetTeam.addMember(person);
        System.out.println("[OK]");
    }
}
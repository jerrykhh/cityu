import java.io.*;
import java.util.Scanner;

public class Attendance{

    private int[] students;
    private int[] attendees;
    public Attendance() throws FileNotFoundException{
        students = new int[20];
        attendees = new int[20];
        Scanner sStudents = new Scanner(new File("StudentList.txt"));
        Scanner sAttendees = new Scanner(new File("AttendanceLog.txt"));
        int i = 0;
        while(sStudents.hasNext()){
            students[i] = sStudents.nextInt();
            attendees[i] = sAttendees.nextInt();
            i++;
        }
        sStudents.close();
        sAttendees.close();
    }

    public boolean belongToClass(int id){
        boolean inClass = false;
        for(int student: students){
            if(student == id)
                inClass = true;
        }
        return inClass;
    }

    public boolean isPresent(int id){
        boolean isPresent = false;
        for(int student: attendees){
            if(student == id)
                isPresent = true;
        }
        return isPresent;
    }

    public void listAbsentees() {
        System.out.println("List of absentees:");
        int count = 0;
        for(int student: students){
            boolean isAttend = false;
            for(int attend: attendees){
                if(student == attend)
                    isAttend = true;
            }
            if(!isAttend){
                System.out.println(student);
                count++;
            }
        }
        System.out.print("Total count: " + count);
	}

	public void listWalkIn() {
		System.out.println("List of walk-in students:");
        for(int attend: attendees){
            boolean isWalkIn = true;
            for(int student: students){
                if(attend == student){
                    isWalkIn = false;
                }
            }
            if(isWalkIn)
                System.out.println(attend);
        }

	}
}
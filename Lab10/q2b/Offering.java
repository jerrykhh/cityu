import java.util.ArrayList;
public class Offering {
    private String semId;
    private ArrayList <Student> studentsList;
    
    public Offering(String semId){
        this.semId = semId;
        this.studentsList = new ArrayList<Student>();
    }

    public String getSemId(){
        return semId;
    }

    public void addStudent(Student s) {
		studentsList.add(s);
    }
    
    public ArrayList<Student> getStudentList(){
        return studentsList;
    }
    
}

public class Submission {
    private String work;
    private Student stu;

    public Submission(String work, Student stu) {
        this.work = work;
        this.stu = stu;
    }
    
    public String getWork(){
        return work;
    }
    
    public Student getStudent(){
        return stu;
    }
}

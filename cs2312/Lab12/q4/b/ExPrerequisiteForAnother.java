public class ExPrerequisiteForAnother extends Exception{
    public ExPrerequisiteForAnother(){
        super("Course Controller Exception!");
    }

    public ExPrerequisiteForAnother(String m){
        super(m);
    }
}

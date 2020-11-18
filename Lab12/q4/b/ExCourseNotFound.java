public class ExCourseNotFound extends Exception{
    public ExCourseNotFound() { super("Course not found!"); }
    public ExCourseNotFound(String message){super(message);}
}

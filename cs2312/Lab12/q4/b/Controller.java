import java.util.List;

public abstract class Controller {
    private List<Course> courseList;

    public Controller(List<Course> list) {
        courseList = list;
    }

    protected List<Course> getCourseList() {
        return courseList;
    }

    public void printCourses() {
        System.out.print("Course List: ");
        for (Course c : courseList) {
            System.out.printf("[%s] ", c);
        }
        System.out.println();
    }

    public abstract void process(Course c) throws ExCourseControl, ExCourseNotFound, ExPrerequisiteForAnother;
}

import java.util.List;

public class DropController {
    private List<Course> courseList;

    public DropController(List<Course> list) {
        courseList = list;
    }

    public void printCourses() {
        System.out.print("Course List: ");
        for (Course c : courseList) {
            System.out.printf("[%s] ", c);
        }
        System.out.println();
    }

    public void process(Course c) {
        courseList.remove(c);
    }
}
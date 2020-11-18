import java.util.List;
import java.util.LinkedList;

public class Programme {
    private List<Course> courseList;
    private AddController addAgent;
    private DropController dropAgent;

    public Programme() {
        courseList = new LinkedList<Course>();
        addAgent = new AddController(courseList);
        dropAgent = new DropController(courseList);
    }

    public void add(Course c) {
        addAgent.process(c);
        addAgent.printCourses();
    }

    public void drop(Course c) {
        try {
            dropAgent.process(c);
            dropAgent.printCourses();
        } catch (ExPrerequisiteForAnother e) {
            System.out.println(e.getMessage());
        } catch (ExCourseNotFound e) {
            System.out.println(e.getMessage());
        }
    }
}
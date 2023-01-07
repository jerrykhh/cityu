import java.util.List;

public class AddController extends Controller {
    public AddController(List<Course> list) {
        super(list);
    }

    @Override
    public void process(Course c) {
        if (getCourseList().contains(c))
            return;
        for (Course p : c.getPreRequisites())
            process(p);
        getCourseList().add(c);
    }
}
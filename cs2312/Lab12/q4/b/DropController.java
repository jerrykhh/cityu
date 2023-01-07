import java.util.List;

public class DropController extends Controller{

    public DropController(List<Course> list) {super(list); }
    
    @Override
    public void process(Course c) throws ExPrerequisiteForAnother, ExCourseNotFound {
        if (!getCourseList().contains(c))
            throw new ExCourseNotFound("Cannot drop " + c + " (Course doesn't exist in the list)");
        for (Course other: getCourseList())
            if (other.getPreRequisites().contains(c))
                throw new ExPrerequisiteForAnother("Cannot drop " + c + " (Required for " + other +")");
            getCourseList().remove(c);
    }
}
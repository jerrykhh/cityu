
import java.util.ArrayList;


public class Student { 

	private String name;
	private ArrayList<IApply> applications = new ArrayList<>();

	public Student(String name) { this.name = name; }

	public String getName() { return name; }

	public void addApplication(IApply a) {
		applications.add(a);
	}

	/* Your task: Add accept() and enquire() */ /* Unique code: 481765124267818134745 */

    public void accept(Application a1) {
        for(IApply app: applications){
            if(app.getApplication() == a1)
                app.accept();
        }
    }

    public void enquire() {
        for(IApply app: applications){
            System.out.print(app.getApplication().getStatus()+ "(" + app.getApplication().getProgramme().getName() + "==>" + name + ") ");
        }
        System.out.println();
    }


} 
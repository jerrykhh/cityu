
import java.util.ArrayList;

public class Programme { /* Unique code: 759426935458431890861 */

	private String name;
	private ArrayList<IAdmit> applications = new ArrayList<>();

	public Programme(String name) { this.name = name; }

	public String getName() { return name; }

	public void addApplication(IAdmit a) {
		applications.add(a);
	}

	/* Your task: Add offer() and generateReport()  */ /* Unique code: 799134936772028553525 */

    void offer(Application a1) {
        for(IAdmit app: applications){
            if(app.getApplication() == a1){
                app.offer();
            }
        }
    }

    void generateReport() {
        for(IAdmit app: applications){
            System.out.print(app.getApplication().getStatus()+ "(" + name + "==>" +  app.getApplication().getStudent().getName() + ") ");
        }
        System.out.println();
    }

} /* Unique code: 279822427415121959737 */

import java.util.ArrayList;

public class Programme { 

	private String name;
	private ArrayList<IAdmit> applications = new ArrayList<>();

	public Programme(String name) { this.name = name; }

	public String getName() { return name; }

	public void addApplication(IAdmit a) {
		applications.add(a);
	}


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

}
import java.util.ArrayList;
public class Team implements Comparable<Team>{
    private String teamName;
    private Employee head;
    private Day dateSetup; // the date this team was setup

    public Team(String n, Employee hd){
        teamName = n;
        head = hd;
        dateSetup = SystemDate.getInstance().clone();
    }

    public static void list(ArrayList<Team> list) {
        System.out.printf("%-15s%-10s%-13s\n", "Team Name", "Leader", "Setup Date");
        for (Team t : list){
            System.out.printf("%-15s%-10s%-13s\n", t.teamName, t.head.getName(), t.dateSetup);
        }
    }

    @Override
	public int compareTo(Team another){
		return this.teamName.compareTo(another.teamName);
	}
}

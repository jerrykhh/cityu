import java.util.ArrayList;
import java.util.Collections;

public class Team implements Comparable<Team> {
    private String teamName;
    private Employee head;
    private ArrayList<Employee> memberList;
    private Day dateSetup;

    public Team(String teamName, Employee headEmployee) {
        this.teamName = teamName;
        head = headEmployee;
        memberList = new ArrayList<Employee>();
        dateSetup = SystemDate.getInstance().clone();
    }

    public void addEmployee(Employee employee){
        memberList.add(employee);
        Collections.sort(memberList);
    }

    public void removeEmployee(Employee employee){
        memberList.remove(employee);
        Collections.sort(memberList);
    }

    public boolean findEmployee(Employee employee){
        return (memberList.contains(employee) || head.compareTo(employee) == 0);
    }
    

    public String getTeamName(){
        return teamName;
    }

    public Employee getHead(){
        return head;
    }

    public Day getDateSetup(){
        return dateSetup;
    }

    public ArrayList<Employee> getMemberList(){
        return memberList;
    }

    public int getMemberCount(){
        return memberList.size();
    }

    public String getMemberName(){
        String member = "";
        for (Employee employee : memberList) {
            member += employee.getName() + " ";
        }
        if(member == "") member = "(no member)";
        return member;
    }

    public static boolean searchTeamMember(ArrayList<Team> list, String searchMemberName){
        for (Team team : list) {
            if(team.getHead().getName().equals(searchMemberName)) return true;
            for (Employee employee : team.getMemberList()) {
                if(employee.getName().equals(searchMemberName))
                    return true;
            }
        }
        return false;
    }

    public static boolean searchTeamName(ArrayList<Team> list, String searchTeamName) {
        for(Team team: list){
            if(team.getTeamName().equals(searchTeamName))
                return true;
        }
        return false;
    }

    public static Team searchTeam(ArrayList<Team> list, String teamName) throws ExTeamNotExist{
        for (Team team : list) {
            if(team.getTeamName().equals(teamName))
                return team;
        }
        throw new ExTeamNotExist();
    }

    @Override
    public int compareTo(Team another) {
        return this.teamName.compareTo(another.teamName);
    }

}

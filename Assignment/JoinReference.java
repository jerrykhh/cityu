import java.util.ArrayList;
public class JoinReference {
    private Team team;
    private Employee employee;

    public JoinReference(Team team, Employee employee){
        this.team = team;
        this.employee = employee;
        team.addEmployee(employee);
    }

    public Team getTeam(){
        return team;
    }

    public Employee getEmployee(){
        return employee;
    }

    public void removeJoin(){
        team.removeEmployee(this.employee);
    }

    public void join(){
        team.addEmployee(this.employee);
    }

    public static boolean searchJoinedTeam(ArrayList<JoinReference> joinReferenceList, String searchEmplyeeName){
        for (JoinReference jReference : joinReferenceList) {
            for(Employee employee: jReference.getTeam().getMemberList()){
                if(employee.getName().equals(searchEmplyeeName))
                    return true;
            }
        }
        return false;
    }

    public static JoinReference searchJoinReference(ArrayList<JoinReference> joinReferenceList, String searchEmplyeeName) throws ExTeamNotExist{
        for (JoinReference joinReference : joinReferenceList) {
            if(joinReference.getEmployee().getName().equals(searchEmplyeeName)){
                return joinReference;
            }
        }
        throw new ExTeamNotExist();
    }

}

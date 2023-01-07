import java.util.ArrayList;
public class EmployeeJoinTeamLogging{
    private Team team;
    private Employee employee;
    private Day logDay;
    private Day changeDay;

    public EmployeeJoinTeamLogging(Team team, Employee employee){
        this.team = team;
        this.employee = employee;
        logDay = new Day(SystemDate.getInstance());
    }

    public void setChangeDay(Day day){
        this.changeDay = day;
        this.changeDay.setDay(changeDay.getDay() - 1);
    }
    
    public Team getTeam(){
        return team;
    }

    public Employee getEmployee(){
        return employee;
    }

    public Day getLogDay(){
        return logDay;
    }

    public Day getChangeDay(){
        return changeDay;
    }

    public static void EmployeeLogging(ArrayList<EmployeeJoinTeamLogging> list, String employeeName){
        System.out.println("The teams that " + employeeName + " has joined: ");
        for (EmployeeJoinTeamLogging logging : list) {
            if(logging.getEmployee().getName().equals(employeeName)){
                System.out.println(logging);
            }
        }
    }

    public String toString(){
        String changeDayString = (changeDay == null)? "--": changeDay.toString();
        String TeamLeaderString = (team.getHead() == employee)? ", as a leader" : "";

        return team.getTeamName() + " (" + logDay + " to " + changeDayString + ")" + TeamLeaderString;
    }



}

import java.util.ArrayList;
public class Project implements Comparable<Project>{
    private String projectId;
    private int estManpower;
    private Day startDay;
    private Team team;

    public Project(String projectId, int estManpower){
        this.projectId = projectId;
        this.estManpower = estManpower;
        team = null;
        startDay = null;
    }

    public String getProjectId(){
        return projectId;
    }
    
    public String getEstManpower(){
        return estManpower + " man-days";
    }

    public Team getTeam(){
        return team;
    }

    public Day getStartDay(){
        return startDay;
    }

    public Day getEndDay(){
         
    }

    public void setTeam(Team team){
        this.team = team;
    }

    public void setDay(Day day){
        this.startDay = day;
    }

    public static boolean searchProject(ArrayList<Project> projectList, String searchProjectId){
        for (Project project : projectList) {
            if(project.getProjectId().equals(searchProjectId))
                return true;
        }
        return false;
    }

    public static void listProject(ArrayList<Project> projectList){
        System.out.printf("%-9s%-14s%-13s%-13s%-13s\n", "Project", "Est manpower", "Team", "Start Day", "End Day");
        for(Project project:projectList){
            if(project.getTeam() == null)
                System.out.printf("%-9s%-14s%-13s%-13s%-13s\n", project.getProjectId(), project.getEstManpower(), "(Not Assigned)", "", "");
            else
                System.out.printf("%-9s%-14s%-13s%-13s%-13s\n", project.getProjectId(), project.getEstManpower(), project.getTeam().getTeamName(), project.getStartDay(), project.getEndDay());
        }
    }

    public static Project Project(ArrayList<Project> list, String projectId){
        for (Project project : list) {
            if(project.getProjectId().equals(projectId))
                return project;
        }
        throw new ExEmployeeNotFound();
    }

    @Override
    public int compareTo(Project another) {
        return this.getProjectId().compareTo(another.getProjectId());
    }
}

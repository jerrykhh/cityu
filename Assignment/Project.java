import java.util.ArrayList;
import java.util.Iterator;

public class Project implements Comparable<Project> {
    private String projectId;
    private int estManpower;
    private Day startDay;
    private Day endDay;
    private Team team;

    public Project(String projectId, int estManpower) {
        this.projectId = projectId;
        this.estManpower = estManpower;
        team = null;
        startDay = null;
    }

    public Project(Project project) {
        this.projectId = project.getProjectId();
        this.estManpower = project.getEstManpowerInt();
        this.startDay = project.getStartDay();
        this.endDay = project.getEndDay();
        this.team = project.getTeam();
    }

    public String getProjectId() {
        return projectId;
    }

    public int getEstManpowerInt() {
        return estManpower;
    }

    public String getEstManpower() {
        return estManpower + " man-days";
    }

    public Team getTeam() {
        return team;
    }

    public Day getStartDay() {
        return startDay;
    }

    public Day getEndDay() {
        return endDay;
    }

    public void setStartDay(Day startDay) {
        this.startDay = startDay;
        setEndDay(startDay);
    }

    public void setEndDay(Day startDay) {
        if (startDay != null) {
            int teamSize = (team.getMemberCount() == 0) ? estManpower
                    : (int) Math.ceil(estManpower * 1.0 / (team.getMemberCount() + 1) * 1.0);
            endDay = Day.plus(startDay, teamSize);
        } else
            endDay = null;

    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public static boolean searchProjectAlready(ArrayList<Project> projectList, String searchProjectId) {
        for (Project project : projectList) {
            if (project.getProjectId().equals(searchProjectId))
                return true;
        }
        return false;
    }

    public static void listProject(ArrayList<Project> projectList) {
        System.out.printf("%-9s%-14s%-13s%-13s%-13s\n", "Project", "Est manpower", "Team", "Start Day", "End Day");
        for (Project project : projectList) {
            if (project.getTeam() == null)
                System.out.printf("%-9s%-14s%-13s%-13s%-13s\n", project.getProjectId(), project.getEstManpower(),
                        "(Not Assigned)", "", "");
            else
                System.out.printf("%-9s%-14s%-13s%-13s%-13s\n", project.getProjectId(), project.getEstManpower(),
                        project.getTeam().getTeamName(), project.getStartDay(), project.getEndDay());
        }
    }

    public static Project searchProject(ArrayList<Project> list, String projectId) throws ExProjectNotExist {
        for (Project project : list) {
            if (project.getProjectId().equals(projectId))
                return project;
        }
        throw new ExProjectNotExist();
    }

    public static boolean checkTeamAvailable(ArrayList<Project> projectList, Team team, Project chkProject)
            throws ExProjectNotAvailable, ExProjectDateStartEarlist {
        int countProject = 0;
        int countAvailable = 0;
        for (Project project : projectList) {
            if (project.getTeam() != null && project.getTeam() == team) {
                if (Day.compareProjectAvaiable(project, chkProject)) {
                    countAvailable++;
                }
                countProject++;
            }
        }
        if (countProject != countAvailable)
            throw new ExProjectNotAvailable();

        return true;
    }

    public static ArrayList<Project> suggestTeam(ArrayList<Project> projectList, ArrayList<Team> teamList,
            Project urgentProject) {
        ArrayList<Team> teams = new ArrayList<Team>();
        ArrayList<Project> projects = new ArrayList<Project>();

        for (Team team : teamList) {
            ArrayList<Project> teamProjects = new ArrayList<Project>();
            for (Project project : projectList) {
                if (project.getTeam() == team) {
                    teamProjects.add(project);
                }
            }
            ArrayList<Project> tempAvaiableList = compareProjectInTeam(teamProjects, urgentProject);
            if (tempAvaiableList != null) {
                for (Project project : tempAvaiableList) {
                    projects.add(project);
                }
            }

        }

        Day minEndDay = projects.get(0).getEndDay();
        for (int i = 1; i < projects.size();) {
            if (projects.get(i).getEndDay().getDateInt() < minEndDay.getDateInt()) {
                minEndDay = projects.get(i).getEndDay();
            }
            i++;
        }
        Iterator<Project> it = projects.iterator();
        while (it.hasNext()) {
            Project project = it.next();
            if (project.getEndDay().getDateInt() != minEndDay.getDateInt())
                it.remove();
        }
        return projects;
    }

    public static ArrayList<Project> compareProjectInTeam(ArrayList<Project> list, Project urgentProject) {
        Day todayObj = new Day(SystemDate.getInstance());
        todayObj = todayObj.next();
        ArrayList<Project> tempProjectList = new ArrayList<Project>();
        for (Project project : list) {
            Project tempProject = new Project(urgentProject);
            tempProject.setTeam(project.getTeam());
            tempProject.setStartDay(todayObj);
            if (!Day.compareProjectAvaiable(project, tempProject)) {
                Day projectEndDay = new Day(project.getEndDay());
                projectEndDay.setDay(projectEndDay.getDay() + 1);
                tempProject.setStartDay(projectEndDay);
            }
            if (compareOtherProjectAvaiable(list, project, tempProject)) {
                tempProjectList.add(tempProject);
            }
        }
        return tempProjectList;
    }

    public static boolean compareOtherProjectAvaiable(ArrayList<Project> list, Project skipProject,
            Project urgentProject) {

        if (list.size() - 1 == 0)
            return true;
        for (Project project : list) {
            if (!Day.compareProjectAvaiable(project, urgentProject)) 
                return false;
        }
        return true;
    }

    @Override
    public int compareTo(Project another) {
        return this.getProjectId().compareTo(another.getProjectId());
    }

    public ArrayList<Employee> getProjectWorker(ArrayList<EmployeeJoinTeamLogging> loggingList){
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        for (EmployeeJoinTeamLogging logging : loggingList) {
            if(logging.getTeam() == team && logging.getEmployee() != this.team.getHead()){
                Employee employee = logging.getEmployee();
                employeeList.add(employee);
            }
        }
        return employeeList;
    }
}

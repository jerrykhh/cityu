import java.util.ArrayList;
import java.util.Collections;

public class Company {

    private ArrayList<Employee> allEmployees;
    private ArrayList<Team> allTeams;
    private ArrayList<Project> allProjects;
    private ArrayList<JoinReference> allJoinReferences;
    private static ArrayList<EmployeeJoinTeamLogging> allLogging = new ArrayList<EmployeeJoinTeamLogging>();

    private static Company instance = new Company();

    public Company() {
        allEmployees = new ArrayList<Employee>();
        allTeams = new ArrayList<Team>();
        allProjects = new ArrayList<Project>();
        allJoinReferences = new ArrayList<JoinReference>();
    }

    public static Company getInstance() {
        return instance;
    }

    public static void changeEmplyeeJoinTeamLogging(Team team, Employee employee) {
        for (EmployeeJoinTeamLogging logging : allLogging) {
            if (logging.getTeam() == team && logging.getEmployee() == employee) {
                logging.setChangeDay(new Day(SystemDate.getInstance()));
            }
        }
    }

    public Employee createEmployee(String name) throws ExEmployeeNameAlready {
        try {
            Employee employee = Employee.searchEmployee(allEmployees, name);
        } catch (ExEmployeeNotFound e) {
            Employee employee = new Employee(name);
            allEmployees.add(employee);
            Collections.sort(allEmployees);
            return employee;
        }
        throw new ExEmployeeNameAlready();
    }

    public void addEmployee(Employee employee) {
        allEmployees.add(employee);
        Collections.sort(allEmployees);
    }

    public void removeEmployee(Employee employee) {
        allEmployees.remove(employee);
        Collections.sort(allEmployees);
    }

    public void listEmployee() {
        allEmployees.forEach(employee -> System.out.println(employee + getTeamName(employee)));
    }

    public void showEmployeeDetails(String employeeName) {
        EmployeeJoinTeamLogging.EmployeeLogging(allLogging, employeeName);
    }

    private String getTeamName(Employee employee) {
        for (Team team : allTeams) {
            if (team.findEmployee(employee)) {
                return " (" + team.getTeamName() + ")";
            }
        }
        return "";
    }

    public void listTeam() {
        System.out.printf("%-15s%-10s%-14s%-20s\n", "Team Name", "Leader", "Setup Date", "Members");
        for (Team team : allTeams) {
            System.out.printf("%-15s%-10s%-14s%-20s\n", team.getTeamName(), team.getHead(), team.getDateSetup(),
                    team.getMemberName());
        }
    }

    public Team createTeam(String teamName, String headName)
            throws ExEmployeeJoinedTeam, ExEmployeeNotFound, ExTeamNameAlready {
        Employee employee = Employee.searchEmployee(allEmployees, headName);
        if (Team.searchTeamMember(allTeams, headName))
            throw new ExEmployeeJoinedTeam();
        if (Team.searchTeamName(allTeams, teamName))
            throw new ExTeamNameAlready();
        Team team = new Team(teamName, employee);
        allLogging.add(new EmployeeJoinTeamLogging(team, employee));
        allTeams.add(team);
        Collections.sort(allTeams);
        return team;
    }

    public void removeTeam(Team team) {
        allTeams.remove(team);
        Collections.sort(allTeams);
    }

    public void addTeam(Team team) {
        allTeams.add(team);
        Collections.sort(allTeams);
    }

    public JoinReference joinTeam(String employeeName, String teamName)
            throws ExEmployeeNotFound, ExEmployeeJoinedTeam, ExTeamNotExist {
        if (!Team.searchTeamName(allTeams, teamName))
            throw new ExTeamNotExist();
        for (Team team : allTeams) {
            if (team.getTeamName().equals(teamName)) {

                if (JoinReference.searchJoinedTeam(allJoinReferences, employeeName))
                    throw new ExEmployeeJoinedTeam();

                Employee employee = Employee.searchEmployee(allEmployees, employeeName);

                JoinReference joinReference = new JoinReference(team, employee);
                allJoinReferences.add(joinReference);
                allLogging.add(new EmployeeJoinTeamLogging(team, employee));
                return joinReference;
            }
        }

        throw new ExEmployeeNotFound();
    }

    public Employee searchEmployee(String employeeName) throws ExEmployeeNotFound {
        return Employee.searchEmployee(allEmployees, employeeName);
    }

    public Team searcTeam(String teamName) throws ExTeamNotExist {
        return Team.searchTeam(allTeams, teamName);
    }

    public JoinReference changeTeam(String employeeName, String teamName)
            throws ExEmployeeNotFound, ExEmployeeJoinedTeam, ExTeamNotExist, ExTeamSame {
        JoinReference newJoinReference;
        JoinReference removeJoinReference = JoinReference.searchJoinReference(allJoinReferences, employeeName);
        if (removeJoinReference.getTeam().getTeamName().equals(teamName))
            throw new ExTeamSame();

        changeEmplyeeJoinTeamLogging(removeJoinReference.getTeam(), removeJoinReference.getEmployee());
        removeJoinReference.removeJoin();
        allJoinReferences.remove(removeJoinReference);
        return joinTeam(employeeName, teamName);
    }

    public void addJoinReference(JoinReference joinReference) {
        joinReference.join();
        allJoinReferences.add(joinReference);
    }

    public void removeJoinReference(JoinReference joinReference) {
        joinReference.removeJoin();
        allJoinReferences.remove(joinReference);
    }

    public JoinReference searchJoinReference(String employeeName) throws ExTeamNotExist {
        return JoinReference.searchJoinReference(allJoinReferences, employeeName);
    }

    public Project createProject(String projectId, int estManpower) throws ExProjectAlready {
        if (Project.searchProjectAlready(allProjects, projectId))
            throw new ExProjectAlready();

        Project project = new Project(projectId, estManpower);
        allProjects.add(project);
        Collections.sort(allProjects);
        return project;
    }

    public void suggestTeam(String projectId) throws ExProjectNotExist {

        Project resultProject = Project.searchProject(allProjects, projectId);
        ArrayList<Project> tempProjectList = Project.suggestTeam(allProjects, allTeams, resultProject);
        tempProjectList.forEach(project -> System.out.println(project.getTeam().getTeamName() + " (Work period: "
                + project.getStartDay() + " to " + project.getEndDay() + ")"));
    }

    public Project takeProject(String teamName, String projectId, Day startDay) throws ExTeamNotExist,
            ExProjectNotExist, ExProjectAlready, ExProjectNotAvailable, ExProjectDateStartEarlist {

        Project project = Project.searchProject(allProjects, projectId);
        if (project.getTeam() != null)
            throw new ExProjectAlready("Project has already been assigned to a team.");
        Team team = Team.searchTeam(allTeams, teamName);
        Project tempProject = new Project(project);
        tempProject.setTeam(team);
        tempProject.setStartDay(startDay);
        try {
            Project.checkTeamAvailable(allProjects, team, tempProject);
        } catch (ExProjectNotAvailable e) {
            throw new ExProjectNotAvailable("The team is not available during the period (" + tempProject.getStartDay()
                    + " to " + tempProject.getEndDay() + ").");
        }
        project.setTeam(team);
        project.setStartDay(startDay);
        return project;
    }

    public void showProjectWorkerDetails(String projectId) throws ExProjectNotExist {
        Project project = Project.searchProject(allProjects, projectId);
        ArrayList<Employee> employees = project.getProjectWorker(allLogging);
        Collections.sort(employees);
        System.out.println("Est manpower : " + project.getEstManpowerInt() + " man-days");
        System.out.println("Team         : " + project.getTeam().getTeamName() + " (Leader is "
                + project.getTeam().getHead().getName() + ")");
        System.out.println("Work period  : " + project.getStartDay() + " to " + project.getEndDay());
        System.out.println("\nMembers:");
        int employeeIndex = 0;
        for (Employee employee : employees) {
            for (EmployeeJoinTeamLogging logging : allLogging) {
                if (logging.getTeam() == project.getTeam() && logging.getEmployee() == employee) {
                    Day printEndDay = (logging.getChangeDay() == null
                            || logging.getChangeDay().getDateInt() > project.getEndDay().getDateInt())
                                    ? project.getEndDay()
                                    : logging.getChangeDay();
                    Day printStartDay = null;
                    if (logging.getLogDay().getDateInt() >= project.getStartDay().getDateInt()
                            && logging.getLogDay().getDateInt() <= project.getEndDay().getDateInt())
                        printStartDay = logging.getLogDay();
                    else if (logging.getChangeDay() == null
                            || logging.getChangeDay().getDateInt() >= project.getStartDay().getDateInt()) {
                        printStartDay = project.getStartDay();
                    }
                    if (printStartDay == null)
                        continue;
                    System.out.println("  " + employee.getName() + "  (" + printStartDay + " to " + printEndDay + ")");
                }
            }

        }
    }

    public void removeProject(Project project) {
        allProjects.remove(project);
        Collections.sort(allProjects);
    }

    public void addProject(Project project) {
        allProjects.add(project);
        Collections.sort(allProjects);
    }

    public void listProjects() {
        Project.listProject(allProjects);
    }

}

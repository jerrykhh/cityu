import java.util.ArrayList;
import java.util.Collections;

public class Company {

    private ArrayList<Employee> allEmployees;
    private ArrayList<Team> allTeams;
    private ArrayList<Project> allProjects;
    private ArrayList<JoinReference> allJoinReferences;

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
        for (Team team : allTeams) {
            if (team.getTeamName().equals(teamName)) {
            
                if (JoinReference.searchJoinedTeam(allJoinReferences, employeeName))
                    throw new ExEmployeeJoinedTeam();
                
                Employee employee = Employee.searchEmployee(allEmployees, employeeName);

                if (!Team.searchTeamName(allTeams, teamName))
                    throw new ExTeamNotExist();

                JoinReference joinReference = new JoinReference(team, employee);
                allJoinReferences.add(joinReference);
                System.out.println(allJoinReferences.size());
                return joinReference;
            }
        }

        throw new ExEmployeeNotFound();
    }

    public Employee searchEmployee(String employeeName) throws ExEmployeeNotFound{
        return Employee.searchEmployee(allEmployees, employeeName);
    }

    public Team searcTeam(String teamName) throws ExTeamNotExist{
        return Team.searchTeam(allTeams, teamName);
    }

    public JoinReference changeTeam(String employeeName, String teamName)
            throws ExEmployeeNotFound, ExEmployeeJoinedTeam, ExTeamNotExist, ExTeamSame {
        JoinReference newJoinReference;
        JoinReference removeJoinReference = JoinReference.searchJoinReference(allJoinReferences, employeeName);
        if (removeJoinReference.getTeam().getTeamName().equals(teamName))
            throw new ExTeamSame();

        removeJoinReference.removeJoin();
        allJoinReferences.remove(removeJoinReference);
        return joinTeam(employeeName, teamName);
    }

    public void addJoinReference(JoinReference joinReference) {
        joinReference.join();
        System.out.println(joinReference.getEmployee().getName() + " " + joinReference.getTeam().getTeamName());
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
        if (Project.searchProject(allProjects, projectId))
            throw new ExProjectAlready();

        Project project = new Project(projectId, estManpower);
        allProjects.add(project);
        Collections.sort(allProjects);
        return project;
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

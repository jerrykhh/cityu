public class CmdTakeProject extends RecordedCommand{

    private Company company;
    private Team team;
    private Project project;
    private Day startDay;

    public void execute(String[] cmdParts) throws ExInsufficientArguments, ExTeamNotExist, ExProjectNotExist, ExProjectDateStartEarlist, ExProjectAlready, ExProjectNotAvailable, ExInvaildDate{
        if(cmdParts.length != 4)
            throw new ExInsufficientArguments();
        company = Company.getInstance();
        startDay = new Day(cmdParts[3]);
        Day.compareProjectStartDate(SystemDate.getInstance(), startDay);
        project = company.takeProject(cmdParts[1], cmdParts[2], startDay);
        pushUndo(this);
        System.out.println("Done.");
    }

    public void undo(){
        team = project.getTeam();
        project.setTeam(null);
        project.setStartDay(null);
    }

    public void redo(){
        project.setTeam(team);
        project.setStartDay(startDay);
    }
}

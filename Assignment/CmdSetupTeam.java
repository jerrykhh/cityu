public class CmdSetupTeam extends RecordedCommand {

    private Company company;
    private Team team;

    public void execute(String[] cmdParts) throws ExInsufficientArguments, ExEmployeeNotFound, ExEmployeeJoinedTeam {
        if (cmdParts.length < 3)
            throw new ExInsufficientArguments();

        try {
            company = Company.getInstance();
            team = company.createTeam(cmdParts[1], cmdParts[2]);
            pushUndo(this);
            System.out.println("Done.");
        } catch (ExTeamNameAlready e) {
            System.out.println(e);
        }
    }

    public void undo() {
        company.removeTeam(team);
    }

    public void redo() {
        company.addTeam(team);
    }
}

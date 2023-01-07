public class setupTeam extends RecordedCommand{
    private Team team;
    private Company company;

    public void execute(String[] cmdParts){
        company = Company.getInstance();
        team = company.createTeam(cmdParts[1], cmdParts[2]);
        clearRedoList();
        addUndoCommand(this);
        System.out.println("Done.");
    }

    public void undoMe(){
        company.removeTeam(team);
        addRedoCommand(this);
    }

    public void redoMe(){
        company.addTeam(team);
        addUndoCommand(this);
    }
}

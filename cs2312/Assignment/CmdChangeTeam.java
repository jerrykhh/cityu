public class CmdChangeTeam extends RecordedCommand{

    private Company company;
    private JoinReference newJoinReference;
    private JoinReference preJoinReference;

    public void execute(String[] cmdParts) throws ExInsufficientArguments, ExEmployeeNotFound, ExEmployeeJoinedTeam, ExTeamNotExist, ExTeamSame{
        if(cmdParts.length != 3)
            throw new ExInsufficientArguments();
        
        company = Company.getInstance();
        company.searchEmployee(cmdParts[1]);
        company.searcTeam(cmdParts[2]);
        preJoinReference = company.searchJoinReference(cmdParts[1]);
        newJoinReference = company.changeTeam(cmdParts[1], cmdParts[2]);
        pushUndo(this);
	System.out.println("Done.");
        
    }

    public void undo(){
        company.removeJoinReference(newJoinReference);
        company.addJoinReference(preJoinReference);
        
    }

    public void redo(){
        company.removeJoinReference(preJoinReference);
        company.addJoinReference(newJoinReference);
    }
}

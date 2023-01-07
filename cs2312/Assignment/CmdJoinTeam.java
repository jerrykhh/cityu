public class CmdJoinTeam extends RecordedCommand{
    
    private Company company;
    private JoinReference joinReference;

    public void execute(String[] cmdParts) throws ExEmployeeNotFound, ExTeamNotExist, ExEmployeeJoinedTeam, ExInsufficientArguments{
        if(cmdParts.length != 3)
            throw new ExInsufficientArguments();
        
        company = Company.getInstance();
        joinReference = company.joinTeam(cmdParts[1], cmdParts[2]);
        pushUndo(this);
        System.out.println("Done.");
    }

    public void undo(){
        joinReference.removeJoin();
    }

    public void redo(){
        joinReference.join();
    }
}

public interface Command {
    public void execute(String[] cmdParts) throws ExInsufficientArguments, ExEmployeeNotFound, ExEmployeeJoinedTeam, ExEmployeeNameAlready, ExTeamNotExist, ExTeamSame;
}
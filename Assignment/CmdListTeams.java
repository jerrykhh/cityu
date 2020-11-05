public class CmdListTeams implements Command{
    public void execute(String[] cmdParts){
        Company.getInstance().listTeam();
    }
}

public class CmdSuggestTeam implements Command{

    public void execute(String[] cmdParts) throws ExProjectNotExist{
        Company.getInstance().suggestTeam(cmdParts[1]);
    }
}

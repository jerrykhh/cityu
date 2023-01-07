public class CmdShowProjectWorkerDetails implements Command{
    public void execute(String[] cmdParts) throws ExProjectNotExist{
        Company.getInstance().showProjectWorkerDetails(cmdParts[1]);
    }
}

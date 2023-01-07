public class CmdListProjects implements Command{
    public void execute(String[] cmdParts){
        Company.getInstance().listProjects();
    }
}

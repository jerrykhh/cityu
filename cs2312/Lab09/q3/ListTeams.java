public class ListTeams implements Command {
    public void execute(String[] cmdParts){
        Company.getInstance().listTeams();
    }
}

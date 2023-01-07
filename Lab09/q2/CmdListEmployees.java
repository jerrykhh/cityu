public class CmdListEmployees {
    public void execute(String[] cmdParts) {
        Company.getInstance().listEmployee();
    }
}

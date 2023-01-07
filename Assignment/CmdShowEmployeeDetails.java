public class CmdShowEmployeeDetails implements Command{
    public void execute(String[] cmdParts){
        Company.getInstance().showEmployeeDetails(cmdParts[1]);
    }
}

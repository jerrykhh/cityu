public class ListAllRecords implements Command{
    public void execute(String[] cmdParts){
        Company company = Company.getInstance();
        company.listEmployees();
    }
}

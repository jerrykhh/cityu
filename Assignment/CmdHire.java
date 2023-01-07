public class CmdHire extends RecordedCommand{
    
    private Employee employee;
    private Company company;

    public void execute(String[] cmdParts) throws ExEmployeeNameAlready, ExInsufficientArguments{

        if(cmdParts.length != 2)
            throw new ExInsufficientArguments();

        company = Company.getInstance();
        employee = company.createEmployee(cmdParts[1]);
        pushUndo(this);
        System.out.println("Done.");
    }

    public void undo(){
        company.removeEmployee(employee);
    }

    public void redo(){
        company.addEmployee(employee);
    }

    

}

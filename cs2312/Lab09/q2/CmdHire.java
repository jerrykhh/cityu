public class CmdHire extends RecordedCommand{
    private Employee e;
    private Company company;

    public void execute(String[] cmdParts){
        company = Company.getInstance();
        e = company.createEmployee(cmdParts[1]);
        clearRedoList();
        addUndoCommand(this);
        System.out.println("Done.");
    }

    public void undoMe(){
        company.removeEmployee(e);
        addRedoCommand(this);
    }

    public void redoMe() {
        company.addEmployee(e);
        addUndoCommand(this);
    }
}

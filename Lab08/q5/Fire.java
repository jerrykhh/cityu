public class Fire extends RecordedCommand{

    private Employee e;

    public void execute(String[] cmdParts){
        e = Company.getInstance().findEmployee(cmdParts[1]);
        Company.getInstance().RemoveEmployee(e);
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    }

    public void redoMe(){
        Company.getInstance().RemoveEmployee(e);
        addUndoCommand(this);
    }

    public void undoMe(){
        Company.getInstance().addEmployee(e);
        addRedoCommand(this);
    }
}

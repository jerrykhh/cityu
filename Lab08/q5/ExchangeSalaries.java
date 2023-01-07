public class ExchangeSalaries extends RecordedCommand{

    private Employee e1, e2;

    public void execute(String[] cmdParts){
        e1 = Company.getInstance().findEmployee(cmdParts[1]);
        e2 = Company.getInstance().findEmployee(cmdParts[2]);
        e1.exchangeSalaries(e1, e2);

        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    }

    public void redoMe(){
        e1.exchangeSalaries(e2, e1);
        addUndoCommand(this);
    }

    public void undoMe(){
        e1.exchangeSalaries(e1, e2);
        addRedoCommand(this);
    }
}

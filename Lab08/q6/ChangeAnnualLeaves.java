public class ChangeAnnualLeaves extends RecordedCommand{
    
    private Employee e;
    private int changeDay;
    private int orignalDay; 
    
    public void execute(String[] cmdParts){
        Company company = Company.getInstance();
        e = company.findEmployee(cmdParts[1]);
        orignalDay = e.getAnnualLeaves();
        changeDay = Integer.parseInt(cmdParts[2]);
        e.changeAnnualLeaves(changeDay);

        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    }

    public void redoMe(){
        e.changeAnnualLeaves(changeDay);
        addUndoCommand(this);
    }

    public void undoMe(){
        e.changeAnnualLeaves(orignalDay);
        addRedoCommand(this);
    }
}

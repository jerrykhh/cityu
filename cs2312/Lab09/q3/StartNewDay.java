public class StartNewDay extends RecordedCommand{
    private Day dateObj;
    private String dateOrgrial;
    private String dateChange;
    public void execute(String[] cmdParts){
        dateObj = SystemDate.getInstance();
        try{
            dateOrgrial = dateObj.toString();
        }catch(NullPointerException e){
            dateOrgrial = null;
        }
        dateChange = cmdParts[1];
        SystemDate.getInstance().set(cmdParts[1]);

        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    } 

    public void undoMe(){
        if(dateOrgrial == null)
            dateObj.set(null);
        dateObj.set(dateOrgrial);
        addRedoCommand(this);
    }

    public void redoMe(){
        dateObj.set(dateChange);
        addUndoCommand(this);
    }
}

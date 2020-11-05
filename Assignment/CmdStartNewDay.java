public class CmdStartNewDay extends RecordedCommand{
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

        pushUndo(this);
        System.out.println("Done.");
    } 

    public void undo(){
        if(dateOrgrial == null)
            dateObj.set(null);
        dateObj.set(dateOrgrial);
    }

    public void redo(){
        dateObj.set(dateChange);
    }
}

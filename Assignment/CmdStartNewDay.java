public class CmdStartNewDay extends RecordedCommand {
    private Day dateObj;
    private String dateOrgrial;
    private String dateChange;

    public void execute(String[] cmdParts) {
        dateObj = SystemDate.getInstance();
        try {
            dateOrgrial = dateObj.toString();
            dateChange = cmdParts[1];
            SystemDate.getInstance().set(cmdParts[1]);
            pushUndo(this);
            System.out.println("Done.");
        } catch (ExInvaildDate e) {
            System.out.println(e.getMessage());
        } 
    }

    public void undo() {
        try {
            if (dateOrgrial == null)
                dateObj.set(null);
            dateObj.set(dateOrgrial);
        } catch (ExInvaildDate e) {
            System.out.println(e.getMessage());
        }
    }

    public void redo() {
        try {
            dateObj.set(dateChange);
        } catch (ExInvaildDate e) {
            System.out.println(e.getMessage());
        }
    }
}

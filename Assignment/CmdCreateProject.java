public class CmdCreateProject extends RecordedCommand {

    private Company company;
    private Project project;

    public void execute(String[] cmdParts) throws ExInsufficientArguments{
        if (cmdParts.length != 3)
            throw new ExInsufficientArguments();
        try {
            company = Company.getInstance();
            int estManpower = Integer.parseInt(cmdParts[2]);
            if (estManpower < 1)
                throw new ExProjectEstimatedPowerFormat("Consider changing " + estManpower + " to a positive non-zero amount.");
            project = company.createProject(cmdParts[1], estManpower);
            pushUndo(this);
            System.out.println("Done.");
        }catch(ExProjectAlready e){
            System.out.println(e.getMessage());
        }catch(NumberFormatException e){
            System.out.println("Wrong number format -- Please enter an integer.");
        }catch (ExProjectEstimatedPowerFormat e) {
            System.out.println("Estimated manpower should not be zero or negative.");
            System.out.println(e.getMessage());
        }
    }

    public void undo() {
        company.removeProject(project);
    }

    public void redo() {
        company.addProject(project);
    }
}

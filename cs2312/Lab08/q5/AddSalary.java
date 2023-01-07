public class AddSalary extends RecordedCommand {

   private Employee e;
   private int addAmount;

   public void execute(String[] cmdParts) {
      Company company = Company.getInstance();
      e = company.findEmployee(cmdParts[1]);
      addAmount = Integer.parseInt(cmdParts[2]);
      e.addSalary(addAmount);
      addUndoCommand(this);
      clearRedoList();
      System.out.println("Done.");
   }

   public void undoMe() {
      e.addSalary(-addAmount);
      addRedoCommand(this);
   }

   public void redoMe() {
      e.addSalary(addAmount);
      addUndoCommand(this);
   }
}

public class AddSalary extends RecordedCommand {

   private Employee e;
   private int addAmount;

   public void execute(String[] cmdParts) {
      try {
         if(cmdParts.length < 3)
            throw new ExInsufficientArguments();
         Company company = Company.getInstance();
         e = company.findEmployee(cmdParts[1]);
         addAmount = Integer.parseInt(cmdParts[2]);
         e.addSalary(addAmount);
         addUndoCommand(this);
         clearRedoList();
         System.out.println("Done.");
      } catch (NumberFormatException e) {
         System.out.println("Wrong number format.");
      } catch (ExEmployeeNotFound e) {
         System.out.println(e.getMessage());
      }catch (ExInsufficientArguments e) {
         System.out.println(e.getMessage());
      }
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

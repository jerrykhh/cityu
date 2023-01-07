import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.print("Please input the file pathname: ");
        String filepath = input.nextLine();
        Scanner inFile = new Scanner(new File(filepath));

        int tot = inFile.nextInt();
        Company company = Company.getInstance();
        for (int i = 0; i < tot; i++)
            company.addEmployee(new Employee(inFile.next(), inFile.nextInt(), inFile.nextInt()));

        while (inFile.hasNext()) {
            String cmdLine = inFile.nextLine();

            if (cmdLine.equals(""))
                continue;

            System.out.println("\n> " + cmdLine);

            String[] cmdParts = cmdLine.split(" ");

            if (cmdParts[0].equals("addSalary"))
                (new AddSalary()).execute(cmdParts);
            if (cmdParts[0].equals("list"))
                (new ListAllRecords()).execute(cmdParts);
            if (cmdParts[0].equals("changeAnnualLeaves"))
                (new ChangeAnnualLeaves()).execute(cmdParts);
            if (cmdParts[0].equals("undo")) 
                RecordedCommand.undoOneCommand();
            if (cmdParts[0].equals("redo")) 
                RecordedCommand.redoOneCommand();
            
        }
    }
}

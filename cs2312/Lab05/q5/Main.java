import java.util.Scanner;
import java.io.FileNotFoundException;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter the filename: ");
        String filepathname = in.next();
        PersonnelOffice po = PersonnelOffice.getInstance();
        po.loadEmployeeData(filepathname);
        System.out.println("\nTotal count: " + po.getTotal() + " records.\n");
        po.report();
		System.out.print("Enter percentage for raising salary: ");
        double raisSalary = in.nextDouble();
        po.raiseAllSalaries(raisSalary);
        System.out.println();
        po.report();
        in.close();
    }
}

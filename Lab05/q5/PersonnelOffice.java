import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonnelOffice {
	private ArrayList<Employee> allEmployees; // Encapsulated array list of employee/manager objects: private
												// ArrayList<Employee> allEmployees;

	private static PersonnelOffice thePO = new PersonnelOffice();

	public static PersonnelOffice getInstance() {
		return thePO;
	}

	// Return the total count of employees
	public int getTotal() {
		return allEmployees.size(); // Total count of employees/managers: allEmployees.size();
	}

	private PersonnelOffice() {
		allEmployees = new ArrayList<Employee>();
	}

	// Display a report of all salaries
	public void report() {
		double totalSalary = 0.0;
		for (Employee employee : allEmployees) { // Iterate through the array list based on allEmployees.size()
			System.out.println(employee.toStringSalaryDetails()); // Call the toStringSalaryDetails methods of
																	// employees/managers:
																	// allEmployees.get(i).toStringSalaryDetails()
			totalSalary += employee.getSalary();
		}
		System.out.println("==============================");
		System.out.printf("Total salary expense: %.2f\n\n", totalSalary);
	}


	// Read employee data from the given file, store in the allEmployees array
	public void loadEmployeeData(String filepathname) throws FileNotFoundException {
		allEmployees.clear(); // remove any existing employees: allEmployees.clear();

		Scanner inFile = new Scanner(new File(filepathname));

		while (inFile.hasNext()) {
			String id = inFile.next(); // Read the ID: inFile.next();

			if (id.charAt(0) == '9') // should be manager
			{
				String name = inFile.next(); // Read a name: inFile.next();
				double salary = inFile.nextDouble();
				double bonus = inFile.nextDouble();
				Manager m;
				m = new Manager(id, name, salary, bonus); // pass to the constructor: id, name, salary, bonus
				allEmployees.add(m); // Add the manager object: m
			} else {
				String name = inFile.next();
				double salary = inFile.nextDouble();
				Employee e;
				e = new Employee(id, name, salary);
				allEmployees.add(e);
			}
		}

		inFile.close();
	}

	public void raiseAllSalaries(double percentage) {
		for (Employee employee : allEmployees)
			employee.raiseSalary(percentage);
	}
}
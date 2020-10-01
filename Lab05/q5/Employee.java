import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Employee
{
	private String id;
	private String name;
	private double salary;

	//Constructor
	public Employee(String aId, String aName, double aSalary)
	{
		id = aId;
		name = aName;
		salary = aSalary;
	}

	//Accessor methods
	public String getId()	{return id;}
	public String getName()	{return name;}
	public double getSalary()	{return salary;}

	//Return a string representation for salary details
	public String toStringSalaryDetails() 
	{ 
		return String.format("[%s %s] Salary: %.2f", id, name, salary); //Compose the string: String.format("[%s %s] Salary: %.2f", id, ____, ____);
	}
	
	//Raise the salary by a given percentage (will be used later)
	public void raiseSalary(double byPercent)
	{
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	//Read employee data from the given file, return an arraylist of employees
	public static ArrayList<Employee> createEmployeeListFromFile(String filepathname) throws FileNotFoundException
	{
		Scanner inFile = new Scanner(new File(filepathname));

		ArrayList<Employee> arraylist_Staff; // Set up an arraylist variable: ArrayList<Employee> arraylist_Staff;
		
		arraylist_Staff = new ArrayList<Employee>(); //Create the array list: new ArrayList<Employee>()

		while (inFile.hasNext()) //As long as the file still has contents: inFile.hasNext()
		{
			String id = inFile.next();//inFile.next();
			String name = inFile.next();//inFile.next();
			double salary = inFile.nextDouble();//(Please figure out by yourself)
			
			Employee e; 
			e = new Employee(id, name, salary);
			arraylist_Staff.add(e);//Add e to the arraylist: arraylist_Staff.add(e);
		}

		inFile.close();
		return arraylist_Staff; //Return the arraylist: return arraylist_Staff;
	}
}
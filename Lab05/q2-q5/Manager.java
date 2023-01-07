public class Manager extends Employee
{
	//added field: Other than the basic salary in the Employee superclass, a manager has a bonus
	private double bonus; //private double bonus;

	//Constructor
	public Manager(String aId, String aName, double aSalary, double aBonus) //There should be 4 parameters now: String aId, String aName, double aSalary, double aBonus
	{
		super(aId, aName, aSalary); //call the constructor of the superclass, Employee: super(aId, aName, aSalary); 
		bonus = aBonus; //Set the bonus field: aBonus
	}

	//Return a string representation for salary details
	public String toStringSalaryDetails() 
	{ 
		return String.format("[%s %s] Basic salary: %.2f, Bonus: %.2f", 
        super.getId(), //super.getId(), 
        super.getName(), //super.getName(), 
				super.getSalary(), //super.getSalary(), 
				bonus); //bonus
	}

	//Accessor method - redefine the .getSalary method of the superclass, Employee
	public double getSalary() 
	{
		return super.getSalary() + bonus; //Basic salary: super.getSalary()
	}
}

public class Team 
{
	//Instance fields
	private Student[] students; //A reference to the array of students in the team: Student [] students;
	private String name; //A reference to the team name: String name;
	
	//Constructor
	public Team(String name, Student[] students) //Two input parameters: String name, Student[] students
	{
		this.name = name;
		this.students = students;
	}	
	
	//Return the String representation for the team: name + student list
	public String toString()
	{
		String result = name + ": "; //Task name: name
		for (int i=0; i < students.length; i++) //Loop through students[0..students.length-1]
		{
			result += "[" + students[i].toString() + "] "; //Student names: students[i].toString()
		}
		
		return result; //Return the result: return result;
	}
	
	//Return the team name
	public String getName()
	{
		return name;//Return the team name: return name;
	}
}
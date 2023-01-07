public class Task {
	
	//Instance fields
	private String name; //A reference to the task name: String name;
	
	//Constructor
	public Task(String name) //One input parameter: String name
	{
		this.name = name;
	}
	
	//Return the String representation 
	public String toString()
	{
		return name;//Return the task name: return name;
	}
}
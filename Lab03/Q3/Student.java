import java.io.*;
import java.util.Scanner;

public class Student {

	//Instance fields
	private String name; //A reference to the student name: String name;
	
	//Constructor
	public Student(String name) //One input parameter: String name
	{
		this.name = name;
	}
	
	//Return the String representation 
	public String toString()
	{
		return name;//Return the student name: return name;
	}
	
	//Static method for reading a list of students from the file and return an array of student objects
	public static Student[] createStudentListFromFile(String filepathname) throws FileNotFoundException
	{
		int count;
		
		Scanner inFile = new Scanner(new File(filepathname));
		
		count=inFile.nextInt(); //read the count
		inFile.nextLine(); //skip line break (otherwise the first .nextLine in the following loop will read an empty line)

		//Prepare the array of student object references
		Student[] result; //An array of students: Student[] result;
		result = new Student[count]; //Create the students array: new Student[count];		

		for (int i=0;i<count;i++)
		{
			String name = inFile.nextLine(); //Read a line of student name: inFile.nextLine();
			result[i] = new Student(name); //Create a student object for result[i]: new Student(name);
		}
		
		inFile.close();//Close the file: inFile.close();
		return result;//Return the result: return result;
	}
	
}
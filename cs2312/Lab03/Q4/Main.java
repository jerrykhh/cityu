import java.util.*;
import java.io.*;

public class Main{
	
	public static void main(String [] args) throws FileNotFoundException
	{	
		System.out.print("Please input the file pathname: ");
		Scanner in = new Scanner(System.in);
		String filepathname = in.nextLine();
		Student[] students; //Define an array of students: Student[] students;
		students = Student.createStudentListFromFile(filepathname); 

		System.out.println("Total number of students: " + students.length);
		
		
		System.out.print("Enter the number of teams: ");
		int numberOfTeam = Integer.parseInt(in.nextLine());
		if(students.length % numberOfTeam != 0){
			System.out.println("Wrong input - It is not a factor of " + students.length +".");
			return;
		}

		
		Team[] teams; //Define an array of teams: Team [] 
		teams = createTeams(students, numberOfTeam);//Call the private method (one of the 3 methods in Main.java) to create the teams for students: createTeams(students);
		
		

		//Print the grouping result:
		System.out.println("\nGrouping result: ");				
		for(Team team: teams){
			System.out.println(team.toString());
		}
		//Create the assignments
		Assignment[] assignments; //Define an array of assignments: Assignment[] assignments 
		assignments = decideTasks(teams, in);//Call the private method (one of the 3 methods in Main.java) to input the tasks for each team: decideTasks(teams, in); 
		

		//Display sorted listing by tasks:
		System.out.println("\nSorted listing by tasks: ");

		for(String task:sortTasks(assignments)){
			Assignment.printTaskTeam(task, assignments);
		}
		
		in.close();
	}

	private static String[] sortTasks(Assignment[] assignments){

		String[] result = new String[assignments.length];
		for(int i = 0; i < assignments.length; i++){
			result[i] = assignments[i].getTaskName();
		}
		Arrays.sort(result);

		return result;
	}
	
	private static Assignment[] decideTasks(Team[] teams, Scanner in)
	{
		Assignment[] assignments; //Define an array of assignments to store the result: Assignment[] assignments;
		assignments = new Assignment[teams.length]; //Create the array: new Assignment[5];	
		
		System.out.print("\nEnter " + teams.length +" task names (eg. \"Lab05 Lab06 Lab07 ..\"): ");	
		String inputTaskNames = in.nextLine();
		inputTaskNames = inputTaskNames.replace(" ", ",");
		System.out.println("\nEnter tasks for the teams (" + inputTaskNames + "):");

		for (int i=0; i < assignments.length; i++)
		{
			System.out.print(teams[i].getName()+ ": "); //Prompt for a team: teams[i].getName()
			String taskName = in.next(); //Get user input of the task name from keyboard: in.next();
			assignments[i] = new Assignment(teams[i], new Task(taskName)); //Create the assignment for:  teams[i], new Task(taskName)
		}
		return assignments; //Return the result: return assignments;
	}

	
	private static Team[] createTeams(Student[] students, int numberOfTeam) 
	{
		Team[] result; 
		result = new Team[numberOfTeam];
		int peoplesOfTeam = students.length / numberOfTeam;

		for(int i = 0; i < result.length; i++){
			char teamLetter = (char)('A' + i);
			result[i] = new Team("Team " + teamLetter, Arrays.copyOfRange(students, peoplesOfTeam * i, peoplesOfTeam * i + peoplesOfTeam));
		}

		return result; 
	}
    
}
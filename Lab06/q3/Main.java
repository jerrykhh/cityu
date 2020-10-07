import java.util.*;
import java.io.*;

public class Main{

	public static void main(String [] args) throws FileNotFoundException
	{	
		Scanner in = new Scanner(System.in);
		
		System.out.print("Please input the file pathname: ");
		String filepathname = in.nextLine();
		
		Team t = new Team(filepathname); //create the team: new ______________________;
		
		System.out.printf(
			"\nThere are %d members in the team: %s\n\n", 
			t.getMemberCount(), t.getStringOfAllMembers()); //call Team methods to get the results: getMemberCount, getStringOfAllMembers
		
		System.out.println("Messages for team contacts: ");
		t.printTeamContactMessages(); //call Team method: printTeamContactMessages
		
		in.close();
	}
}
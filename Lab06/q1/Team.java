import java.util.*;
import java.io.*;

public class Team{
	
	//Instance data field
	Member[] allMembers;
	
	//Constructor	
	public Team(String filepathname) throws FileNotFoundException
	{
		Scanner inFile = new Scanner(new File(filepathname));
		
		int tot = inFile.nextInt(); //Read from file: nextInt();
		inFile.nextLine(); //skip line break after the count: inFile.nextLine(); 
		allMembers = new Member[tot]; //Create the array: new Member[tot];

		for (int i = 0; i < allMembers.length; i++) //loop how many times?
		{
			String name = inFile.next(); //Read from file: .next();
			allMembers[i] = new Member(name); // Create a member object: new ____________;
		}
				
		inFile.close(); //close the file
	}
	
	//Return total count of members (simply allMembers.length)
	public int getMemberCount()
	{
        return allMembers.length;
	}
	
	//Return a string of listing of all members
	public String getStringOfAllMembers()
	{
		String result="";
		for (Member member: allMembers) //loop for each member
				result+= member.getName() + " ";// allMembers[i].getName()+" ";
		result=result.trim(); //.trim() is for removing leading and trailing spaces 
		return result;
	}

	//Display team contact messages
	public void printTeamContactMessages()
	{
		String allNames = getStringOfAllMembers(); //obtains a string like: "Helena Peter Mary Paul"
		for (int i = 0; i < getMemberCount(); i++) //loop for each member
		{
			String name_i = allMembers[i].getName(); //e.g. "Helena"
			System.out.print("Dear " + name_i);
			String teammates = getStringOfAllMembers().replace(name_i, ""); //e.g. "Peter Mary Paul". Use a trick: create a string based on allNames, but remove name_i: allNames.replace(name_i, "");
			System.out.println(",  please contact your teammates: " + teammates); //teammates
		}
	}	
}
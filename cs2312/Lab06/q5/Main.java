import java.util.*;
import java.io.*;

public class Main{

	public static void main(String [] args) throws FileNotFoundException
	{	
		Scanner in = new Scanner(System.in);
		
		System.out.print("Please input the file pathname of each team: ");
		String filepathname = in.nextLine();
		
		String[] files = filepathname.split(" ", -1);
		List<Team> teamList = new ArrayList<Team>();

		for(String file: files){
			Team t = new Team(file);
			teamList.add(t);
		}
		System.out.println("Listing of teams:");
		int index = 1;
		for(Team team: teamList){
			System.out.println("[Team " + index++ + "] " + team.getMemberCount() + " members: " + team.getStringOfAllMembers());
		}

		System.out.print("Enter a name for searching: ");
		System.out.println();
		String searchName = in.nextLine();
		index = 1;
		boolean isFind = false;
		for(Team team: teamList){
			Member findResult = team.findMember(searchName);
			if(findResult != null){
				String type = "a normal member";
				if(findResult.getRole() instanceof RLeader){
					type = "the leader";
				}else if (findResult.getRole() instanceof RDisappeared){
					type = "a disappeared member";
				}
				System.out.println("Result: " + searchName + " is " + type + " in Team " + index);
				isFind = true;
			}
			index++;
		}
		if(!isFind)
			System.out.println("Result: Not found");
		
		in.close();
	}
}
public class RNormalMember implements Role//It should be: public class RNormalMember implements Role 
{
	//Generate team contact message for the member
	public String genTeamContactMsg(Team team)
	{
		Member leader = team.getLeader();; //call team.getLeader();
		if (leader==null) //is it null? compare with: null
			return "There is no leader in your team.  Please contact your instructor.";
		else
			return "Please contact your leader: " + leader.getName(); //call leader.getName();
	}
	
	//Return name[+role] strings
	public String getNameAndRole(Member member)
	{
		return member.getName(); // simply: member.getName();
	}	
}
public class Member 
{
	//Instance fields
    private String name;
    private Role role;
    
    Member(String name, Role role){
        this.name = name;
        this.role = role;
    }
	
	//Constructor
    
    public String getName(){
        return name;
    }
	
	//Assessor method
	public Role getRole(){
        return role;
    }
	
}
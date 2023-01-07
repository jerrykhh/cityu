import java.util.Scanner;

public class Main
{
	public static void main(String[] args) 
	{
		Student helena = new Student("50123456"); //ID is 50123456
		Student kit = new Student("50888999");
		Student jason = new Student("50101010");
		
		Course course = new Course("CS2312");
		course.addOffering("2012-13A");
		course.addOffering("2012-13B");
		course.addOffering("2013-14A");
		course.addOffering("2013-14B");
		
		helena.takeCourse(course, "2013-14A");
		kit.takeCourse(course, "2012-13B");
		jason.takeCourse(course, "2012-13B");
		
		System.out.println("DEFAULT TESTING:");
		System.out.println(helena.hasBeenClassmateOf(kit));//show boolean: false
		System.out.println(helena.hasBeenClassmateOf(jason));//show boolean: false
		System.out.println(kit.hasBeenClassmateOf(jason));//show boolean: true		
		
		//More testing: add Michael and when he took CS2312
		System.out.println("\nMORE TESTING:");
		Student michael = new Student("50999000");
		Scanner in = new Scanner(System.in);
		System.out.print("Type the semesters which Michael took CS2312: ");
		String s = in.nextLine(); //user's input, e.g. "2012-13A 2012-13B 2013-14B"
		String allSem[] = s.split(" ");
		for (String e:allSem)
			michael.takeCourse(course, e);		
		System.out.println(michael.hasBeenClassmateOf(helena));//show boolean: false
		System.out.println(michael.hasBeenClassmateOf(kit));//show boolean: true
		System.out.println(michael.hasBeenClassmateOf(jason));//show boolean: true		
		in.close();		
		
/* Sample Run-down:
DEFAULT TESTING:
false
false
true

MORE TESTING:
Type the semesters which Michael took CS2312: 2012-13A 2012-13B 2013-14B
false
true
true
*/
	}
}
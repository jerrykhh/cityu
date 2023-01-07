import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException 
	{
		Scanner in = new Scanner(System.in); //create a scanner object for keyboard input

		System.out.print("Please enter the filename: ");
		String filepathname = in.next(); //Read user input: in.next();
		
		Day[] days= Day.createDayListFromFile(filepathname);
		
		System.out.println("\nTotally " + days.length + " entries:");
		
		for(int i = 0 ; i < days.length; i++)
			System.out.println( (i + 1) +". "+ days[i].getDay() + "/" + days[i].getMonth() + "/" + days[i].getYear());
        

		in.close();
    }
}
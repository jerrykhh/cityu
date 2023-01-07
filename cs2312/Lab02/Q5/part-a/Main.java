import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner s = new Scanner(System.in);

		Attendance at = new Attendance();
		
		System.out.print("Type the student ID to search: ");
		int id = s.nextInt();
		if (at.belongToClass(id)==false)
			System.out.println("Result: Not belong to the class.\n");
		else {
			if (at.isPresent(id))
				System.out.println("Result: The student is present.\n");
			else
				System.out.println("Result: No show!\n");
		}
		
		s.close();

	}

}
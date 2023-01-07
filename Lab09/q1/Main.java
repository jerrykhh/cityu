import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		SystemDate.createTheInstance("01-Jan-2020");

		Company co = Company.getInstance();
		co.createEmployee("Bob");
		co.createEmployee("John");
		co.createEmployee("Grace");

		co.createTeam("X Troop", "Bob");
		co.createTeam("Spider Gang", "John");

		SystemDate.getInstance().set("01-Feb-2020"); // Advance to 1/2/2020
		co.createTeam("Team 007", "Grace");

		co.listTeams();
	}
}
import java.io.FileNotFoundException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Main { 
	public static void main(String[] args) throws FileNotFoundException {

		Group a = new Group("Ace Team", "ace.txt");
		Group b = new Group("Best Gang", "best.txt");
		Group c = new Group("The Crown", "crown.txt");

		a.show(); // Output [Ace Team: Mary(leader)]
		b.show(); // Output [Best Gang: Brian(leader), Bessy, Bagel, Bill, Bell]
		c.show(); // Output [The Crown: Pinky(leader), Paul]

		if (a.existMember("Paul")) { System.out.println("Paul is found."); } // No output 
		if (b.existMember("Paul")) { System.out.println("Paul is found."); } // No output
		if (c.existMember("Paul")) { System.out.println("Paul is found."); } // Output [Paul is found.]

		Group.changeTeam("Mary", b, c); // Output [No. Mary is not found in Best Gang.]
		Group.changeTeam("Mary", a, b); // Output [No. Mary is the leader in Ace Team.]
		Group.changeTeam("Paul", c, a); // Output [OK]
		Group.changeTeam("Paul", a, b); // Output [OK]
		Person.showHistory("Paul"); // Output [The Crown => Ace Team => Best Gang]
	}
} 


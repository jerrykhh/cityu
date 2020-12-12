/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Main { /* Unique code: 752940388629254026665 */

	public static void main(String [] args) {	

		Staff s1 = new Staff("Helena");
		Staff s2 = new Staff("Tommy");
		Staff s3 = new Staff("Jason");
		Staff s4 = new Staff("Mary");
		Staff s5 = new Staff("Ann");

		s1.setHelper(s2); //Helena's helper is Tommy
		s2.setHelper(s3); //Tommy's helper is Jason; Jason has no helper

		s4.setHelper(s5); //Mary's helper is Ann;  Ann has no helper

		Job a = new Job("Job A");
		Job b = new Job("Job B");
		Job c = new Job("Job C");
		Job d = new Job("Job D");
		Job e = new Job("Job E");
		Job f = new Job("Job F");
	
		s5.assignTask(a); 	// Output [Ann will do Job A]
		s1.assignTask(b); 	// Output [Jason will do Job B]
		s1.assignTask(c); 	// Output [Tommy will do Job C]
		s3.finishJob(); 	// Output [Jason is free now]
		s1.assignTask(d); 	// Output [Jason will do Job D]
		s1.assignTask(e); 	// Output [Helena will do Job E]
		s1.assignTask(f); 	// Output [No manpower]
		s3.finishJob(); 	// Output [Jason is free now]
		s2.finishJob(); 	// Output [Tommy is free now]
		s1.assignTask(f); 	// Output [Jason will do Job F]
	}		
} /* Unique code: 500433528675833875201 */
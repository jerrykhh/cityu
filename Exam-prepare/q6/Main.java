public class Main { /* Unique code: 808572258112903734143 */

	public static void main(String[] args) {
		Student s1 = new Student("Helena");
		Student s2 = new Student("Jason");
		Programme p1 = new Programme("CS"); //Programme of Computer Science
		Programme p2 = new Programme("CM"); //Programme of Creative Media
		
		Application a1 = new Application(s1, p1);
		Application a2 = new Application(s1, p2);
		Application a3 = new Application(s2, p1);
		Application a4 = new Application(s2, p2);
		
		p1.offer(a1);
		p2.offer(a2);
		p1.offer(a3);

		s1.accept(a1); 
		s1.enquire();       	//Output [Accepted(CS==>Helena) Offered(CM==>Helena) ]
		s2.enquire();       	//Output [Offered(CS==>Jason) Pending(CM==>Jason) ] 
		
		p1.generateReport(); 	//Output [Accepted(CS==>Helena) Offered(CS==>Jason) ]
		p2.generateReport(); 	//Output [Offered(CM==>Helena) Pending(CM==>Jason) ]
	}
} /* Unique code: 157888733207857340936 */





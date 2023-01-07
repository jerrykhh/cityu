public class Main {
    public static void main(String[] args) {
		
		// Three students
		Student amy = new Student("Amy");
		Student jason = new Student("Jason");
		Student bill = new Student("Bill");

		// Contents of student work
		String wAmy = "Amy's work";
		String wJason = "Jason's work";
		String wBill = "Bill's work";

		// Each student submits a piece of work to TurnWorkIn
		// Note: Jason's work is submitted by both jason and bill!!
		amy.submitWork(wAmy); 	// TurnWorkIn outputs [OK] 
		jason.submitWork(wJason); 	// TurnWorkIn outputs [OK]
		bill.submitWork(wJason);	// TurnWorkIn outputs [Plagiarism: Jason's work (submitted by Bill), Jason's work (submitted by Jason)]

	}
}

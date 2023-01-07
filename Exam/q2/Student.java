public class Student {
    private String name;

	public Student(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void submitWork(String work) {
		TurnWorkIn.getInstance().receiveSubmission(new Submission(work,this));
	}
}

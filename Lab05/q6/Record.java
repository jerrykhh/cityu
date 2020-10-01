public class Record {

	private String id;
	private String area;
	private int age;

	public Record(String id, String area, int age) {
		this.id=id;
		this.area=area;
		this.age=age;
	}

	public String getId() {return id;}
	public String getArea() {return area;}
	public int getAge() {return age;}

}
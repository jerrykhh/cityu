import java.util.ArrayList;

public class Course {
    private String code;
    private ArrayList<Offering> offerList;

    public Course(String code){
        this.code = code;
        offerList = new ArrayList<Offering>();
    }

    public String getCode(){
        return code;
    }

    public ArrayList<Offering> getOfferList(){
        return offerList;
    }

    public void addOffering(String semseter){
        offerList.add(new Offering(semseter));
    }

    public Offering getOffering(String semId){
        for (Offering offering : offerList) {
            if(offering.getSemId().equals(semId))
                return offering;
        }
        return null;
    }

    public void listStudents(){
        for (Offering offering : offerList) {
            System.out.print(offering.getSemId()+ ": ");
            offering.getStudentList().forEach( student -> System.out.print(student.getStudentId() + " "));
            System.out.println();
        }
    }
}

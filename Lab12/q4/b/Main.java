
public class Main {
    public static void main(String[] args) {
        Course CS1102 = new Course("CS1102");
        Course CS2311 = new Course("CS2311");
        CS2311.addPreReq(CS1102);
        Course CS2312 = new Course("CS2312");
        CS2312.addPreReq(CS2311);
        Course CS2115 = new Course("CS2115");
        Course CS3103 = new Course("CS3103");
        CS3103.addPreReq(CS2115);
        CS3103.addPreReq(CS2312);
        Course CS3334 = new Course("CS3334");
        CS3334.addPreReq(CS2311);
        Programme programme = new Programme();
        programme.add(CS3334);
        programme.drop(CS2312);
        programme.drop(CS2311);
        programme.drop(CS3334);
    }
}
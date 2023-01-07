public class SystemDate extends Day{

    private static SystemDate instance;
    private SystemDate(String sDay) throws ExInvaildDate {super(sDay);}

    public static SystemDate getInstance(){
        return instance;
    }

    public static void createTheInstance(String date) throws ExInvaildDate
    {
        if (instance==null) //make sure only one instance can be created (Singleton)
            instance = new SystemDate(date);
        else
            System.out.println("Cannot create one more system date instance.");
    }
}
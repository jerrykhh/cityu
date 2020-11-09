public class ExProjectNotAvailable extends Exception{

    public ExProjectNotAvailable(){
        super("Team Not Available");
    }

    public ExProjectNotAvailable(String message){
        super(message);
    }
}

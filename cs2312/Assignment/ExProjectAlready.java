public class ExProjectAlready extends Exception{
    public ExProjectAlready(){
        super("Project code already exists.");
    }
    public ExProjectAlready(String message){
        super(message);
    }
}

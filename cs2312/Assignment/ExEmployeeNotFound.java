public class ExEmployeeNotFound extends Exception {
    public ExEmployeeNotFound() {
        super("Employee name does not exist.");
    }

    public ExEmployeeNotFound(String message) {
        super(message);
    }
}
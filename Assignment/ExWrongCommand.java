public class ExWrongCommand extends Exception {
    public ExWrongCommand() {
        super("");
    }

    public ExWrongCommand(String message) {
        super(message);
    }
}
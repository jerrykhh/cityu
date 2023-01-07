import java.util.Stack;

public abstract class RecordedCommand implements Command{
    private static Stack<RecordedCommand> redoList = new Stack<RecordedCommand>();
    private static Stack<RecordedCommand> undoList = new Stack<RecordedCommand>();

    public abstract void undo();
    public abstract void redo();

    protected static void pushRedo(RecordedCommand recordedCommand){
        redoList.push(recordedCommand);
    }

    protected static void pushUndo(RecordedCommand recordedCommand){
        redoList.clear();
        undoList.push(recordedCommand);
    }

    protected static void clearRedoList(){
        redoList.clear();
    }

    public static void undoCommand(){
        if(undoList.isEmpty()){
            System.out.println("Nothing to undo.");
            return;
        }
        RecordedCommand undoCommand = undoList.pop();
        redoList.push(undoCommand);
        undoCommand.undo();
    }

    public static void reddoCommand(){
        if(redoList.isEmpty()){
            System.out.println("Nothing to redo.");
            return;
        }
        RecordedCommand redoCommand = redoList.pop();
        undoList.push(redoCommand);
        redoCommand.redo();
    }


    
}


import java.util.*;

public abstract class RecordedCommand implements Command{
    private static ArrayList<RecordedCommand> undoList = new ArrayList<>();
    private static ArrayList<RecordedCommand> redoList = new ArrayList<>();;

    public abstract void undoMe();
    public abstract void redoMe();

    protected static void addUndoCommand(RecordedCommand cmd){
        undoList.add(cmd);
    }

    protected static void addRedoCommand(RecordedCommand cmd) {
        redoList.add(cmd);
    }

    protected static void clearRedoList(){
        redoList.clear();
    }

    public static void undoOneCommand(){
        undoList.remove(undoList.size()-1).undoMe();
    }

    public static void redoOneCommand(){
        redoList.remove(redoList.size()-1).redoMe();
    }

}

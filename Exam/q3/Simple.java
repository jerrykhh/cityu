public class Simple implements CountingController{

    private final int value = 1;
    
    public void updateCounter(Counter counter) {
        counter.setValue(counter.getValue() + value);
        System.out.println("Increment by " + value + " result is " + counter.getValue());
    }
    
}
public class Advanced implements CountingController{

    private int value = 1;

    @Override
    public void updateCounter(Counter counter) {
        counter.setValue(counter.getValue() + value);
        System.out.println("Increment by " + value + " result is " + counter.getValue());
        value++;
    }
    
}
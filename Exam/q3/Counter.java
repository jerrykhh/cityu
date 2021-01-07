public class Counter {
    private int value = 0;
    private CountingController k;

    public Counter(CountingController k) {
        this.k = k;
    }

    public void changeController(CountingController k) {
        this.k = k;
    }

    // Add your code below

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void tick() {
        k.updateCounter(this);
    }
}

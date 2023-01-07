
public class AgeGroupCounter extends Counter {

    private int start;
    private int end;

    public AgeGroupCounter(int start, int end) {
        this.start = start;
        this.end = end;

    }

    public void countData(Record r) {
        if (r.getAge() >= start && r.getAge() <= end) {
            super.countData(r);
        }
    }

    public String toString() {
        return String.format("[Age %d to %d] Count = %d", start, end, getCount());
    }
}

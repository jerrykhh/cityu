public class Main {
    private static void enterA(SpyOfA spy) {
        spy.reportToA();
    }

    private static void enterB(SpyOfB spy) {
        spy.reportToB();
    }

    public static void main(String[] args) {
        DoubleAgent spy = new JuniorDoubleAgent();
        enterA(spy);
        enterB(spy);
    }
}
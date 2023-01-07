public abstract class Animal implements Runnable {
    private String name;

    public Animal(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }
}
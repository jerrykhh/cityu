public class Main {
    public static void main(String[] args) {
		
        CountingController cc1 = new Simple();
        CountingController cc2 = new Advanced();

        Counter counter1 = new Counter(cc1);
        counter1.tick(); //Output [Increment by 1,  result is 1]
        counter1.tick(); //Output [Increment by 1,  result is 2]
        counter1.tick(); //Output [Increment by 1,  result is 3]
        counter1.tick(); //Output [Increment by 1,  result is 4]
        counter1.tick(); //Output [Increment by 1,  result is 5]

        Counter counter2 = new Counter(cc2);
        counter2.tick(); //Output [Increment by 1,  result is 1]
        counter2.tick(); //Output [Increment by 2,  result is 3]
        counter2.tick(); //Output [Increment by 3,  result is 6]
        counter2.tick(); //Output [Increment by 4,  result is 10]
        counter2.tick(); //Output [Increment by 5,  result is 15]

        counter1.changeController(new Advanced());
        counter1.tick(); //Output [Increment by 1,  result is 6]
        counter1.tick(); //Output [Increment by 2,  result is 8]
        counter1.tick(); //Output [Increment by 3,  result is 11]
		
    }
}

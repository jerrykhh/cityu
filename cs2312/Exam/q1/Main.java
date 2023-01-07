public class Main {
    public static void main(String[] args) {
		Cook c1 = new Cook("Candy"); 
		Cook c2 = new Cook("Cara");
		Recipe r1 = new Recipe("Grilled Fish");
		Recipe r2 = new Recipe("Baked beancurd");
		Recipe r3 = new Recipe("Minced cabbage");
		Dish d1 = c1.cookDish(r1);
		Dish d2 = c1.cookDish(r2);
		Dish d3 = c2.cookDish(r2);

		d1.obtainScore(5);
		d2.obtainScore(4);
		d3.obtainScore(6);

		c1.report(); //Output [I got 2 scores: 5 4]
		c2.report(); //Output [I got 1 scores: 6]

		r1.report(); //Output [Grilled Fish: The best cook is Candy]
		r2.report(); //Output [Baked beancurd: The best cook is Cara]
		r3.report(); //Output [Minced cabbage: (No data)]
	}
}

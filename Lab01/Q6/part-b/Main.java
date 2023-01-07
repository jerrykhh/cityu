public class Main{

	public static void main(String [] args) 
	{	
		Customer c1, c2;
		c1 = new Customer(200);
		c2 = new Customer(200);
		
		Group g1 = new Group();
		g1.add(c1);
		g1.add(c2);

		Shop s1 = new Shop();
		s1.earn(g1, 300); //s1 earns and get totally $300 from the group
		s1.earn(c1, 30);
		System.out.println(s1.getProfit()); //expected output: 330
        System.out.println(c1.getAmount()); //expected output: 20
        
	}
}
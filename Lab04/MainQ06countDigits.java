import java.util.*;

public class MainQ06countDigits
{
	// Return the number of digits in integer x. 	
	static int countDigits(int x)
	{	
		if(x < 10){
			return 1;
		}else{
			int m1 = countDigits(x/10);
			return ++m1;
		}
	}

	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		
		int x;
		System.out.print("input x (-1 to end) : "); x=s.nextInt();

		while (x!=-1)
		{
			System.out.println(countDigits(x));
			System.out.print("input x (-1 to end) : "); x=s.nextInt();
		}

		s.close();
	}
}

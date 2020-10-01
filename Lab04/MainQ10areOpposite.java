import java.util.*;

public class MainQ10areOpposite
{
	
	// todo: Add required methods below
	//	
	static int tail(int x)
	{	
		if(x<100){
            return x%10;
        }
        else{
            int m1 = tail(x / 10);
            return (m1 * 10 + x % 10);
        }
	}

	static int leftMostDigit(int x)
	{	
		if(x < 10){
			return x;
		}else{
			return leftMostDigit(x/10);
		}
		
	}
	

	// Determine whether the sequences of digits in 2 integers (without the 0 digit) are opposite to one another (eg. 123 and 321)
	static boolean areOpposite(int x1, int x2)
	{	
		if(x1 < 10 && x2 < 10){
			return (x1 == x2);
		}else{
			boolean m1 = areOpposite(x1/10, tail(x2));
			return m1 == true && x1%10 == leftMostDigit(x2);
		}
	}

	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		
		int x1,x2;
		System.out.print("input 2 integers, separated by a space (\"-1 -1\" to end) : "); 
		x1=s.nextInt();x2=s.nextInt();

		while (x1!=-1)
		{
			if (areOpposite(x1,x2)) 
				System.out.println("true");
			else
				System.out.println("false");
			
			System.out.print("input 2 integers, separated by a space (\"-1 -1\" to end) : "); 
			x1=s.nextInt();x2=s.nextInt();
		}

		s.close();
	}
}

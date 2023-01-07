import java.util.*;

public class MainQ11reverse
{
	
	// todo: Add required methods below
	//	
	static int tail(int x)
	{	
		if(x<100){
            return x%10;
        }else{
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

	
	
	// Return an integer that is the reversed version of the input integer x (without the 0 digit). eg. 1234=>4321 
	static int reverse(int x)
	{	
		if(x < 10){
			return x;
		}else{
			int m1 = reverse(tail(x));

			return m1 * 10 + leftMostDigit(x);
		}
	}


	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		
		int x;
		System.out.print("input x (-1 to end) : "); x=s.nextInt();

		while (x!=-1)
		{
			System.out.println(reverse(x));
			
			System.out.print("input x (-1 to end) : "); x=s.nextInt();
		}

		s.close();
	}
}

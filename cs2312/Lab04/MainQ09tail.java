import java.util.*;

public class MainQ09tail
{
	
	// Given an input integer x, return y which contains all digits in x except the left-most one.
	//  e.g., 123=>23, 1234=>234. Assume x>10.
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


	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		
		int x;
		System.out.print("input x (-1 to end) : "); x=s.nextInt();

		while (x!=-1)
		{
			System.out.println(tail(x));
			
			System.out.print("input x (-1 to end) : "); x=s.nextInt();
		}

		s.close();
	}
}

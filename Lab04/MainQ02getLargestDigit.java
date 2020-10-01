import java.util.*;

public class MainQ02getLargestDigit
{
	//Return the largest digit in an integer n.
	static int getLargestDigit(int n)
	{
		if (n<10)
		{
			return n;
		}
		else
		{	
			int m1 = getLargestDigit(n/10);
			if(m1 > n%10){
				return m1;
			}else{
				return n%10;
			}
		}
	}

	public static void main(String[] args)
	{
		System.out.print("Input n: ");
		Scanner s = new Scanner(System.in);
		int n=s.nextInt();
		
		System.out.println(getLargestDigit(n));

		s.close();
	}
}

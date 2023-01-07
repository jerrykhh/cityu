import java.util.*;

public class MainQ00showDigits
{
	//show digits of n one by one, separated by spaces
	static void showDigits(int n)
	{
		if (n<10)
			System.out.print(n+" ");
		else
		{	
			showDigits(n/10);
			System.out.print(n%10+" ");
		}
	}

	public static void main(String[] args)
	{
		System.out.print("Input n: ");
		Scanner s = new Scanner(System.in);
		int n=s.nextInt();
		showDigits(n);
		s.close();
	}
}

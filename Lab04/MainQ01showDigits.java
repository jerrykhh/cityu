import java.util.*;

public class MainQ01showDigits
{
	static void showDigits(int n)
	{
		if (n<10)
			System.out.print(n+" ");
		else
		{	
			int leading = n/10;
			int right_most = n%10;
			showDigits(leading);
			System.out.print(right_most+" ");
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

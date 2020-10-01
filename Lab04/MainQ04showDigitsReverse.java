import java.util.*;

public class MainQ04showDigitsReverse
{
	// Show the digits of integer x reversely (each followed by a space). 	
	static void showDigitsReverse(int x)
	{	
		if(x < 10){
			System.out.print(x + " ");
		}else{
			System.out.print(x%10 + " ");
			showDigitsReverse(x/10);
		}		
	}


	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		
		int x;
		System.out.print("input x (-1 to end) : "); x=s.nextInt();

		while (x!=-1)
		{
			showDigitsReverse(x);
			
			System.out.println();
			
			System.out.print("input x (-1 to end) : "); x=s.nextInt();
		}

		s.close();
	}
}

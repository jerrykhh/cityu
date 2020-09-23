import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		
		
		int width, height;
		Scanner input = new Scanner(System.in);
		System.out.print("Input the width of the multiplication table (2-10): ");
		width = input.nextInt();
		System.out.print("Input the height of the multiplication table (2-10): ");
        height = input.nextInt();

        for(int c = 1; c <= height; c++){
            for(int r = 1; r <= width; r++){
                System.out.printf("%5d|", c*r);
            } 
            System.out.println();
        }
		
	}
}

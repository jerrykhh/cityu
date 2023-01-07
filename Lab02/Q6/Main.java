import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		
		
		int width, height;
		Scanner input = new Scanner(System.in);
		System.out.print("Input the width of the multiplication table (2-10): ");
		width = input.nextInt();
		System.out.print("Input the height of the multiplication table (2-10): ");
        height = input.nextInt();

        for(int row = 1; i <= height; i++){

            if(i == 1){
                System.out.print("/");
                for(int line = 0; line <= width * 6; line++){
                    System.out.print("-");
                }
                System.out.println("\\");
            }
            System.out.print("|");
            

            for(int r = 1; r <= width; r++){
                if(r == 1)System.out.printf("%5d|", i*r);
                else System.out.printf("%6d", i*r);
            } 
            System.out.println(" |");

            if(i == 1 || i == height){
                if(i == height) System.out.print("\\");
                else System.out.print("|");
                for(int line = 0; line <= width * 6; line++){
                    System.out.print("-");
                }
                if(i == height) System.out.println("/");
                else System.out.println("|");
            }
        }
		
	}
}

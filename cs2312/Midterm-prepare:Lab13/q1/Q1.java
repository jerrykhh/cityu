import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jerrykwok
 */
public class Q1 {

    public static void main(String[] args) {
        try {
            String report = analyze();
            System.out.println(report);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open input file");
        }
    }

    private static String analyze() throws FileNotFoundException {
        int total = 0;
        int count = 0;
        Scanner f = null;
        f = new Scanner(new File("./profits.txt"));
        String result = "Total: ";
        try {
            while (f.hasNext()) {
                total += Integer.parseInt(f.nextLine());
                count++;
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong data");
            result += "[cannot be calculated]";
            return result;
        }

        if (count < 12) {
            System.out.println("Insufficient data!");
            result += "[cannot be calculated]";
        } else {
            result += total + "\nFrom main: Reporting is OK.";
        }

        return result;
    }
}

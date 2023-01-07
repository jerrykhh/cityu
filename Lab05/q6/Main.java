import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException 
	{
			//Read input file pathname
	Scanner in = new Scanner(System.in); 
	System.out.print("Please enter the filename: ");
	String filepathname = in.next();

	//Grab the StatisticsSystem ss and add counters 
	StatisticsSystem ss = StatisticsSystem.getInstance();  
	
	ss.addCounter(new Counter());

	// /*Now removed*/ ss.addCounter(new AreaCounter("YuenLong")); 
	// /*Now removed*/ ss.addCounter(new AreaCounter("KwunTong")); 

	in.nextLine(); // There is now a '\n' in the input stream, skip it: in.nextLine(); 
	System.out.print("\nEnter the area names (e.g. TaiPo YuenLong WongTaiSin KwunTong): ");
	String line = in.nextLine(); //Read a line: in.nextLine(); 
	Scanner scannerLine = new Scanner(line); //Create the scanner for input from the string: new Scanner(line);
	while (scannerLine.hasNext()) //Still have contents or not: hasNext()
		ss.addCounter(new AreaCounter(scannerLine.next())); //Area name is grabbed from the string: scannerLine.next()
	int start, end; 
	do{
		System.out.print("\nEnter the age groups ('-1 -1' to end): ");
		start = in.nextInt();
		end = in.nextInt();
		if(start != -1 && end != -1)
			ss.addCounter(new AgeGroupCounter(start, end));
	}while(start != -1 && end != -1);
	scannerLine.close();
	//The ss will load file data and tell its counters to count 
	ss.countData(filepathname);		
	
	//The ss will tell its counters to report
	ss.report();
	
	in.close();
	}
}
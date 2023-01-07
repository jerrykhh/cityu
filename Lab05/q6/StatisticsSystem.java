import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class StatisticsSystem
{
	private ArrayList<Counter> allCounters; 

	private static StatisticsSystem theSS = new StatisticsSystem();
	public static StatisticsSystem getInstance() {return theSS;}
	
	private StatisticsSystem()
	{
		allCounters = new ArrayList<Counter>();
	}
		
	public void report()
	{
		System.out.println("\nStatistics: ");
		System.out.println("============");
		for (Counter c: allCounters)
			System.out.println(c);		
	}
	
	public void countData(String filepathname) throws FileNotFoundException 
	{
		Scanner inFile = new Scanner(new File(filepathname));

		while (inFile.hasNext())
		{
			String line = inFile.nextLine();
			Scanner lineStream = new Scanner(line);
			String id=lineStream.next(); 
			String area=lineStream.next(); 
			int age = lineStream.nextInt(); 
			lineStream.close();
			
			Record r = new Record(id,area,age);
			for (Counter c: allCounters)
				c.countData(r);
		}
		
		inFile.close();
	}

	public void addCounter(Counter counter) {
		allCounters.add(counter);
	}

}
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Runner {
	
	public static void main(String args[]) throws FileNotFoundException
	{
		File file = new File("input.txt");
		Scanner input = new Scanner(file);
		while(input.hasNextLine())
		{
			WordLadder w = new WordLadder(input.next(), input.next());
			System.out.println(w.findLadder());

		}
		System.out.println("\nTime = " + (System.currentTimeMillis()));

	}

}

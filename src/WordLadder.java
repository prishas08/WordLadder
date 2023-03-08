import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class WordLadder {
	
	Queue<Stack <String>> queue = new LinkedList<>();
	String start;
	String end;
	String [] dictionary;
	String strDictionary;
	String backUp;
	
	public WordLadder(String s, String e) throws FileNotFoundException
	{
		start = s;
		end = e;
		File file = new File("dictionary.txt");
		Scanner input = new Scanner(file);
		dictionary = new String[127142];
		strDictionary = "";
		String tmp="";
		while(input.hasNext())
		{
			tmp =input.next();
			
			if(tmp.length()==start.length())
			{
				strDictionary += tmp.toLowerCase() + " ";

			}
		}
	}
	
	public String findLadder()
	{
		backUp = start + " ";
		String s = "Word Ladder Not Found between : " + start + " " + end;
		if(start.equals(end))
		{
			s = "Word Ladder Found: " + start;
			return s;
		}
		if(start.length()!=end.length() || !strDictionary.contains(start))
		{
			s = "Word Ladder Not Found between : " + start + " " + end;
			return s;
		}
		for(int i = 0; i < start.length(); i++)
		{
			for(char ch = 'a'; ch <= 'z'; ch++)
			{
				String trail = "";
				for(int x = 0; x < start.length(); x++)
				{
					if(x==i)
					{
						trail += ch;
					}
					else
					{
						trail += start.substring(x,x+1);
					}
				}
				if(strDictionary.contains(trail) && !trail.equals(start) && !backUp.contains(trail))
				{
					backUp += trail + " ";
					Stack<String> WordStack = new Stack<String>();
					WordStack.add(start);
					WordStack.add(trail);
					queue.offer(WordStack);
					
					if(trail.equals(end))
					{
						s = "Word Ladder Found: " + WordStack;
						return s;

					}
				}
				
				
			}
		}

		while(start!=end || !queue.isEmpty())
		{
			Stack<String> firstStack = new Stack<String>();
			firstStack = queue.peek();
			String str = "";
			if(!queue.isEmpty())
			{
				str = firstStack.peek();
			}
			else
			{
				s = "Word Ladder Not Found between : " + start + " " + end;
				return s;
			}
			int counter = 0;
			for(int i = 0; i < str.length(); i++)
			{
				for(char ch = 'a'; ch <= 'z'; ch++)
				{
					String trail = "";
					for(int x = 0; x < str.length(); x++)
					{
						if(x==i)
						{
							trail += ch;
						}
						else
						{
							trail += str.substring(x,x+1);
						}
					}
					if(strDictionary.contains(trail) && !trail.equals(str) && !backUp.contains(trail))
					{
						if(trail.equals(end))
						{
							queue.peek().add(trail);
							start = end;
							s = "Word Ladder Found: " + queue.peek();
							return s;
						}
						queue.add(copyAndAdd(trail));	
						counter++;
						backUp += trail + " ";

					}
				}
			}

			if(counter == 0)
			{
				queue.poll();
			}
			queue.poll();

		}
		return s;
	}
	
	public Stack<String> copyAndAdd(String add)
	{
		String [] s = new String[queue.peek().size()];
		for(int i = 0; i <s.length; i++)
		{
			s[i] = queue.peek().pop();
		}
		Stack <String> newStack = new Stack<String>();
		for(int i = s.length-1; i >= 0; i--)
		{
			newStack.add(s[i]);
			queue.peek().add(s[i]);
		}
		newStack.add(add);
		return newStack;
	}
	

}

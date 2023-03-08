import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class WordLadder1 {
	
	Queue<Stack <String>> queue = new LinkedList<>();
	String start;
	String end;
	String [] dictionary;
	String strDictionary;
	String backUp;
	
	public WordLadder1(String s, String e) throws FileNotFoundException
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
		//System.out.println(strDictionary);
		/*for(int i = 0; i < dictionary.length; i++)
		{
			dictionary[i] = input.next();
		}*/

	}
	
	public String findLadder()
	{
		backUp = start + " ";
		String s = "Word Ladder Not Found between" + start + " " + end;
		if(start.equals(end))
		{
			s = "Word Ladder Found: " + start;
			return s;
		}
		for(int i = 0; i < start.length(); i++)
		{
			for(char ch = 'a'; ch <= 'z'; ch++)
			{
				String trail = start.replace(start.charAt(i), ch);
				//String tmp = new String ("$" + trail + "$");
				//System.out.println(tmp);
				if(strDictionary.contains(trail) && !trail.equals(start) && !backUp.contains(trail))
				{
					backUp += trail + " ";
					Stack<String> WordStack = new Stack<String>();
					WordStack.add(start);
					WordStack.add(trail);
					queue.offer(WordStack);
					System.out.println(WordStack);
					if(trail.equals(end))
					{
						s = "Word Ladder Found: " + WordStack;
						//System.out.println(s);
						return s;

					}
				}
				
				
			}
		}
	
	

		while(start!=end || !queue.isEmpty())
		{
			String str = queue.peek().peek();
			System.out.println(queue.peek());
			int counter = 0;
			for(int i = 0; i < str.length(); i++)
			{
				for(char ch = 'a'; ch <= 'z'; ch++)
				{
					//String trail = str.replace(str.charAt(i), ch);
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
					//System.out.println("Str : " + str);
					//System.out.println("TRAIL : " + trail);
					//have to fix the third parameter
					
					//if(strDictionary.contains(trail) && !trail.equals(str) && queue.contains(trail)==false)
					if(strDictionary.contains(trail) && !trail.equals(str) && !queue.contains(trail))
					{
						//System.out.println(queue.peek());
						System.out.println("STR : " + str);
						System.out.println("TRAIL *******: " + trail);

						if(trail.equals(end))
						{
							System.out.println("QUEEUE PEEK" + queue.peek());
							queue.peek().add(trail);
							
							System.out.println("QUEEUE PEEK" + queue.peek());
							start = end;
							s = "Word Ladder Found: " + queue.peek();
							return s;
						}
						if(counter > 0)
						{
							//System.out.println("TRAIL: " + trail);
							//queue.add(copyAndAdd(trail));
							//queue.add(queue.poll());
							
							queue.add(copyAndAdd(trail));
							queue.add(queue.peek());
							/*if(trail.equals(end))
							{
								//System.out.println("QUEEUE PEEK" + queue.peek());
								queue.peek().add(trail);
								start = end;
								s = "Word Ladder Found: " + queue.peek();
								return s;
							}*/
							queue.poll();
						}
						else
						{
							queue.peek().add(trail);
							//System.out.println("HERER" + queue.peek());
							queue.add(queue.peek());
							//queue.poll();
						}
						counter++;
						backUp += trail + " ";
						//System.out.println(queue.peek());


					}
					
					/*if(trail.equals(end))
					{
						start = end;
						s = "Word Ladder Found: " + queue.peek();
						return s;
					}*/

				}
				

			}
			//System.out.println("BACKUP : " + backUp);
			//System.out.println(queue.peek());
			if(counter == 0)
			{
				//System.out.println("HEY" + queue.peek());
				queue.poll();
			}
			//Queue<Stack <String>> copy = new LinkedList<>();

			/*while(queue.isEmpty()==false)
			{
				
				Stack <String> t = new Stack<String>();
				while(queue.peek().isEmpty()==false)
				{
					System.out.println(queue.peek());
					t.add(queue.peek().peek());
					String str = queue.peek().peek();
					int counter = 0;
					for(int i = 0; i < str.length(); i++)
					{
						for(char ch = 'a'; ch <= 'z'; ch++)
						{
							String trail = str.replace(str.charAt(i), ch);
							if(strDictionary.contains(trail) && !trail.equals(str) && !queue.contains(trail))
							{
								//if need to make new stack
								if(counter > 0)
								{
									copy.add(copyAndAdd(trail));
									//queue.peek().
									/*Stack<String> temporary = new Stack<>();
									//NEED TO FIX EVERYTHING BELOW
									while(queue.peek().isEmpty()==false)
									{
										//System.out.println(copy.peek());
										//copy.peek.peek is not being removed
										//this doesn't work
										//System.out.println(copy.peek().peek());
										temporary.add(queue.peek().pop());
										//copy.peek().pop();
										System.out.println("HERE IS TEMP" + temporary);
										//this works
										//System.out.println(copy.peek().peek());
									}
									temporary.add(trail);
									while(temporary.isEmpty()==false)
									{
										//System.out.println(temporary);
										//copy.add(temporary);
										copy.peek().add(temporary.pop());
										//if(temporary.isEmpty()==false)
										//temporary.pop();
										//System.out.println("here is the copy" + copy);

										
									}
									
								}
								//if need to add to original stack
								else
								{
									//System.out.println("HERE");
									queue.peek().add(trail);
									//System.out.println(copy.peek());

								}
								counter++;
							}
						}
					}
					queue.peek().pop();
				}
				copy.offer(t);
				System.out.println("HERERERERE" + copy);
				//while(copy.peek().isEmpty()==false)
				//{
					
				//}
				queue.poll();
				
			}
			//System.out.println("HERE IS COPY: " + copy);

			while(copy.isEmpty()==false)
			{
				Stack <String> t = new Stack<String>();
				while(copy.peek().isEmpty()==false)
				{
					t.add(copy.peek().pop());
					if(queue.peek().peek().equals(end))
					{
						//System.out.println("HERE");
						start = end;
						s = "Word Ladder Found: " + queue.peek();
						//System.out.println(queue.peek());
						break;
					}
					
				}
				queue.offer(t);
				copy.poll();
				//System.out.println(queue.peek().peek());
				//the queue.peek.peek is empty
				
			}*/
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
		//System.out.println("OLD STACK" + queue.peek());
		//System.out.println("NEW STACK" + newStack);

		return newStack;
	}
	

}

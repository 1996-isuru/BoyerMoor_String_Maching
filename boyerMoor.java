import java.io.*;
//import java.io.FileReader;
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
import java.util.*;
//import java.util.Scanner;

public class boyerMoor
{
	public static void main(String[] args)
	{ 
		Vector<Character> list = new Vector<Character>();

		File f = new File("pi.txt");
			try
		{
			list=readLines(f);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		String fileName="results.txt";
		System.out.println("\n\t       Boyer-Moore Algorithum\n");
		
		    	String w = new String();
		    	//input string want to search
		    	Scanner suuuu = new Scanner(System.in);
				System.out.println("Enter your string: ");
				w = suuuu.nextLine();

				try
				{    
		           BufferedWriter results=new BufferedWriter(new FileWriter(fileName,true));
		           results.write("\n\t       Boyer-Moore Algorithum");
		           results.write("\nSearch results of : "+w+"\n");
		           results.close();    
		        }
		        catch(Exception e7)
		        {	
		        	System.out.println(e7);
		        }
				
				Map<Character,Integer> vec = new HashMap<Character,Integer>();
				vec.put(w.charAt(0),w.length()-1);
				
				for(int ii=1;ii<w.length();ii++)
				{
					if(vec.containsKey(w.charAt(ii)))
					{
						vec.replace(w.charAt(ii),ii);
					}
					else
					{
						vec.put(w.charAt(ii),ii);
					}
				}

				int count11=0,skips=0;

				while(skips<=list.size()-w.length())
				{
					int iii=w.length()-1;
					while(iii>=0 && w.charAt(iii)==list.get(skips+iii))
					{
						iii--;
					}

					if(iii<0)
					{
						count11++;
						System.out.println("Matching substring found, starting at : "+skips);
						try
				        { 
		   
				           	BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true)); 
				         	out.write("Matching substring found, starting at : "+skips+"\n"); 
				           	out.close(); 
				       	} 
				       	catch(IOException e9)
				       	{	 
				           	System.out.println("exception occoured" + e9); 
				       	}
				     
				       	if(skips+w.length()<list.size())
				       	{	
				       		if(vec.containsKey(list.get(skips+w.length())))
				       		{
				       			skips=skips+w.length()-vec.get(list.get(skips+w.length()));
				       		}
				       		else
				       		{
				       			skips=skips+w.length()+1;
				       		}
				       	}
				       	else
				       	{
				       		skips++;
				       	}
					}
					else
					{
						if(vec.containsKey(list.get(skips+iii)))
				       	{
				       		skips=skips+Math.max(1,iii-vec.get(list.get(skips+iii)));
				       	}
				       	else
				       	{
				       		skips=skips+Math.max(1,iii+1);
				       	}
					}
				}

				System.out.println("\nNumber of found substrings : "+count11+"\n");
	}
	
	public static Vector readLines(File f) throws IOException
	{
		Vector<Character> vectorlist = new Vector<Character>();
		try
		{
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line;
			int numberOfLines = 0;
			int n,i,k;
			int charCount = 0;
		
			while((line = br.readLine()) != null)
			{
				numberOfLines++;
				n = numberOfLines;
				if (n >= 22)
				{
					if (!line.isEmpty())
					{
						char[] sChars = line.toCharArray();
						
						for(int l = 0; l < 54; l++)
						{
							if(sChars[l] != ' ')
							{
								vectorlist.add(sChars[l]); 
								charCount += line.length();
							    charCount = 0;
							} 	
						}		
					}			
				}	
			}
			br.close();
			fr.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return vectorlist;
	}
}
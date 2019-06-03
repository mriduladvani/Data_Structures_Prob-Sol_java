//package test;
import java.util.*;
class Project{
public static void main(String[] args)
{

	ArrayList<String> arr= new ArrayList<String>();
	Scanner sc= new Scanner(System.in);
	int lines=Integer.parseInt(sc.nextLine());
	for(int i=0;i<lines;i++)
	{
	arr.add(sc.nextLine());
	}
	int gram= Integer.parseInt(sc.nextLine());
	String finalstring = "";
	for(int i=0;i<lines;i++)
	{
		finalstring=finalstring+arr.get(i);
	}
	HashMap<String, Integer> hm= new HashMap<String, Integer>();
	String anagram = "";
	for(int i=0;i<=finalstring.length()-gram;i++)
	{	
		for(int j=i;j<i+gram;j++)
		{
			anagram=anagram+ finalstring.charAt(j);
		}
		if(!anagram.contains(" ") && !anagram.contains(",") && !anagram.contains("."))
		{
			
			if(hm.containsKey(anagram))
			{
				hm.put(anagram, hm.get(anagram)+1);
			}
			else
			{
				hm.put(anagram, 1);
			}
		}
		anagram="";
		 
	}
	
	int max=0;
	String targetgram="";
	
	for(String c: hm.keySet())
	{
		if(hm.get(c)>max)
		{
			max=hm.get(c);
			targetgram=c;
		}
		if(hm.get(c)==max)
		{
			if(c.compareTo(targetgram)<0)
			{
				targetgram=c;
			}
		}
		
	}
	
	if(targetgram.length()==1)
	{
		System.out.print("Unigram "+ targetgram);
	}
	else if(targetgram.length()==2)
	{
		System.out.print("Bigram "+ targetgram);
	}
	else if(targetgram.length()==3)
	{
		System.out.print("Trigram "+ targetgram);
	}
	else
	{
		System.out.print("Invalid request");
	}
	
	
	
	
}}
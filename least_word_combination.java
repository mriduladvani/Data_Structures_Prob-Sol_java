//package test;
import java.util.*;

class least_word_combination{
	public static void main(String[] args)
	{
		Scanner sc= new Scanner(System.in);
		String str= sc.nextLine();
		String letters= sc.nextLine();
		ArrayList<Character> letter= new ArrayList<Character>();
		ArrayList<Character> clone_1= new ArrayList<Character>();
		ArrayList<String> list_of_strings= new ArrayList<String>();
		for(int i=0;i<letters.length();i++)
		{
			letter.add(letters.charAt(i));
			clone_1.add(letters.charAt(i));
		}
		
		int start_index=0,end_index=0, counter=0,i=0;
		while(i<str.length())
		{
			if(clone_1.contains(str.charAt(i)) && counter==0)
			{
				start_index=i;
				counter++;
				clone_1.remove(new Character(str.charAt(i)));
			}
			if(clone_1.contains(str.charAt(i)) && counter!=0 && counter<letters.length())
			{
				counter++;
				clone_1.remove(new Character(str.charAt(i)));
			}
			if(counter== letters.length())
			{
				end_index=i;
				list_of_strings.add(str.substring(start_index, end_index+1));
				i=start_index;
				start_index=0;
				end_index=0;
				counter=0;
				for(int j=0;j<letters.length();j++)
				{
					clone_1.add(letters.charAt(j));
				}
			}
			i++;
		}
		/*
		for( i=0;i<list_of_strings.size();i++)
		{
			System.out.println(list_of_strings.get(i));
		}
		*/
		String smallest_string="";
		for(String smallest_comb : list_of_strings)
		{
			if(smallest_string.length()==0 && smallest_comb.length()!=0)
			{
				smallest_string= smallest_comb;
			}
			
			else if(smallest_comb.length()<smallest_string.length() && smallest_comb.length()!=0)
			{
				smallest_string= smallest_comb;
			}
		}
		
		System.out.println(smallest_string);
		
		
		/*
		qploresinazxrhqknhoilerthf
		lion
		
		MKWPLNHNNKLASOPQLRHLI
		HILL
		
		1qnkyp098fSkkmnQryS9pkYn0Qd7mksy0fRW0a7Sxzc
		*/
	}
}
//package test;
import java.util.*;

class Node{
	Node left;
	Node right;
	String data;
	Node(String data)
	{
		this.data=data;
	}
}
class lab7{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		int n= sc.nextInt();
		//System.out.println(n);
		ArrayList<String> arraylist= new ArrayList<String>();
		ArrayList<String> reln_list= new ArrayList<String>();
		//arraylist.add(sc.nextLine());
		for(int i=0;i<=n;i++)
		{
			arraylist.add(sc.nextLine());
		}
		int reln= sc.nextInt();
		for(int i=0;i<=reln;i++)
		{
			reln_list.add(sc.nextLine());
		}
		
		//System.out.println(arraylist.get(1).split(" ")[0]);
		Node root = new Node(arraylist.get(1).split(" ")[0]);
		HashMap<String, ArrayList<String>> hm= new HashMap<String, ArrayList<String>>();
		//ArrayList<String> values= new ArrayList<>();
		for(int i=1;i<arraylist.size();i++)
		{		
			ArrayList<String> values= new ArrayList<>();
			String[] names_array=new String[2];
			names_array= arraylist.get(i).split(" ");
			if(hm.containsKey(names_array[0]))
			{
				hm.get(names_array[0]).add(names_array[1]);
			}
			else
			{
				values.add(names_array[1]);
				hm.put(names_array[0], values);
			}
			//values.clear();
		}
		//System.out.print(hm);
		make_tree(root, hm);
		printtree(root);
        System.out.println("");
        check_reln(root, hm, reln_list);
        
        //System.out.println(root.data+find_level(root, "priyanka", 0));
       // System.out.println(reln_list.get(1).split(" ")[0]+reln_list.get(1).split(" ")[1]);
        //check_reln(hm, reln_list);
       // System.out.println(arraylist);
        //System.out.println(reln_list);
		
		
		//System.out.println(hm);
/*
8
motilal jawahar
jawahar indira
motilal kamla
indira sanjay
sanjay varun
indira rajiv
rajiv priyanka
rajiv rahul
6
motilal child jawahar
varun descendent indira
priyanka sibling varun
sanjay child indira
sanjay ancestor varun
kamala ancestor rahul
*/
	}
	
	public static void make_tree(Node root, HashMap<String, ArrayList<String>> hm)
	{	//System.out.println("here is the root data"+ root.data);
		if(root==null)
        {return;}
        if(hm.containsKey(root.data))
		{
			if(hm.get(root.data).size()>1)
			{
				root.left= new Node(hm.get(root.data).get(0));
				root.right= new Node(hm.get(root.data).get(1));
			}
			else
			{
				root.left= new Node(hm.get(root.data).get(0));
			}
		}
		make_tree(root.left, hm);
		make_tree(root.right, hm);
	}
	
    public static int find_level(Node root, String data, int level)
    {
       
        if(root==null) return level;
        else if(root.data.equals(data))
        {        return level;}
        else
        {
            int left_depth=find_level(root.left, data, level+1);
            int right_depth=find_level(root.right, data, level+1);
            if(left_depth>right_depth) return left_depth;
            else return right_depth;
        }
    }
    
    public static void check_reln(Node root, HashMap<String, ArrayList<String>> hm, ArrayList<String> reln_list )
    {
        for(int i=1; i<reln_list.size(); i++)
        {
            String reln_array[]= new String[3];
            reln_array= reln_list.get(i).split(" ");
            String str1= reln_array[0];
            String str2= reln_array[1];
            String str3= reln_array[2];
            int str1level= find_level(root, str1, 0);
            int str3level= find_level(root, str3, 0);
            
            if(str2.equals("child"))
            {
                if(str1level>str3level) System.out.print("T ");
                else System.out.print("F ");
            }
            if(str2.equals("descendent"))
            {
                if(str1level>str3level) System.out.print("T ");
                else System.out.print("F ");
            }
            if(str2.equals("sibling"))
            {
                if(str1level==str3level) System.out.print("T ");
                else System.out.print("F ");
            }
            if(str2.equals("ancestor"))
            {
                if(str1level>str3level) System.out.print("T ");
                else System.out.print("F ");
            }
            
        
        }
    }
	
	
	
	public static void printtree(Node root)
	{
        if(root==null)
        {return ;}
		System.out.print(root.data+" ");
		printtree(root.left);
		printtree(root.right);
	}
}





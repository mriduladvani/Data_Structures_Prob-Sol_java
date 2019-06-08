//package test;
import java.util.*;

class Node{
int data;
Node left;
Node right;

Node(int data){this.data=data;
left=right=null;}
}

class avlcheck{
	
	
	public static Node addtotree(Node root, int num)
	{
		if(root==null)
		{
			root= new Node(num);
			return root;
		}
		
		if(num<root.data)
		{
			root.left= addtotree(root.left, num);
		}
		
		else if(num>root.data)
		{
			root.right= addtotree(root.right, num);
		}
		
		return root;
				
	}
	
	
	public static int getheight(Node root)
	{
		if(root==null)
		{
			return 0;
		}
		int ldepth=getheight(root.left);
		int rdepth=getheight(root.right);
		
		if(ldepth>rdepth) return ldepth+1;
		else return rdepth+1;	
	}
	
	
	
	public static String preordertraversal(Node root, String str)
	{
		if(root==null) return str;
		
		//System.out.print(root.data+" ");
		str=str+root.data+" ";
		str= preordertraversal(root.left, str);
		str= preordertraversal(root.right, str);
		return str;
	}
	
	public static boolean checkavl(Node root)
	{
		if(root==null) return true;
		
		int left_height= getheight(root.left);
		int right_height= getheight(root.right);
		if(Math.abs(left_height-right_height)<=1 && checkavl(root.left) && checkavl(root.right))
		{
			return true;
		}
		else return false;
		
	}
	
		
	
	
	public static void main(String[] args)
	{
		Scanner sc= new Scanner(System.in);
		ArrayList<Integer> input= new ArrayList<Integer>();
		String inputstring= sc.nextLine();
		String inputarr[]= inputstring.split(" ");
		for(int i=0;i<inputarr.length;i++)
		{
			if(inputarr[i].equals("-1")==false)
			{
				input.add(Integer.parseInt(inputarr[i]));
			}
			
		}
		
		Node root= new Node(input.get(0));
		
		for(int i=0;i<input.size();i++)
		{
			root= addtotree(root, input.get(i));
		}
		
		//preordertraversal(root);
		String str="";
		boolean result= checkavl(root);
		if(result)
		{
			String string= preordertraversal(root, str);
			System.out.print(string.substring(0, string.length()-1));
		}
		else
		{
			System.out.print("NOT");
		}
		
		//20 10 5 11 12 30 27 35 26 25 -1
		
	}
	
}

//package test;
import java.io.*;
import java.util.*;

//Node definition
class Node{
String data;
Node left;
Node right;

Node(String str){data=str;
left=right=null;}
}


class levelordertree{

//function to extract the tree from the string representation of it
public static Node treecreation(String str)
{
if(str.length()==0) {return null;}

//creating root
int i=0,j=0;
while(j<str.length() && str.charAt(j)!='(' && str.charAt(j)!=')') j++;
Node root = new Node(str.substring(i, j));

//creating the left node
if(j<str.length())
{
i=j;
int count=1;
while(j+1<str.length() && count!=0)
{
j++;
if(str.charAt(j)=='(') count++;
if(str.charAt(j)==')') count--;
}
root.left= treecreation(str.substring(i+1,j));
}
j++;

//creating the right node
if(j<str.length())
{
root.right= treecreation(str.substring(j+1, str.length()-1));
}

return root;
}

//normal traversal of the tree
public static void traverseNode(Node node)
{
if(node==null) return;
System.out.println(node.data);
traverseNode(node.left);
traverseNode(node.right);
}

//finding the height of the tree
public static int maxdepth(Node node)
{
if(node==null) return 0;
else
{
int ldepth= maxdepth(node.left);
int rdepth= maxdepth(node.right);
if(ldepth>rdepth)
{
return ldepth+1;
}
else return rdepth+1;

}
}


//iteration for the level 
public static void printlevelorder(Node node, ArrayList<String> path)
{
int h= maxdepth(node);
for(int i=1;i<=h;i++)
{
printlevel(node, i, path);
}
}

//traversal of each level
public static void printlevel(Node node, int level, ArrayList<String> path)
{
String str="";
if(node==null)
{
return;
}

if(level==1)
{
path.add(node.data);
System.out.print(node.data+" ");

}
if(level>1)
{
printlevel(node.left, level-1, path);
printlevel(node.right, level-1,path);
}
}

//figuring out if the target is present in the tree
public static boolean findtarget(Node root, String target)
{
if(root==null)
{
return false;}
else if(root.data.equals(target))
{
//System.out.println("target found "+ root.data);
return true;
}


return findtarget(root.left, target) || findtarget(root.right, target);






//return root;


}

//generic function to find the path between the root and any node(the node needs to be in the tree)
public static boolean findpath(Node root, String target, ArrayList<String> path)
{
    //ArrayList path= new ArrayList<Integer>();
    //path.clear();
    if(root==null)
    {
        return false;
    }
    path.add(root.data);
    if(root.data.equals(target))
    {
        return true;
    }
    if(findpath(root.left, target, path) || findpath(root.right, target, path) ) return true;
    path.remove(path.size()-1);
    return false;
}

//main function
public static void main(String[] args)
{
String str;
ArrayList<String> levelOrderPath= new ArrayList<String>();   //storing the level order traversal of the tree
Scanner sc= new Scanner(System.in);
str= sc.nextLine();
str=str.replaceAll("\\s", "");
String target=sc.next(); //the target that needs to be found
int distance= Integer.parseInt(sc.next());
int len=str.length();
String newstr="";
String string= str.substring(1, len-1);
Node root= treecreation(string); //creating the tree
printlevelorder(root, levelOrderPath); //displaying the level order

//if the target isnt in the tree, the program only prints the level order
if(findtarget(root, target)==false)
{
for(int i=0;i<levelOrderPath.size();i++)
{
System.out.print(levelOrderPath.get(i));}
System.exit(0);}



ArrayList<String> path1= new ArrayList<String>(); //path bw root and target
ArrayList<String> path2= new ArrayList<String>(); //path bw root and each element of levelorder path, updated regularly
ArrayList<String> path3= new ArrayList<String>(); //path between the root and the least common ancestor of root and the corresponding levelorder element
String LCA_node="";
String dstr="";

findpath(root, target, path1);
/*
for(int k=0;k<path1.size();k++)
{
System.out.print(" "+path1.get(k));}
*/

//if root is at the desired distance from the target
if(path1.size()-1==distance)
{
System.out.print(root.data+" ");}

//checking each element from the levelorder path if it is at the required distance
//if it is, element is printed
//if it isnt, we move forward with the next levelorder element
for(int i=1;i<levelOrderPath.size();i++)
{
findpath(root, levelOrderPath.get(i), path2);


int lower_size= path1.size()<path2.size() ? path1.size() : path2.size();

//finding the least common ancestor
for(int j=0;j<lower_size;j++)
{
   if(path1.get(j)!=path2.get(j)) 
   {
      LCA_node=path1.get(j-1);
   break;}
   else
   {
	   LCA_node= path1.get(lower_size-1);
   }
}
//System.out.println("LCA "+LCA_node );

findpath(root, LCA_node, path3);
int d_t1r= path1.size()-1; //distance between root and target
int d_t2r= path2.size()-1; //distance between each element of level order and root
int d_LCAr= path3.size()-1; //distance between root and LCA
if(d_t1r+d_t2r-(2*d_LCAr)==distance) //formula to find the distance between any 2 nodes in a tree (here: target and each element of the level order)
{
	dstr=dstr+levelOrderPath.get(i)+" ";
//System.out.print(levelOrderPath.get(i)+" x");
}

path2.clear();
path3.clear();
}


System.out.print(dstr);
/*
SOME SAMPLE TEST CASES ALONG WITH OUTPUT EXPECTED FROM THEM:


(F(B(A)(D(C)(E)))(G(I(H)())))
D
2
EXPECTED OP: FBGADICEHFA


(A (B(D) (E(F) (G))) (C))
F
4
EXPECTED OP: ABCDEFGC


(8(3(13()(7))())(10(1(14)())(6(4)())))
10
1
EXPECTED OP: 831013167144816



(5(N(3)(11(2)()))(B()(1(W))))
2
3
EXPECTED OP: 5 N B 3 11 1 2 W 5 3


(Z(X(2)(V(Y)(1)))(T()(99(R))))
R
6
EXPECTED OP: Z X T 2 V 99 Y 1 R Y 1


(9(5(1)(2()(6)))(4))
1
4
EXPECTED OP: 9 5 4 1 2 6


(T(P(Q)(D()(6)))(3))
6
3

EXPECTED OP: T P 3 Q D 6 T Q

 */


}}

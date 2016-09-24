package treeAndGraph.binaryTreeTraversal;

import util.ArrayListUtil;
import util.BinaryNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import static util.ArrayUtil.print;

/**
 * User: xinyuwan, Date: 11/24/13, Time: 10:15 PM
 */
public class DFSAndPrintPath
{
    public static void main(String[] args)
    {
        /*
         *            5
         *      -5          7
         *   3     10    -2    3
         * 7                     9
         */
        BinaryNode<Integer> i=new BinaryNode<Integer>(9);
        BinaryNode<Integer> h=new BinaryNode<Integer>(3, null, i);
        BinaryNode<Integer> g=new BinaryNode<Integer>(-2);
        BinaryNode<Integer> e=new BinaryNode<Integer>(7);
        BinaryNode<Integer> d=new BinaryNode<Integer>(10);
        BinaryNode<Integer> c=new BinaryNode<Integer>(3, e, null);
        BinaryNode<Integer> f=new BinaryNode<Integer>(7, g, h);
        BinaryNode<Integer> b=new BinaryNode<Integer>(-5, c, d);
        BinaryNode<Integer> a=new BinaryNode<Integer>(5, b, f);

        BinaryNode<Integer> i2=new BinaryNode<Integer>(10);
        BinaryNode<Integer> h2=new BinaryNode<Integer>(3, null, i2);
        //Test containsSubTree
        System.out.println("Contains Subtree: "+ContainsSubtree.isSubtree(a, b)
                +" "+ContainsSubtree.isSubtree(a, c)+" "+ContainsSubtree.isSubtree(a, h2));

        //Test sumPaths all methods
        System.out.println("Sum paths from root:");
        SumPaths.printRootPaths(a, 10);
        System.out.println("Sum paths from any where:");
        SumPaths.printAllSumPaths(a, 10);
        System.out.println("Check sum in path: "+SumPaths.checkSumInPath(a, 10)+
                " "+SumPaths.checkSumInPath(a, 6));

        //Test printAllAncestors
        System.out.println("Ancestors of i: ");
        PrintAllAncestors.printAnc(a, i);
        PrintAllAncestors.printAnc2(a, i);
        System.out.println();

        //Test createLListForSameLevelNodes
        ArrayList l1=CreateLListForSameLevelNodes.create(a);
        ArrayList l2=CreateLListForSameLevelNodes.create2(a);
        System.out.println("Print lists for nodes at all levels:");
        ArrayListUtil.printLList(l1);
        System.out.println();
        ArrayListUtil.printLList(l2);


        //Test vsum table
        System.out.println("Vertical sum: ");
        VerticalSum.vsum(a);

        //Test diameter()
        System.out.println("Diameter of tree: "+DiameterOfBinaryTree.diameter(a));

        //Test mirror() and isMirror
        System.out.println("After Mirror:");
        MirrorTree.mirror(a);
        a.levelorder(a);
        BinaryNode<Integer> i1=new BinaryNode<Integer>(9);
        BinaryNode<Integer> h1=new BinaryNode<Integer>(3, null, i1);
        BinaryNode<Integer> g1=new BinaryNode<Integer>(-2);
        BinaryNode<Integer> e1=new BinaryNode<Integer>(7);
        BinaryNode<Integer> d1=new BinaryNode<Integer>(10);
        BinaryNode<Integer> c1=new BinaryNode<Integer>(3, e1, null);
        BinaryNode<Integer> f1=new BinaryNode<Integer>(7, g1, h1);
        BinaryNode<Integer> b1=new BinaryNode<Integer>(-5, c1, d1);
        BinaryNode<Integer> a1=new BinaryNode<Integer>(5, b1, f1);
        System.out.println("Is a1 mirror of a?: "+MirrorTree.isMirror(a1, a));
    }
}

class ContainsSubtree
{
  /*
   * Problem: see if tree1 has  a subtree tree2
   */
   public static boolean isSubtree(BinaryNode root, BinaryNode p)
   {
      //empty tree is the subtree of all tree
      if(p==null)
          return true;
      //no node in root matches p
      if(root==null)
          return false;
      //found one match node, we'll go from here to see if all subNodes of root matches p's subNode
      if(root.data==p.data)
          return isIdentical(root, p);
      return isSubtree(root.left, p) || isSubtree(root.right, p);
    }
    //Auxiliary method to help judge whether the two trees have the same structure and data
    public static boolean isIdentical(BinaryNode r1, BinaryNode r2)
    {
        if(r1==null && r2==null)
            return true;
        if(r1==null || r2==null)
            return false;
        if(r1.data!=r2.data)
            return false;
        return isIdentical(r1.left, r2.left) && isIdentical(r1.right, r2.right);
    }
}

class SumPaths
{
   /*
    * Problem1: print all paths from root in binary tree that equals to sum k
    */
    public static void printRootPaths(BinaryNode<Integer> root, int k)
    {
        int height=root.getHeight();
        int[] paths=new int[height];
        printRootPaths(root, k, paths, 0, 0);
    }
    private static void printRootPaths(BinaryNode<Integer> root, int k, int[] paths, int level, int sum)
    {
        if(root==null)
            return;
        paths[level]=root.data;
        //sum is used to store the cumulative value of sum in current path,
        //it prevents from calculating the sum at each recursion
        sum+=root.data;
        if(sum==k)
            print(paths, 0, level);
        printRootPaths(root.left, k, paths, level+1, sum);
        printRootPaths(root.right, k, paths, level+1, sum);

    }
    /*
     * Problem2: print all sum paths from any where
     * We have to check sum at each node to scan every possibility (O(nlogn))
     */
    public static void printAllSumPaths(BinaryNode<Integer> root, int sum)
    {
        int height=root.getHeight();
        int[] paths=new int[height];
        printAllSumPaths(root, sum, paths, 0);
    }
    private static void printAllSumPaths(BinaryNode<Integer> root, int k, int[] path, int level)
    {
        if(root==null)
            return;
        path[level]=root.data;
        int sumVal=0;
        for(int i=level; i>=0; i--)
        {
            sumVal+=path[i];
            if(sumVal==k)
                print(path, i, level);
        }
        printAllSumPaths(root.left, k, path, level+1);
        printAllSumPaths(root.right, k, path, level+1);

    }
    /*
     * Problem3: check if path with sum k in binary tree
     */
    public static boolean checkSumInPath(BinaryNode<Integer> root, int sum)
    {
        /*
         * basecase: when root==null it can be the null left child a parent that
         * has a nonnull right child, so in this case, we cannot return true. We
         * only check the sum==0 when root is leaf
         */
        if(root==null)
            return false;
        sum-=root.data;
        if(root.isLeaf()) {
            return sum==0;
        }
        return checkSumInPath(root.left, sum)||checkSumInPath(root.right, sum);
    }
}

class PrintAllAncestors
{
    /*
     * Problem: Print all ancestors of the given node
     */
    //method1: use addition array as buffer to store ancestors
    public static void printAnc(BinaryNode<Integer> root, BinaryNode node)
    {
        int[] ancestors=new int[root.getHeight()];
        printAnc(root, node, 0, ancestors);
    }
    public static boolean printAnc(BinaryNode<Integer> root, BinaryNode node, int level, int[] anc)
    {
        if(root==null)
            return false;
        anc[level]=root.data;
        if(root==node)
        {
            print(anc, 0, level-1);
            return true;
        }
        return printAnc(root.left, node, level+1, anc)
                || printAnc(root.right, node, level+1, anc);
    }
    //method2: more space efficent: use boolean to check whether current node is the ancestor
    public static boolean printAnc2(BinaryNode root, BinaryNode node)
    {
        if(root==null)
            return false;
        if(root.left==node || root.right==node ||
                printAnc2(root.left, node) || printAnc2(root.right, node))
        {
            System.out.print(root.data+" ");
            return true;
        }
        return false;
    }
}

class CreateLListForSameLevelNodes
{
   /*
    * Problem: create linkedlists for nodes at all level, return an ArrayList to hold the lists
    */
    //Method1: pre-order traversal
    public static ArrayList<LinkedList<BinaryNode>> create(BinaryNode root)
    {
        ArrayList<LinkedList<BinaryNode>> list=new ArrayList<LinkedList<BinaryNode>>();
        create(root, 0, list);
        return list;
    }
    private static void create(BinaryNode root, int level, ArrayList<LinkedList<BinaryNode>> resultList)
    {
        if(root==null)
            return;
        LinkedList<BinaryNode> list=null;
        if(resultList.size()==level)
        {
            list=new LinkedList<BinaryNode>();
            resultList.add(list);
        }
        else
            list=resultList.get(level);
        list.add(root);
        create(root.left, level+1, resultList);
        create(root.right, level+1, resultList);
    }
    //Method2: BFS traversal
    public static ArrayList<LinkedList<BinaryNode>> create2(BinaryNode root)
    {
        ArrayList<LinkedList<BinaryNode>> resultList=new ArrayList<LinkedList<BinaryNode>>();
        LinkedList<BinaryNode> list=new LinkedList<BinaryNode>();
        list.add(root);
        while(!list.isEmpty())
        {
            resultList.add(list);
            LinkedList<BinaryNode> parentList=list;
            list=new LinkedList<BinaryNode>();
            for(BinaryNode b : parentList)
            {
                if(b.hasLeft())
                    list.add(b.left);
                if(b.hasRight())
                    list.add(b.right);
            }
        }
        return resultList;
    }
}

class VerticalSum
{
    /*
     * Problem: print the vertical sum of all column in binary tree
     * in a tree like 3  col0:3, 8, 9 col-1: 4 col-2 5
     *              4   7
     *            5  8 9  6
     */
    public static void vsum(BinaryNode<Integer> root)
    {
        HashMap<Integer, Integer> vsumTable=new HashMap<Integer, Integer>();
        vsum(root, 0, vsumTable);
        for(int x: vsumTable.values())
            System.out.print(x+" ");
        System.out.println();
    }
    private static void vsum(BinaryNode<Integer> root, int col, HashMap<Integer, Integer> vsumTable)
    {
        if(root==null)
             return;
        int sum=root.data;
        if(vsumTable.containsKey(col))
            sum=vsumTable.get(col)+root.data;
        vsumTable.put(col, sum);
        vsum(root.left, col-1, vsumTable);
        vsum(root.right, col+1, vsumTable);
    }
}

class MirrorTree
{
    /*
     * Problem: convert the tree to its mirror and test whether two trees are mirrors
     */
    public static void mirror(BinaryNode root)
    {
        if(root==null || root.isLeaf())
            return;
        BinaryNode temp=root.left;
        root.left=root.right;
        root.right=temp;
        mirror(root.left);
        mirror(root.right);
    }
    public static boolean isMirror(BinaryNode r1, BinaryNode r2)
    {
        if(r1==null && r2==null)
            return true;
        if(r1==null || r2==null)
            return false;
        if(!r1.data.equals(r2.data))
            return false;
        return isMirror(r1.left, r2.right) && isMirror(r1.right, r2.left);
    }
}

class DiameterOfBinaryTree
{
    /*
     * Problem: find the diameter of Binary Tree, which refers to the distance
     * of farthest nodes in tree
     */
    public static int diameter(BinaryNode root)
    {
        if(root==null)
            return 0;
        int left=height(root.left);
        int right=height(root.right);
        return left+right+1;
    }
    public static int height(BinaryNode root)
    {
        if(root==null)
            return 0;
        return 1+Math.max(height(root.left), height(root.right));
    }
}

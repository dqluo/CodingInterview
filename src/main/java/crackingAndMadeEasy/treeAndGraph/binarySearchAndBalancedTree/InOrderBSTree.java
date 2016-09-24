package crackingAndMadeEasy.treeAndGraph.binarySearchAndBalancedTree;

import util.BinaryNode;
import util.IntWrapper;
import util.ResultWrapperFC;

/**
 * User: xinyuwan, Date: 11/27/13, Time: 2:12 PM
 */
public class InOrderBSTree
{
    public static void main(String[] args)
    {
        /*
         *              5
         *        3           10
         *     2    4      6     15
         *   1                      17
         */
        BinaryNode<Integer> i=new BinaryNode<Integer>(17);
        BinaryNode<Integer> h=new BinaryNode<Integer>(15, null, i);
        BinaryNode<Integer> g=new BinaryNode<Integer>(6);
        BinaryNode<Integer> e=new BinaryNode<Integer>(1);
        BinaryNode<Integer> d=new BinaryNode<Integer>(4);
        BinaryNode<Integer> c=new BinaryNode<Integer>(2, e, null);
        BinaryNode<Integer> f=new BinaryNode<Integer>(10, g, h);
        BinaryNode<Integer> b=new BinaryNode<Integer>(3, c, d);
        BinaryNode<Integer> a=new BinaryNode<Integer>(5, b, f);
        a.levelorder(a);
        //Test print()
        System.out.println("Pint all in range [2,6]:");
        PrintAllInRange.print(a, 2, 6);
        //Test kthSmallest()
        System.out.println("\nThe 7th small node is: "+FindKthSmallest.find(a, 7, new IntWrapper()).data);
        //Test floorAndCeling()
        ResultWrapperFC fc=FloorAndCeiling.find(a, 12, new ResultWrapperFC());
        System.out.println("Floor and ceiling of 12: ["+fc.floor+", "+fc.ceiling+"]");
        fc=FloorAndCeiling.find(a, 10, new ResultWrapperFC());
        System.out.println("Floor and ceiling of 10: ["+fc.floor+", "+fc.ceiling+"]");

    }
}

class PrintAllInRange
{
    /*
     * Problem1: print all node with value in between k1, k2  in BSTree
     *
     * the goal is to print all qualified elements with minimal traversal
     */
    public static boolean print(BinaryNode<Integer> root, int k1, int k2)
    {
        if(root==null)
            return true;
        boolean left=true;
        //we only go left if root.data>=k1
        if(root.data>=k1)
            left=print(root.left, k1, k2);
        //if left==false or root.data>k2, we don't need to go right
        if(!left || root.data>k2)
            return false;
        //now it's time to truly decide whether we printout root.data or not
        if(root.data<=k2 & root.data>=k1)
            System.out.print(root.data+" ");
        //if we reach this point, then root<=k2 for sure, we need to go right
        return print(root.right, k1, k2);
    }
}

class FindKthSmallest
{
   /*
    * Problem2: find the kth smallest element in Binary Search Tree
    */
    public static BinaryNode find(BinaryNode<Integer> root, int k, IntWrapper count)
    {
        if(root==null)
            return null;
        BinaryNode left=find(root.left, k, count);
        if(left!=null)
            return left;
        if(++count.value==k)
            return root;
        return find(root.right, k, count);
    }
}

class FloorAndCeiling
{
    /*
     * Problem:3 Find floor and ceiling of a particular data in BSTree
     * e.g. tree: 2, 4, 7, 9, 10;  [floor, ceiling] for 5->[4, 7]
     */

    /*
     * Go one scan, and collect both floor and ceiling. The stop case is when
     * ceiling has normal value(<Integer.MAX). We keep update floor data as long
     * as root.data<data. If otherwise root.data>=data, we've found the ceiling.
     */
    public static ResultWrapperFC find(BinaryNode<Integer> root, int data, ResultWrapperFC rw)
    {
        if(root==null)
            return rw;
        ResultWrapperFC left=find(root.left, data, rw);
        //use ceiling as indicator of stop
        if(left.ceiling<Integer.MAX_VALUE)
            return rw;
        if(root.data<data)
        {
            rw.floor=root.data;
            return find(root.right, data, rw);
        }
        else
        {
            //Note: if root.data==data, we have to set both floor and ceiling
            if(root.data==data)
                rw.floor=data;
            rw.ceiling=root.data;
            return rw;
        }
    }
}

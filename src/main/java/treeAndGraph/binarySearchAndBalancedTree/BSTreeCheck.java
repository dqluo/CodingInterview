package treeAndGraph.binarySearchAndBalancedTree;

import util.BinaryNode;
import util.IntWrapper;

/**
 * User: xinyuwan, Date: 11/26/13, Time: 10:43 PM
 */
public class BSTreeCheck
{
    /*
     * Problem: check whether a binary tree is BST
     */
    public static void main(String[] args)
    {
        /*
         *               5
         *          3        7
         *      2       4 6      8
         *   1                       9
         *                              10
         */
        BinaryNode<Integer> j=new BinaryNode<Integer>(10);
        BinaryNode<Integer> i=new BinaryNode<Integer>(9, null, j);
        BinaryNode<Integer> h=new BinaryNode<Integer>(8, null, i);
        BinaryNode<Integer> g=new BinaryNode<Integer>(6);
        BinaryNode<Integer> e=new BinaryNode<Integer>(1);
        BinaryNode<Integer> d=new BinaryNode<Integer>(4);
        BinaryNode<Integer> c=new BinaryNode<Integer>(2, e, null);
        BinaryNode<Integer> f=new BinaryNode<Integer>(7, g, h);
        BinaryNode<Integer> b=new BinaryNode<Integer>(3, c, d);
        BinaryNode<Integer> a=new BinaryNode<Integer>(5, b, f);
        System.out.println(isBSTree(a));
        System.out.println(isBSTree2(a));
        h.data=10;
        System.out.println(isBSTree(a));
        System.out.println(isBSTree2(a));
    }
    //Method1: min/max value
    public static boolean isBSTree(BinaryNode<Integer> root)
    {
        return isBSTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBSTree(BinaryNode<Integer> root, int min, int max)
    {
        if(root==null)
            return true;
        if(root.data<min || root.data>max)
            return false;
        return isBSTree(root.left, min, root.data)
                && isBSTree(root.right, root.data, max);
    }

    /*
     * Method2: using inorder traversal, and compare the data of current
     * node with the previous.The prev is used to store the data of prev node,
     * we can also use a static variable.
     *
     */
    public static boolean isBSTree2(BinaryNode<Integer> root)
    {
        return isBSTree2(root, new IntWrapper(Integer.MIN_VALUE));
    }
    private static boolean isBSTree2(BinaryNode<Integer> root, IntWrapper prev)
    {
        if(root==null)
            return true;
        boolean left=isBSTree2(root.left, prev);
        if(!left)
            return false;
        if(root.data<prev.value)
            return false;
        prev.value=root.data;
        return isBSTree2(root.right, prev);
    }
}

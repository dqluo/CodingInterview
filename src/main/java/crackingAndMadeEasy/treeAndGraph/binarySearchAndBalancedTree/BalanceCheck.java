package crackingAndMadeEasy.treeAndGraph.binarySearchAndBalancedTree;

import util.BinaryNode;

/**
 * User: xinyuwan, Date: 11/26/13, Time: 10:43 PM
 */
public class BalanceCheck
{
    /*
     * Problem: check whether a binary tree is balanced or not
     */

    public static void main(String[] args)
    {
        /*
         *        1
         *     2      3
         *         4     5
         *     (6)
         */
        BinaryNode<Integer> f=new BinaryNode<Integer>(6);
        BinaryNode<Integer> e=new BinaryNode<Integer>(5);
        BinaryNode<Integer> d=new BinaryNode<Integer>(4);
        BinaryNode<Integer> c=new BinaryNode<Integer>(3, d, e);
        BinaryNode<Integer> b=new BinaryNode<Integer>(2);
        BinaryNode<Integer> a=new BinaryNode<Integer>(1, b, c);
        System.out.println(isBalanced(a));
        d.left=f;
        System.out.println(isBalanced(a));
    }
    public static boolean isBalanced(BinaryNode root)
    {
        return checkHeight(root)!=-1;
    }
    /*
     * Combine the check process with get height process, using -1
     * indicating false case;
     *
     * Height related problems are better using post-order
     */
    private static int checkHeight(BinaryNode root)
    {
        if(root==null)
            return 0;
        int left=checkHeight(root.left);
        //pre-checking left and right to improve performance
        if(left==-1)
            return -1;
        int right=checkHeight(root.right);
        if(right==-1)
            return -1;
        int diff=left-right;
        if(Math.abs(diff)>1)
            return -1;
        return Math.max(left, right)+1;
    }
}

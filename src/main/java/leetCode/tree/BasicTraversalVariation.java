package leetCode.tree;

import leetCode.ADT.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * User: xinyuwan, Date: 1/5/14, Time: 7:44 PM
 */
public class BasicTraversalVariation
{
    /*
     * Total problems: 6
     */
    public static void main(String[] args)
    {
        TreeNode a=new TreeNode(1);
        TreeNode b=new TreeNode(2);
        TreeNode c=new TreeNode(2);
        TreeNode d=new TreeNode(3);
        TreeNode e=new TreeNode(3);
        a.left=b;
        a.right=c;
        b.left=d;
        c.right=e;
        //test isSymmetric
        SymmetricTree st=new SymmetricTree();
        System.out.println("Is symmetric? "+st.isSymmetric(a));
        TreeNode f=new TreeNode(4);
        e.right=f;
        System.out.println("Is symmetric? "+st.isSymmetric(a));

        //test minDepth
        System.out.println("Min depth "+new MinDepth().minDepth(a));
    }
}

/*
 * Preorder
 */
class MaxDepth
{
    /*
     * Problem: Given a binary tree, find its maximum depth.
     * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     */
//    public int maxDepth(TreeNode root)
//    {
//        if(root==null)
//            return 0;
//        return 1+Math.max(maxDepth(root.left), maxDepth(root.right));
//    }

    public int maxDepth(TreeNode root){
        List<TreeNode> list=new ArrayList<TreeNode>();
        list.add(root);
        int height=0;
        while(list.size()>0)
        {
            int size=list.size();
            for(int i=0;i<size;i++)
            {
                TreeNode temp=list.remove(0);
                if(temp.left!=null)
                    list.add(temp.left);
                if(temp.right!=null)
                    list.add(temp.right);
            }
            height++;
        }
        return height;
    }
}

class SameTree
{
    /*
     * Problem: Given two binary trees, write a function to check if they are equal or not.
     * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
     */
    public boolean isSameTree(TreeNode p, TreeNode q)
    {
        if(p==null && q==null)
            return true;
        if(p==null || q==null)
            return false;
        if(p.val!=q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

class SymmetricTree
{
    /*
     * Problem: Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     * For example, this binary tree is symmetric:
             1
           / \
          2   2
         / \ / \
        3  4 4  3
     * But the following is not:
            1
          / \
         2   2
         \   \
         3    3
     */
    //use levelorder but everytime enqueue 2 nodes and store the left of first and right of second,
    // as well as the right of first and left of second to make sure we compare the right pair
    public boolean isSymmetric(TreeNode root)
    {
        if(root==null)
            return true;
        Queue<TreeNode> q=new LinkedList<TreeNode>();
        q.add(root.left);
        q.add(root.right);
        while(!q.isEmpty())
        {
            TreeNode left=q.remove();
            TreeNode right=q.remove();
            if(left==null && right==null)
                continue;
            if(left==null || right==null || left.val!=right.val)
                return false;
            q.add(left.left);
            q.add(right.right);
            q.add(left.right);
            q.add(right.left);
        }
        return true;
    }
    //preorder
    public boolean isSymmetric2(TreeNode root)
    {
         if(root==null)
             return true;
         return isMirror(root.left, root.right);
    }
    private boolean isMirror(TreeNode r1, TreeNode r2)
    {
         if(r1==null && r2==null)
             return true;
         if(r1==null || r2==null)
             return false;
         if(r1.val != r2.val)
             return false;
         return isMirror(r1.left, r2.right) && isMirror(r1.right, r2.left);
    }
}

class ValidateBST
{
    /*
     * Problem: Given a binary tree, determine if it is a valid binary search tree (BST).
     */
    public boolean isValidBST(TreeNode root)
    {
        return isBSTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private boolean isBSTree(TreeNode root, int min, int max)
    {
        if(root==null)
            return true;
        if(root.val<min || root.val>max)
            return false;
        return isBSTree(root.left, min, root.val)
                && isBSTree(root.right, root.val, max);
    }
}

/*
 * Postorder
 */
class BalancedBinaryTree
{
    /*
     * Problem: Given a binary tree, determine if it is height-balanced.
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
     */
    public boolean isBalanced(TreeNode root)
    {
        return checkHeight(root) !=-1;
    }
    private int checkHeight(TreeNode root)
    {
        if(root==null)
            return 0;
        int left=checkHeight(root.left);
        if(left==-1)
            return -1;
        int right=checkHeight(root.right);
        if(right==-1)
            return -1;
        int diff=Math.abs(left-right);
        if(diff > 1)
            return -1;
        return 1+Math.max(left, right);
    }
}

/*
 * Levelorder
 */
class MinDepth
{
    /*
     * Problem: Given a binary tree, find its minimum depth.
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
     */
    //Levelorder: find the first leave node
    public int minDepth(TreeNode root)
    {
        if(root==null)
            return 0;
        Queue<TreeNode> q=new LinkedList<TreeNode>();
        TreeNode current=root;
        int level=1;
        q.add(current);
        q.add(null);
        while(!q.isEmpty())
        {
            current=q.remove();
            if(current==null)
            {
                if(!q.isEmpty())
                {
                    level++;
                    q.add(null);
                }
                continue;
            }
            if(current.left==null && current.right==null)
                break;
            if(current.left!=null)
                q.add(current.left);
            if(current.right!=null)
                q.add(current.right);
        }
        return level;
    }

    int min=Integer.MAX_VALUE;
    public int minDepth2(TreeNode root)
    {
        if(root==null)
            return 0;
        minDepth(root, 1);
        return min;
    }
    private void minDepth(TreeNode root, int level)
    {
        if(root.left==null && root.right==null)
        {
            min=Math.min(level, min);
            return;
        }
        if(root.left!=null)
            minDepth(root.left, level+1);
        if(root.right!=null)
            minDepth(root.right, level+1);
    }
}



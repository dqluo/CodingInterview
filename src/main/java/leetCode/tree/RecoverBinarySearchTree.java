package leetCode.tree;

import leetCode.ADT.TreeNode;

import java.util.Stack;

/**
 * User: xinyuwan, Date: 12/24/13, Time: 7:13 PM
 */
public class RecoverBinarySearchTree
{
    public static void main(String[] args)
    {
        TreeNode a=new TreeNode(3);
        TreeNode b=new TreeNode(1);
        TreeNode c=new TreeNode(2);
        a.left=b;
        a.right=c;
        System.out.println("Before recovering: "+new BinaryTreeInorder().inorderTraversal(a));
        RecoverBinarySearchTree rt=new RecoverBinarySearchTree();
        rt.recoverTree(a);
        System.out.println("After recovering: "+new BinaryTreeInorder().inorderTraversal(a));
    }
    /*
     * Problem: Two elements of a binary search tree (BST) are swapped by mistake.
     * Recover the tree without changing its structure.
     * Note: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
     *
     * Analysis: use two pointers to mark the two nodes to swap. The first must be larger
     * than the second. Do an inorder traverse and record the previous node to compare
     * with the current so that we can identify the wrong nodes. If previous.val>root.val,
     * we know that root.val must be the one we need. But for the previous one, it's possible
     * that if the two nodes happen to be contiguous than we've found both(i.e.[1, 3, 2, 4, 5]);
     * otherwise the we should update the second and break once we found it
     */
    public void recoverTree(TreeNode root)
    {
        if(root==null)
            return;
        TreeNode previous=null, first=null, second=null;
        Stack<TreeNode> s=new Stack<TreeNode>();
        while(root!=null || !s.isEmpty())
        {
            while(root!=null)
            {
                s.push(root);
                root=root.left;
            }
            if(!s.isEmpty())
            {
                root=s.pop();
                if(previous!=null && previous.val > root.val)
                {
                    if(first==null)
                    {
                        first=previous;
                        second=root;
                    }
                    else
                    {
                        second=root;
                        break;
                    }
                }
                //remember to update previous no matter it's null or not
                previous=root;
                root=root.right;
            }
        }
        //swap the value of first and second
        int temp=first.val;
        first.val=second.val;
        second.val=temp;
    }
}

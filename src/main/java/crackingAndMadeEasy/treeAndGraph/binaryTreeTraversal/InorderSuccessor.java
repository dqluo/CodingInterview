package crackingAndMadeEasy.treeAndGraph.binaryTreeTraversal;

import util.TreeNode;

/**
 * User: xinyuwan, Date: 11/26/13, Time: 7:31 PM
 */
public class InorderSuccessor
{
    /*
     * Problem: find the inorder successor of the current node
     *      10
     *    3
     *      6
     *        8
     *      7
     */
    public static void main(String[] args)
    {
        TreeNode f=new TreeNode(7);
        TreeNode e=new TreeNode(8, f, null);
        TreeNode d=new TreeNode(6, null, e);
        TreeNode b=new TreeNode(3, null, d);
        TreeNode a=new TreeNode(10, b, null, null);
        b.parent=a;
        d.parent=b;
        e.parent=d;
        f.parent=e;
        TreeNode n=b;
        while(n!=null)
        {
            System.out.print(n.data+" ");
            n=inOrderSuccessor(n);
        }

    }
    public static TreeNode inOrderSuccessor(TreeNode node)
    {
        if(node==null)
            return null;
        //Found right children, return leftmost node
        //of right subtree
        if(node.right!=null)
            return getLeftMost(node.right);
        //Case1: n is on the left of q: return q
        //Case2: n is on the right of q: return the first node that is
        //on the left of its parent
        TreeNode parent=node.parent;
        while(parent!=null && node!=parent.left)
        {
            node=parent;
            parent=parent.parent;
        }
        return parent;
    }
    private static TreeNode getLeftMost(TreeNode node)
    {
        if(node==null)
            return null;
        while(node.left!=null)
            node=node.left;
        return node;
    }
}
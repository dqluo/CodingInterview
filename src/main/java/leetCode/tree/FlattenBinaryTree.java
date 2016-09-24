package leetCode.tree;

import leetCode.ADT.TreeNode;

import static leetCode.ADT.TreeNode.preorder;

/**
 * User: xinyuwan, Date: 12/22/13, Time: 1:05 PM
 */
public class FlattenBinaryTree
{
    public static void main(String[] args)
    {
        FlattenBinaryTree fbt=new FlattenBinaryTree();

        TreeNode a=new TreeNode(1);
        TreeNode b=new TreeNode(2);
        TreeNode c=new TreeNode(3);
        a.left=b;
        a.right=c;
        TreeNode d=new TreeNode(4);
        b.right=d;
        TreeNode e=new TreeNode(5);
        TreeNode f=new TreeNode(6);
        c.left=e;
        c.right=f;
        fbt.flatten(a);
        preorder(a);

    }
    /*
     * Problem:  Given a binary tree, flatten it to a linked list in-place.
     * For example, Given
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     * The flattened tree should look like:
     * 1
     *  \
     *   2
     *    \
     *     3
     *      \
     *       4
     *        \
     *         5
     *          \
     *           6
     */
    TreeNode previous;
    public void flatten(TreeNode root)
    {
        if(root==null)
            return;
        if(previous!=null)
            previous.right=root;
        previous=root;
        //because we will modify left and right in the traversal we store them first
        TreeNode left=root.left;
        TreeNode right=root.right;
        //if left is not null, we go left but we must set root.left=null since we'll set root.left=root.right when recurse down
        if(left!=null)
        {
            root.left=null;
            flatten(left);
        }
        //don't set root.right=null, since we might have set root.right=root.left in previous recursion
        if(right!=null)
        {
            flatten(right);
        }
    }
}

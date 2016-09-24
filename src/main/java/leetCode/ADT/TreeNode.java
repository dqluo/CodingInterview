package leetCode.ADT;

/**
 * User: xinyuwan, Date: 12/11/13, Time: 11:54 PM
 */
public class TreeNode
{
    public int val;
    public TreeNode left, right;
    public TreeNode(int x)
    {
        val=x;
    }

    public static int getHeight(TreeNode root)
    {
        if(root==null)
            return 0;
        return 1+Math.max(getHeight(root.left), getHeight(root.right));
    }
    public static void preorder(TreeNode root)
    {
        if(root==null)
           return;
        System.out.print(root.val+" ");
        preorder(root.left);
        preorder(root.right);
    }
    public static void inorder(TreeNode root)
    {
        if(root==null)
            return;
        inorder(root.left);
        System.out.print(root.val+" ");
        inorder(root.right);
    }
}

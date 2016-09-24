package util;

public class TreeNode
{
    public int data;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;

    public TreeNode(int d)
    {
        this(d, null, null, null);
    }
    public TreeNode(int d, TreeNode l, TreeNode r)
    {
        this(d, l, r, null);
    }
    public TreeNode(int d, TreeNode l, TreeNode r, TreeNode p)
    {
        data=d;
        left=l;
        right=r;
        parent=p;
    }
    public void inorder(TreeNode root)
    {
        if(root!=null)
        {
            inorder(root.left);
            System.out.print(root.data+" ");
            inorder(root.right);
        }
    }
}

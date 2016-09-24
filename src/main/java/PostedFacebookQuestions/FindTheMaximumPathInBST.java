package PostedFacebookQuestions;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-17
 * Time: 上午9:21
 * To change this template use File | Settings | File Templates.
 */
public class FindTheMaximumPathInBST {
    public static int max=0;

    public static int maximumPath(TreeNode root){
        if(root==null)
            return 0;
        int left=maximumPath(root.left);
        int right=maximumPath(root.right);
        if(left+right+1>max)
            max=left+right+1;
        return 1+Math.max(left, right);
    }

    public static void main(String[] args)
    {
        TreeNode node1=new TreeNode(1);
        TreeNode node2=new TreeNode(2);
        TreeNode node3=new TreeNode(3);
        TreeNode node4=new TreeNode(4);
        TreeNode node5=new TreeNode(5);
        TreeNode node6=new TreeNode(6);
        TreeNode node7=new TreeNode(7);
        TreeNode node8=new TreeNode(8);
        TreeNode node9=new TreeNode(9);
        node5.left=node2;
        node5.right=node7;
        node2.left=node1;
        node2.right=node4;
        node4.left=node3;
        node7.left=node6;
        node7.right=node8;
        node8.right=node9;
        maximumPath(node5);
        System.out.println(max);
    }
}

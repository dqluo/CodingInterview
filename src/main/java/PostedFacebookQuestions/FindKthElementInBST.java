package PostedFacebookQuestions;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-16
 * Time: 下午3:38
 * To change this template use File | Settings | File Templates.
 */
public class FindKthElementInBST {
    static int number=0;
    public static void find(TreeNode root, int k)
    {
        if(root==null)
            return;
        find(root.left, k);
        number++;
        if(number==k)
            System.out.println(root.data);
        find(root.right, k);
    }

    public static int find2(TreeNode root, int k)
    {
        Stack<TreeNode> stack=new Stack<TreeNode>();
        TreeNode next=root;
        int index=0;
        while(!stack.isEmpty() || next!=null)
        {
            while(next!=null)
            {
                stack.push(next);
                next=next.left;
            }
            if(!stack.isEmpty())
            {
                next=stack.pop();
                index++;
                if(index==k)
                    return next.data;
                next=next.right;
            }
        }
        return Integer.MIN_VALUE;
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
        node4.left=node2;
        node4.right=node6;
        node2.left=node1;
        node2.right=node3;
        node6.left=node5;
        node6.right=node7;
        find(node4, 5);
        System.out.println(find2(node4, 6));
    }
}


class TreeNode{
    public int data;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int data)
    {
        this.data=data;
    }
}

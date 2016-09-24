package postedLinkedInQuestions;

import leetCode.ADT.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Q56. 给一个二叉树，返回它的镜像
 */

public class TreeMirror {

    public static void mirror(TreeNode root){
        if(root==null)
            return;
        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;
        mirror(root.left);
        mirror(root.right);
    }

    private static void printTreeByLevel(TreeNode root){
        if(root == null) return;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int i = 0; i < size; i++)
            {
                TreeNode parent = q.poll();
                System.out.print(parent.val+" ");
                if(parent.left != null)
                    q.offer(parent.left);
                if(parent.right != null)
                    q.offer(parent.right);
            }
            System.out.println();
        }
    }

    public static void main(String args[]){
        TreeNode node1=new TreeNode(1);
        TreeNode node2=new TreeNode(2);
        TreeNode node3=new TreeNode(3);
        TreeNode node4=new TreeNode(4);
        TreeNode node5=new TreeNode(5);
        TreeNode node6=new TreeNode(6);
        TreeNode node7=new TreeNode(7);
        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        node3.right=node7;
        mirror(node1);
        printTreeByLevel(node1);
    }
}

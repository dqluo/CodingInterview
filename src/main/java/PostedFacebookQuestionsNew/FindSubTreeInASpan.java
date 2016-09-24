package PostedFacebookQuestionsNew;


import leetCode.ADT.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *  给一个span(min, max)和BST, 返回一个子树, 子树里面的节点都在这个span里面.
 */
public class FindSubTreeInASpan {

    public static TreeNode findSubtree(TreeNode node, int min, int max){
        if(node==null || min>max)
            return null;
        TreeNode root=findRoot(node, min, max);
        constructTree(root, min, max);
        return root;
    }

    private static TreeNode findRoot(TreeNode root, int min, int max){
        if(root==null)
            return root;
        if(root.val>=min && root.val<=max)
            return root;
        else if(root.val<max)
            return findRoot(root.left, min, max);
        else
            return findRoot(root.right, min, max);
    }

    private static void constructTree(TreeNode root, int min, int max){
        if(root==null)
            return;
        if(root.left != null) {
            if (root.left.val < min)
                root.left = null;
            else
                constructTree(root.left, min, max);
        }
        if(root.right != null) {
            if (root.right.val > max)
                root.right = null;
            else
                constructTree(root.right, min, max);
        }
    }

    private static void printTree(TreeNode root){
        if(root==null)
            return;
        List<TreeNode> queue=new ArrayList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                TreeNode temp=queue.remove(0);
                System.out.print(temp.val+" ");
                if(temp.left!=null)
                    queue.add(temp.left);
                if(temp.right!=null)
                    queue.add(temp.right);
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
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
        printTree(node4);
        System.out.println();
        TreeNode node=findSubtree(node4, 3, 5);
        printTree(node);
    }
}

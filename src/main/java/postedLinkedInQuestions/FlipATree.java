package postedLinkedInQuestions;

import leetCode.ADT.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree where all the right nodes are leaf nodes, flip it
 * upside down and turn it into a tree with left leaf nodes.
 * Keep in mind: ALL RIGHT NODES IN ORIGINAL TREE ARE LEAF NODE.
 * for example, turn these:
 *         1                 1
 *        / \               / \
 *       2   3             2   3
 *      / \
 *     4   5
 *    / \
 *   6   7
 *  into these:
 *        1               1
 *       /               /
 *      2---3         2---3
 *     /
 *    4---5
 *   /
 *  6---7
 * where 6 is the new root node for the left tree, and 2 for the right tree.
 * oriented correctly:
 *     6                   2
 *    / \                 / \
 *   7   4              3   1
 *      / \
 *     5   2
 *        / \
 *       3   1
 *
*/
public class FlipATree {

    public static TreeNode flip(TreeNode root){
        TreeNode preRoot=root;
        while(preRoot.left!=null){
            TreeNode tempRoot=preRoot.left;
            TreeNode tempRight=preRoot;
            TreeNode tempLeft=preRoot.right;
            tempRoot.left=tempLeft;
            tempRoot.right=tempRight;
            preRoot=tempRoot;
        }
        return preRoot;
    }

    public static void printTree(TreeNode node){
        if(node==null)
            return;
        List<TreeNode> list=new ArrayList<TreeNode>();
        list.add(node);
        while(list.size()>0){
            int size=list.size();
            for(int i=0;i<size;i++){
                TreeNode tempNode=list.remove(list.size()-1);
                System.out.print(tempNode.val+" ");
                if(tempNode.left!=null)
                    list.add(0, tempNode.left);
                if(tempNode.right!=null)
                    list.add(0, tempNode.right);
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
        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node4.left=node6;
        node4.right=node7;
        printTree(node1);
        System.out.println();
        TreeNode node=flip(node1);
        printTree(node);
    }
}

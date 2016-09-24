package postedLinkedInQuestions;

import leetCode.ADT.TreeNode;

/**
 * Q45. Same Tree
 */
public class SameTree {

    public static boolean sameTree(TreeNode n1, TreeNode n2){
        if(n1==null && n2==null)
            return true;
        if(n1==null || n2==null)
            return false;
        return (n1.val==n2.val && sameTree(n1.left, n2.left) && sameTree(n1.right, n2.right));
    }

    public static void main(String[] args){
        TreeNode n11=new TreeNode(1);
        TreeNode n12=new TreeNode(2);
        TreeNode n13=new TreeNode(3);
        TreeNode n14=new TreeNode(4);
        TreeNode n15=new TreeNode(5);
        TreeNode n16=new TreeNode(6);
        TreeNode n17=new TreeNode(7);
        n11.left=n12;
        n11.right=n13;
        n12.left=n14;
        n12.right=n15;
        n13.left=n16;
        n13.right=n17;
        TreeNode n21=new TreeNode(1);
        TreeNode n22=new TreeNode(2);
        TreeNode n23=new TreeNode(3);
        TreeNode n24=new TreeNode(4);
        TreeNode n25=new TreeNode(5);
        TreeNode n26=new TreeNode(6);
        TreeNode n27=new TreeNode(7);
        n21.left=n22;
        n21.right=n23;
        n22.left=n24;
        n22.right=n25;
        n23.left=n26;
        n23.right=n27;
        System.out.println(sameTree(n11, n21));
    }
}

package leetCode;

import leetCode.ADT.TreeNode;

import java.util.ArrayList;

/**
 * User: xinyuwan, Date: 12/11/13, Time: 11:53 PM
 */
public class SymmetricTree
{
    public static void main(String[] args)
    {
        TreeNode a=new TreeNode(1);
        TreeNode b=new TreeNode(2);
        TreeNode c=new TreeNode(2);
        a.left=b;
        a.right=c;
//        System.out.println(new SymmetricTree().isSymmetric(a));

    }

}

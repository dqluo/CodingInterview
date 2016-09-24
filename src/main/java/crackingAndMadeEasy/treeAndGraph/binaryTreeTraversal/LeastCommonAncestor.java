package crackingAndMadeEasy.treeAndGraph.binaryTreeTraversal;

import util.BinaryNode;

/**
 * User: xinyuwan, Date: 11/26/13, Time: 7:04 PM
 */
public class LeastCommonAncestor
{
    /*
     * Find the least Common Ancestor for two sub-binaryTree A and B
     */
    public static void main(String[] args)
    {

        /*
         *             5
         *         3        7
         *      2     4   6   8
         *   1                  9
         */
        BinaryNode<Integer> i=new BinaryNode<Integer>(9);
        BinaryNode<Integer> h=new BinaryNode<Integer>(8, null, i);
        BinaryNode<Integer> g=new BinaryNode<Integer>(6);
        BinaryNode<Integer> e=new BinaryNode<Integer>(1);
        BinaryNode<Integer> d=new BinaryNode<Integer>(4);
        BinaryNode<Integer> c=new BinaryNode<Integer>(2, e, null);
        BinaryNode<Integer> f=new BinaryNode<Integer>(7, g, h);
        BinaryNode<Integer> b=new BinaryNode<Integer>(3, c, d);
        BinaryNode<Integer> a=new BinaryNode<Integer>(5, b, f);
        System.out.println(LCA(a, e, i).data+" "+LCA2(a, e, i).data+" "+LCAForBST2(a, e, i).data);
        System.out.println(LCA(a, e, f).data+" "+LCA2(a, e, f).data+" "+LCAForBST2(a, e, f).data);
        System.out.println(LCA(a, e, d).data+" "+LCA2(a, e, d).data+" "+LCAForBST2(a, e, d).data);
        System.out.println(LCA(a, g, i).data+" "+LCA2(a, g, i).data+" "+LCAForBST2(a, g, i).data);
        System.out.println(LCA(a, i, h).data+" "+LCA2(a, i, h).data+" "+LCAForBST2(a, i, h).data);
        System.out.println(LCA(a, i, a).data+" "+LCA2(a, i, a).data+" "+LCAForBST2(a, i, a).data);
    }

    //Method1: directly test the root node while traversing the tree
    public static BinaryNode LCA(BinaryNode root, BinaryNode a, BinaryNode b)
    {
        BinaryNode left, right;
        if(root==null)
            return null;
        if(root==a || root==b)
            return root;
        left=LCA(root.left, a, b);
        right=LCA(root.right, a, b);
        //if both not null, the root must be the least common ancestor
        if(left!=null && right!=null)
            return root;
        else
            return left==null? right : left;
    }
    //Method2: use covers to help decide which side a and b is under
    public static BinaryNode LCA2(BinaryNode root, BinaryNode a, BinaryNode b)
    {
        if(root==null)
            return null;
        //we need to consider the case when root==a|b
        if(root==a || root==b)
            return root;
        boolean isAOnLeft=covers(root.left, a);
        boolean isBOnLeft=covers(root.left, b);
        if(isAOnLeft != isBOnLeft)
            return root;
        //else a, b on the same side
        BinaryNode childSide=isAOnLeft? root.left : root.right;
        return  LCA2(childSide, a, b);
    }
    //this method helps decide whether p node is under root or not
    public static boolean covers(BinaryNode root, BinaryNode p)
    {
        if(root==null)
            return false;
        if(root==p)
            return true;
        return covers(root.left, p) || covers(root.right, p);
    }


    //LCA for BSTree
    //This method find the LCA for BSTree by taking advantage of the data property
    public static BinaryNode LCAForBST(BinaryNode<Integer> root, BinaryNode<Integer> a, BinaryNode<Integer> b)
    {
        if(root==null)
            return null;
        int min=Math.min(a.data, b.data);
        int max=Math.max(a.data, b.data);
        //a, b on different side, found LCA
        if(root.data>=min && root.data<=max)
            return root;
        else if(root.data>max)
            return LCAForBST(root.left, a, b);
        else
            return LCAForBST(root.right, a, b);
    }
    public static BinaryNode LCAForBST2(BinaryNode<Integer> root, BinaryNode<Integer> a, BinaryNode<Integer> b)
    {
        int min=Math.min(a.data, b.data);
        int max=Math.max(a.data, b.data);
        while(root!=null)
        {
            if(root.data<min)
                root=root.right;
            else if(root.data>max)
                root=root.left;
            else
                return root;
        }
        return null;
    }

}
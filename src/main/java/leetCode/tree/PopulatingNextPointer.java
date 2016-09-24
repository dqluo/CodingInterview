package leetCode.tree;

import leetCode.ADT.TreeLinkNode;

/**
 * User: xinyuwan, Date: 1/6/14, Time: 12:18 AM
 */
public class PopulatingNextPointer
{
    /*
     * Total problems: 2
     */
    public static void main(String[] args)
    {
        TreeLinkNode a=new TreeLinkNode(1);
        TreeLinkNode b=new TreeLinkNode(2);
        TreeLinkNode c=new TreeLinkNode(3);
        TreeLinkNode d=new TreeLinkNode(4);
        TreeLinkNode e=new TreeLinkNode(5);
        TreeLinkNode f=new TreeLinkNode(6);
        TreeLinkNode g=new TreeLinkNode(7);
        a.left=b; a.right=c;
        b.left=d; b.right=e;
        c.left=f; c.right=g;
        PopulatingNextPointer pnp=new PopulatingNextPointer();
        pnp.connect(a);
        TreeLinkNode temp=d;
        System.out.print("Print Level starting with 4: ");
        while(temp!=null)
        {
            System.out.print(temp.val+" ");
            temp=temp.next;
        }
        System.out.println();

        TreeLinkNode h=new TreeLinkNode(8);
        TreeLinkNode i=new TreeLinkNode(9);
        TreeLinkNode j=new TreeLinkNode(10);
        TreeLinkNode k=new TreeLinkNode(11);
        TreeLinkNode l=new TreeLinkNode(12);
        d.left=h; d.right=i;
        f.left=j; g.left=k; g.right=l;

        PopulatingNextPointer2 pnp2=new PopulatingNextPointer2();
        pnp2.connect(a);
        temp=h;
        System.out.print("Print Level starting with 8: ");
        while(temp!=null)
        {
            System.out.print(temp.val+" ");
            temp=temp.next;
        }
        System.out.println();
    }
    /*
     * Problem: Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
     * Initially, all next pointers are set to NULL.
     * Note:
     * You may only use constant extra space.
     * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
     */
    //preorder: set the next pointer from parent level, make sure not parent level not leaf node, and set left.next and right.next
    public void connect(TreeLinkNode root)
    {
         if(root==null || root.left==null || root.right==null)
             return;
         if(root.next!=null)
             root.right.next=root.next.left;
         root.left.next=root.right;
         connect(root.left);
         connect(root.right);
    }
    //iterative: set the next pointer level by level, iterate each level by using the next pointer
    public void connect2(TreeLinkNode root)
    {
        if(root==null)
            return;
        while(root.left!=null)
        {
            TreeLinkNode current=root, rightSib=null;
            while(current!=null)
            {
                if(current.next!=null)
                    rightSib=current.next.left;
                    //we must make rightSib to be null at the beginning of every iteration
                    //otherwise, the rightSib still points to current.left which will result
                    //in cyclic reference between right and left
                else
                    rightSib=null;
                current.left.next=current.right;
                current.right.next=rightSib;
                current=current.next;
            }
            root=root.left;
        }
    }
}

class PopulatingNextPointer2
{
    /*
     * Problem: Follow up for problem "Populating Next Right Pointers in Each Node".
     * What if the given tree could be any binary tree? Would your previous solution still work?
     * Note: You may only use constant extra space.
     */
   /*
    * Analysis: The basic logic of this problem is essentially the same. However,
    * we need to consider multiple different cases:
    * 1. root.left!=null && root.right!=null
    * we simply connect these two and try to connect root.right to newRightSib
    * 2. root.left!=null && root.right==null
    * we need to find the next RightSib and connect root.left to it
    * 3. root.left==null && root.right!=null
    * we need to find the next RightSib and connect root.right to it
    * 4. root.left==null && root.right ==null
    * we do nothing and continue
    *
    * From the above 4 cases: we came to two important conclusions:
    * 1. connect root.left to root.right if they are both not null;
    * 2. connect the rightmost child of root to the next right sibling
    * As a result we need to find a way to get the next rightSib. Because root.next could be
    * any node(leaf, hasLeft, hasRight, hasBoth), we need to find one non-null rightSib if possible
    * otherwise if we reach the end of the list at this level, we return null.
    *
    * There is one final point we need to be very very very careful here: we need to recurse down to right
    * then left, otherwise we will lose connections like the below situation:
    * When we are at level x(x=1), we connect nodes at level x+1, but before we connect 6 and 7, we go down to
    * level x+2. This is fine when the nodes at level x+1 have at lease one child. However since 6 is leaf, and
    * 6 and 7 are not connected by the time we recurce to 5, we won't be able to connect 10 and 11, since 11 is
    * the left child of 7. So we have to go right first and make sure that we finish the right half of the list
    * first, and then safely go left, and thus the getNextRightSib will guarantee to find the rightSib if it exists.
    *          1
    *      2  ->    3
    *   4 -> 5 -> 6   7
    * 8->9 ->  10    11  12
    *
    */
    public void connect(TreeLinkNode root)
    {
        if(root==null)
            return;
        //only calculate rightSib once
        TreeLinkNode rightSib=getNextRightSib(root);
        if(root.left!=null)
            root.left.next= root.right==null? rightSib : root.right;
        if(root.right!=null)
            root.right.next=rightSib;
        connect(root.right);
        connect(root.left);
    }
    public TreeLinkNode getNextRightSib(TreeLinkNode root)
    {
        while(root!=null && root.next!=null)
        {
            if(root.next.left!=null)
                return root.next.left;
            if(root.next.right!=null)
                return root.next.right;
            root=root.next;
        }
        return null;
    }
}

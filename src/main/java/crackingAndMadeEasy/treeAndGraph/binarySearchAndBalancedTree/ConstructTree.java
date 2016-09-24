package crackingAndMadeEasy.treeAndGraph.binarySearchAndBalancedTree;

import util.BinaryNode;
import util.IntWrapper;

/**
 * User: xinyuwan, Date: 11/27/13, Time: 4:06 PM
 */
public class ConstructTree
{
    public static void main(String[] args)
    {
        int[] arr={1, 2, 3, 4, 5, 6, 7, 8};
        BinaryNode<Integer> a=CreateBalBSTreeOnSortedArr.construct(arr, 0, arr.length-1);
        System.out.print("Create a balanced BSTree, inorder: ");
        a.inorder();
        System.out.println("Height: "+a.getHeight());

        int[] inorder={1, 2, 3, 4, 5, 6};
        int[] preorder={4, 2, 1, 3, 6, 5};
        BinaryNode newTree=CreateBinaryTreeOnIOAndPO.construct(inorder, preorder);
        System.out.print("Create a binary tree, inorder: ");
        newTree.inorder();
        System.out.print("Preorder: ");
        newTree.preorder();

        char[] charSeq="ILILL".toCharArray();
        BinaryNode ILTree=CreateILTree.construct(charSeq);
        System.out.print("Create a ILTree, preorder: ");
        ILTree.preorder();
        System.out.println("Levelorder: ");
        ILTree.levelorder(ILTree);
    }
}

class CreateBalBSTreeOnSortedArr
{
    /*
     * Problem1: create a BSTree with minimal height, based on given array
     */
    public static BinaryNode construct(int[] a, int first, int last)
    {
        if(first>last)
            return null;
        //add this condition to avoid redundant call stacks(do not go down if leaf)
        if(first==last)
            return new BinaryNode<Integer>(a[first]);
        int mid=(first+last)/2;
        BinaryNode left=construct(a, first, mid-1);
        BinaryNode right=construct(a, mid+1, last);
        return new BinaryNode<Integer>(a[mid], left, right);
    }
}

class CreateBinaryTreeOnIOAndPO
{
    /*
     * Problem2: create a binary tree based on two arrays: inorder sequence and preorder sequence
     */
    public static BinaryNode construct(int[] io, int[] po)
    {
        return construct(io, po, 0, po.length-1, new IntWrapper());
    }
    private static BinaryNode construct(int[] io, int[] po, int first, int last, IntWrapper preIndex)
    {
        if(first>last)
            return null;
        /*
         * if we reach this point, we definitely need to create a new node, also,
         * using newData to keep the current node value before we advance preIndex
         */
        int newData=po[preIndex.value];
        BinaryNode newNode=new BinaryNode<Integer>(newData);
        //no matter first is equal to last, we need to advance preIndex
        preIndex.value++;
        if(first==last)
            return newNode;
        //limit the range to first and last to improve performance
        int index=find(io, newData, first, last);
        newNode.left=construct(io, po, first, index-1, preIndex);
        newNode.right=construct(io, po, index+1, last, preIndex);
        return newNode;
    }
    private static int find(int[] a, int data, int first, int last)
    {
        for(int i=first; i<=last; i++)
        {
            if(a[i]==data)
                return i;
        }
        return -1;
    }
}

class CreateILTree
{
  /*
   * Problem3: create a IL tree, in which I represents innerNode, L represents leaveNode
   * IL tree has the property that each innerNode must have two children.
   */
    public static BinaryNode construct(char[] preorder)
    {
        return construct(preorder, new IntWrapper());
    }
    private static BinaryNode construct(char[] preorder, IntWrapper index)
    {
        if(preorder==null || index.value>=preorder.length)
            return null;
        BinaryNode<Character> newNode=new BinaryNode<Character>(preorder[index.value]);
        index.value++;
        //this base case is equivalent to "first==last"
        if(newNode.data=='L')
            return newNode;
        newNode.left=construct(preorder, index);
        newNode.right=construct(preorder,index);
        return newNode;
    }
}
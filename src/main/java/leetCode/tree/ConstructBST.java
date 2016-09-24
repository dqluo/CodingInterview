package leetCode.tree;

import leetCode.ADT.ListNode;
import leetCode.ADT.TreeNode;

import static leetCode.ADT.ListNode.createList;

/**
 * User: xinyuwan, Date: 12/20/13, Time: 5:54 PM
 */
public class ConstructBST
{
    /*
     * Total problems: 4
     */
    public static void main(String[] args)
    {
        //test convert sorted array to BST
        int[] arr={1, 4, 8, 10, 11, 27};
        TreeNode rootArr=new ConvertSortedArrayToBST().sortedArrayToBST(arr);
        System.out.print("Inorder of the converted array: ");
        System.out.println(new BinaryTreeInorder().inorderTraversal(rootArr));

        //test convert sorted list to BST
        int[] arr2={1, 2, 3, 4, 5};
        ListNode listArr2=createList(arr2);
        TreeNode rootArr2=new ConvertSortedLinkedToBST().sortedListToBST(listArr2);
        System.out.print("Inorder of the converted list: ");
        System.out.println(new BinaryTreeInorder().inorderTraversal(rootArr2));

        //test construct BST from inorder and preorder/postorder
        int[] inorder={1, 4, 7, 9, 10, 11};
        int[] preorder={7, 4, 1, 10, 9, 11};
        int[] postorder={1, 4, 9, 11, 10, 7};
        TreeNode r1=new ConstructBSTFromInAndPre().buildTree(preorder, inorder);
        TreeNode r2=new ConstructBSTFromInAndPost().buildTree(inorder, postorder);
        System.out.print("Levelorder of the inorder/preorder tree: ");
        System.out.println(new BinaryTreeLevelorder().levelOrder(r1));
        System.out.print("Levelorder of the inorder/postorder tree: ");
        System.out.println(new BinaryTreeLevelorder().levelOrder(r2));
    }
}

class ConvertSortedArrayToBST
{
    /*
     * Problem: Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
     */
    public TreeNode sortedArrayToBST(int[] num)
    {
        return sortedArrayToBST(num, 0, num.length-1);
    }

    private TreeNode sortedArrayToBST(int[] num, int first, int last)
    {
        if(first > last)
            return null;
        int mid=(first+last)/2;
        TreeNode root=new TreeNode(num[mid]);
        root.left=sortedArrayToBST(num, first, mid-1);
        root.right=sortedArrayToBST(num, mid+1, last);
        return root;
    }
}

class ConvertSortedLinkedToBST
{
    /*
     * Problem: Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
     *
     * Analysis: Key is inorder traversal
     * using the property that inorder travseral a bst is the same order as
     * the sorted list, we move forward head each time we go right and control
     * the recursion using first and last index
     */
    ListNode listHead;
    public TreeNode sortedListToBST(ListNode head)
    {
        listHead=head;
        int size=0;
        while(head!=null)
        {
            head=head.next;
            size++;
        }
        return createBST(0, size-1);
    }
    private TreeNode createBST(int first, int last)
    {
        if(first > last)
            return null;
        int mid=(first+last)/2;
        TreeNode left=createBST(first, mid-1);
        TreeNode root=new TreeNode(listHead.val);
        root.left=left;
        listHead=listHead.next;
        TreeNode right=createBST(mid+1, last);
        root.right=right;
        return root;
    }
}

class ConstructBSTFromInAndPre
{
    /*
     * Problem: Given preorder and inorder traversal of a tree, construct the binary tree.
     * Note: You may assume that duplicates do not exist in the tree.
     *
     * Analysis: The diff between this problem with previous convertToBST problem is that we
     * need to construct BST with two rather than one array. When we construct BST form one
     * sorted array, we actually do 2 things in each recursion: we find the root value; and we
     * find the left and right subtrees. We can achieve this by calculate the mid of first and last
     * which not only will help us find the root value in array(a[mid]), but the range of left(0, mid-1)
     * and right(mid+1, length-1).
     *
     * We can apply the similar approach to this problem. We use a field preIndex to increase index one
     * by one in preorder array, to achieve the first thing: finding the root value; we use this value to
     * identify the range of left and right in inorder array by searching that value from index first to last.
     * We can then set the left and right subtrees based on that index in inorder array.
     */
    int preIndex;
    public TreeNode buildTree(int[] preorder, int[] inorder)
    {
        return buildTree(preorder, inorder, 0, preorder.length-1);
    }
    private TreeNode buildTree(int[] preorder, int[] inorder, int first, int last)
    {
        if(first>last)
            return null;
        int newData=preorder[preIndex];
        TreeNode newNode=new TreeNode(newData);
        preIndex++;
        if(first==last)
            return newNode;
        int index=search(inorder, newData, first, last);
        if(index<0)
            throw new IllegalArgumentException("index is supposed to be greater than 0");
        newNode.left=buildTree(preorder, inorder, first, index-1);
        newNode.right=buildTree(preorder, inorder, index+1, last);
        return newNode;
    }

    private int search(int[] inorder, int k, int first, int last)
    {
        for(int i=first; i<=last; i++)
        {
            if(k==inorder[i])
                return i;
        }
        return -1;
    }
}

class ConstructBSTFromInAndPost
{
    /*
     * Problem: Given inorder and postorder traversal of a tree, construct the binary tree.
     * Note: You may assume that duplicates do not exist in the tree.
     *
     * Anaylysis: almost the same approach as the previous problem. Only diff is that we search from
     * the back to front due the property of postorder (left, right, root)
     */
    int poIndex;
    public TreeNode buildTree(int[] inorder, int[] postorder)
    {
        poIndex=postorder.length-1;
        return buildTree(inorder, postorder, 0, poIndex);
    }

    private TreeNode buildTree(int[] inorder, int[] postorder, int first, int last)
    {
        if(first>last)
            return null;
        int value=postorder[poIndex];
        TreeNode root=new TreeNode(value);
        poIndex--;
        if(first==last)
            return root;
        int ioIndex=search(inorder, value, first, last);
        if(ioIndex<0)
            throw new IllegalArgumentException("index is supposed to be greater than 0");
        //note the sequence here, we need to set root.right first to correspond the decreased poIndex
        root.right=buildTree(inorder, postorder, ioIndex+1, last);
        root.left=buildTree(inorder, postorder, first, ioIndex-1);
        return root;
    }
    private int search(int[] array, int value, int first, int last)
    {
        for(int i=first; i<=last; i++)
        {
            if(value==array[i])
                return i;
        }
        return -1;
    }
}

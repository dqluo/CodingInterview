package treeAndGraph.binaryTreeTraversal;

import util.BinaryNode;
import util.IntWrapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * User: xinyuwan, Date: 11/24/13, Time: 5:02 PM
 */
public class LevelOrder
{
    // most of the following tasks can be done by both level order and DFS
    public static void main(String[] args)
    {
        /*
         * Binary Tree a
         *        7
         *     3     12
         *   9  17 21  18
         * 1              8
         *                  11
         */
        BinaryNode<Integer> j=new BinaryNode<Integer>(11);
        BinaryNode<Integer> i=new BinaryNode<Integer>(8, null, j);
        BinaryNode<Integer> h=new BinaryNode<Integer>(18, null, i);
        BinaryNode<Integer> g=new BinaryNode<Integer>(21);
        BinaryNode<Integer> e=new BinaryNode<Integer>(1);
        BinaryNode<Integer> d=new BinaryNode<Integer>(17);
        BinaryNode<Integer> c=new BinaryNode<Integer>(9, e, null);
        BinaryNode<Integer> f=new BinaryNode<Integer>(12, g, h);
        BinaryNode<Integer> b=new BinaryNode<Integer>(3, c, d);
        BinaryNode<Integer> a=new BinaryNode<Integer>(7, b, f);

        System.out.println("Max Vale: "+FindMaxValue.max(a)+" "
                +FindMaxValue.max2(a, Integer.MIN_VALUE)+" "+FindMaxValue.max3(a));

        System.out.println("Sum of All: "+SumOfAll.sum(a)+" "+SumOfAll.sum2(a));

        System.out.println("Number of Leaves: "+NumberOfLeaves.count(a)+" "+NumberOfLeaves.count2(a));

        System.out.println("Contains 8?: "+SearchInBinaryTree.search(a, 8)+" "
                +SearchInBinaryTree.search2(a, 8));
        System.out.println("Contains 19?: "+SearchInBinaryTree.search(a, 19)+" "
                +SearchInBinaryTree.search2(a, 19));

        System.out.println("Deepest node: "+FindDeepestNode.deepest(a).data);

        System.out.println("Height: "+GetHeight.height(a));

        System.out.print("Reverse Levelorder: ");
        ReverseLevelOrder.reverse(a);

        System.out.println("Zigzag Traverse: ");
        ZigZagTraversal.traverse(a);

    }
}

class FindMaxValue
{
    /*
     * Problem1: find the node with the max value in Binary Tree
     */

    //postorder traversal, get the max of left and right, then compare with root
    public static int max(BinaryNode<Integer> root)
    {
        if(root==null)
            return Integer.MIN_VALUE;
        int maxOfChildren=Math.max(max(root.left), max(root.right));
        return Math.max(root.data, maxOfChildren);
    }

    /*
     * preorder traversal, because this one is top down, we need an initial value max
     * Note: we don't necessarily have to use IntWrapper here, because by using preorder
     * the max is updated before recurse to left and right, and most importantly, we'll compare
     * leftmax and rightmax afterwards, and return the bigger to ensure no max is overlooked.
     */
    public static int max2(BinaryNode<Integer> root, int max)
    {
        if(root==null)
            return max;
        if(root.data>max)
            max=root.data;
        int maxLeft=max2(root.left, max);
        int maxRight=max2(root.right, max);
        return Math.max(maxLeft, maxRight);
    }

    public static int max3(BinaryNode<Integer> root)
    {
        if(root==null)
            return Integer.MIN_VALUE;
        int max=Integer.MIN_VALUE;
        Queue<BinaryNode> q=new LinkedList<BinaryNode>();
        q.add(root);
        while(!q.isEmpty())
        {
            root=q.remove();
            if(root.data>max)
                max=root.data;
            if(root.hasLeft())
                q.add(root.left);
            if(root.hasRight())
                q.add(root.right);
        }
        return max;
    }
}

class SumOfAll
{
    /*
     * Problem2: Get the sum of all nodes in Binary Tree
     */
    public static int sum(BinaryNode<Integer> root)
    {
        if(root==null)
            return 0;
        return root.data+sum(root.left)+sum(root.right);
    }
    public static int sum2(BinaryNode<Integer> root)
    {
        if(root==null)
            return 0;
        int sum=0;
        Queue<BinaryNode> q=new LinkedList<BinaryNode>();
        q.add(root);
        while(!q.isEmpty())
        {
            root=q.remove();
            sum+=root.data;
            if(root.hasLeft())
                q.add(root.left);
            if(root.hasRight())
                q.add(root.right);
        }
        return sum;
    }
}

class NumberOfLeaves
{
 /*
  * Problem3: get the number of leaves in Binary Tree
  * Note: the same methods applies for number of full node(two children),
  * and half node(only one children), we could make modifications to the if
  * statement in both traversal methods:
  * Full node: if(root.hasLeft() && root.hasRight())
  * Half node: if(!root.isLeaf() && (!root.hasLeft() || !root.hasRight()))
  */
    public static int count(BinaryNode root)
    {
        if(root==null)
            return 0;
        if(root.isLeaf())
            return 1;
        return count(root.left)+count(root.right);
    }

    public static int count2(BinaryNode root)
    {
        if(root==null)
            return 0;
        int count=0;
        Queue<BinaryNode> q=new LinkedList<BinaryNode>();
        q.add(root);
        while(!q.isEmpty())
        {
            root=q.remove();
            if(root.isLeaf())
                count++;
            if(root.hasLeft())
                q.add(root.left);
            if(root.hasRight())
                q.add(root.right);
        }
        return count;
    }
}

class SearchInBinaryTree
{
    /*
     * Problem4: search element in Binary Tree
     */
    public static<T extends Comparable<T>> boolean search(BinaryNode root, T entry)
    {
        if(root==null)
            return false;
        if(root.data.equals(entry))
            return true;
        return search(root.left, entry)|| search(root.right, entry);
    }

    public static<T extends Comparable<T>> boolean search2(BinaryNode root, T entry)
    {
        if(root==null)
            return false;
        Queue<BinaryNode> q=new LinkedList<BinaryNode>();
        q.add(root);
        while(!q.isEmpty())
        {
            root=q.remove();
            if(root.data.equals(entry))
                return true;
            if(root.hasLeft())
                q.add(root.left);
            if(root.hasRight())
                q.add(root.right);
        }
        return false;
    }

}

class FindLevelWithMaxSum
{
    /*
    * Problem5: find the level with the max sum
    */
    public static int max(BinaryNode<Integer> root)
    {
        if(root==null)
            return 0;
        int maxSum=0, sum=0;
        Queue<BinaryNode> q=new LinkedList<BinaryNode>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty())
        {
            root=q.remove();
            if(root==null)
            {
                if(sum>maxSum)
                    maxSum=sum;
                if(!q.isEmpty())
                    q.add(null);
                sum=0;
                continue;
            }
            sum+=root.data;
            if(root.hasLeft())
                q.add(root.left);
            if(root.hasRight())
                q.add(root.right);
        }
        return maxSum;
    }

    private static int max(BinaryNode<Integer> root, int level, int maxSum, ArrayList<Integer> sumList)
    {
        if(root==null)
            return maxSum;
        if(sumList.size()==level)
            sumList.add(level, root.data);
        else
            sumList.set(level, sumList.get(level)+root.data);
        int sum=sumList.get(level);
        if(sum>maxSum)
            maxSum=sum;
        int leftSum=max(root.left, level+1, maxSum, sumList);
        int rightSum=max(root.right, level+1, maxSum, sumList);
        return maxSum;
    }
}

class FindDeepestNode
{
    /*
     * Problem6: Find the deepest node in tree
     */
    public static BinaryNode deepest(BinaryNode root)
    {
        if(root==null)
            return null;
        Queue<BinaryNode> q=new LinkedList<BinaryNode>();
        q.add(root);
        while(!q.isEmpty())
        {
            root=q.remove();
            if(root.hasLeft())
                q.add(root.left);
            if(root.hasRight())
                q.add(root.right);
        }
        return root;
    }
}

class GetHeight
{
    /*
     * Problem7: get the height of BinaryNode
     * Note: By enqueueing null value, we'll mark that we've touched all
     * current level nodes, and the queue(if not empty) holds all the next level nodes,
     * therefore, we could enqueue null again to mark the end of the next level
     */
    public static int height(BinaryNode root)
    {
        if(root==null)
            return 0;
        int height=1;
        Queue<BinaryNode> q=new LinkedList<BinaryNode>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty())
        {
            root=q.remove();
            if(root==null)
            {
                if(!q.isEmpty())
                {
                    q.add(null);
                    height++;
                }
                continue;
            }
            if(root.hasLeft())
                q.add(root.left);
            if(root.hasRight())
                q.add(root.right);
        }
        return height;
    }
}

class ReverseLevelOrder
{
    /*
     * Problem8: reversely level order the tree, namely start from the last node in last level
     */
    public static void reverse(BinaryNode root)
    {
        if(root==null)
            return;
        Queue<BinaryNode> q=new LinkedList<BinaryNode>();
        Stack<BinaryNode> s=new Stack<BinaryNode>();
        q.add(root);
        while(!q.isEmpty())
        {
            root=q.remove();
            s.push(root);
            if(root.hasLeft())
                q.add(root.left);
            if(root.hasRight())
                q.add(root.right);
        }
        while(!s.isEmpty())
        {
            System.out.print(s.pop().data+" ");
        }
        System.out.println();
    }
}

class ZigZagTraversal
{
    /*
    * Problem9: traverse the tree in level order, and in a sequence like
    * level1: left->right, level2: right->left, level3: left->right
    */
    public static void traverse(BinaryNode root)
    {
        if(root==null)
            return;
        Stack<BinaryNode> currentLevel=new Stack<BinaryNode>();
        Stack<BinaryNode> nextLevel=new Stack<BinaryNode>();
        boolean goLeft=true;
        currentLevel.push(root);
        while(!currentLevel.isEmpty())
        {
            root=currentLevel.pop();
            System.out.print(root.data+" ");
            if(goLeft)
            {
                if(root.hasLeft())
                    nextLevel.push(root.left);
                if(root.hasRight())
                    nextLevel.push(root.right);
            }
            else
            {
                if(root.hasRight())
                    nextLevel.push(root.right);
                if(root.hasLeft())
                    nextLevel.push(root.left);
            }
            if(currentLevel.isEmpty())
            {
                Stack temp=currentLevel;
                currentLevel=nextLevel;
                nextLevel=temp;
                goLeft=goLeft? false:true;
                System.out.println();
            }
        }
    }
}

class DeleteNode
{
    /*
     * Problem10: delete node using level order. Find the target node first, then find the parent node of
     * of the first leaf, and replace the target node data with the first leaf data
     */
    public static BinaryNode deleteNode(BinaryNode<Integer> root, int data)
    {
        Queue<BinaryNode> q=new LinkedList<BinaryNode>();
        BinaryNode<Integer> next=null;
        q.add(root);
        while(!q.isEmpty())
        {
            next=q.remove();
            if(next.data==data)
            {
                if(next==root && root.isLeaf())
                    return null;
                BinaryNode<Integer> substituteParent=getParentOfFirstLeave(root);
                if(substituteParent.hasLeft() && substituteParent.left.isLeaf())
                {
                    next.data=substituteParent.left.data;
                    substituteParent.left=null;
                }
                else
                {
                    next.data=substituteParent.right.data;
                    substituteParent.right=null;
                }
                break;
            }
            if(next.hasLeft())
                q.add(next.left);
            if(next.hasRight())
                q.add(next.right);
        }
        return root;
    }
    public static BinaryNode getParentOfFirstLeave(BinaryNode root)
    {
        if(root==null)
            return null;
        Queue<BinaryNode> q=new LinkedList<BinaryNode>();
        q.add(root);
        BinaryNode next=null;
        while(!q.isEmpty())
        {
            next=q.remove();
            if(next.hasLeft())
            {
                if(next.left.isLeaf())
                    break;
                q.add(next.left);
            }
            if(next.hasRight())
            {
                if(next.right.isLeaf())
                    break;
                q.add(next.right);
            }
        }
        return next;
    }
}



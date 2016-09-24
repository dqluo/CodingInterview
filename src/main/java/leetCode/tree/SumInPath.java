package leetCode.tree;

import leetCode.ADT.TreeNode;

import java.util.ArrayList;

import static leetCode.ADT.TreeNode.getHeight;

/**
 * User: xinyuwan, Date: 12/18/13, Time: 12:45 AM
 */
public class SumInPath
{
    /*
     * Total problems: 4
     */
    public static void main(String[] args)
    {
        TreeNode a=new TreeNode(1);
        TreeNode b=new TreeNode(2);
        TreeNode c=new TreeNode(3);
        a.left=b;
        a.right=c;
        TreeNode d=new TreeNode(4);
        c.left=d;
        TreeNode e=new TreeNode(5);
        c.right=e;

        //test PathSum
        PathSum ps=new PathSum();
        System.out.println("Sum 8 exist? "+ps.hasPathSum(a, 8));
        System.out.println("Sum 10 exits? "+ps.hasPathSum(a, 10));

        //test PathSum2
        PathSum2 ps2=new PathSum2();
        TreeNode f=new TreeNode(5);
        TreeNode g=new TreeNode(-4);
        e.left=g;
        b.left=f;
        System.out.println("All paths sum to 8: "+ps2.pathSum(a, 8));

        //test SumRootToLeaveNumbers
        SumRootToLeaveNumbers srl=new SumRootToLeaveNumbers();
        System.out.println("Sum root to leaves numbers: "+srl.sumNumbers(a));

        //test MaxPathSum
        BinaryTreeMaxPathSum btmp=new BinaryTreeMaxPathSum();
        System.out.println("Max path sum: "+btmp.maxPathSum(a));

    }
}
class PathSum
{
    /*
     * Problem: Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
     * For example:
     * Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
     * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
     */
    public boolean hasPathSum(TreeNode root, int sum)
    {
        /*
         * base case: when root==null it can be the null left child a parent that
         * has a non-null right child, so in this case, we cannot return true. We
         * only check the sum==0 when root is leaf
         */
        if(root==null)
            return false;
        sum-=root.val;
        if(root.left == null && root.right ==null)
            return sum==0;
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }
}

class PathSum2
{
    /*
     * Problem: Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
     */
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum)
    {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        Integer[] path=new Integer[getHeight(root)];
        pathSum(root, sum, 0, path, result);
        return result;
    }
    private void pathSum(TreeNode root, int sum, int level, Integer[] path, ArrayList<ArrayList<Integer>> result)
    {
        if(root==null)
            return;
        sum-=root.val;
        path[level]=root.val;
        if(root.left==null && root.right==null && sum==0)
        {
            result.add(cloneToList(path, level));
            return;
        }
        pathSum(root.left, sum, level+1, path, result);
        pathSum(root.right, sum, level+1, path, result);
    }
    private int getHeight(TreeNode root)
    {
        if(root==null)
            return 0;
        return 1+Math.max(getHeight(root.left), getHeight(root.right));
    }
    private ArrayList<Integer> cloneToList(Integer[] array, int end)
    {
        ArrayList<Integer> result=new ArrayList<Integer>();
        for(int i=0; i<=end; i++)
            result.add(array[i]);
        return result;
    }
}

class SumRootToLeaveNumbers
{
    /*
     * Problem: Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
     * An example is the root-to-leaf path 1->2->3 which represents the number 123.
     * Find the total sum of all root-to-leaf numbers.
     * For example,
     *
     *      1
     *    / \
     *  2   3
     * The root-to-leaf path 1->2 represents the number 12.
     * The root-to-leaf path 1->3 represents the number 13.
     * Return the sum = 12 + 13 = 25.
     */
    //Method1 only uses one field variable sum to store cumulative sum. Because we only advance power of 10
    //when we recurse down, and simply add it to sum, we can do this to all nodes in the tree.
    int sum;
    public int sumNumbers(TreeNode root)
    {
        if(root==null)
            return 0;
        sum(root, 0);
        return sum;
    }
    private void sum(TreeNode root, int currentSum)
    {
        currentSum=currentSum*10+root.val;
        if(root.left==null && root.right==null)
        {
            sum+=currentSum;
        }
        else
        {
            if(root.left!=null)
                sum(root.left, currentSum);
            if(root.right!=null)
                sum(root.right, currentSum);
        }
    }

    public int sumNumbers2(TreeNode root)
    {
        int[] num=new int[getHeight(root)];
        sum(root, 0, num);
        return sum;
    }
    private void sum(TreeNode root, int level, int[] num)
    {
        if(root==null)
            return;
        num[level]=root.val;
        if(root.left==null && root.right==null)
            sum+=getNumber(num, level);
        sum(root.left, level+1, num);
        sum(root.right, level+1, num);
    }
    protected int getNumber(int[] a, int n)
    {
        int result=0, power=1;
        for(int i=n; i>=0; i--)
        {
            result+=a[i]*power;
            power*=10;
        }
        return result;
    }
 }

class BinaryTreeMaxPathSum
{
    /*
     * Problem: Given a binary tree, find the maximum path sum.
     * The path may start and end at any node in the tree.
     * For example: Given the below binary tree,
               1
              / \
             2   3
     * Return 6.
     */
    int maxSum;
    public int maxPathSum(TreeNode root)
    {
        maxSum=root==null? 0 : root.val;
        findMaxSum(root);
        return maxSum;
    }
    /*
     * Use postorder to get left max and right max.
     * here, the trick is that we don't need left max or right max if they are negative because if
     * so, we don't need to calculate root.val+left/right, we only need to compare root.val with previous maxSum
     *
     * There are several possible situations here:
     * 1. left and right are negative: then we only need to compare root.val with previous maxSum
     * 2. left or right is negative: we only need compare the positive side + root.val with maxSum
     * 3. left and right are positive: we need to plus them all to compare with maxSum
     *
     * The findMaxSum does two things: keep updating maxSum and return the larger sum of one side
     * The first task is done by comparing maxSum with root.val+left+right, because left and right would be
     * zero if their actual sum<0, so in this way we saved a lot of time to compare meaningless pairs(the 3
     * situations are listed above).

     * The second task is done by passing up root.val+maxof(left, right). if left and right are both less than 0,
     * we only need to return the root.val: this is because no matter what the maxSum happens in the left or right
     * subtree, we have recorded the maxSum value in maxSum. So to continue, we don't need the paths below root.
     * In other words, we don't need to add any more node to the path which begins from current root, because any node
     * we add will decrease the value of the whole path.
     */
    private int findMaxSum(TreeNode root)
    {
        if(root==null)
            return 0;
        int left=Math.max(0, findMaxSum(root.left));
        int right=Math.max(0, findMaxSum(root.right));
        maxSum=Math.max(root.val+left+right, maxSum);
        return root.val+Math.max(left, right);
    }
}
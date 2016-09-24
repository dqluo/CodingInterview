package leetCode.dp;

import leetCode.ADT.TreeNode;

import java.util.ArrayList;

/**
 * User: xinyuwan, Date: 1/11/14, Time: 4:25 PM
 */
public class UniqueBST
{
    /*
     * Total Problems: 2
     * 1 is dfs: UniqueBST2
     */
    public static void main(String[] args)
    {
        //test UniqueBST
        UniqueBST ub=new UniqueBST();
        for(int i=0; i<=10; i++)
        {
            System.out.println("Num of unique BSTs with "+i+" nodes: "+
                    ub.numTrees(i)+", "+ub.numTrees2(i));
        }

        //test UniqueBST2
        UniqueBST2 ub2=new UniqueBST2();
        ArrayList<TreeNode> result=ub2.generateTrees(3);
        for(TreeNode root : result)
        {
            TreeNode.preorder(root);
            System.out.println();
        }
    }
    /*
     * Top down DP
     * Analysis: given n nodes, we could make any node i in [1..n] to be the root, and [1..i] as left
     * subtree, [i+1..n] as right subtree. So the total number of such BST is the sum of all possible
     * BST with root in [1..n]. For a defined root i, the possible BST number is numOfLeft * numOfRight.
     * Therefore, we get numTrees(n)=Sum(numTrees(1..i)*numTrees(i+1..n)) -- 1=< i <=n
     */
    public int numTrees(int n)
    {
         int[] cache=new int[n+1];
         return numTreesDP(n, cache);
    }

    public int numTreesDP(int n, int[] cache)
    {
         if(n==0)
             return 1;
         if(cache[n] > 0)
             return cache[n];
         for(int i=1; i<=n; i++)
         {
             cache[n]+=numTreesDP(i-1, cache) * numTreesDP(n-i, cache);
         }
         return cache[n];
    }
    /*
     * Bottom up DP
     * Analysis: same as above: cache[i]=sum(cache[1..j]*cache[j+1..n])
     */
    public int numTrees2(int n)
    {
        if(n <= 0)
            return 1;
        int[] cache=new int[n+1];
        cache[0]=1;
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=i; j++)
                cache[i]+=cache[j-1] * cache[i-j];
        }
        return cache[n];
    }
}

class UniqueBST2
{
    /*
     * Problem: Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
     *
     * Analysis: DFS
     * The logic of this problem is the same as problem1. The difference here is that for a given node as root,
     * instead of calculating the leftSubtreeNum * rightSubtreeNum, we need to get the actual left and right subtree
     * nodes. And for each node in left list, we need to pair it with each node in right, and set them as the left and right
     * subtree of the root.
     */
    public ArrayList<TreeNode> generateTrees(int n)
    {
        return generateTrees(1, n);
    }
    private ArrayList<TreeNode> generateTrees(int first, int last)
    {
        ArrayList<TreeNode> result=new ArrayList<TreeNode>();
        if(first > last)
            result.add(null);
        else if(first == last)
            result.add(new TreeNode(first));
        else
        {
            //again, i controls loop span; for recursion, first and last are used together to shrink the
            //number of nodes by half, and after getting the right and left list, we process the combination of them
            for(int i=first; i<=last ; i++)
            {
                ArrayList<TreeNode> leftNodeList=generateTrees(first, i-1);
                ArrayList<TreeNode> rightNodeList=generateTrees(i+1, last);
                for(TreeNode left : leftNodeList)
                {
                    for(TreeNode right : rightNodeList)
                    {
                        TreeNode root=new TreeNode(i);
                        root.left=left;
                        root.right=right;
                        result.add(root);
                    }
                }
            }

        }
        return result;
    }
}

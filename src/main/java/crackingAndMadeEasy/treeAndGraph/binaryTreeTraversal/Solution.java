package crackingAndMadeEasy.treeAndGraph.binaryTreeTraversal;


import util.ArrayListUtil;
import util.ArrayUtil;
import util.BinaryNode;

import java.util.*;

/**
 * User: xinyuwan, Date: 12/8/13, Time: 3:09 PM
 */
public class Solution
{
    int maxSum;
    public int maxPathSum(BinaryNode<Integer> root)
    {
        maxSum=root==null? 0 : root.data;
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
     * The findMaxSum does two things: keep updating maxSum and return the larger sum of on side
     * The first task is done by comparing maxSum with root.val+left+right, because left and right would be
     * zero if their actual sum<0, so in this way we saved a lot of time to compare meaningless pairs(the 3
     * situations are listed above).

     * The second task is done by passing up root.val+maxof(left, right). if left and right are both less than 0,
     * we only need to return the root.val: this is because no matter what the maxSum happens in the left or right
     * subtree, we have recorded the maxSum value in maxSum. So to continue, we don't need the paths below root.
     * In other words, we don't need to add any more node to the path which begins from current root, because any node
     * we add will decrease the value of the whole path.
     */
    private int findMaxSum(BinaryNode<Integer> root)
    {
        if(root==null)
            return 0;
        int left=Math.max(0, findMaxSum(root.left));
        int right=Math.max(0, findMaxSum(root.right));
        maxSum=Math.max(root.data+left+right, maxSum);
        return root.data+Math.max(left, right);
    }

    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num)
    {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        if(num==null)
            return null;
        Arrays.sort(num);
        result.add(new ArrayList<Integer>());
        /*
         * The only difference between this problem and subset1 is that we only
         * extend a subset when the current element is not dup of previous:
         * e.g. [[], [1]]
         * the current element is 2, 2!=1, so we can extend both [] and [1];
         *[[], [1], [2], [1,2]]
         * now, the current element is still 2, and 2==2, because we have extended [][1] using
         * 2 in last iteration, we don't have to do that in this iteration, we only start from
         * the first new subset after extending the previous subset, which corresponds to index=previous size;
         * We use start to keep the value of the index where we should begin extend subset in the current iteration,
         * every time if we finish adding all the new extended subsets into result, we need to check if the next
         * element is equal to current, if so we update start to size, otherwise set start to 0
         */
        int start=0;
        for(int i=0; i<num.length; i++)
        {
            int current=num[i];
            int size=result.size();
            /*
             * Note here, we cannot use j<result.size(), otherwise will run out of memory.
             * This is becuase we add a newsubset to result everytime, which will update the
             * result.size(), and the loop will therefore never ends
             */
            for(int j=start; j<size; j++)
            {
                ArrayList<Integer> newSubset=new ArrayList<Integer>();
                newSubset.addAll(result.get(j));
                newSubset.add(current);
                result.add(newSubset);
            }
            if(i<num.length-1 && num[i]==num[i+1])
                start=size;
            else
                start=0;
        }
        return result;
    }
    public static void main(String[] args)
    {
        int[] num={1, 2, 2};
        ArrayList<ArrayList<Integer>> list=new Solution().subsetsWithDup(num);
        for(ArrayList<Integer> l : list)
        {
            System.out.println(l);
        }
    }

}

class Solution2
{
    private Integer[] columns;

    /*
     * Method 1: we construct the solutions while we recurse down
     */
    private ArrayList<String[]> solutions;

    public ArrayList<String[]> solveNQueens(int n)
    {
        columns=new Integer[n];
        solutions=new ArrayList<String[]>();
        String[] solution=new String[n];
        placeQueens1(0, n, solution);
        return solutions;
    }

    private void placeQueens1(int row, int n, String[] solution)
    {
        if(row==n)
        {
            solutions.add(solution.clone());
            return;
        }
        for(int col=0; col<n; col++)
        {
            if(checkValid(row, col))
            {
                columns[row]=col;
                String rowString=rowToString(col, n);
                solution[row]=rowString;
                placeQueens1(row+1, n, solution);
            }
        }
    }
    private String rowToString(int col, int n)
    {
        StringBuffer sb=new StringBuffer();
        for(int j=0; j<n; j++)
        {
            if(j==col)
                sb.append("Q");
            else
                sb.append(".");
        }
        return sb.toString();
    }


    /*
     * Method2: we convert the results to final solutions list in one method
     */
    private ArrayList<Integer[]> results;

    public ArrayList<String[]> solveNQueens2(int n)
    {
        columns=new Integer[n];
        results=new ArrayList<Integer[]>();
        placeQueens(0, n);
        return convertToResult(results, n);

    }
    private void placeQueens(int row, int n)
    {
        if(row==n)
        {
            results.add(columns.clone());
            return;
        }
        for(int col=0; col<n; col++)
        {
            if(checkValid(row, col))
            {
                columns[row]=col;
                placeQueens(row+1, n);
            }
        }
    }

    private boolean checkValid(int row1, int col1)
    {
        for(int row2=0; row2<row1; row2++)
        {
            int col2=columns[row2];
            //check vertically
            if(col2==col1)
                return false;
            //check diagonally
            int colDiff=Math.abs(col1-col2);
            int rowDiff=row1-row2;
            if(colDiff==rowDiff)
                return false;
        }
        return true;
    }

    private ArrayList<String[]> convertToResult(ArrayList<Integer[]> list, int n)
    {
        ArrayList<String[]> result=new ArrayList<String[]>();
        for(Integer[] cols : list)
        {
            String[] sol=new String[n];
            for(int i=0; i<n; i++)
            {
                StringBuffer sb=new StringBuffer();
                for(int j=0; j<n; j++)
                {
                    if(j==cols[i])
                        sb.append("Q");
                    else
                        sb.append(".");
                }
                sol[i]=sb.toString();
            }
            result.add(sol);
        }
        return result;
    }
}

class Solution3
{
    public static void main(String[] args)
    {
        Solution3 s=new Solution3();
        System.out.println("There are total "+s.totalNQueens(12)+" solutions");
        for(Integer[] result : s.results)
            ArrayUtil.print(result);
    }

    private Integer[] columns;
    private ArrayList<Integer[]> results;

    public int totalNQueens(int n)
    {
        columns=new Integer[n];
        results=new ArrayList<Integer[]>();
        return placeQueens(0, n);
    }

    public int placeQueens(int row, int n)
    {
        if(row==n)
        {
            results.add(columns.clone());
            return 1;
        }
        int count=0;
        for(int col=0; col<n; col++)
        {
            if(checkValid(row, col))
            {
                columns[row]=col;
                count+=placeQueens(row+1, n);
            }
        }
        return count;
    }

    private boolean checkValid(int row, int col)
    {
        for(int row2=0; row2 < row; row2++)
        {
            int col2=columns[row2];
            //check vertically
            if(col2 == col)
                return false;
            //check diagonally
            int colDiff=Math.abs(col-col2);
            int rowDiff=row-row2;
            if(colDiff==rowDiff)
                return false;
        }
        return true;
    }

}

class Solution4
{
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
        d.right=e;
        ArrayList<ArrayList<Integer>> list=new Solution4().levelOrder(a);
        ArrayListUtil.print(list);
    }
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root)
    {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        Queue<TreeNode> q=new LinkedList<TreeNode>();
        ArrayList<Integer> list=new ArrayList<Integer>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty())
        {
            root=q.remove();
            if(root==null)
            {
                result.add(list);
                list=new ArrayList<Integer>();
                if(!q.isEmpty())
                    q.add(null);
                continue;
            }
            list.add(root.val);
            if(root.left!=null)
                q.add(root.left);
            if(root.right!=null)
                q.add(root.right);
        }
        return result;
    }
}

class Solution5
{
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root)
    {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        /*
         * We must check root==null or not, otherwise will throw OutOfMemoryException,
         * This is because later on, we will enqueue both root(null) and null, and in the
         * loop, when we find root==null && q is empty, we will continuously add null into q,
         * and run into an infinite loop. Therefore, always check root==null or not if using
         * the "enqueue null" method in level order.
         */
        if(root==null)
            return result;
        Queue<TreeNode> q=new LinkedList<TreeNode>();
        ArrayList<Integer> list=new ArrayList<Integer>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty())
        {
            root=q.remove();
            if(root==null)
            {
                result.add(list);
                list=new ArrayList<Integer>();
                if(!q.isEmpty())
                    q.add(null);
                continue;
            }
            list.add(root.val);
            if(root.left!=null)
                q.add(root.left);
            if(root.right!=null)
                q.add(root.right);
        }
        Collections.reverse(result);
        return result;
    }
}
class Solution6
{
    TreeLinkNode lastRight=null;

    public void connect(TreeLinkNode root)
    {
        if(root==null || root.left==null || root.right==null)
            return;
        TreeLinkNode current=root, rightSib=null;
        while(current!=null)
        {
            if(current.next!=null)
                rightSib=current.next.left;
            else
                rightSib=null;
            current.left.next=current.right;
            current.right.next=rightSib;
            current=current.next;
        }
        connect(root.left);
    }

    /*
     * The basic logic of this problem is essentially the same. However,
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
     * There is one final point we need to be very very very careful here: we need to recurce down to right
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
    public void connect2(TreeLinkNode root)
    {
        if(root==null)
            return;
        if(root.left!=null)
            root.left.next=root.right==null? getNextRightSib(root):root.right;
        if(root.right!=null)
            root.right.next=getNextRightSib(root);
        connect2(root.right);
        connect2(root.left);
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

    public static void main(String[] args)
    {
        TreeLinkNode a=new TreeLinkNode(1);
        TreeLinkNode b=new TreeLinkNode(2);
        TreeLinkNode c=new TreeLinkNode(3);
        a.left=b;
        a.right=c;
        TreeLinkNode d=new TreeLinkNode(4);
        TreeLinkNode e=new TreeLinkNode(5);
        b.left=d;
        b.right=e;
        TreeLinkNode f=new TreeLinkNode(6);
        TreeLinkNode g=new TreeLinkNode(7);
        c.left=f;
        c.right=g;
        TreeLinkNode h=new TreeLinkNode(8);
        TreeLinkNode i=new TreeLinkNode(9);
        d.left=h;
        d.right=i;
        TreeLinkNode j=new TreeLinkNode(10);
        e.right=j;
        TreeLinkNode k=new TreeLinkNode(11);
        TreeLinkNode l=new TreeLinkNode(12);
        g.left=k;
        g.right=l;


        new Solution6().connect2(a);
        TreeLinkNode cursor=h;
        while(cursor!=null)
        {
            System.out.print(cursor.val+" ");
            cursor=cursor.next;

        }
    }
}

class TreeLinkNode {
     int val;
     TreeLinkNode left, right, next;
     TreeLinkNode(int x) { val = x; }
}

class TreeNode
{
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x;}
 }


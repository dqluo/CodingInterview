package leetCode.gimmick;

/**
 * User: xinyuwan, Date: 12/15/13, Time: 4:00 PM
 */
public class MaxSubArray
{
    /*
     * Problem: Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
     * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
     * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
     */
    public static void main(String[] args)
    {
        int[] a={31,-41,59,26,-53,58,97,-93,-23,84};
        MaxSubArray msa=new MaxSubArray();
        System.out.println("Max sum of sub array: "+msa.maxSubArray(a));
        System.out.println("Max sum of sub array: "+msa.maxSubArray2(a));
    }

    /*
     * Method1: Divide and Conquer
     */
    public int maxSubArray(int[] A)
    {
        if(A.length==0)
            return 0;
        return maxSubArray(A, 0, A.length-1);
    }
    private int maxSubArray(int[] A, int first, int last)
    {
        if(first>last)
            return Integer.MIN_VALUE;
        if(first==last)
            return A[first];
        int mid=(first+last)/2;
        int leftMax=maxSubArray(A, first, mid-1);
        int rightMax=maxSubArray(A, mid+1, last);
        /*
         * we need to find the sequence accross left and right, which means:
         * 1. we at least need A[mid]
         * 2. we need to extend both left and right from mid, and try to see how far we could
         * reach in both directions. The extreme case is we move 0 steps left/right, and that's
         * the reason we should set leftCrossMax/rightCrossMax to zero at the beginning
         */
        int leftCrossMax=0, rightCrossMax=0, sum=0;
        for(int i=mid-1; i>=first; i--)
        {
            sum+=A[i];
            leftCrossMax=Math.max(leftCrossMax, sum);
        }
        sum=0;
        for(int i=mid+1; i<=last; i++)
        {
            sum+=A[i];
            rightCrossMax=Math.max(rightCrossMax, sum);
        }
        return Math.max(leftCrossMax+rightCrossMax+A[mid],
                Math.max(leftMax, rightMax));
    }

    /*
     * Method2: O(n)
     * Analysis: notice the fact that A could be denoted as sequence of positive and negative interspersed
     * [9, -5, -10, 4, 2, -1], think of each as a sum of individual sequence. When do we add a sequence in
     * our current sum? Only when sum+current>0. This is because,
     * 1. if sum is negative, adding a positive (not big enough to offset the negative value) or a negative
     * will not contribute to the sum. To seek for potential larger sum, we need to discard previous sum, and restart from the next.
     * 2. if sum is positive, adding a positive will sure to increase the sum; Or, if a negative has less abs value than
     * the current sum, so that it acts as a connector between sum and future potential larger element.
     *
     * As a result, only add current when sum+current>=0, reset sum to 0 otherwise
     */
    public int maxSubArray2(int[] A)
    {
         int sum=0, maxSum=Integer.MIN_VALUE;
         for(int i=0; i<A.length; i++)
         {
             sum+=A[i];
             maxSum=Math.max(maxSum, sum);
             if(sum < 0)
                 sum=0;
         }
         return maxSum;
    }
}

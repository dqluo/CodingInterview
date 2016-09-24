package leetCode;

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
        System.out.println(new MaxSubArray().maxSubArray(a));
    }
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
}

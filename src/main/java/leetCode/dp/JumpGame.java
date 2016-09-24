package leetCode.dp;

/**
 * User: xinyuwan, Date: 12/26/13, Time: 12:49 PM
 */
public class JumpGame
{
    /*
     * Total problems: 2
     */
    public static void main(String[] args)
    {
        int[] arr={2, 3, 1, 0, 1, 4, 0, 5, 1, 1, 2, 1};
        int[] arr2={2, 3, 1, 1, 0, 1};
        //test jumpGame
        JumpGame jg=new JumpGame();
        System.out.println("For arr, can jump? "+jg.canJump(arr)+", "+jg.canJump2(arr));
        System.out.println("For arr2, can jump? "+jg.canJump(arr2)+", "+jg.canJump2(arr2));

        //test jumpGame2
        JumpGame2 jg2=new JumpGame2();
        System.out.println("For arr, min jump steps: "+jg2.jump(arr)+", "+jg2.jump2(arr));
        System.out.println("For arr2, min jump steps: "+jg2.jump(arr2)+", "+jg2.jump2(arr2));

    }
    /*
     * Problem: Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Determine if you are able to reach the last index.
     */

    /**
     * Gimmick approach: We iterate from the end, using index as the indicator of the position we want to jump,
     * and i is the position we want to jump from. Since if we could jump from the last to the
     * beginning, we will definitely do it reversely, so as long as index is 0, we will know that
     * the path is through. For each step, we compare A[i] with the difference between i and index
     * determining if we can reach that far, if not we will move i backward to see the next element
     *
     */
    public boolean canJump(int[] A)
    {
        int index=A.length-1;
        for(int i=A.length-2; i>=0; i--)
        {
            if(A[i] >= index-i)
                index=i;
        }
        return index==0;
    }
    /*
     * DP approach, we start from the beginning, check every position if we could jump from 0 to i.
     * To do this, we need to check elements from 0 to i-1 to see whether we could reach from j to i.
     * If we can find one such position, we mark cache[i] to be true and move forward. The worst case
     * of this approach is O(n2)
     */
    public boolean canJump2(int[] A)
    {
        boolean[] cache=new boolean[A.length];
        cache[0]=true;
        for(int i=1; i<A.length; i++)
        {
            for(int j=0; j<i; j++)
            {
                if(A[j]>=i-j && cache[j])
                {
                    cache[i]=true;
                    break;
                }
            }
        }
        return cache[A.length-1];
    }
}

class JumpGame2
{

/* Problem: Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * For example: Given array A = [2,3,1,1,4]
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 */

/* Greedy algorithm: for each step, we move to the max position we can reach,
 * and update the pointer i to that position(j). In the next iteration, we know that
 * we are at the position where from 0 to i, we used min steps. If we find that we can step
 * to end from i, we are done, otherwise keep looking.
 */
    public int jump(int[] A)
    {
        int n=A.length;
        if(n<=1)
            return 0;
        int i=0, steps=0, maxPosToReach=0;
        while(i < n)
        {
            //check the case of failure to jump
            if(A[i]==0)
                return -1;
            maxPosToReach=A[i]+i;
            steps++;
            if(maxPosToReach >= n-1)
                break;
            int temp=0;
            for(int j=i+1; j<=maxPosToReach; j++)
            {
                if(A[j]+j > temp)
                {
                    temp=A[j]+j;
                    i=j;
                }
            }
        }
        return steps;
    }
    /*
     * DP approach: same logic as problem1.
     * cache[i] is the min steps from A[0] to A[i]
     * Hence, cache[i]=min(cache[0]...cache[i-1])+1, if A[j] is reachable && A[j]+j>=i (0=< j< i)
     *
     */
    public int jump2(int[] A)
    {
        int n=A.length;
        if(n<=1)
            return 0;
        int[] cache=new int[n];
        for(int i=1; i<n; i++)
            cache[i]=-1;
        for(int i=1; i<n; i++)
        {
            int min=n;
            for(int j=0; j<i; j++)
            {
                if(A[j] >= i-j && cache[j]>=0)
                    min=Math.min(min, cache[j]+1);
            }
            //check the case of failure to jump
            if(min==n)
                return -1;
            cache[i]=min;
        }
        return cache[n-1];
    }
}


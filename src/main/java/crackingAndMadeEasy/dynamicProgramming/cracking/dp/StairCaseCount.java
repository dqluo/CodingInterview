package crackingAndMadeEasy.dynamicProgramming.cracking.dp;

import java.util.Hashtable;

/**
 * User: xinyuwan, Date: 12/4/13, Time: 7:23 PM
 */
public class StairCaseCount
{
    public static void main(String[] args)
    {
        for(int i=0; i<11; i++)
            System.out.println(countWaysDP(i, new int[i+1])+
                    " "+countWaysDP(i , new Hashtable<Integer, Integer>()));
    }
    public static int countWays(int n)
    {
        if(n<0)
            return 0;
        if(n==0)
            return 1;
        return countWays(n-1)+countWays(n-2)+countWays(n-3);
    }
    public static int countWaysDP(int n, int[] memo)
    {
        if(n<0)
            return 0;
        if(n==0)
            return 1;
        if(memo[n]!=0)
            return memo[n];
        memo[n]=countWaysDP(n-1, memo)+countWaysDP(n-2, memo)+countWaysDP(n-3, memo);
        return memo[n];
    }
    public static int countWaysDP(int n, Hashtable<Integer, Integer> memo)
    {
        if(n<0)
            return 0;
        if(n==0)
            return 1;
        if(memo.containsKey(n))
            return memo.get(n);
        int result=countWaysDP(n-1, memo)+countWaysDP(n-2, memo)+countWaysDP(n-3, memo);
        memo.put(n, result);
        return result;
    }
}



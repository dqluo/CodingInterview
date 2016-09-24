package leetCode.dp;

import java.util.HashMap;

/**
 * User: xinyuwan, Date: 1/10/14, Time: 3:20 PM
 */
public class ClimbingStairs
{
    public static void main(String[] args)
    {
        ClimbingStairs cs=new ClimbingStairs();
        for(int i=0; i<=10; i++)
        {
            System.out.println("Total ways to climb "+i+" stairs: "+cs.climbStairs(i));
        }
    }
    /*
     * Problem: You are climbing a stair case. It takes n steps to reach to the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     *
     * The logic is exactly same as Fibonacci
     */
    HashMap<Integer, Integer> cache=new HashMap<Integer, Integer>();
    public int climbStairs(int n)
    {
        if(n < 0)
            return 0;
        if(n == 0)
            return 1;
        if(cache.containsKey(n))
            return cache.get(n);
        int result=climbStairs(n-1)+climbStairs(n-2);
        cache.put(n, result);
        return result;
    }
}

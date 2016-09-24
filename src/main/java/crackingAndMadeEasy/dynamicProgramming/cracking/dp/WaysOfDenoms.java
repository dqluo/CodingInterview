package crackingAndMadeEasy.dynamicProgramming.cracking.dp;
import util.Pair;

import java.util.HashMap;

/**
 * User: xinyuwan, Date: 12/5/13, Time: 9:00 PM
 */
public class WaysOfDenoms
{
    /*
    * Problem: given an infinite number of quarters(25 cents), dimes(10 cents), nickels(5 cents)
    * and pennies(1 cent), write code to calculate the number of ways of representing n cents
    *
    * Approach: makeChange(100)=
    *  makeChange(100 using 0 quarters)+
    *  makeChange(75 using 0 quarters)+
    *  makeChange(50 using 0 quarters)+
    *  makeChange(25 using 0 quarters)+
    *  1
    *  =>
    *  makeChange(100 using 0 quarters)=
    *   makeChange(100 using 0 quarters, 0 dimes)+
    *   makeChange(100 using 0 quarters, 1 dimes)+
    *   makeChange(100 using 0 quarters, 2 dimes)+
    *   ...
    *   makeChange(100 using 0 quarters, 10 dimes)
    * makeChange(75 using 0 quarters)= ...
    * makeChange(50 using 0 quarters)= ...
    * makeChange(25 using 0 quarters)= ...
    * Each one of these expands out once we start applying nickels.
    * We end up with tree-like recursive structure where each call expands out to four or more calls.
    * The base case of recursion is the fully reduced statement. For example, makeChange(50 using 0 quarters, 5 dimes)
    * is fully reduced to 1, since 5 dimes equals 50 cents
    *
    */
    public static void main(String[] args)
    {
        System.out.println(makeChange(100, 25));
        System.out.println(makeChange2(12, 0));
        System.out.println(makeChange2DP(12, 0));
    }

    public static int makeChange(int n, int denom)
    {
        int nextDenom=0;
        switch(denom)
        {
            case 25:
                nextDenom=10;
                break;
            case 10:
                nextDenom=5;
                break;
            case 5:
                nextDenom=1;
                break;
            case 1:
                return 1;
        }
        int ways=0;
        for(int i=0; i*denom<=n; i++)
        {
            ways+=makeChange(n-i*denom, nextDenom);
        }
        return ways;
    }
    //assumes denoms is sorted from largest to smallest
    public static int makeChange2(int n, int index)
    {
        if(index >= denoms.length-1)
             return 1;
        int denom=denoms[index];
        int ways=0;
        for(int i=0; i*denom<=n; i++)
        {
            ways+=makeChange2(n - i * denom, index + 1);
        }
        return ways;
    }
    private static HashMap<Pair, Integer> cache=new HashMap<Pair, Integer>();
    private static int[] denoms={6, 3, 2, 1};
//    private static int[] denoms={25, 10, 5, 1};

 /*
  * When n is big enough, we could dicover overlaps between recursive calls
  *                                     12, 0
  *                              /
  *
  *                   12,1                   6,1          0,1
  *                /        \               /   \
  *        12,2             9,2         6,2 ... 0,2
  *     /       \         /    \       /    \
  * 12,3  ...6,3..0,3   9,3 ... 0,3 6,3 4,3..0,3
  *
  * As is shown above, (6, 3), (6, 2)... are repetitive. So we use DP to cache the results
  *
  *
  */
    public static int makeChange2DP(int n, int index)
    {
        if(index >= denoms.length-1)
            return 1;
        Pair p=new Pair(n, index);
        if(cache.containsKey(p))
            return cache.get(p);
        int denom=denoms[index];
        int ways=0;
        for(int i=0; i*denom<=n; i++)
        {
            ways+=makeChange2DP(n - i * denom, index + 1);
        }
        cache.put(p, ways);
        return ways;
    }
}

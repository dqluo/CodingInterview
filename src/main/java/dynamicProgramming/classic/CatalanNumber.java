package dynamicProgramming.classic;

/**
 * User: xinyuwan, Date: 12/10/13, Time: 4:16 PM
 */
public class CatalanNumber
{
    /*
     * Problem: how many binary search trees are there with n vertices
     * Cn=C0*Cn-1+..+Ci-1*Cn-i+..+Cn-1*C0
     */
    public static void main(String[] args)
    {
        for(int i=0; i<=6; i++)
            System.out.println(i+": "+numTrees(i)+" "+numTrees2(i));
    }
    public static int numTrees(int n)
    {
        if(n==0)
            return 1;
        int count=0;
        for(int i=1; i<=n; i++)
        {
            count+=numTrees(i-1) * numTrees(n-i);
        }
        return count;
    }
    public static int numTrees2(int n)
    {
        int[] cache=new int[n+1];
        return numTreesDP(n, cache);
    }
    public static int numTreesDP(int n, int[] cache)
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
}

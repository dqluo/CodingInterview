package leetCode.dp;

/**
 * User: xinyuwan, Date: 12/27/13, Time: 3:56 PM
 */
public class DistinctSubsequence
{
    public static void main(String[] args)
    {
        String s="rabbbit", t="rabbit";
        DistinctSubsequence ds=new DistinctSubsequence();
        System.out.println("Number of distinct subsequences: "+ds.numDistinct(s, t));
        System.out.println("Number of distinct subsequences: "+ds.numDistinct2(s, t));
    }

    /*
     * Problem: Given a string S and a string T, count the number of distinct subsequences of T in S.
     * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
     * Here is an example:
     * S = "rabbbit", T = "rabbit"
     * Return 3.

     * Analysis:
     * define cache[i][j] as the number of distinct subsequences of T[0, i] in S[0, j], we will have:
     * 1. if the length of T is 0, then we return 1, otherwise when the length of S is 0, we return 0
     * 2. if the jth character of S and the ith character of T are not the same, cache[i][j]=cache[i][j-1],
     * this means that we cannot obtain T[i] from S[j]
     * 3. if the jth character of S and the ith character of T are the same, cache[i][j]=cache[i][j-1]+cache[i-1][j-1],
     * this means that we can either choose to obtain T[i] from S[j], in this way, T[0, i-1] comes from S[0, j-1];
     * or we choose to ignore this S[j] which means T[i] comes from other char in S[0, j-1]. Together these two conditions
     * will make the total number of distinct subsequence when T[i]==S[j]
     */
    public int numDistinct(String S, String T)
    {
         int m=T.length(), n=S.length();
         if(m==0)
             return 1;
         if(n < m)
             return 0;
         int[][] cache=new int[m+1][n+1];
         for(int i=0; i<n+1; i++)
             cache[0][i]=1;
         //Note do not overwrite cache[0][0] here
         for(int i=1; i<m+1; i++)
             cache[i][0]=0;
         for(int i=1; i<m+1; i++)
         {
             for(int j=1;j<n+1; j++)
             {
                 if(S.charAt(j-1)==T.charAt(i-1))
                     cache[i][j]=cache[i][j-1]+cache[i-1][j-1];
                 else
                     cache[i][j]=cache[i][j-1];
             }
         }
         return cache[m][n];
    }

    public int numDistinct2(String S, String T)
    {
        int m=T.length(), n=S.length();
        if(m==0)
            return 1;
        if(n < m)
            return 0;

        int[] cache=new int[n+1];
        for(int i=0; i<n+1; i++)
            cache[i]=1;
        for(int i=1; i<m+1; i++)
        {
            int leftTop=cache[0];
            cache[0]=0;
            for(int j=1; j<n+1; j++)
            {
                int current=cache[j-1];
                if(S.charAt(j-1)==T.charAt(i-1))
                    current+=leftTop;
                leftTop=cache[j];
                cache[j]=current;
            }
        }
        return cache[n];
    }
}

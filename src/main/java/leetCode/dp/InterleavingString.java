package leetCode.dp;

/**
 * User: xinyuwan, Date: 1/1/14, Time: 1:47 AM
 */
public class InterleavingString
{
    public static void main(String[] args)
    {
        String s1="aabcc", s2="dbbca", s3="aadbbcbcac", s4="aadbbbaccc";
        InterleavingString ils=new InterleavingString();
        System.out.println("Can s1 and s2 interleave s3? "+ils.isInterleave2(s1, s2, s3));
        System.out.println("Can s1 and s2 interleave s4? "+ils.isInterleave2(s1, s2, s4));
    }
    /*
     * Problem: Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
     * For example, Given: s1 = "aabcc", s2 = "dbbca",
     * When s3 = "aadbbcbcac", return true.
     * When s3 = "aadbbbaccc", return false.
     *
     * Analysis:
     * define cache[i][j] as: for the chars in S3[0, i+j], i chars come from S1[0, i], and j chars come from s2[0, j]
     * therefore, we will have:
     * 1. for s3[0, i+j], we can interleave it from s1[0, i] and s2[0, j] when s3[0, i+j-1] could be interleaved
     * with s1[0, i-1] and s2[0, j], plus s1[i]==s3[i+j-1]; OR
     * 2. we can interleave it from s1[0, i] and s2[0, j] when s3[0, i+j-1] could be interleaved
     * with s1[0, i] and s2[0, j-1], plus s2[j]==s3[i+j-1]
     */
    public boolean isInterleave(String s1, String s2, String s3)
    {
        int m=s1.length(), n=s2.length();
        if(m+n != s3.length())
            return false;
        boolean[][] cache=new boolean[m+1][n+1];
        //we can interleave two empty strings to be an empty string
        cache[0][0]=true;
        for(int i=1; i<=m; i++)
            cache[i][0]=cache[i-1][0] && s1.charAt(i-1)==s3.charAt(i-1);
        for(int j=1; j<=n; j++)
            cache[0][j]=cache[0][j-1] && s2.charAt(j-1)==s3.charAt(j-1);
        for(int i=1; i<=m; i++)
        {
            for(int j=1; j<=n; j++)
            {
                cache[i][j]=(cache[i-1][j] && s1.charAt(i-1)==s3.charAt(i+j-1))
                        || (cache[i][j-1] && s2.charAt(j-1)==s3.charAt(i+j-1));
            }
        }
        return cache[m][n];
    }
    //1-D array cache
    public boolean isInterleave2(String s1, String s2, String s3)
    {
        int m=s1.length(), n=s2.length();
        if(m+n != s3.length())
            return false;
        boolean[] cache=new boolean[n+1];
        //we can interleave two empty strings to be an empty string
        cache[0]=true;
        for(int j=1; j<=n; j++)
            cache[j]=cache[j-1] && s2.charAt(j-1)==s3.charAt(j-1);
        for(int i=1; i<=m; i++)
        {
            cache[0]=cache[0] && s1.charAt(i-1)==s3.charAt(i-1);
            for(int j=1; j<=n; j++)
            {
                cache[j]=(cache[j] && s1.charAt(i-1)==s3.charAt(i+j-1))
                        || (cache[j-1] && s2.charAt(j-1)==s3.charAt(i+j-1));
            }
        }
        return cache[n];
    }
}

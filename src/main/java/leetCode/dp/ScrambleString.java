package leetCode.dp;

import crackingAndMadeEasy.dynamicProgramming.cracking.recursionOnly.AllPermutationsOfString;

/**
 * User: xinyuwan, Date: 1/5/14, Time: 1:19 AM
 */
public class ScrambleString
{
    public static void main(String[] args)
    {
        String s1="great";
        ScrambleString ss=new ScrambleString();
        System.out.println("Printing the perms that are not scrambles: ");
        for(String perm : AllPermutationsOfString.getPerms(s1))
        {
            boolean isScramble=ss.isScramble2(s1, perm);
            if(!isScramble)
                System.out.println(perm);
        }
    }
    /*
     * Problem: Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
     * Below is one possible representation of s1 = "great":
             great
            /    \
          gr    eat
         / \    /  \
        g   r  e   at
                  / \
                 a   t
    * To scramble the string, we may choose any non-leaf node and swap its two children.
    * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
            rgeat
           /    \
         rg     eat
        / \    /  \
       r   g  e   at
                 / \
                a   t
   * We say that "rgeat" is a scrambled string of "great".
   * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
         rgtae
        /    \
      rg    tae
     / \   /  \
   r   g  ta  e
         / \
       t   a
  * We say that "rgtae" is a scrambled string of "great".
  * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
  */
    /*
     * DFS
     * Analysis: We need to split the two strings from index 1 to string.length()-1, and check the following 2 conditions:
     * 1. if s1[0..i-1] isScrambleOf s2[0..i-1] && s1[i..len-1] isScrambleOf s2[i, len-1]  return true;
     * 2. if s1[0..i-1] isScrambleOf s2[len-i, len-1] && s1[i, len-1] isScrambleOf s2[0, len-i-1] return true;
     */
    public boolean isScramble(String s1, String s2)
    {
        if(s1.length()!= s2.length())
            return false;
        if(s1.equals(s2))
            return true;
        //before we go further recursion, we check the permutation condition first
        if(s1.length()==1 || !isPermutation(s1, s2))
            return false;
        for(int i=1; i<s1.length(); i++)
        {
            String leftOfS1=s1.substring(0, i);
            String rightOfS1=s1.substring(i);
            String leftOfS2=s2.substring(0, i);
            String rightOfS2=s2.substring(i);
            if(isScramble(leftOfS1, leftOfS2) && isScramble(rightOfS1, rightOfS2))
                return true;
            leftOfS2=s2.substring(0, s2.length()-i);
            rightOfS2=s2.substring(s2.length()-i);
            if(isScramble(leftOfS1, rightOfS2) && isScramble(rightOfS1, leftOfS2))
                return true;
        }
        return false;
    }
    private boolean isPermutation(String s1, String s2)
    {
        if(s1.isEmpty() && s2.isEmpty())
            return true;
        if(s1.length() != s2.length())
            return false;
        int[] cache=new int[256];
        for(int i=0; i<s1.length(); i++)
        {
            cache[s1.charAt(i)]+=1;
        }
        for(int i=0; i<s2.length(); i++)
        {
            if(cache[s2.charAt(i)]==0)
                return false;
            cache[s2.charAt(i)]--;
        }
        return true;
    }

    /*
     * 3-D DP
     * Analysis:
     * In the recursion solution, we split the strings and compare recursively. It ends up with comparing every possible pair of equal-length substrings in the two strings. So, suppose the length of the two strings is n, the subproblems are
     * For each pair of (n-1)-char-long substrings of the two strings, are they scramble to each other?
     * For each pair of (n-2)-char-long substrings, are they scramble?
     * ... ...
     * For each pair of 2-char-long substrings, are they scramble?
     * For each pair of char in the two strings, are they scramble (i.e. do they equal)?
     * That is saying, we can build up a table and solve the problem in a bottom-up fashion.
     * Then to verify whether two k-char-long strings are scramble, we still split the two strings at k-1 position. For each pair of split, we don't need to take O(n) time to recursively compare the two of them. Instead, simply take O(1) time to look at the table.
     * This gives us a O(n^4)-time solution which uses O(n^4) spaces.
     *
     * Define cache[i][j][k] as whether s1[i..i+k] and s2[j..j+k] are scrambled string. Note that i and j
     * marks the start of the two string, and although k is used as length, k is ranged from 0 to length-1.
     * Therefore the questions becomes to calculate cache[0][0][s1.length-1]
     */
    public boolean isScramble2(String s1, String s2)
    {
        if(s1.length()!=s2.length())
            return false;
        if(s1.equals(s2))
            return true;
        int len=s1.length();
        boolean[][][] cache=new boolean[len][len][len];
        //calculate the the base case for length=1
        for(int i=0; i<len; i++)
        {
            for(int j=0; j<len; j++)
                cache[i][j][0]=s1.charAt(i)==s2.charAt(j);
        }
        //k is used to iterate len, k equals actualLength-1, start from k=1
        for(int k=1; k<len; k++)
        {
            //max of i or j can only be len-(k+1)
            for(int i=0; i<len-k; i++)
            {
                for(int j=0; j<len-k; j++)
                {
                    //split into [0..p] and [p+1..k]
                    for(int p=0; p<k; p++)
                    {
                        //to calculate cache[i][j][k] we need to check similar conditions as in dfs approach
                        cache[i][j][k]=(cache[i][j][p] && cache[i+p+1][j+p+1][k-p-1])
                                || (cache[i][j+k-p][p] && cache[i+p+1][j][k-p-1]);
                        if(cache[i][j][k])
                            break;
                    }
                }
            }
        }
        return cache[0][0][len-1];
    }


}

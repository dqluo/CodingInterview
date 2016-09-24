package leetCode.dp;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * User: xinyuwan, Date: 12/26/13, Time: 4:33 PM
 */
public class StringPalindrome
{
    /*
     * Total problems: 3
     * (1 has dfs method: palindromePartitioning)
     */
    public static void main(String[] args)
    {
        String s="abaabxxbaa";
        //test LongestPalindrome
        LongestPalindromeSubstring lps=new LongestPalindromeSubstring();
        System.out.println("Longest palindrome substring: "+lps.longestPalindrome(s));
        //test PartitionPalindrome
        PalindromePartitioning pp=new PalindromePartitioning();
        System.out.print("All possible partitioned palindrome substring: ");
        System.out.println(pp.partition(s));

        System.out.print("All possible partitioned palindrome substring2: ");
        System.out.println(pp.partition2(s));

        //test PartitionPalindrome2
        PalindromePartitioning2 pp2=new PalindromePartitioning2();
        System.out.println("Min cut for palindrome: "+pp2.minCut2(s));
    }
}

class LongestPalindromeSubstring
{
    /*
     * Problem: Given a string S, find the longest palindromic substring in S.
     * You may assume that the maximum length of S is 1000, and there exists one
     * unique longest palindromic substring.
     *
     * Use boolean cache otherwise will exceeds time limit
     */
    public String longestPalindrome(String s)
    {
        int length=s.length();
        if(length<=1)
            return s;
        boolean[][] cache=new boolean[length][length];
        int start=0, end=0;
        for(int i=0; i<length; i++)
            cache[i][i]=true;
        for(int i=0; i<length-1; i++)
        {
            if(s.charAt(i)==s.charAt(i+1))
            {
                cache[i][i+1]=true;
                start=i;
                end=i+1;
            }
        }
        for(int len=3; len<=length; len++)
        {
            for(int i=0; i<=length-len; i++)
            {
                int j=i+len-1;
                if(s.charAt(i)==s.charAt(j) && cache[i+1][j-1])
                {
                    cache[i][j]=true;
                    start=i;
                    end=j;
                }
            }
        }
        return s.substring(start, end+1);
    }
}
class PalindromePartitioning
{
    /*
     * Problem: Given a string s, partition s such that every substring of the partition is a palindrome.
     * Return all possible palindrome partitioning of s.
     * For example, given s = "aab",
     * Return
     * [
     * ["aa","b"],
     * ["a","a","b"]
     * ]
     *
     * DFS Solution
     */
    public ArrayList<ArrayList<String>> partition(String s)
    {
        ArrayList<ArrayList<String>> result=new ArrayList<ArrayList<String>>();
        ArrayList<String> solution=new ArrayList<String>();
        partition(s, solution, result);
        return result;
    }

    private void partition(String s, ArrayList<String> solution, ArrayList<ArrayList<String>> result)
    {
        if(s.isEmpty())
        {
            result.add((ArrayList<String>)solution.clone());
            return;
        }
        //i controls loop span, i is the end+1 of the substring; we substring the original to control recursion
        for(int i=1; i<=s.length(); i++)
        {
            String current=s.substring(0, i);
            //Note that because after cutting, every substring needs to be palindrome, we only recurse down
            //when current substring is palindrome
            if(isPalindrome(current))
            {
                solution.add(current);
                partition(s.substring(i), solution, result);
                solution.remove(solution.size()-1);
            }
        }
    }

    private boolean isPalindrome(String s)
    {
        if(s==null || s.isEmpty())
            return true;
        for(int i=0, j=s.length()-1; i<j; i++, j--)
        {
            if(s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }
    /*
     * Method2: dp+backtracking: similar as wordbreak2
     */
    public ArrayList<ArrayList<String>> partition2(String s)
    {
        ArrayList<ArrayList<String>> result=new ArrayList<ArrayList<String>>();
        if(s==null || s.isEmpty())
            return result;
        int n=s.length();
        //construct the boolean cache as the longest palindrome substring
        boolean[][] cache=new boolean[n][n];
        for(int i=0; i<n; i++)
            cache[i][i]=true;
        for(int i=0; i<n-1; i++)
            cache[i][i+1]=s.charAt(i)==s.charAt(i+1);
        for(int k=3; k<=n; k++)
        {
            for(int i=0; i<=n-k; i++)
            {
                int j=i+k-1;
                cache[i][j]=cache[i+1][j-1] && s.charAt(i)==s.charAt(j);
            }
        }
        //next construct the solutions map for backtracking
        ArrayList<ArrayList<Integer>> solutions=new ArrayList<ArrayList<Integer>>();
        boolean[] cutCache=new boolean[n];
        for(int i=0; i<n; i++)
        {
            ArrayList<Integer> solution=new ArrayList<Integer>();
            if(cache[0][i])
            {
                cutCache[i]=true;
                solution.add(i);
            }
            for(int j=0; j<i; j++)
            {
                if(cutCache[j] && cache[j+1][i])
                {
                    cutCache[i]=true;
                    solution.add(j);
                }
            }
            solutions.add(solution);
        }
        //now backtrack and add each ArrayList<String> to result
        backTrack(s, solutions, s.length()-1, new LinkedList<String>(), result);
        return result;

    }
    private void backTrack(String s, ArrayList<ArrayList<Integer>> solutions, int end, LinkedList<String> pals, ArrayList<ArrayList<String>> result)
    {
        if(solutions.isEmpty())
            return;
        ArrayList<Integer> current=solutions.get(end);
        for(int i=0; i<current.size(); i++)
        {
            int start=current.get(i);
            if(start==end)
            {
                pals.add(0, s.substring(0, start + 1));
                result.add(toArrayList(pals));
            }
            else
            {
                pals.add(0, s.substring(start + 1, end + 1));
                backTrack(s, solutions, start, pals, result);
            }
            pals.removeFirst();
        }
    }
    private ArrayList<String> toArrayList(LinkedList<String> list)
    {
        ArrayList<String> result=new ArrayList<String>();
        for(String s : list)
            result.add(s);
        return result;
    }
}

class PalindromePartitioning2
{
    /*
     * Problem: Given a string s, partition s such that every substring of the partition is a palindrome.
     * Return the minimum cuts needed for a palindrome partitioning of s.
     * For example, given s = "aab",
     * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
     *
     * Analysis: double DP, the first part has the same logic as Longest Substring Palindrome, the second part
     * use 1-D cache to calculate min cut of (0, i) based on the result of the first part.
     * In the second part, we use cutCache as a 1-D cache such that for index i, cutCache[i] means the min cut
     * from 0 to i(f(i)):
     * f(0)=0;
     * f[1) = 0 -- if str[0, 1] is palindrome
     *      = f(0)+1 -- otherwise
     * f(2) = 0 -- if str[0, 2] is palindrome
     *      = f(0)+1 -- if str[1, 2] is palindrome
     *      = f(1)+1 -- otherwise
     * f(3) = 0 -- if str[0, 3] is palindrome
     *      = f(0)+1 -- if str[1, 3] is palindrome
     *      = f(1)+1 -- if str[2, 3] is palindrome
     *      = f(2)+1 -- otherwise
     * By segregating the two DP process, we can get O(n2) time and space complexity
     */
    public int minCut2(String s)
    {
        if(s==null || s.isEmpty())
            return 0;
        int n=s.length();
        boolean[][] palCache=new boolean[n][n];
        int[] cutCache=new int[n];
        //we process the palCache first
        for(int i=0; i<n; i++)
            palCache[i][i]=true;
        for(int i=0; i<n-1; i++)
        {
            if(s.charAt(i)==s.charAt(i+1))
                palCache[i][i+1]=true;
        }
        for(int k=3; k<=n; k++)
        {
            for(int i=0; i<=n-k; i++)
            {
                int j=i+k-1;
                if(s.charAt(i)==s.charAt(j) && palCache[i+1][j-1])
                    palCache[i][j]=true;
            }
        }

        //now we process cutCache
        for(int i=0; i<n; i++)
        {
            if(palCache[0][i])
                cutCache[i]=0;
            else
            {
                int minCut=n;
                for(int j=0; j<i; j++)
                {
                    if(palCache[j+1][i] && cutCache[j]+1 < minCut)
                        minCut=cutCache[j]+1;
                }
                cutCache[i]=minCut;
            }
        }
        return cutCache[n-1];
    }
}

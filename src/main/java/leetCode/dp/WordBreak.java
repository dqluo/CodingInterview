package leetCode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * User: xinyuwan, Date: 12/28/13, Time: 6:41 PM
 */
public class WordBreak
{
    /*
     * Total problems: 2
     */
    public static void main(String[] args)
    {
        String s="catsanddog";
        Set<String> dict=new HashSet<String>();
        String[] arr={"cat", "cats", "and", "sand", "dog", "a", "aa", "aaaa"};
        dict.addAll(Arrays.asList(arr));
        //test WordBreak
        WordBreak wb=new WordBreak();
        System.out.println("Is \"catsanddog\" breakable? "+wb.wordBreak(s, dict));
        //test WordBreak2
        WordBreak2 wb2=new WordBreak2();
        System.out.println("All possible sentences: "+wb2.wordBreak(s, dict));
    }

    /*
     * Problem: Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
     * For example, given s = "leetcode", dict = ["leet", "code"].
     * Return true because "leetcode" can be segmented as "leet code".
     */
    //Method1--DFS Solution
    public boolean wordBreak(String s, Set<String> dict)
    {
        if(s==null || s.isEmpty())
            return true;
        if(dict == null || dict.isEmpty())
            return false;
        for(int i=1; i<=s.length(); i++)
        {
            if(dict.contains(s.substring(0, i)) && wordBreak(s.substring(i), dict))
                return true;
        }
        return false;
    }
    /*
     * Method2
     * Analysis: logic is similar to partitionPalindrome.
     * Define cache[i] as whether s[0, i] could be word break, therefore
     * cache[i]=true -- s[0, i] is in dict
     *         =true -- any of s[0, k] and s[k+1, i] is in dict
     *         =false -- otherwise
     * we could build the cache as we traverse through the string, and the time complexity is O(n2)*search complexity
     */
    public boolean wordBreak2(String s, Set<String> dict)
    {
        if(s==null || s.isEmpty())
            return true;
        if(dict == null || dict.isEmpty())
            return false;
        boolean[] cache=new boolean[s.length()];
        for(int i=0; i<s.length(); i++)
        {
            if(dict.contains(s.substring(0, i+1)))
                cache[i]=true;
            else
            {
                for(int k=0; k<i; k++)
                {
                    cache[i]=cache[k] && dict.contains(s.substring(k+1, i+1));
                    if(cache[i])
                        break;
                }
            }
        }
        return cache[s.length()-1];
    }
}

class WordBreak2
{
    /*
     * Problem: Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
     * Return all such possible sentences.
     * For example, given s = "catsanddog", dict = ["cat", "cats", "and", "sand", "dog"].
     * A solution is ["cats and dog", "cat sand dog"].
     *
     *
     * First, note we don't need a search method here, a search method will only drag the performance of the algorithm since
     * we can only get a O(lengthOfDict) complexity. However, by using dict's own contains() method, we can get O(1) complexity,
     * since dict is probably constructed by HashSet behind the scene.
     *
     * Second, the basic logic of the first part of this problem is the same as the last one, the only two differences are:
     * 1. instead of breaking the inner for loop immediately after cache[i]=true, we need to cover every case that makes s[0, i]
     * breakable. So it's not if--else logic but a if--and logic here
     *
     * 2. we need to construct a 2-D ArrayList to record for s[0, i], we could break at what possible k point in the middle
     * e.g. for s[0, 2]: we could break at (1, 2), which means that (s[0, 1], s[1, 2]) and (s[0, 2]) are two valid breaks for s[0, 2]
     *
     * 3. Third, we need to be very careful about the bactracking part. This is the second part of this problem. We start from the last
     * index and work backward towards the start of the string. We construct each word at each recursion in the for loop. When we hit a
     * the base case that solutions[i][j]=i, we add the string to result. Note that it is possible that for solution[i] there are more
     * than one element, and one of them is the basecase, whereas for others we still need to recurse down(like solution[2]={1, 2}, for
     * 1, we still need to recurse down, but for 2, we have reached one base case here).
     */
    ArrayList<String> result;
    public ArrayList<String> wordBreak(String s, Set<String> dict)
    {
        result=new ArrayList<String>();
        if(s==null || s.isEmpty() || dict==null || dict.isEmpty())
            return result;
        boolean[] cache=new boolean[s.length()];
        ArrayList<ArrayList<Integer>> solutions=new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<s.length(); i++)
        {
            ArrayList<Integer> list=null;
            if(dict.contains(s.substring(0, i+1)))
            {
                cache[i]=true;
                list=new ArrayList<Integer>();
                list.add(i);
            }
            for(int k=0; k<i; k++)
            {
                if(cache[k] && dict.contains(s.substring(k+1, i+1)))
                {
                    cache[i]=true;
                    if(list==null)
                        list=new ArrayList<Integer>();
                    list.add(k);
                }
            }
            solutions.add(list);
        }
        backTrack(s, solutions, s.length()-1, new StringBuffer());
        return result;
    }
    private void backTrack(String s, ArrayList<ArrayList<Integer>> solutions, int end, StringBuffer sb)
    {
        ArrayList<Integer> current=solutions.get(end);
        if(current==null || current.isEmpty())
            return;
        for(int i=0; i<current.size(); i++)
        {
            int begin=current.get(i);
            if(begin==end)
            {
                sb.insert(0, s.substring(0, end+1));
                result.add(sb.toString());
                sb.delete(0, end+1);
                continue;
            }
            sb.insert(0, " "+s.substring(begin+1, end+1));
            backTrack(s, solutions, begin, sb);
            sb.delete(0, 1+end-begin);
        }
    }
}

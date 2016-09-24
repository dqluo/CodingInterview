package crackingAndMadeEasy.dynamicProgramming.cracking.dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * User: xinyuwan, Date: 12/6/13, Time: 8:28 PM
 */
public class LongestWordMadeByOthers
{
    /*
     * Problem: Given a list of words, write a program to find the longest word made of
     * other words in the list
     */
    public static void main(String[] args)
    {
        String[] words={"atest", "test", "a", "tester", "team", "testateam", "testereeee"};
        longestWordByTwo(words);
        longestWordByMany(words);
    }
    /*
     * Simplification: check the longest word that can be formed by two words
     */
    public static String longestWordByTwo(String[] list)
    {
        Arrays.sort(list, new LengthComparator());
        HashMap<String, Boolean> map=new HashMap<String, Boolean>();
        for(String s : list)
             map.put(s, true);
        for(String s : list)
        {
            for(int i=1; i<s.length(); i++)
            {
                String left=s.substring(0, i);
                String right=s.substring(i);
                if(map.containsKey(left) && map.containsKey(right))
                {
                    System.out.println(s + " = " + left + " + " + right);
                    return s;
                }
            }
        }
        return "";
    }

    /*
     * Real Problem: check the longest word that can be formed by multiple words
     */
    private static HashMap<String, Boolean> cache=new HashMap<String, Boolean>();
    public static String longestWordByMany(String[] list)
    {
        Arrays.sort(list, new LengthComparator());
        for(String s : list)
            cache.put(s, true);
        for(String s : list)
        {
            if(canBuild(s, true))
            {
                System.out.println(s);
                return s;
            }
        }
        return "";
    }
    /*
     * This method is the core of the algorithm:
     * 1. it checks whether the String s can be built by the rest of the list
     * 2. by using the boolean value isOriginalWord, we distinguish two situations
     * of calling canBuild(): the first is to check a word(string) can be built by
     * other word in the list; the second is to check whether a word exists in
     * cache or not. Note here, the logic is first check if s is in list(if so, it would
     * have been put into the cache and marked as true); if not, we begin to check whether
     * s could be composed of other words. if both fail, we mark s as false.
     *
     * Therefore the cache has two functions:
     * 1. to store words that exist in list, if we need to check a longer word can be built
     * by a shorter word, we first check whether it's in list or not
     *
     * 2. to store the word and whether it can be built or not. Initially, all words are
     * marked as true. But as the iteration go from longest word to shortest, we will
     * change the value to be false if we confirmed that the word cannot be built.
     *
     *
     */
    private static boolean canBuild(String s, boolean isOriginalWord)
    {
        if(!isOriginalWord && cache.containsKey(s))
            return cache.get(s);
        for(int i=1; i<s.length(); i++)
        {
            String left=s.substring(0, i);
            String right=s.substring(i);
            if(cache.containsKey(left) && cache.get(left)
                && canBuild(right, false))
                return true;
        }
        cache.put(s, false);
        return false;
    }



}
class LengthComparator implements Comparator<String>
{
    public int compare(String s1, String s2)
    {
        if(s1.length() > s2.length())
            return -1;
        else if(s1.length()==s2.length())
            return 0;
        else
            return 1;
    }
}

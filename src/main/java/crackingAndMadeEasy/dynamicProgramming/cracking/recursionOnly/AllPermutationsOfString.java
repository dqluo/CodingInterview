package crackingAndMadeEasy.dynamicProgramming.cracking.recursionOnly;

import java.util.ArrayList;

/**
 * User: xinyuwan, Date: 12/4/13, Time: 6:47 PM
 */
public class AllPermutationsOfString
{
    /*
     * Problem: write a function to compute all permutations of a string
     */
    public static void main(String[] args)
    {
        System.out.println(getPerms("love"));
        System.out.println(getPerms("aabc"));
    }

    public static ArrayList<String> getPerms(String s)
    {
        if(s==null)
            return null;
        ArrayList<String> perms=new ArrayList<String>();
        if(s.length()==0)
        {
            perms.add("");
            return perms;
        }
        char firstChar=s.charAt(0);
        String remainder=s.substring(1);
        /*
         * use a different variable as the receiver of the recursion, to
         * avoid concurrentModificationException
         */
        ArrayList<String> words=getPerms(remainder);
        for(String word: words)
        {
            for(int i=0; i<=word.length(); i++)
            {
                //avoid repeating elements
                if(i>0 && word.charAt(i-1)==firstChar)
                    continue;
                perms.add(insertCharAt(word, firstChar, i));
            }
        }
        return perms;
    }

    private static String insertCharAt(String s, char c, int i)
    {
        String s1=s.substring(0, i);
        String s2=s.substring(i);
        return s1+c+s2;
    }
}

package crackingAndMadeEasy.searchAndSort.moderate;

import util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * User: xinyuwan, Date: 12/2/13, Time: 5:23 PM
 */
public class GroupAnagrams
{
    public static void main(String[] args)
    {
        String[] a={"acre", "dog", "care", "race", "god", "cod", "site", "doc", "ride"};
        sort2(a);
        ArrayUtil.print(a);
    }

    public static void sort(String[] a)
    {
        Arrays.sort(a, new AnagramComparator());
    }

    public static void sort2(String[] a)
    {
        HashMap<String, ArrayList<String>> map=new HashMap<String, ArrayList<String>>();
        for(String s : a)
        {
            String sortedS=sortChars(s);
            ArrayList<String> list=null;
            if(!map.containsKey(sortedS))
            {
                list=new ArrayList<String>();
                list.add(s);
                map.put(sortedS, list);
            }
            else
            {
                list=map.get(sortedS);
                list.add(s);
            }
        }
        int index=0;
        for(String key : map.keySet())
        {
            ArrayList<String> anagrams=map.get(key);
            for(String s : anagrams)
            {
                a[index]=s;
                index++;
            }
        }
    }

    /*
     * Auxiliary method to help sort a string in order for later
     * comparison, since after sorting, anagrams should have the
     * same order
     */
    public static String sortChars(String s)
    {
        if(s==null || s.isEmpty())
            return s;
        char[] charArray=s.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}

class AnagramComparator implements Comparator<String>
{
    public int compare(String s1, String s2)
    {
        return GroupAnagrams.sortChars(s1)
                .compareTo(GroupAnagrams.sortChars(s2));
    }
}
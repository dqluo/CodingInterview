package leetCode.stringAndArray;

/**
 * User: xinyuwan, Date: 1/1/14, Time: 4:05 PM
 */
public class ImplementStrStr
{
    /*
     * Problem: Implement strStr().
     * Returns a pointer to the first occurrence of needle in haystack,
     * or null if needle is not part of haystack.
     */

    /*
     * Analysis: For each char in haystack[0, haystack.length-needle.length], we check that if it can be
     * the first char of needle, if so go ahead from i to check the rest of needle; otherwise we go on
     * to check i+1 as the first char of needle.
     */
    public String strStr(String haystack, String needle)
    {
        if(needle.length()==0)
            return haystack;
        for(int i=0;i<haystack.length()-needle.length()+1;i++)
        {
            int j=0;
            for(;j<needle.length();j++)
            {
                if(haystack.charAt(i+j)!=needle.charAt(j))
                    break;
            }
            if(j==needle.length())
            {
                return haystack.substring(i);
            }
        }
        return null;
    }
}

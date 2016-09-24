package leetCode.dfs;

/**
 * User: xinyuwan, Date: 1/19/14, Time: 6:58 PM
 */
public class StringMatching
{
    public static void main(String[] args)
    {
        String s1="aaabcad", p1="a*b*d", p2="a?ab?*f";
        WildCardMatching wcm=new WildCardMatching();
//        System.out.println("Wildcard matching by p1? "+wcm.isMatch(s1, p1));
        System.out.println("Wildcard matching by p2? "+wcm.isMatch(s1, p2));
    }
}

class WildCardMatching
{
    /*
     * Problem: Implement wildcard pattern matching with support for '?' and '*'.
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     * The matching should cover the entire input string (not partial).
     * The function prototype should be:
     * bool isMatch(const char *s, const char *p)
     * Some examples:
     * isMatch("aa","a") → false
     * isMatch("aa","aa") → true
     * isMatch("aaa","aa") → false
     * isMatch("aa", "*") → true
     * isMatch("aa", "a*") → true
     * isMatch("ab", "?*") → true
     * isMatch("aab", "c*a*b") → false
     */
    public boolean isMatch(String s, String p)
    {
        int sStart=0, pStart=0;
        while(sStart<s.length() && pStart<p.length())
        {
            if(p.charAt(pStart)=='?')
            {
                sStart++;
                pStart++;
            }
            else if(p.charAt(pStart)!='*')
            {
                if(p.charAt(pStart)!=s.charAt(sStart))
                    return false;
                else
                {
                    pStart++;
                    sStart++;
                }
            }
            else
            {
                //The tricky part comes from '*', we will substitute each * with ?, and try to recursively
                //call isMatch, until the newly built string has equal length of s
                StringBuilder sbuilder=new StringBuilder();
                String remainingS=s.substring(sStart);
                while(sbuilder.length()<remainingS.length())
                {
                    String newString=sbuilder.toString()+p.substring(pStart+1);
                    if(isMatch(remainingS, newString))
                        return true;
                    else
                        sbuilder.append("?");
                }
                return false;
            }
        }
        //pStart==p.length, sStart<s.length, not match
        if(sStart<s.length())
            return false;
        //sStart==s.length && pStart==p.length, match
        if(pStart==p.length())
            return true;
        //sStart==s.length && pStart<p.length, unless all chars left in p are '*', otherwise not match
        while(pStart<p.length())
        {
            if(p.charAt(pStart)!='*')
                return false;
            pStart++;
        }
        return true;
    }
}

class RegularExpressionMatching
{
    /*
     * Problem: '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     * The matching should cover the entire input string (not partial).
     * The function prototype should be:
     * bool isMatch(const char *s, const char *p)
     * Some examples:
     * isMatch("aa","a") → false
     * isMatch("aa","aa") → true
     * isMatch("aaa","aa") → false
     * isMatch("aa", "a*") → true
     * isMatch("aa", ".*") → true
     * isMatch("ab", ".*") → true
     * isMatch("aab", "c*a*b") → true
     */
    public boolean isMatch(String s, String p)
    {
        if(p.length()==0)
            return s.length()==0;
        if(p.length()==1 || p.charAt(1)!='*')
        {
            if(s.length()<1 || (p.charAt(0)!='.' && p.charAt(0)!=s.charAt(0)))
                return false;
            return isMatch(s.substring(1), p.substring(1));
        }
        else
        {
            int i=-1;
            while(i<s.length() && (i<0 || p.charAt(0)=='.' || p.charAt(0)==s.charAt(i)))
            {
                if(isMatch(s.substring(i+1), p.substring(2)))
                    return true;
                i++;
            }
            return false;
        }
    }
}

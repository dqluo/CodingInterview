package leetCode.stringAndArray;

/**
 * User: xinyuwan, Date: 12/20/13, Time: 3:38 PM
 */
public class LengthOfLastWord
{
    public static void main(String[] args)
    {
        String s1="hello World  ", s2="       ";
        LengthOfLastWord llw=new LengthOfLastWord();
        System.out.println("Length of last word for s1: "+llw.lengthOfLastWord(s1));
        System.out.println("Length of last word for s2: "+llw.lengthOfLastWord(s2));
    }
    /*
     * Problem: Given a string s consists of upper/lower-case
     * alphabets and empty space characters ' ', return the length of last word in the string.
     * If the last word does not exist, return 0.
     * Note: A word is defined as a character sequence consists of non-space characters only.
     * For example, Given s = "Hello World", return 5.
     */
    public int lengthOfLastWord(String s)
    {
        if(s==null || s.isEmpty())
            return 0;
        int end=s.length()-1;
        while(end>=0 && s.charAt(end)==' ')
            end--;
        //corner case: not valid char in string
        if(end < 0)
            return 0;
        //now i is at the last index of the first non-space word from the last
        int start=end;
        while(start>=0 && s.charAt(start)!=' ')
            start--;
        //now start is at the index before the first index of the non-space word
        return end-start;
    }
}

package leetCode.stringAndArray;

/**
 * User: xinyuwan, Date: 12/24/13, Time: 6:37 PM
 */
public class ValidPalindrome
{
    public static void main(String[] args)
    {
        String s="A man, a plan, a canal: Panama";
        String s2=".,";
        ValidPalindrome vp=new ValidPalindrome();
        System.out.println(vp.isPalindrome(s));
        System.out.println(vp.isPalindrome(s2));
    }
    /*
     * Problem: Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     * For example,
     * "A man, a plan, a canal: Panama" is a palindrome.
     * "race a car" is not a palindrome.
     * Note: Have you consider that the string might be empty? This is a good question to ask during an interview.
     * For the purpose of this problem, we define empty string as valid palindrome.
     */
    public boolean isPalindrome(String s)
    {
        if(s.length()<2)
            return true;
        int i=0, j=s.length()-1;
        while(i < j)
        {
            while(i < j && !Character.isLetterOrDigit(s.charAt(i)))
                i++;
            while(i < j && !Character.isLetterOrDigit(s.charAt(j)))
                j--;
            if(i < j && Character.toLowerCase(s.charAt(i))
                    != Character.toLowerCase(s.charAt(j)))
                return false;
            i++;
            j--;
        }
        return true;
    }
}

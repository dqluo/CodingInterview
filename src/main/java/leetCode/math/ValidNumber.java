package leetCode.math;

/**
 * User: xinyuwan, Date: 1/12/14, Time: 6:21 PM
 */
public class ValidNumber
{
    public static void main(String[] args)
    {
        String[] a={"1 ", "0.1", "0.1e50", ".2", "-5e-90", " 005047e+6",
                "abc", "6+1", "0e", "e1", "2e2e3", "33e.2", "+3-9"};
        ValidNumber vn=new ValidNumber();
        System.out.println("Is valid number? ");
        for(String num : a)
        {
            System.out.print(vn.isNumber(num)+" ");
        }

        System.out.println(vn.isNumber(" 005047e+6"));
    }
    /*
     * Problem: Validate if a given string is numeric.
     * Some examples:
     * "0" => true
     * " 0.1 " => true
     * "abc" => false
     * "1 a" => false
     * "2e10" => true
     * Note: It is intended for the problem statement to be ambiguous.
     * You should gather all requirements up front before implementing one.
     *
     * Analysis: Not too much algorithm, just list all the possible false cases, and check them
     * one by one after trimming the string.
     */

    public boolean isNumber(String s)
    {
        if(s==null)
            return false;
        s=s.trim();
        if(s.isEmpty())
            return false;
        boolean firstNum=false, firstE=false, firstDot=false;
        for(int i=0; i<s.length(); i++)
        {
            char c=s.charAt(i);
            if(Character.isDigit(c))
                firstNum=true;
            else if(c=='+' || c== '-' )
            {
                if(i>0 && s.charAt(i-1)!='e')
                    return false;
            }
            else if(c=='e')
            {
                if(!firstNum || firstE)
                    return false;
                firstE=true;
                //we need to return the result based on firstNum
                //(e.g. 'e' is last char, we return false, since firstNum==false)
                firstNum=false;
            }
            else if(c=='.')
            {
                if(firstDot || firstE)
                    return false;
                firstDot=true;
            }
            else
                return false;
        }
        return firstNum;
    }
}

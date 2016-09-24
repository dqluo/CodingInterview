package leetCode.stringAndArray;

/**
 * User: xinyuwan, Date: 12/21/13, Time: 11:44 PM
 */
public class CountAndSay
{
    public static void main(String[] args)
    {
        CountAndSay cas=new CountAndSay();
        System.out.println("Count and say for level 5: "+cas.countAndSay(5));
    }
    /*
     * Problem: The count-and-say sequence is the sequence of integers beginning as follows:
     * 1, 11, 21, 1211, 111221, ...
     * 1 is read off as "one 1" or 11.
     * 11 is read off as "two 1s" or 21.
     * 21 is read off as "one 2, then one 1" or 1211.
     * Given an integer n, generate the nth sequence.
     * Note: The sequence of integers will be represented as a string.
     */
    public String countAndSay(int n)
    {
        if(n<=0)
            return "";
        String origin="1";
        //we iterate n-1 times
        for(int i=1; i<n; i++)
        {
            //we generate a string for each level
            StringBuffer sb=new StringBuffer();
            int count=1;
            char lastChar=origin.charAt(0);
            for(int j=1; j<origin.length(); j++)
            {
                if(origin.charAt(j)==lastChar)
                    count++;
                else
                {
                    sb.append(count);
                    sb.append(lastChar);
                    count=1;
                    lastChar=origin.charAt(j);
                }
            }
            sb.append(count);
            sb.append(lastChar);
            origin=sb.toString();
        }
        return origin;
    }
}

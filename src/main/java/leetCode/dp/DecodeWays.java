package leetCode.dp;

/**
 * User: xinyuwan, Date: 1/14/14, Time: 12:47 AM
 */
public class DecodeWays
{
    public static void main(String[] args)
    {
        String code="1102657";
        DecodeWays dw=new DecodeWays();
        System.out.println("Number of decode ways for code: "+dw.numDecodings(code));


    }
    /*
     * Problem: A message containing letters from A-Z is being encoded to numbers using
     * the following mapping:
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Given an encoded message containing digits, determine the total number of ways to decode it.
     * For example,
     * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
     * The number of ways decoding "12" is 2.
     */

    /*
     * DP approach
     * Analysis: define cache[i] as the number of decoding ways for s[0, i], we have
     * cache[i]=cache[i]+cache[i-1](if s[i] is valid)+cache[i-2](if s[i-1, i] is valid)
     */
    public int numDecodings(String s)
    {
        if(s==null || s.isEmpty())
            return 0;
        int[] result=new int[s.length()];
        //first initialize result[0];
        if(s.charAt(0)!='0')
            result[0]=1;
        if(s.length()==1)
            return result[0];
        //initialize result[1] for future use
        if(isValid(s.substring(1, 2)))
            result[1]+=result[0];
        if(isValid(s.substring(0, 2)))
            result[1]++;
        for(int i=2;i<s.length();i++)
        {
            if(isValid(s.substring(i, i+1)))
                result[i]+=result[i-1];
            if(isValid(s.substring(i-1,i+1)))
                result[i]+=result[i-2];
        }
        return result[s.length()-1];
    }

    private boolean isValid(String string)
    {
        if(string.length()==0)
            return false;
        //if the first character is '0', we can not decode it as one by one or by two
        if(string.charAt(0)=='0')
            return false;
        if(string.length()==2 && Integer.parseInt(string)>26)
            return false;
        return true;
    }
//    public int numDecodings(String s) {
//         if(s.length()==0)
//             return 0;
//         if(s.length()==1)
//         {
//             if(isValid(s))
//             {
//                 return 1;
//             }
//             else
//                 return 0;
//         }
//         if(s.length()==2)
//         {
//             if(isValid(s))
//             {
//                 return 2;
//             }
//             else if(isValid(s.substring(0,1)) && isValid(s.substring(1)))
//             {
//                 return 1;
//             }
//             else
//                 return 0;
//         }
//         int result=0;
//         if(s.length()>=1 && isValid(s.substring(0,1)))
//             result=result+numDecodings(s.substring(1));
//         if(s.length()>=2 && isValid(s.substring(0,2)))
//             result=result+numDecodings(s.substring(2));
//         return result;
//    }
}

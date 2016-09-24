package leetCode.dfs;

import util.ArrayListUtil;

import java.util.ArrayList;

/**
 * User: xinyuwan, Date: 1/1/14, Time: 9:25 PM
 */
public class RestoreIPAddress
{
    public static void main(String[] args)
    {
        String s="0000";
        String s2="25525511135";
        String s3="99999999999";

        RestoreIPAddress rip=new RestoreIPAddress();
        System.out.print("Result for s: ");
        System.out.println(rip.restoreIpAddresses(s));
        System.out.print("Result for s2: ");
        System.out.println(rip.restoreIpAddresses(s2));
        System.out.print("Result for s3: ");
        System.out.println(rip.restoreIpAddresses(s3));
    }
    /*
     * Problem: Given a string containing only digits, restore it by returning all possible valid IP address combinations.
     * For example: Given "25525511135",
     * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
     *
     * Analysis: use dfs to traverse all the possible valid ip addresses. Because we don't know the length of
     * a specific token(could be [1, 3]), we need a method to check whether we need to recurse down to find the
     * next token. The isValidToken method handles whether we accept the current token as part of the address and
     * whether we should recurse down or not.
     */
    public ArrayList<String> restoreIpAddresses(String s)
    {
        ArrayList<String> result=new ArrayList<String>();
        if(s==null || s.length()==0 || s.length() > 12)
            return result;
        restoreIpAddress(result, s, "", 0);
        return result;
    }
    private void restoreIpAddress(ArrayList<String> result, String s, String buffer, int count)
    {
        //note how we deal with the base case, it's better to check the basecase when
        //there's only one token left, since we don't need to add additional "." to the last token
        if(count==3 && isValidToken(s))
        {
            result.add(buffer+s);
            return;
        }
        //count controls recursion, i controls for loop span by split the string to s[0..i-1] and s[i, length-1]
        for(int i=1; i<=3 && i<=s.length(); i++)
        {
            String token=s.substring(0, i);
            if(isValidToken(token))
                restoreIpAddress(result, s.substring(i), buffer+token+".", count+1);
        }
    }
    private boolean isValidToken(String s)
    {
        //check limit on length
        if(s==null || s.length()==0 || s.length()>3)
            return false;
        //check zero value
        if(s.charAt(0)=='0')
            return s.equals("0");
        //check the range of number value
        int num=Integer.parseInt(s);
        if(num < 0 || num > 255)
            return false;
        return true;
    }
}

package dynamicProgramming.cracking.dp;

import java.util.HashMap;

/**
 * User: xinyuwan, Date: 12/6/13, Time: 10:00 PM
 */
public class WaysOfParenthesizingExp
{
    /*
     * Problem: given a boolean expression consisting of the symbols 0, 1, &, | and ^,
     * and a desired boolean result, implement a function to count the number of ways
     * of parenthesizing the expression such that it evaluates to result
     */
    public static void main(String[] args)
    {
        String exp="1^0|0|1";
        System.out.println(count(exp, true, 0, exp.length()-1));
        System.out.println(countDP(exp, true, 0, exp.length()-1));
    }
    /*
     * f(1^0\0\1, true) = f(1^(0\0\1), true) + f((1^0)\(0\1), true) + f((1^0\0)\1, true)
     * if we want to compute f(exp, result), we could iterate through exp, treating each
     * operator as the first operator to be parenthesized.
     *
     *  for each inner expression like below, the computation is as follow:
     *  f((1^0)\(0\1), true) = f(1^0, true) * f(0|1, true)+
     *  f(1^0, false) * f(0|1, true) + f(1^0, true) * f(1|0, false)
     *
     *  implementing this is now just a matter of applying this recurrence relation.
     *
     */

    public static int count(String exp, boolean result, int start, int end)
    {
        if(start == end)
        {
            char c=exp.charAt(start);
            if((c == '1' && result) || (c == '0' && !result))
                return 1;
            else
                return 0;
        }
        int count=0;
        if(result)
        {
            for(int i=start+1; i<end; i+=2)
            {
                char operator=exp.charAt(i);
                if(operator == '&')
                    count+=count(exp, true, start, i-1)*count(exp, true, i+1, end);
                else if(operator == '|')
                {
                    count+=count(exp, true, start, i-1)*count(exp, true, i+1, end);
                    count+=count(exp, true, start, i-1)*count(exp, false, i+1, end);
                    count+=count(exp, false, start, i-1)*count(exp, true, i+1, end);
                }
                else if(operator == '^')
                {
                    count+=count(exp, false, start, i-1)*count(exp, true, i+1, end);
                    count+=count(exp, true, start, i-1)*count(exp, false, i+1, end);
                }
            }
        }
        else
        {
            for(int i=start+1; i<end; i+=2)
            {
                char operator=exp.charAt(i);
                if(operator == '&')
                {
                    count+=count(exp, false, start, i-1)*count(exp, true, i+1, end);
                    count+=count(exp, false, start, i-1)*count(exp, false, i+1, end);
                    count+=count(exp, true, start, i-1)*count(exp, false, i+1, end);
                }
                else if(operator == '|')
                    count+=count(exp, false, start, i-1)*count(exp, false, i+1, end);
                else if(operator == '^')
                {
                    count+=count(exp, false, start, i-1)*count(exp, false, i+1, end);
                    count+=count(exp, true, start, i-1)*count(exp, true, i+1, end);
                }
            }
        }
        return count;
    }
    private static HashMap<String, Integer> cache=new HashMap<String, Integer>();
    public static int countDP(String exp, boolean result, int start, int end)
    {
        String key=""+result+start+end;
        if(cache.containsKey(key))
            return cache.get(key);
        if(start == end)
        {
            char c=exp.charAt(start);
            if((c == '1' && result) || (c == '0' && !result))
                return 1;
            else
                return 0;
        }
        int count=0;
        if(result)
        {
            for(int i=start+1; i<end; i+=2)
            {
                char operator=exp.charAt(i);
                if(operator == '&')
                    count+=countDP(exp, true, start, i - 1)*countDP(exp, true, i + 1, end);
                else if(operator == '|')
                {
                    count+=countDP(exp, true, start, i - 1)*countDP(exp, true, i + 1, end);
                    count+=countDP(exp, true, start, i - 1)*countDP(exp, false, i + 1, end);
                    count+=countDP(exp, false, start, i-1)*countDP(exp, true, i+1, end);
                }
                else if(operator == '^')
                {
                    count+=countDP(exp, false, start, i - 1)*countDP(exp, true, i + 1, end);
                    count+=countDP(exp, true, start, i - 1)*countDP(exp, false, i + 1, end);
                }
            }
        }
        else
        {
            for(int i=start+1; i<end; i+=2)
            {
                char operator=exp.charAt(i);
                if(operator == '&')
                {
                    count+=countDP(exp, false, start, i - 1)*countDP(exp, true, i + 1, end);
                    count+=countDP(exp, false, start, i - 1)*countDP(exp, false, i + 1, end);
                    count+=countDP(exp, true, start, i - 1)*countDP(exp, false, i + 1, end);
                }
                else if(operator == '|')
                    count+=countDP(exp, false, start, i - 1)*countDP(exp, false, i + 1, end);
                else if(operator == '^')
                {
                    count+=countDP(exp, false, start, i - 1)*countDP(exp, false, i + 1, end);
                    count+=countDP(exp, true, start, i - 1)*countDP(exp, true, i + 1, end);
                }
            }
        }
        cache.put(key, count);
        return count;
    }



}

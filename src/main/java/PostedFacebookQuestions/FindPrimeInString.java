package PostedFacebookQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-16
 * Time: 下午2:05
 * To change this template use File | Settings | File Templates.
 */
public class FindPrimeInString {

    public static ArrayList<Integer> findPrime(String s)
    {
        String lastNum="";
        int index=0;
        HashSet<Integer> set=new HashSet<Integer>();
        ArrayList<Integer> result=new ArrayList<Integer>();
        while(index<s.length())
        {
            if(Character.isDigit(s.charAt(index)))
            {
                lastNum+=s.charAt(index);
                for(int i=0;i<lastNum.length();i++)
                {
                    String current=lastNum.substring(i, lastNum.length());
                    int num=Integer.parseInt(current);
                    if(!set.contains(num) && isPrime(num))
                    {
                        set.add(num);
                        result.add(num);
                    }
                }
            }
            else
            {
                lastNum="";
            }
            index++;
        }
        return result;
    }

    private static boolean isPrime(int number)
    {
        if(number<2)
            return false;
        for(int i=2;i<=Math.sqrt(number);i++)
        {
            if(number%i==0)
                return false;
        }
        return true;
    }

    public static void main(String[] args)
    {
        String string="abc2134def31";
        ArrayList<Integer> result=findPrime(string);
        for(int i=0;i<result.size();i++)
        {
            System.out.print(result.get(i)+" ");
        }
    }
}

package leetCode;

import java.util.HashMap;

/**
 * User: xinyuwan, Date: 12/11/13, Time: 9:15 PM
 */
public class IntegerManipulation
{
    public static void main(String[] args)
    {
        int x=1234;
        int y=Integer.MAX_VALUE;
        System.out.println("Reverse x and y: "+new ReverseInteger().reverse(x)+" "+
        new ReverseInteger().reverse(y));

        String roman="DCMLLLXIV";
        System.out.println("Roman to Integer: "+new RomanToInteger().romanToInt(roman));

    }
}

class ReverseInteger
{
    /*
     * Problem: Reverse digits of an integer. Example1: x = 123, return 321 Example2: x = -123, return -321
     * Have you thought about this?
     * Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!
     * If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
     * Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?
     * Throw an exception? Good, but what if throwing an exception is not an option? You would then have to re-design the function (ie, add an extra parameter).
     */
    public int reverse(int x)
    {
        int flag=1;
        if(x<0)
        {
            x=-x;
            flag=-1;
        }
        long result=0;
        while(x>0)
        {
            result=result*10+x%10;
            x=x/10;
        }
        result*=flag;
        if(result>Integer.MAX_VALUE || result < Integer.MIN_VALUE)
            return result>0? Integer.MAX_VALUE : Integer.MIN_VALUE;
        else
            return (int)result;
    }
}

class RomanToInteger
{

    public int romanToInt(String s)
    {
        int result=0;
        HashMap<Character, Integer> map=new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        for(int i=0; i<s.length();i++)
        {
            int temp=map.get(s.charAt(i));
            //if next position is larger than this position, then we need to minus the number
            if((i+1)<s.length() && map.get(s.charAt(i+1))>temp)
                temp=-temp;
            result+=temp;
        }
        return result;
    }
}

class IntegerToRoman
{
    public String intToRoman(int num)
    {
        String[] digit={"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] ten={"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] hundred={"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] thousand={"", "M", "MM", "MMM"};
        if(num>3999)
            num=3999;
        if(num<0)
            num=0;
        StringBuilder sb=new StringBuilder();
        sb.append(thousand[num/1000]);
        sb.append(hundred[num/100%10]);
        sb.append(ten[num/10%10]);
        sb.append(digit[num%10]);
        return sb.toString();
    }
}



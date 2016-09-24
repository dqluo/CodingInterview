package leetCode.math;

import java.util.HashMap;

/**
 * User: xinyuwan, Date: 12/11/13, Time: 9:15 PM
 */
public class IntegerManipulation
{
    /*
     * Total problems: 5
     */
    public static void main(String[] args)
    {
        //test ReverseInteger
        int x=1234;
        int y=Integer.MAX_VALUE;
        ReverseInteger ri=new ReverseInteger();
        System.out.println("Reverse x and y: "+ri.reverse(x)+" "+ri.reverse(y));

        //test RomanToInteger
        String roman="DCMLLLXIV";
        System.out.println("Roman to Integer: "+new RomanToInteger().romanToInt(roman));

        //test IntegerToRoman
        int integer=3210;
        System.out.println("Integer to Roman: "+new IntegerToRoman().intToRoman(integer));

        //test PalindromeNumber
        int z=1297887921;
        PalindromeNumber pi=new PalindromeNumber();
        System.out.println("Is Palindrome? "+pi.isPalindrome(z));

        System.out.println("Is Palindrome? "+pi.isPalidrome2(z));

        //test atoi
        String[] arr={"-108afaf", "0", "21474836471", "2147483657", "-21474836481"};
        StringToInteger sti=new StringToInteger();
        System.out.println("String to integer: ");
        for(String s : arr)
        {
            System.out.print(sti.atoi(s)+" ");
        }
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
     *
     * Analysis: the logic is to iterate from lowest digit, get each digit from x%10 and add to 10*result.
     * Need to consider 2 corner cases.
     */
    public int reverse(int x)
    {
        //corner case1: x is negative, restore the sign after reversal
        int flag=1;
        if(x<0)
        {
            x=-x;
            flag=-1;
        }
        //corner case2: integer might overflow,
        //we need to compare with integer boundaries, so use long type for result
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
    /*
     * Problem: Given a roman numeral, convert it to an integer.
     * Input is guaranteed to be within the range from 1 to 3999.
     */
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
    /*
     * Problem: Given an integer, convert it to a roman numeral.
     * Input is guaranteed to be within the range from 1 to 3999.
     */
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

class PalindromeNumber
{
    /*
     * Problem: Determine whether an integer is a palindrome. Do this without extra space.
     * Some hints:
     * Could negative integers be palindromes? (ie, -1)
     * If you are thinking of converting the integer to string, note the restriction of using extra space.
     * You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?
     * There is a more generic way of solving this problem.
     *
     * Analysis: the logic is to get the highest and lowest digits of x and compare them. To get the
     * highest digit, we should get power of 10 first. Need to consider 2 corner cases
     */
    public boolean isPalindrome(int x)
    {
        //corner case1
        if(x < 0)
            return false;
        //corner case2
        if(x < 10)
            return true;
        int power=1;
        //Now x >10, we need to first get the "length" of x in terms of power of 10
        while(x/power >= 10)
            power*=10;
        //then we go from both end by getting the highest and lowest digit, note we only go through the loop
        //when x >=10. The "=" part is crucial.
        while(x >= 10)
        {
            int high=x/power, low=x%10;
            if(high!=low)
                return false;
            //after each iteration, strip off both ends and divide power by 100
            x=(x-power*high)/10;
            power/=100;
        }
        return true;
    }

    public boolean isPalidrome2(int x){
        if(x<0)
            return false;
        if(x<10)
            return true;
        int power=1;
        while(x/10>=power)
            power=10*power;
        while(x>=10){
            int first=x/power;
            int last=x%10;
            if(first!=last)
                return false;
            x=(x-first*power)/10;
            power=power/100;
        }
        return true;
    }
}

class StringToInteger
{
    /*
     * Problem: Implement atoi to convert a string to an integer.
     * Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.
     * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
     * spoilers alert... click to show requirements for atoi.
     * Requirements for atoi:
     * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
     * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
     * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
     * If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
//     */
    public int atoi(String str)
    {
        if(str==null)
            return 0;
        str=str.trim();
        if(str.isEmpty())
            return 0;
        int result=0;

        boolean negative=false;
        int i=0;
        if(str.charAt(0)=='-' || str.charAt(0)=='+')
        {
            i++;
            if(str.charAt(0)=='-')
                negative=true;
        }
        for(; i<str.length(); i++)
        {
            char c=str.charAt(i);
            //return the result before non-digit char
            if(!Character.isDigit(c))
                break;
            //now begin to check corner case for boundary values
            if(!negative && result > Integer.MAX_VALUE/10)
                return Integer.MAX_VALUE;
            if(!negative && result==Integer.MAX_VALUE/10 && (c-'0')>=7)
                return Integer.MAX_VALUE;
            if(negative && -result < Integer.MIN_VALUE/10)
                return Integer.MIN_VALUE;
            if(negative && -result == Integer.MIN_VALUE/10 && (c-'0')>=8)
                return Integer.MIN_VALUE;
            result=result*10 + (c-'0');
        }
        return negative? -result : result;
    }
}


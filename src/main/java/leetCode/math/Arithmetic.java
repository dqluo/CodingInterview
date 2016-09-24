package leetCode.math;

import leetCode.ADT.ListNode;
import util.ArrayUtil;

import static leetCode.ADT.ListNode.createList;
import static leetCode.ADT.ListNode.printList;

/**
 * User: xinyuwan, Date: 1/11/14, Time: 7:41 PM
 */
public class Arithmetic
{
    /*
     * Total problems: 7
     */
    public static void main(String[] args)
    {
        //test AddBinary
        String a="1101", b="1001111";
        AddBinary ab=new AddBinary();
        System.out.println("Result of adding binaries: "+ab.addBinary(a, b));

        //test PlusOne
        int[] a1={9, 9, 9, 9}, a2={1, 8, 8, 9};
        PlusOne po=new PlusOne();
        System.out.print("Result of plus one to a1: ");
        ArrayUtil.print(po.plusOne(a1));
        System.out.print("Result of plus one to a2: ");
        ArrayUtil.print(po.plusOne(a2));

        //test AddTwoNumbers
        int[] b1={3, 1, 9}, b2={8, 1, 1, 1};
        AddTwoNumbers atn=new AddTwoNumbers();
        System.out.print("Result of adding list b1 and b2: ");
        printList(atn.addTwoNumbers(createList(b1), createList(b2)));

        //test MultiplyString
        String num1="6913259244";
        String num2="71103343";
        MultiplyStrings ms=new MultiplyStrings();
        System.out.println("Result of multiplication: "+ms.multiply(num1, num2));

        //test DivideTwoIntegers
        int x=256, y=15;
        DivideTwoIntegers dti=new DivideTwoIntegers();
        System.out.println("Result of division: "+dti.divide(x, y));

        //test SqrtX
        SqrtX sx=new SqrtX();
        System.out.println("Sqrt of 101: "+sx.sqrt(101));

        //test PowerXN
        PowerXN pxn=new PowerXN();
        System.out.println("Power of (3, 4): "+pxn.pow(3, 4));
    }

}

/*
 * Addition
 */
class AddBinary
{
    /*
     * Problem: Given two binary strings, return their sum (also a binary string).
     * For example,
     * a = "11"
     * b = "1"
     * Return "100".
     *
     * Analysis: logic is the same as adding two decimal numbers. We add from the last digit of a and b,
     * keep track of carry, and when we reached highest digit of the larger number, we check whether carry==1,
     * so as to decide whether to add one more digit or not. Note, here we delegate the get bit process to
     * the bit() method, which will handle both normal case(index>0) and exception case(index <0).
     */

    public String addBinary(String a, String b)
    {
        if(a==null || a.isEmpty())
            return b;
        if(b==null || b.isEmpty())
            return a;
        int lastIndexOfA=a.length()-1, lastIndexOfB=b.length()-1;
        int maxLength=Math.max(lastIndexOfA, lastIndexOfB)+1;
        StringBuffer sb=new StringBuffer();
        int carry=0;
        for(int i=0; i<maxLength; i++)
        {
            int value=carry+bit(a, lastIndexOfA-i)+bit(b, lastIndexOfB-i);
            carry=value/2;
            sb.insert(0, value%2);
        }
        if(carry==1)
            sb.insert(0, 1);
        return sb.toString();
    }
    //this method helps padding s with '0', when we touch the digits not in s
    private int bit(String s, int index)
    {
        return (index<0 || s.charAt(index)=='0')? 0 : 1;
    }
}

class PlusOne
{
    /*
     * Problem: Given a number represented as an array of digits, plus one to the number.
     *
     * Analysis: logic is to iterate from the lowest digit, however, instead of add every digit with 1,
     * we check whether current digit==9, if not, we need to reset carry=0 and break the loop
     */
    public int[] plusOne(int[] digits)
    {
        int carry=0, i=digits.length-1;
        for(; i>=0; i--)
        {
            if(digits[i]==9)
            {
                digits[i]=0;
                carry=1;
            }
            else
            {
                digits[i]+=1;
                carry=0;
                break;
            }
        }
        if(carry==0)
            return digits;
        //carry=1, we need to add one more digit
        int[] newDigits=new int[digits.length+1];
        newDigits[0]=1;
        System.arraycopy(digits, 0, newDigits, 1, digits.length);
        return newDigits;
    }
}

class AddTwoNumbers
{
    /*
     * Problem: You are given two linked lists representing two non-negative numbers.
     * The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     */
    //this is forwardSum
    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        return forwardSum(l1, l2, 0);
    }
    private ListNode forwardSum(ListNode l1, ListNode l2, int carry)
    {
        if(l1==null && l2==null && carry==0)
            return null;
        int value=carry;
        if(l1!=null)
            value+=l1.val;
        if(l2!=null)
            value+=l2.val;
        ListNode newNode=new ListNode(value%10);
        newNode.next=forwardSum(l1==null? null : l1.next, l2==null? null : l2.next, value/10);
        return newNode;
    }
}

/*
 * Multiplication
 */
class MultiplyStrings
{
    /*
     * Problem: Given two numbers represented as strings, return multiplication of the numbers as a string.
     * Note: The numbers can be arbitrarily large and are non-negative.
     *
     * Analysis:
     * 1. for each digit in both numbers, multiply them and store this value into corresponding index
     * 2. for each index from the last, we perform addition, and keeps carry
     * 3. build the result string, if carry==0, we need to skip the potential 0s from the highest end
     */
    public String multiply(String num1, String num2)
    {
        //Exception case
        if(num1==null || num2==null || num1.isEmpty() || num2.isEmpty())
            return "";
        //Normal case with result 0, cannot use Integer.parseInt() since num can be overflow of integer
        if(num1.equals("0") || num2.equals("0"))
            return "0";
        //we store the "raw" calculated value into each element of the array first
        int len1=num1.length(), len2=num2.length();
        int[] result=new int[len1+len2];
        for(int i=len1-1; i>=0; i--)
        {
            int digit1=num1.charAt(i)-'0';
            for(int j=len2-1; j>=0; j--)
            {
                int digit2=num2.charAt(j)-'0';
                result[i+j+1]+=digit1*digit2;
            }
        }
        //Now we need to calculate the final value of each element with carry
        int carry=0, value=0;
        for(int i=result.length-1; i>=0; i--)
        {
            value=result[i]+carry;
            result[i]=value%10;
            carry=value/10;
        }
        //Finally we build the string from the highest digit to improve performance
        StringBuilder sb=new StringBuilder();
        int i=0;
        //Need to skip to the first non-zero digit from the highest
        while(i<result.length && result[i]==0)
            i++;
        while(i<result.length)
        {
            sb.append(result[i]);
            i++;
        }
        return sb.toString();
    }
}

/*
 *  Division
 */
class DivideTwoIntegers
{
    /*
     * Problem: Divide two integers without using multiplication, division and mod operator.
     */
    public int divide(int dividend, int divisor)
    {
        if(divisor==0)
            return Integer.MAX_VALUE;
        if(divisor==1)
            return dividend;
        if(divisor==2)
            return dividend >> 1;
        //first check the sign
        int sign=1;
        if((dividend>0 && divisor<0) || (dividend<0 && divisor>0))
            sign=-1;
        //second, cast int to long for the case when a or b is Integer.MIN_VALUE
        long a=Math.abs((long)dividend), b=Math.abs((long)divisor), result=0;
        //third, perform minus to a and b, we increase the value of b by left shifting 1 each time
        while(a >= b)
        {
            long c=b;
            for(int i=0; c<=a; i++, c=c<<1)
            {
                a-=c;
                result=result+(1 << i);
            }
        }
        //finally remember to restore both the sign and the type
        return sign*(int)result;
    }
}

/*
 * Power And Sqrt
 */
class PowerXN
{
    /*
     * Problem: Implement pow(x, n).
     *
     * Analysis: idea is to shrink the number of multiplication(it is straightforward that we need to
     * multiply x less than n times). To achieve this, we can denote n in terms of binary:
     * e.g. n=5=(2**2)*1+(2**1)*0+(2**0)*1, which means that x**5=(x**4)*(x**0), we can get O(logN) complexity
     * by only multiply logN times. So for the binary denotation of n, we need to iterate through each
     * bit, if we get 1 then multiply corresponding power of x to that bit(for bit 3, if it's 1, we multiply it
     * with x**3); if we get 0, simply disregard that bit but still accumulate x's power.
     */
    public double pow(double x, int n)
    {
        if(n < 0)
        {
            if(x==0)
                throw new ArithmeticException("x cannot be 0 if n<0");
            n=-n;
            x=1/x;
        }
        double result=1;
        for(int i=n; i>0; i>>=1)
        {
            if((i & 1) ==1)
                result*=x;
            x*=x;
        }
        return result;
    }
}

class SqrtX
{
    /*
     * Problem: Implement int sqrt(int x).
     * Compute and return the square root of x.
     */

    //use binary search to narrow down middle
    public int sqrt(int x)
    {
        if(x < 0)
            return 0;
        int first=0, last=x;
        while(first <= last)
        {
            long mid=(first+last)/2;
            long tempPower=mid*mid;
            if(tempPower==x)
                return (int)mid;
            else if(tempPower < x)
                first=(int)mid+1;
            else
                last=(int)mid-1;
        }
        return last;
    }

    //http://www.cnblogs.com/AnnieKim/archive/2013/04/18/3028607.html
    //Newton Iteration
    public int sqrt2(int x)
    {
        if(x<=0)
            return 0;
        double last=0;
        double result=1;
        while(last!=result)
        {
            last=result;
            result=(result+x/result)/2;
        }
        return (int)result;
    }
}



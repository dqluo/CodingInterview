package crackingAndMadeEasy.searchAndSort.basic;

import java.util.Arrays;
import java.util.Hashtable;

/**
 * User: xinyuwan, Date: 11/30/13, Time: 10:23 PM
 */
public class BitManipulable
{
    public static void main(String[] args)
    {
        //Test missingNum()
        int[] a={1, 2, 4, 6, 3, 7, 8};
        System.out.print("Missing number: ");
        System.out.print(FindMissingNumber.missingNum(a, 8)+" "
                +FindMissingNumber.missingNum2(a, 8)+" "+FindMissingNumber.missingNum3(a, 8));

        //Test oddNum()
        int[] b={1, 2, 3, 3, 2, 1, 4};
        System.out.println("\nOdd number occur: "+FindOddNumberOccur.oddOccur(b));

        //Test twoRepeat()
        int[] c={1, 2, 3, 4, 5, 2, 4};
        System.out.println("Two repeats: ");
        FindTwoRepeatElements.twoRepeats(c, 5);
        FindTwoRepeatElements.twoRepeats3(c, 5);
        FindTwoRepeatElements.twoRepeats2(c);

    }
}

class FindMissingNumber
{
    /*
     * Problem1: find the missing number is a contiguous set 1-n,
     * the array is disordered
     */
    //Method1: sort the array, monitor by increasing count pointer
    public static int missingNum(int[] a, int n)
    {
        Arrays.sort(a);
        int count=1;
        for(int i=0; i<a.length; i++)
        {
            if(count<a[i])
                return count;
            count++;
        }
        //if count==n, a is missing n
        return count==n ? n : 0;
    }
    //Method2: use hashtable by two scans
    public static int missingNum2(int[] a, int n)
    {
        Hashtable<Integer, Boolean> h=new Hashtable<Integer, Boolean>();
        int i=0;
        for(; i<a.length; i++)
            h.put(a[i], true);
        //The second scan is based on the contiguous number(1-n)
        //because a.length=n-1 so upper-bond is a.length+1
        for(i=1; i<=n; i++)
        {
            if(!h.containsKey(i))
                return i;
        }
        //if no such element exists, return 0
        return 0;
    }
    //Method3: use bit manipulation
    public static int missingNum3(int[] a, int n)
    {
        int x=0;
        for(int i=0; i<a.length; i++)
            x=x^a[i];
        //now scan 1 to n
        for(int i=1; i<=n; i++)
            x=x^i;
        //if no such element exists, x^y is 0
        return x;
    }
}

class FindOddNumberOccur
{
    /*
     * problem2: array of all positive integers, all elements occur even number
     * of times, except one element, find the element
     */
    //Method1: use Hashtable by two scans
    public static int oddOccur(int[] a)
    {
        Hashtable<Integer, Boolean> h=new Hashtable<Integer, Boolean>();
        int i=0;
        for(; i<a.length; i++)
        {
            if(h.containsKey(a[i]))
                h.put(a[i], !h.get(a[i]));
            else
                h.put(a[i], true);
        }
        //when there're large number of repeat elements in array
        //use keys() to iterate within hashtable
        for(Integer n : h.keySet())
        {
            if(h.get(n))
                return n;
        }
        return Integer.MIN_VALUE;
    }
    //Method2: use bit manipulation
    //this method is based on the fact that only one number occurs odd times
    public static int oddOccur2(int[] a)
    {
        int x=0;
        for(int i=0; i<a.length; i++)
            x=x^a[i];
        return x;
    }
}

class FindTwoRepeatElements
{
    /*
     * Problem3: given an array with number range from (1-n), two of them
     * are repeat, together a.length=n+2, find the two repeat elements
     */

    //Method1: use count array
    public static void twoRepeats(int[] a, int n)
    {
        int[] count=new int[1+n];
        for(int i=0; i<a.length; i++)
        {
            count[a[i]]++;
            if(count[a[i]]==2)
                System.out.print(a[i]+" ");
        }
        System.out.println();
    }
    //Method2: use negative mark, we can use this in missingNum problem
    public static void twoRepeats2(int[] a)
    {
        for(int i=0; i<a.length; i++)
        {
            if(a[Math.abs(a[i])]<0)
                System.out.print(Math.abs(a[i])+" ");
            else
                a[Math.abs(a[i])]=-a[Math.abs(a[i])];
        }
    }
    //Method3: use bit manipulation
    //we first get the result of x^y so as to get the pattern
    //that defines a set of elements in a, all of them share the common
    //trait that the rightmost bit 1 is at the same position. Because, the rightmost 1
    //in x^y belongs to either one of x and y, we can divide the element into two sets,
    //and use XOR again to get the x and y seperatedly.
    public static void twoRepeats3(int[] a, int n)
    {
        int xor=a[0];
        int i=1, x=0, y=0, rightMostBit=0;
        for(; i<a.length; i++)
            xor=xor^a[i];
        for(i=1; i<=n; i++)
            xor=xor^i;
        rightMostBit=xor & (~xor+1);
        for(i=0; i<a.length; i++)
        {
            if((a[i]&rightMostBit)!=0)
                x=x^a[i];
            else
                y=y^a[i];
        }
        for(i=1; i<=n; i++)
        {
            if((i&rightMostBit) !=0)
                x=x^i;
            else
                y=y^i;
        }
        System.out.println(x+" "+y);
    }
    //Method4: use sort technique
    //Method5: use equations: x+y=s-(n(n+1)/2), xy=p/n!
}

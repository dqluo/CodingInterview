package crackingAndMadeEasy.searchAndSort.basic;

import java.util.Arrays;
import java.util.HashMap;

/**
 * User: xinyuwan, Date: 11/29/13, Time: 4:43 PM
 */
public class Duplicate
{
    public static void main(String[] args)
    {
        int[] a={3, 2, 2, 3, 1, 3, 2, 3, 3};
        int[] b={3, 4, 5, 0, 1, 2};
        System.out.println("Has Duplicate -- a? "+DuplicateCheck.check(a)
                +" "+DuplicateCheck.check2(a)+" "+DuplicateCheck.check3(a)+" "+DuplicateCheck.check4(a));
        System.out.println("Has Duplicate -- b? "+DuplicateCheck.check(b)
                +" "+DuplicateCheck.check2(b)+" "+DuplicateCheck.check3(b)+" "+DuplicateCheck.check4(b));
        int[] a2={3, 2, 2, 3, 1, 3, 2, 3, 3};
        System.out.println("Element with max occurrence: "+FindMaxOccurDup.find(a2)
                +" "+FindMaxOccurDup.find2(a2));

        int[] a3={3, 2, 2, 3, 1, 3, 2, 3, 3};
        System.out.println("First duplicate element: "+FindFirstDuplicate.find(a3));

    }
}

class DuplicateCheck
{
    public static boolean check(int[] a)
    {
        for(int i=0; i<a.length-1; i++)
        {
            for(int j=i+1; j<a.length; j++)
            {
                if(a[i]==a[j])
                    return true;
            }
        }
        return false;
    }
    public static boolean check2(int[] a)
    {
        Arrays.sort(a);
        for(int i=0; i<a.length-1; i++)
        {
            if(a[i]==a[i+1])
                return true;
        }
        return false;
    }
    public static boolean check3(int[] a)
    {
        HashMap<Integer, Boolean> map=new HashMap<Integer, Boolean>();
        for(int i=0; i<a.length; i++)
        {
            if(map.containsKey(a[i]))
                return true;
            map.put(a[i], true);
        }
        return false;
    }
    public static boolean check4(int[] a)
    {
        for(int i=0; i<a.length; i++)
        {
            int absIndex=Math.abs(a[i]);
            if(a[absIndex]<0)
                return true;
            a[absIndex]=-a[absIndex];
        }
        return false;
    }
}

class FindMaxOccurDup
{
    public static int find(int[] a)
    {
        int max=0, maxOccur=0;
        HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
        for(int i=0; i<a.length; i++)
        {
            int temp=1;
            if(map.containsKey(a[i]))
                temp=map.get(a[i])+1;
            if(temp>maxOccur)
            {
                maxOccur=temp;
                max=a[i];
            }
            map.put(a[i], temp);
        }
        return max;
    }

    public static int find2(int[] a)
    {
        for(int i=0; i<a.length; i++)
        {
            a[a[i]%a.length]+=a.length;
        }
        int max=0, maxOccur=0;
        for(int i=0; i<a.length; i++)
        {
            int temp=a[i]/a.length;
            if(maxOccur<temp)
            {
                maxOccur=temp;
                max=i;
            }
        }
        return max;
    }
}

class FindFirstDuplicate
{
    public static int find(int[] a)
    {
        HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
        int position=a.length;
        for(int i=0; i<a.length; i++)
        {
            if(map.containsKey(a[i]))
            {
                int p=map.get(a[i]);
                if(p<position)
                    position=p;
            }
            else
                map.put(a[i], i);
        }
        if(position<a.length)
            return a[position];
        return Integer.MAX_VALUE;
    }
}

package crackingAndMadeEasy.searchAndSort.basic;

import java.util.Arrays;
import java.util.HashMap;

/**
 * User: xinyuwan, Date: 12/1/13, Time: 2:51 PM
 */
public class FindSum
{
    public static void main(String[] args)
    {
        int[] a={1, 60, -10, 70, -80, 85, 23, 47, -30};
        //Test sumK()
        FindTwoSumK.sumK(a, 55);
        FindTwoSumK.sumK2(a, 55);

        int[] b={1, 60, -10, 70, -80, 90, 23, 47, -30};
        FindSumCloseToZero.find(b);

        int[] c={1, 60, -10, 70, -80, 85, 23, 47, -30};
        FindThreeSumK.find(c, 78);
        FindThreeSumK.find2(c, 78);

        int[] d={-1, -10, -2, 3, -4, 5, 13, -12, 8};
        FindQuadraticSum.find(d);

        int[] e={1, 60, -10, 70, -80, 85, 23, 47, -30};
        int[] f={-1, -10, -2, 3, -4, 5, 13, -12, 8};
        CheckABSum.check(e, f, -17);
        CheckABSum.check2(e, f, -17);

    }
}

class FindTwoSumK
{
    public static void sumK(int[] a, int k)
    {
        Arrays.sort(a);
        for(int i=0, j=a.length-1; i<j;)
        {
            int sum=a[i]+a[j];
            if(sum==k)
            {
                System.out.println("Found "+a[i]+" "+a[j]);
                return;
            }
            else if(sum < k)
                i++;
            else
                j--;
        }
        System.out.println("Not Fount");
    }

    public static void sumK2(int[] a, int k)
    {
        HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
        for(int i=0; i<a.length; i++)
        {
            if(map.containsKey(k-a[i]))
            {
                System.out.println("Found "+a[i]+" "+(k-a[i]));
                return;
            }
            else
                map.put(a[i], i);
        }
        System.out.println("Not Fount");
    }
}

class FindSumCloseToZero
{
    public static void find(int[] a)
    {
        Arrays.sort(a);
        int minSum=Integer.MAX_VALUE, x=0, y=0;
        for(int i=0, j=a.length-1; i<j;)
        {
            int temp=a[i]+a[j];
            if(temp == 0)
            {
                System.out.println("Found 0= "+a[i]+" "+a[j]);
                return;
            }
            if(Math.abs(temp) < Math.abs(minSum))
            {
                minSum=temp;
                x=a[i];
                y=a[j];

            }
            //now we should update i and j
            if(temp > 0)
                j--;
            else
                i++;
        }
        System.out.println("Found "+minSum+"= "+x+" "+y);
    }
}

class FindThreeSumK
{
    public static void find(int[] a, int k)
    {
        Arrays.sort(a);
        for(int h=0; h<a.length-2; h++)
        {
            for(int i=h+1, j=a.length-1; i<j;)
            {
                int sum=a[i]+a[j]+a[h];
                if(sum==k)
                {
                    System.out.println("Found: "+a[h]+" "
                            +a[i]+" "+a[j]);
                    return;
                }
                else if(sum < k)
                    i++;
                else
                    j--;
            }
        }
        System.out.println("Not found");
    }
    public static void find2(int[] a, int k)
    {
        HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
        for(int h=0; h<a.length; h++)
        {
            /*
             * if there're duplicates in array, we only store the
             * value of k-a[h], which appears earlier(smaller h)
             */
            if(!map.containsKey(k-a[h]))
                map.put(k-a[h], h);
        }
        Arrays.sort(a);
        for(int twoSum : map.keySet())
        {
            for(int i=0, j=a.length-1; i<j;)
            {
                int sum=a[i]+a[j];
                if(sum == twoSum)
                {
                    int index=map.get(sum);
                    if(index!=i && index!=j)
                    {
                        System.out.println("Found: "+a[index]+" "
                                +a[i]+" "+a[j]);
                        return;
                    }
                }
                else if(sum < twoSum)
                    i++;
                else
                    j--;
            }
        }
        System.out.println("Not found");
    }
}

class FindQuadraticSum
{
    public static void find(int[] a)
    {
        for(int i=0; i<a.length; i++)
            a[i]=a[i]*a[i];
        Arrays.sort(a);
        for(int h=a.length-1; h>=2; h--)
        {
            for(int i=0, j=h-1; i<j;)
            {
                int sum=a[i]+a[j];
                if(sum==a[h])
                {
                    System.out.println("Found "+a[h]+" = "
                            +a[i]+" + "+a[j]);
                    return;
                }
                else if(sum > a[h])
                    j--;
                else
                    i++;
            }
        }
        System.out.println("Not Found");
    }
}

class CheckABSum
{
    public static boolean check(int[] a, int[] b, int k)
    {
        Arrays.sort(b);
        for(int i=0; i<a.length; i++)
        {
            int result=BinarySearch.search(b, k-a[i]);
            if(result > 0)
            {
                System.out.println("Found "+a[i]+" in a, and "+b[result]+" in b");
                return true;
            }
        }
        System.out.println("Not found");
        return false;
    }
    public static boolean check2(int[] a, int[] b, int k)
    {
        int[] shorter=a, longer=b;
        if(a.length>b.length)
        {
            shorter=b;
            longer=a;
        }
        HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
        for(int i=0; i<shorter.length; i++)
        {
            if(!map.containsKey(k-shorter[i]))
                map.put(k-shorter[i], i);
        }
        for(int i=0; i<longer.length; i++)
        {
            if(map.containsKey(longer[i]))
            {
                System.out.println("Found "+longer[i]+" in longer, and "
                        +shorter[map.get(longer[i])]+" in shorter");
                return true;
            }
        }
        System.out.println("Not found");
        return false;
    }
}
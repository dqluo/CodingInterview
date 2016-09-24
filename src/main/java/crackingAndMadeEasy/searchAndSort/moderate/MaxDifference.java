package crackingAndMadeEasy.searchAndSort.moderate;

import util.ArrayUtil;

/**
 * User: xinyuwan, Date: 12/2/13, Time: 8:30 PM
 */
public class MaxDifference
{
    public static void main(String[] args)
    {
        int[] a={34, 8, 10, 3, 2, 80, 30, 33, 1};
        System.out.println(maxDiff(a));
    }
    public static int maxDiff(int[] a)
    {
        int[] left=new int[a.length], right=new int[a.length];
        left[0]=a[0];
        for(int i=1; i<a.length; i++)
        {
            if(a[i]<left[i-1])
                left[i]=a[i];
            else
                left[i]=left[i-1];
        }
        right[a.length-1]=a[a.length-1];
        for(int j=a.length-2; j>=0; j--)
        {
            if(a[j] > right[j+1])
                right[j]=a[j];
            else
                right[j]=right[j+1];
        }
        ArrayUtil.print(left);
        ArrayUtil.print(right);
        int maxDiff=0;
        for(int i=0, j=0; i<a.length && j<a.length;)
        {
            if(left[i] < right[j])
            {
                j++;
                maxDiff=Math.max(maxDiff, j-i);
            }
            else
                i++;
        }
        return maxDiff;
    }
}

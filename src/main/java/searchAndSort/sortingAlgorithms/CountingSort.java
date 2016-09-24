package searchAndSort.sortingAlgorithms;

import util.ArrayUtil;

/**
 * User: xinyuwan, Date: 11/29/13, Time: 2:17 PM
 */

public class CountingSort
{
    public static void main(String[] args)
    {
        int[] a={6, 2, 5, 2, 7, 9, 5, 2, 1, 3, 8, 3};
        countingSort(a, 9);
        ArrayUtil.print(a);
    }
    public static void countingSort(int[] a, int k)
    {
        int[] sorted=new int[a.length];
        int[] bucket=new int[k+1];
        int i=0;
        for(; i<a.length; i++)
            bucket[a[i]]++;
        for(i=1; i<bucket.length; i++)
            bucket[i]+=bucket[i-1];
        for(i=0; i<a.length; i++)
        {
            sorted[bucket[a[i]]-1]=a[i];
            bucket[a[i]]--;
        }
        System.arraycopy(sorted, 0, a, 0, a.length);
    }
}

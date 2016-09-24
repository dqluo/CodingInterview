package crackingAndMadeEasy.searchAndSort.sortingAlgorithms;

import util.ArrayUtil;

/**
 * User: xinyuwan, Date: 11/29/13, Time: 2:01 PM
 */
public class BucketSort
{
    public static void main(String[] args)
    {
        int[] a={6, 2, 5, 2, 7, 9, 5, 2, 1, 3, 8, 3};
        bucketSort(a, 9);
        ArrayUtil.print(a);
    }
    public static void bucketSort(int[] array, int k)
    {
        int[] bucket=new int[k+1];
        for(int i=0; i<array.length; i++)
            bucket[array[i]]++;
        int j=0;
        for(int i=0; i<bucket.length; i++)
        {
            while(bucket[i]>0)
            {
                array[j]=i;
                j++;
                bucket[i]--;
            }
        }
    }
}

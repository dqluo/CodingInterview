package searchAndSort.sortingAlgorithms;

import util.ArrayUtil;

/**
 * User: xinyuwan, Date: 11/29/13, Time: 1:39 PM
 */
public class InsertionSort
{
    public static void main(String[] args)
    {
        int[] a={5, 3, 4, 2, 1, 8, 0};
        insertionSort(a);
        ArrayUtil.print(a);
    }

    public static void insertionSort(int[] a)
    {
        for(int i=1; i<a.length; i++)
        {
            int nextUnsorted=a[i];
            int j=i;
            for(; j>0 && a[j-1]>nextUnsorted; j--)
            {
                a[j]=a[j-1];
            }
            a[j]=nextUnsorted;
        }
    }
    public static void insertionSort(int[] a, int first, int last)
    {
        for(int i=first+1; i<=last; i++)
        {
            int nextUnsorted=a[i];
            int j=i;
            for(; j>first && a[j-1]>nextUnsorted; j--)
            {
                a[j]=a[j-1];
            }
            a[j]=nextUnsorted;
        }
    }
}

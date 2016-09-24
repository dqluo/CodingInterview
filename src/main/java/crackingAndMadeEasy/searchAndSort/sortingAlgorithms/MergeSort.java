package crackingAndMadeEasy.searchAndSort.sortingAlgorithms;

import util.ArrayUtil;

/**
 * User: xinyuwan, Date: 11/29/13, Time: 2:20 PM
 */
public class MergeSort
{
    public static void main(String[] args)
    {
        //Test merge
        int[] a={3, 6, 19, 28, 2, 12, 20, 49};
        merge(a, new int[a.length], 0, (0+a.length-1)/2, a.length-1);
        ArrayUtil.print(a);
        //Test mergeSort
        int[] b={9, 3, 2, 15, 12, 7, 10, 6, 11, 8};
        mergeSort(b);
        ArrayUtil.print(b);

    }
    //O(n*LogN)
    public static void mergeSort(int[] array)
    {
        mergeSort(array, new int[array.length], 0, array.length-1);
    }
    private static void mergeSort(int[] a, int[] temp, int first, int last)
    {
        if(first>=last)
            return;
        int mid=(first+last)/2;
        mergeSort(a, temp, first, mid);
        mergeSort(a, temp, mid+1, last);
        merge(a, temp, first, mid, last);
    }
    private static void merge(int[] a, int[] temp, int first, int mid, int last)
    {
        int nextOfFirst=first;
        int nextOfSecond=mid+1;
        int i=first;
        while(nextOfFirst <= mid && nextOfSecond <= last)
        {
            if(a[nextOfFirst]<a[nextOfSecond])
            {
                temp[i]=a[nextOfFirst];
                nextOfFirst++;
            }
            else
            {
                temp[i]=a[nextOfSecond];
                nextOfSecond++;
            }
            i++;
        }
        while(nextOfFirst <= mid)
        {
            temp[i]=a[nextOfFirst];
            i++;
            nextOfFirst++;
        }
        while(nextOfSecond <= last)
        {
            temp[i]=a[nextOfSecond];
            i++;
            nextOfSecond++;
        }
        System.arraycopy(temp, first, a, first, last - first + 1);
    }
}

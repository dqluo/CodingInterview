package searchAndSort.sortingAlgorithms;

import util.ArrayUtil;

/**
 * User: xinyuwan, Date: 11/29/13, Time: 1:16 PM
 */
public class SelectionSort
{
    public static void main(String[] args)
    {
        int[] a={5, 3, 4, 2, 1, 8, 0};
        selectionSort(a);
        ArrayUtil.print(a);
    }
    //O(n2)
    public static void selectionSort(int[] array)
    {
        //we only need to iterate to a[length-2], because a[length-1] is largest at that point
        for(int i=0; i<array.length-1; i++)
        {
            int nextMin=array[i], indexOfNextMin=i;
            for(int j=i+1; j<array.length; j++)
            {
                if(array[j]<nextMin)
                {
                    nextMin=array[j];
                    indexOfNextMin=j;
                }
            }
            ArrayUtil.swap(array, i, indexOfNextMin);
        }
    }
}

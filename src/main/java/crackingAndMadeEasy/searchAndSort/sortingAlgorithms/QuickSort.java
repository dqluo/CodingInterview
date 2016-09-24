package crackingAndMadeEasy.searchAndSort.sortingAlgorithms;

import util.ArrayUtil;

/**
 * User: xinyuwan, Date: 11/29/13, Time: 4:09 PM
 */
public class QuickSort
{
    public static void main(String[] args)
    {
        int[] a={9, 3, 19, 5, 12, 13, 17, 18, 13};

        quickSort(a);
        ArrayUtil.print(a);
    }
    public static void quickSort(int[] a)
    {
        quickSort(a, 0, a.length-1);
    }
    public static void quickSort(int[] a, int first, int last)
    {
        int pivotIndex=partition(a, first, last);
        if(first < pivotIndex-1)
            quickSort(a, first, pivotIndex-1);
        if(last > pivotIndex)
            quickSort(a, pivotIndex, last);
    }
    public static int partition(int[] a, int first, int last)
    {
        int pivotItem=a[(first+last)/2];
        int left=first, right=last;
        while(left <= right)
        {
            while(a[left] < pivotItem)
                left++;
            while(a[right] > pivotItem)
                right--;

            if(left <= right)
            {
                ArrayUtil.swap(a, left, right);
                left++;
                right--;
            }
        }
        /*
         * Add the note to prove that after the while loop, we can conclude that:
         * 1.left > right
         * 2. if left >= right+1, then the element(s) in between must equal to pivot
         * 3.The reason of (if <= ) rather than (if <) is to ensure left > right after the loop
         * 4. why we need left > right rather than left == right, is that in this case, we need
         * to prevent infinite recursive call of quickSort() when first+1=last && pivotIndex=first
         */
        System.out.println("Pivot is "+pivotItem+", after partition, left is "+left+", right is "+right);
        return left;
    }
}

package crackingAndMadeEasy.searchAndSort.sortingAlgorithms;

import util.ArrayUtil;

/**
 * User: xinyuwan, Date: 11/29/13, Time: 1:06 PM
 */
public class BubbleSort
{
    public static void main(String[] args)
    {
        int[] a={5, 3, 4, 2, 1, 8, 0};
        bubbleSort(a);
        ArrayUtil.print(a);
    }
    /*
     *  O(n2)
     *
     * Each time after scanning the array,
     * we reduce the pass by, since now the last
     * element of a is the largest element
     */
    public static void bubbleSort(int[] array)
    {
        boolean done=false;
        for(int pass=array.length-1; pass>0 && !done; pass--)
        {
            done=true;
            for(int i=0; i<pass; i++)
            {
                if(array[i]>array[i+1])
                {
                    ArrayUtil.swap(array, i, i+1);
                    done=false;
                }
            }
        }
    }
}

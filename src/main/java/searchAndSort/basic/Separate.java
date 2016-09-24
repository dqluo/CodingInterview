package searchAndSort.basic;

import util.ArrayUtil;

/**
 * User: xinyuwan, Date: 12/1/13, Time: 12:46 PM
 */
public class Separate
{
    public static void main(String[] args)
    {
        //Test SeparateEvenOdd
        int[] a={9, 64, 28, 13, 32, 45, 77, 82, 12, 10, 8};
        SeparateEvenOdd.moveEvenToFront(a);
        ArrayUtil.print(a);

        //Test SeparateZeroFront
        int[] b={1, 0, 0, 1, 1, 0, 1, 0, 0, 0};
        SeparateZeroOne.moveZeroToFront(b);
        ArrayUtil.print(b);

        //Test SortZerooOneTwo
        int[] c={0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        SortZeroOneTwo.sort(c);
        ArrayUtil.print(c);

        //Test MoveZeroToEnd
        int[] d={2, 0, 3, 0, 4, 0, 0, 5, 5, 8};
        int[] e={3, 0, 1, 0, 2, 2, 7, 0, 0, 4};
        SeparateZeroWithOrder.moveZeroToEnd(d);
        ArrayUtil.print(d);
        SeparateZeroWithOrder.moveZeroToFront(e);
        ArrayUtil.print(e);
    }
}

class SeparateEvenOdd
{
    /*
     * Problem1: move even number element to front and odd to end
     */
    public static void moveEvenToFront(int[] a)
    {
        int i=0, j=a.length-1;
        while(i<j)
        {
            //find the first even from end
            while(a[j]%2!=0 && i<j)
                j--;
            //find the first odd from end
            while(a[i]%2==0 && i<j)
                i++;
            if(i<j)
            {
                ArrayUtil.swap(a, i, j);
                i++;
                j--;
            }
        }
    }
}

class SeparateZeroOne
{
    /*
     * Problem2: move 0 to front and 1 to end
     */
    public static void moveZeroToFront(int[] a)
    {
        int i=0, j=a.length-1;
        while(i<j)
        {
            //find the first 0 from end
            while(a[j]!=0 && i<j)
                j--;
            //find the first 1 from end
            while(a[i]==0 && i<j)
                i++;
            if(i<j)
            {
                ArrayUtil.swap(a, i, j);
                i++;
                j--;
            }
        }
    }
}

class SortZeroOneTwo
{
    /*
     * Problem3: sort an array with elements 0, 1, 2
     */
    public static void sort(int[] a)
    {
        int first=0, mid=0, last=a.length-1;
        //need to make sure when mid==last, we go check mid again to swap element not 2
        while(mid<=last)
        {
            switch(a[mid])
            {
                //first always points to the position, where all elements before that
                //position are zeros; and a[first] is at most 1
                case 0:
                    ArrayUtil.swap(a, first, mid);
                    mid++;
                    first++;
                    break;
                case 1:
                    mid++;
                    break;
                //last always points to the position, where all elements after that one
                //are twos. We don't update mid here, because a[last] could be as any of
                //0, 1, 2, so we need to further deal with a[mid] after swap
                case 2:
                    ArrayUtil.swap(a, mid, last);
                    last--;
                    break;
                default:
                    throw new IllegalArgumentException("Elements must be 0, 1, 2");
            }
        }
    }
}

class SeparateZeroWithOrder
{
    /*
     * Problem4: move zero to front/back, keep other non-zero elements in order
     *
     * Use pointer nextIndex to monitor the next
     * available position for non zeros, we scan from back
     * to front in order to make elements in place. The
     * worst case happens when no zeros exist
     */
    public static void moveZeroToEnd(int[] a)
    {
        int nextAvailIndex=0;
        for(int i=0; i<a.length; i++)
        {
            if(a[i]!=0)
            {
                a[nextAvailIndex]=a[i];
                nextAvailIndex++;
            }
        }
        for(; nextAvailIndex<a.length; nextAvailIndex++)
            a[nextAvailIndex]=0;
    }
    public static void moveZeroToFront(int[] a)
    {
        int nextAvailIndex=a.length-1;
        for(int i=a.length-1; i>=0; i--)
        {
            if(a[i]!=0)
            {
                a[nextAvailIndex]=a[i];
                nextAvailIndex--;
            }
        }
        for(; nextAvailIndex>=0; nextAvailIndex--)
            a[nextAvailIndex]=0;
    }
}


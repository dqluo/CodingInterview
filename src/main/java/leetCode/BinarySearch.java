package leetCode;

/**
 * User: xinyuwan, Date: 12/15/13, Time: 2:43 PM
 */
public class BinarySearch
{

}

class SearchInsertionPosition
{
    /*
     * Problem: Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
     * You may assume no duplicates in the array.
     * Here are few examples.
     * [1,3,5,6], 5 → 2
     * [1,3,5,6], 2 → 1
     * [1,3,5,6], 7 → 4
     * [1,3,5,6], 0 → 0
     */
    public int searchInsert(int[] A, int target)
    {
        int first=0, last=A.length-1;
        while(first <= last)
        {
            int mid=(first+last)/2;
            if(A[mid]==target)
                return mid;
            else if(A[mid] > target)
                last=mid-1;
            else
                first=mid+1;
        }
        /*
         * If we reach here, target is not in the array
         * 1. if target is greater than A[mid] in the previous iteration,
         * first=mid+1=first+1(because first==last), and when we step out of
         * the iteration, first is the index we want to put the targe;
         * 2. if target is smaller than A[mid], last=mid-1=last-1=first-1, and
         * we still return first as the index to put target
         */
        return first;
    }
}

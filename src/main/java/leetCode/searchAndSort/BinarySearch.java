package leetCode.searchAndSort;

/**
 * User: xinyuwan, Date: 12/15/13, Time: 2:43 PM
 */
public class BinarySearch {
    /*
     * Total problems: 5
     */
    public static void main(String[] args) {
        //test SearchInsertionPosition
        SearchInsertionPosition sip = new SearchInsertionPosition();
        int[] a1 = {1, 3, 5, 6};
        for (int i = 0; i <= 7; i++)
            System.out.println("Insertion position for " + i + " is " + sip.searchInsert(a1, i));

        //test SearchInRange
        int[] a2 = {5, 7, 7, 8, 8, 10};
        SearchForRange sfr = new SearchForRange();
        int[] result = sfr.searchRange(a2, 8);
        System.out.println("Search range for 8: " + result[0] + ", " + result[1]);

        //test SearchInRotatedSortedArray
        int[] b1 = {5, 9, 11, 13, 1, 2, 3, 4};
        SearchInRotatedSortedArray sirs = new SearchInRotatedSortedArray();
        System.out.println("Result of searching 3: " + sirs.search(b1, 3));
        System.out.println("Result of searching 8: " + sirs.search(b1, 8));

        //test SearchInRotatedSortedArray2
        int[] b2 = {5, 6, 9, 2, 5, 5, 5, 5, 5};
        SearchInRotatedSortedArray2 sirs2 = new SearchInRotatedSortedArray2();
        System.out.println("Result of searching 9: " + sirs2.search(b2, 9));
        System.out.println("Result of searching 1: " + sirs2.search(b2, 1));

    }
}

class SearchInsertionPosition {
    /*
     * Problem: Given a sorted array and a target value, return the index if the target is found.
     * If not, return the index where it would be if it were inserted in order.
     * You may assume no duplicates in the array.
     * Here are few examples.
     * [1,3,5,6], 5 → 2
     * [1,3,5,6], 2 → 1
     * [1,3,5,6], 7 → 4
     * [1,3,5,6], 0 → 0
     */
    public int searchInsert(int[] A, int target) {
        int first = 0, last = A.length - 1;
        while (first <= last) {
            int mid = (first + last) / 2;
            if (A[mid] == target)
                return mid;
            else if (A[mid] > target)
                last = mid - 1;
            else
                first = mid + 1;
        }
        /*
         * If we reach here, target is not in the array
         * 1. if target is greater than A[mid] in the previous iteration,
         * first=mid+1=first+1=last+1, and when we step out of
         * the iteration, first is the index we want to put the target;
         * 2. if target is smaller than A[mid], last=mid-1=last-1=first-1, and
         * we still return first as the index to put target (because first is the position of A[mid],
         * we should return a valid position in the array)
         */
        return first;
    }
}

class SearchInRotatedSortedArray {
    /*
     * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
     * You are given a target value to search. If found in the array return its index, otherwise return -1.
     * You may assume no duplicate exists in the array.
     */
    public int search(int[] A, int target) {
        return search(A, target, 0, A.length - 1);
    }

    public int search(int[] A, int target, int first, int last) {
        if (first > last)
            return -1;
        int mid = (first + last) / 2;
        if (target == A[mid])
            return mid;
        //A[mid] is in the first half before pivot
        if (A[mid] > A[first]) {
            if (target == A[first])
                return first;
            else if (target > A[first] && target < A[mid])
                return search(A, target, first, mid - 1);
            else
                return search(A, target, mid + 1, last);
        }
        //A[mid] is in the second half between pivot and last
        else {
            if (target == A[last])
                return last;
            else if (target < A[last] && target > A[mid])
                return search(A, target, mid + 1, last);
            else
                return search(A, target, first, mid - 1);
        }
    }
}

class SearchInRotatedSortedArray2 {
    /*
     * Problem : Follow up for "Search in Rotated Sorted Array":
     * What if duplicates are allowed?
     * Would this affect the run-time complexity? How and why?
     * Write a function to determine if a given target is in the array.
     *
     * the only difference is that we cannot say that previous part
     * is ordered when A[mid]==A[first]. So we advance first to find
     * a potential greater element then A[mid]
     */
    public boolean search(int[] A, int target) {
        int first = 0, last = A.length - 1;
        while (first <= last) {
            int mid = (first + last) / 2;
            if (A[mid] == target)
                return true;
            if (A[mid] > A[first]) {
                if (target >= A[first] && target < A[mid])
                    last = mid - 1;
                else
                    first = mid + 1;
            } else if (A[mid] < A[first]) {
                if (target <= A[last] && target > A[mid])
                    first = mid + 1;
                else
                    last = mid - 1;
            }
            /* when A[mid]==A[first], there are two cases:
             * 1. no dups in array: since target!=A[mid], we can only advance first to check A[last](last=first+1)
             * 2. dups in array: e.g. [5, 6, 9, 2, 5, 5, 5, 5, 5], mid=4, A[mid]=A[first]=5, but we cannot advance
             * first to be mid+1, since the actual possible sequence is between first and mid. In this case, we need to
             * shrink the range be advance first one by one, until we can get a mid that makes A[mid]!=A[first]
             */
            else
                first++;
        }
        return false;
    }
}

class SearchForRange {
    /*
     * Problem: Given a sorted array of integers, find the starting and ending position of a given target value.
     * Your algorithm's runtime complexity must be in the order of O(log n).
     * If the target is not found in the array, return [-1, -1].
     * For example,
     * Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
     *
     * Analysis: find the index of target first, then scan through left and right until finding the first
     * element that is not equal to tartget. If target only appears once, start and end is the same
     */
    public int[] searchRange(int[] A, int target) {
        int first = 0, last = A.length - 1, mid = 0;
        while (first <= last) {
            mid = (first + last) / 2;
            if (target == A[mid])
                break;
            else if (target < A[mid])
                last = mid - 1;
            else
                first = mid + 1;
        }
        int[] result = new int[2];
        if (first > last) {
            result[0] = -1;
            result[1] = -1;
        } else {
            int i = mid - 1;
            while (i >= 0 && A[i] == target)
                i--;
            result[0] = i + 1;
            i = mid + 1;
            while (i <= A.length - 1 && A[i] == target)
                i++;
            result[1] = i - 1;
        }
        return result;
    }
}

class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int A[], int B[]) {
        int total = A.length + B.length;
        if (total % 2 == 1)
            return findMedianSortedArrays(A, 0, A.length - 1, B, 0, B.length - 1, total / 2 + 1);
        else {
            return (findMedianSortedArrays(A, 0, A.length - 1, B, 0, B.length - 1, total / 2) +
                    findMedianSortedArrays(A, 0, A.length - 1, B, 0, B.length - 1, total / 2 + 1)) / 2;
        }
    }

    public double findMedianSortedArrays(int A[], int aStart, int aEnd, int B[], int bStart, int bEnd, int k) {
        int aLength = aEnd - aStart + 1;
        int bLength = bEnd - bStart + 1;
        if (aLength > bLength)
            return findMedianSortedArrays(B, bStart, bEnd, A, aStart, aEnd, k);
        if (aLength == 0)
            return (double) B[bStart + k - 1];
        if (k == 1)
            return (double) Math.min(A[aStart], B[bStart]);
        int pa = Math.min(k / 2, aLength);
        int pb = k - pa;
        if (A[aStart + pa - 1] < B[bStart + pb - 1])
            return findMedianSortedArrays(A, aStart + pa, aEnd, B, bStart, bEnd, k - pa);
        else if (A[aStart + pa - 1] > B[bStart + pb - 1])
            return findMedianSortedArrays(A, aStart, aEnd, B, bStart + pb, bEnd, k - pb);
        else
            return (double) A[aStart + pa - 1];
    }
}

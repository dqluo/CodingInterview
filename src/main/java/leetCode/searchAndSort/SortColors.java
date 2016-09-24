package leetCode.searchAndSort;

import util.ArrayUtil;

/**
 * User: xinyuwan, Date: 12/17/13, Time: 4:29 PM
 */
public class SortColors {
    public static void main(String[] args) {
        int[] array = {2, 2, 1, 0, 1, 0, 0, 1, 2};
        SortColors sc = new SortColors();
        sc.sortColors(array);
        System.out.print("After sorting: ");
        ArrayUtil.print(array);
    }

    /*
     * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     * Note: You are not suppose to use the library's sort function for this problem.
     */
    public void sortColors(int[] A) {
        int first = 0, mid = 0, last = A.length - 1;
        while (mid <= last) {
            if (A[mid] == 0) {
                swap(A, mid, first);
                mid++;
                first++;
            } else if (A[mid] == 1)
                mid++;
            else {
                swap(A, mid, last);
                last--;
            }
        }
    }

    private void swap(int[] a, int i, int j) {
        if (i == j)
            return;
        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];
    }
}

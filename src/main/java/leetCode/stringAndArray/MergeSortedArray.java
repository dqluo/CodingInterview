package leetCode.stringAndArray;

import util.ArrayUtil;

/**
 * User: xinyuwan, Date: 1/18/14, Time: 6:46 PM
 */
public class MergeSortedArray
{
    public static void main(String[] args)
    {
        int[] A={1, 8, 21, 23, 30, 0, 0, 0, 0, 0, 0};
        int[] B={3, 15, 17, 22, 31, 49};
        MergeSortedArray msa=new MergeSortedArray();
        msa.merge(A, 5, B, 6);
        ArrayUtil.print(A);
    }
    /*
     * Problem: Given two sorted integer arrays A and B, merge B into A as one sorted array.
     * You may assume that A has enough space to hold additional elements from B.
     * The number of elements initialized in A and B are m and n respectively.
     */
    public void merge(int A[], int m, int B[], int n)
    {
        int i=m-1, j=n-1, index=m+n-1;
        while(i>=0 && j>=0)
        {
            if(A[i]>B[j])
            {
                A[index]=A[i];
                i--;
            }
            else
            {
                A[index]=B[j];
                j--;
            }
            index--;
        }
        while(j>=0)
        {
            A[index]=B[j];
            j--;
            index--;
        }
    }
}

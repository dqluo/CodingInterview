package leetCode.stringAndArray;

import leetCode.ADT.ListNode;
import util.ArrayUtil;

import java.util.ArrayList;
import java.util.HashMap;

import static leetCode.ADT.ListNode.createList;
import static leetCode.ADT.ListNode.printList;

/**
 * User: xinyuwan, Date: 12/14/13, Time: 2:52 PM
 */
public class RemoveArray
{
    /*
     * Total problems: 3
     */
    public static void main(String[] args)
    {
        //test RemoveDupFromSortedArray
        int[] a={1, 1, 2, 3, 3, 3};
        RemoveDupFromSortedArray rsa=new RemoveDupFromSortedArray();
        System.out.println("After removing dup, the array's length is: " + rsa.removeDuplicates(a));
        ArrayUtil.print(a);

        //test RemoveDupFromSortedArray2
        int[] a2={1, 1, 1, 1, 2, 2};
        RemoveDupFromSortedArray2 rsa2=new RemoveDupFromSortedArray2();
        System.out.println("After removing dup more than 2, the array's length is: " + rsa2.removeDuplicates(a));
        ArrayUtil.print(a);

        //test RemoveElement
        int[] a3={6, 9, 1, 4, 7, 6, 8, 1, 1, 2, 1};
        RemoveElement re=new RemoveElement();
        System.out.println("After removing 1, the array's length is: "+re.removeElement(a3, 1));
        ArrayUtil.print(a3);

    }
}

class RemoveDupFromSortedArray
{
    /*
     * Problem:Given a sorted array, remove the duplicates in place such that
     * each element appear only once and return the new length.
     * Do not allocate extra space for another array, you must do this in place with constant memory.
     * For example,
     * Given input array A = [1,1,2],
     * Your function should return length = 2, and A is now [1,2].
     */
    public int removeDuplicates(int[] A)
    {
        if(A.length==0)
            return 0;
        /*
         * Use index to mark the position of the last unique element,
         * and while iterating through the array, check the current with
         * previous if they are dup. If not, we find the next unique element
         * and therefore, we need to advance index and copy that element to index.
         * One extreme situation is that every element in A is unique. In this
         * case, we just copy every element to its index since index and i are updating
         * at the same time.
         */
        int index=0;
        for(int i=1; i<A.length; i++)
        {
            if(A[i]!=A[i-1])
            {
                index++;
                A[index]=A[i];
            }
        }
        return index+1;
    }
}

class RemoveDupFromSortedArray2
{
    /*
     * Problem: Follow up for "Remove Duplicates": What if duplicates are allowed at most twice?
     * For example, Given sorted array A = [1,1,1,2,2,3],
     * Your function should return length = 5, and A is now [1,1,2,2,3].
     */
    public int removeDuplicates(int[] A)
    {
        if(A.length==0)
            return 0;
        /*
         * The basic logic is the same. We still use index to mark
         * the index of last unique one, the difference is that we
         * advance index and copy A[i] when A[i]==A[i-1]. This is because
         * when there are more than 2 dups, like 1, 1, 1, 2, 2, and when
         * index is at position 2 and we copy element 2 to index, i++ but index
         * stays at 2. This time since 2 is dup, and we need the 2 at position 4,
         * we copy that to position 3.
         */
        int index=0;
        for(int i=1; i<A.length;)
        {
            if(A[i]==A[i-1])
            {
                index++;
                //this step is crucial
                A[index]=A[i];
                while(i<A.length && A[i]==A[i-1])
                    i++;
            }
            else
            {
                index++;
                A[index]=A[i];
                i++;
            }
        }
        return index+1;
    }
}

class RemoveElement
{
    /*
     * Problem: Given an array and a value, remove all instances of that value in place and return the new length.
     * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
     */
    public int removeElement(int[] A, int elem)
    {
        int lastIndex=A.length-1;
        for(int i=0; i<=lastIndex;)
        {
            if(A[i]==elem)
            {
                A[i]=A[lastIndex];
                lastIndex--;
            }
            else
                i++;
        }
        return lastIndex+1;
    }
}


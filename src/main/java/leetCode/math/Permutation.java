package leetCode.math;

import util.ArrayUtil;

import java.util.Arrays;

/**
 * User: xinyuwan, Date: 1/4/14, Time: 6:28 PM
 */
public class Permutation
{
    /*
     * Total problems: 2
     */
    public static void main(String[] args)
    {
        //test next permutation
        int[] num={1, 2, 4, 3, 1};
        int[] num2={5, 4, 3, 2};
        NextPermutation np=new NextPermutation();
        np.nextPermutation(num);
        ArrayUtil.print(num);
        np.nextPermutation(num2);
        ArrayUtil.print(num2);

        //test permutation sequence
        PermutationSequence ps=new PermutationSequence();
        for(int i=1; i<=24; i++)
        {
            System.out.println("Permutation "+i+": "+ps.getPermutation(4, i));
        }
    }
}

/*
 * Problem: Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place, do not allocate extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 */
class NextPermutation
{
    /*
     * Problem: Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
     * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
     * The replacement must be in-place, do not allocate extra memory.
     * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     *
     */
    public void nextPermutation(int[] num)
    {
        if(num.length <= 1)
            return;
        //find the first number after an increasing sequence from the end
        int indexOfSmaller=num.length-2;
        while(indexOfSmaller >= 0 && num[indexOfSmaller] >= num[indexOfSmaller+1])
            indexOfSmaller--;
        //The num is the last permutation, we need to return the first one by sorting the array
        if(indexOfSmaller < 0)
        {
            Arrays.sort(num);
            return;
        }
        //we have found the number to move to back, we need to find the first number that is larger than it from back
        int indexofNextLarger=num.length-1;
        while(indexofNextLarger > indexOfSmaller && num[indexofNextLarger] <= num[indexOfSmaller])
            indexofNextLarger--;
        swap(num, indexofNextLarger, indexOfSmaller);
        //Now we need to find the first permutation sequence starting with the just swapped number;
        //we can do this by reversing the sequence(decreasing) after that number
        reverse(num, indexOfSmaller+1, num.length-1);
    }
    private void swap(int[] a, int i, int j)
    {
        if(i==j)
            return;
        a[i]=a[i]^a[j];
        a[j]=a[i]^a[j];
        a[i]=a[i]^a[j];
    }
    private void reverse(int[] a, int start, int end)
    {
        if(start>=end)
            return;
        for(int i=start, j=end; i<j; i++, j--)
            swap(a, i, j);
    }
}

class PermutationSequence
{
    /*
     * Problem: The set [1,2,3,…,n] contains a total of n! unique permutations.
     * By listing and labeling all of the permutations in order,
     * We get the following sequence (ie, for n = 3):
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * Given n and k, return the kth permutation sequence.
     * Note: Given n will be between 1 and 9 inclusive.
     *
     * Analysis: consider the sequence below
     * 1 2 3
     * 1 3 2
     * 2 1 3
     * 2 3 1
     * 3 1 2
     * 3 2 1
     * for each number 1, 2, 3, we have 2 sequence starting with each. This is because n=3 and (n-1)!=2
     * So we can locate the first index by k/(n-1)!. Assume k=3, 3/2=1, which means we need to pick up
     * num[1]=2 as the first number. In other words, we've located block with index 1. Next which sequence
     * in block1 should we pick? we pick k%(n-1)=3%2=1. We pick num[1]=3. Note we need to shift the elements
     * after 2 one by one. We repeat this process and construct our permutation sequence.
     *
     */
    public String getPermutation(int n, int k)
    {
        //create an array for 1~n, we need to pick number based on index
        int[] num=new int[n];
        for(int i=0; i<n; i++)
            num[i]=i+1;
        StringBuilder sb=new StringBuilder();
        //only compute factorial once
        int fact=factorial(n);
        //convert k to index
        k--;
        for(int i=0; i<n; i++)
        {
            fact=fact/(n-i);
            int index=k/fact;
            sb.append(num[index]);
            //every time we pick a number out, we need to shift the array
            shift(num, index);
            //k is the index for next iteration(in the block we located in this iteration)
            k=k%fact;
        }
        return sb.toString();
    }
    private int factorial(int n)
    {
        if(n<=1)
            return 1;
        int result=1;
        while(n > 1)
        {
            result*=n;
            n=n-1;
        }
        return result;
    }
    private void shift(int[] a, int index)
    {
        for(int i=index; i<a.length-1; i++)
            a[i]=a[i+1];
    }
}
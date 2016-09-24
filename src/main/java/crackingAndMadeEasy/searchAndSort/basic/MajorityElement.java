package crackingAndMadeEasy.searchAndSort.basic;

/**
 * User: xinyuwan, Date: 11/30/13, Time: 7:32 PM
 */
public class MajorityElement
{
    public static void main(String[] args)
    {
        //Test majority()
        int[] a={5, 5, 9, 4, 4, 5, 4, 5, 5, 5, 1, 5};
        System.out.println(FindMajorityMoreThanHalf.majority(a));
        int[] b1={5, 9, 3, 9, 2, 9, 4, 9, 8, 9, 6, 9};
        int[] b2={5, 3, 2, 4, 9, 9, 9, 9, 8, 9, 6, 9};
        System.out.println(FindMajorityIn2N.majorityIn2N(b1));
        System.out.println(FindMajorityIn2N.majorityIn2N(b2));
    }
}

class FindMajorityMoreThanHalf
{
    /*
     * Problem1: find the majority element in array.
     * The majority element is the the one that occurs more than
     * n/2 of the array
     */
    //Use count as cursor to find the element repeating over n/2 times
    public static int majority(int[] a)
    {
        int count=0, result=0;
        for(int i=0; i<a.length; i++)
        {
            if(count==0)
            {
                result=a[i];
                count++;
            }
            //count>0 && element is the same
            else if(a[i]==result)
                count++;
                //count>0 && element is not the same
            else
                count--;
        }
        //if count>=1 than majority exists, else no such element
        return count>=1? result:Integer.MIN_VALUE;
    }
}

class  FindMajorityIn2N
{
    /*
     * Problem2: find the majority element in the array with 2n elements.
     * The majority element is the one that occurring n times, the other
     * n elements are  all different
     */
    public static int majorityIn2N(int[] a)
    {
        int i=0;
        //case1: check if the array is like this: 1, 1, 2, 3, 4, 1...
        //this case is the opposite one to the second one
        for(; i<a.length-1; i++)
        {
            if(a[i]==a[i+1])
                return a[i];
        }
        //case2: if we get here, the array must be like 1, 3, 1, 4, 1, 5
        for(i=0; i<a.length-2; i++)
        {
            if(a[i]==a[i+2])
                return a[i];
        }
        return Integer.MIN_VALUE;
    }
}

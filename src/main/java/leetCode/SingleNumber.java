package leetCode;

/**
 * User: xinyuwan, Date: 12/15/13, Time: 10:05 PM
 */
public class SingleNumber
{
    public static void main(String[] args)
    {
        int[] a={1, 3, 3, 3, 2, 2, 2};
        SingleNumber2 sn2=new SingleNumber2();
        System.out.println(sn2.singleNumber(a));
    }
}

class SingleNumber2
{
    /*
     * Problem: Given an array of integers, every element appears three times except for one. Find that single one.
     * Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     *
     * The logic of this problem is:
     * 1. we use an array bits to store how many times a bit 1 appears at which position of all elements in array
     * e.g. A={0001, 0011, 0011, 0011}, then for position 0(from right to left), bit 1 appears 4 times, so bits[0]=4,
     *
     * 2. the difference between the element and others is that at certain bit position, bit 1 appears 3 times for
     * others, but only 1 time for that element if it has bit 1 at that position. So if bits[i]%3==1, then the 1 must comes
     * from that element, which means that that element has bit 1 at i(otherwise is 0).
     *
     * 3. if we iterate through every bit for every element in the array, and calucate for bits[i], we'll construct the number
     * finally by getting every bit 1 in that number.
     */
    public int singleNumber(int[] A)
    {
        int result=0;
        int[] bits=new int[32];
        for(int i=0; i<32; i++)
        {
            for(int j=0; j<A.length; j++)
            {
                if((A[j]>>i & 1)== 1)
                    bits[i]+=1;
            }
            result+=bits[i]%3<<i;
        }
        return result;
    }
}

package leetCode.gimmick;

/**
 * User: xinyuwan, Date: 12/31/13, Time: 5:40 PM
 */
public class Candy
{
    public static void main(String[] args)
    {
        int[] ratings={4, 9, 2, 1, 8, 4, 10};
        Candy c=new Candy();
        System.out.println("Min candies: "+ c.candy(ratings));
    }
    /*
     * Problem: There are N children standing in a line. Each child is assigned a rating value.
     * You are giving candies to these children subjected to the following requirements:
     * Each child must have at least one candy.
     * Children with a higher rating get more candies than their neighbors.
     * What is the minimum candies you must give?
     *
     * Analysis: the key of this problem is to ensure the current element has more candies than its
     * left and right. So we can do this by traversing from the left first to calculate a relative
     * number of candies for each element. We will add 1 to the candy number of the left element when
     * the current is larger than left. We will do this for the right pass also, the only difference
     * is that we compare the number of the candies for the current element first, and put the larger
     * of result[i] and result[i+1]+1 into result[i] to ensure result[i] is larger than both sides
     */
    public int candy(int[] ratings)
    {
        int n=ratings.length;
        if(n==0)
            return 0;
        //go from left first
        int[] result=new int[n];
        for(int i=1; i<n; i++)
        {
            if(ratings[i] > ratings[i-1])
                result[i]=result[i-1]+1;
        }
        //go from right
        for(int i=n-2; i>=0; i--)
        {
            if(ratings[i] > ratings[i+1])
                result[i]=Math.max(result[i], result[i+1]+1);
        }
        //now calculate the total, starting from at least n candies.
        int min=n;
        for(int i=0; i<n; i++)
            min+=result[i];
        return min;
    }
}

package postedLinkedInQuestions;

/**
 * careercup. Find the maximum sum subset in an array with negative integers
 */
public class findMaxSumSubset {

    public static int findMaxSum(int[] array){
        int sum=0;
        int maxSum=Integer.MIN_VALUE;
        for(int i=0;i<array.length;i++){
            sum=sum+array[i];
            maxSum=Math.max(maxSum, sum);
            if(sum<0)
                sum=0;
        }
        return maxSum;
    }
}

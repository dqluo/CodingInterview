package postedLinkedInQuestions;

/**
 * Q24. Find longest sub-array with sum to K
 */
public class LongestSubarrayWithSumK {

    public static int find(int[] array, int k){
        int start=0;
        int sum=0;
        int result=Integer.MIN_VALUE;
        for(int i=0;i<array.length;i++){
            sum=sum+array[i];
            if(sum==k)
                result=Math.max(i-start+1,result);
            else {
                while (start < i && sum > k) {
                    sum = sum - array[start];
                    start++;
                }
                if (sum == k)
                    result = Math.max(i - start + 1, result);
            }
        }
        return result;
    }

    public static void main(String args[]){
        int[] array={2,3,5,1,1,1,1,4,7,9,2};
        System.out.println(find(array, 8));
    }
}

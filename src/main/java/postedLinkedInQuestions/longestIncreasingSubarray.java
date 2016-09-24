package postedLinkedInQuestions;

/**
 * Q22. Longest increasing sub-array?  O(n), better than O(n)
 */
public class longestIncreasingSubarray {

    public static int longestIncreasingSubarray(int[] array){
        int[] result=new int[array.length];
        result[0]=1;
        for(int i=1;i<array.length;i++){
            if(array[i]>array[i-1])
                result[i]=result[i-1]+1;
            else
                result[i]=1;
        }
        return result[result.length-1];
    }

    public static void main(String args[]){
        int[] array={2,3,4,5,1,2,3,5,6,7,8};
        System.out.println(longestIncreasingSubarray(array));
    }
}

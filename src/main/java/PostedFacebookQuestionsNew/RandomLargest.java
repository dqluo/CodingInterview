package PostedFacebookQuestionsNew;

/**
 * 一个数组，找最大数，可能有重复，要求random输出最大index，比如
 * [ 1 2 3 4 5 6 6 6],最大的是6，index可能是5，6，7。每次call
 * 这个function的时候，random输出5，6，7.
 */
public class RandomLargest {

    public static int randomLargest(int[] number){
        int[] index=new int[number.length];
        int max=Integer.MIN_VALUE;
        int largestIndex=0;
        for(int i=0;i<number.length;i++){
            if(number[i]>max){
                max=number[i];
                largestIndex=0;
                index[largestIndex]=i;
                largestIndex++;
            }else if(number[i]==max){
                index[largestIndex]=i;
                largestIndex++;
            }
        }
        return index[(int)(Math.random()*largestIndex)];
    }

    public static void main(String[] args){
        int[] number={1,2,3,4,5,6,6,6};
        for(int i=0;i<6;i++)
            System.out.println(randomLargest(number));
    }
}

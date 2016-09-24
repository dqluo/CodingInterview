package TwitterOnlineAssessment;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-3-14
 * Time: 下午2:32
 * To change this template use File | Settings | File Templates.
 */
public class NumberOfPairs {
    static final int BOUNDARY=1000000000;
    public static int numberOfPairs(int[] array){
        long numberOfOdds=0, numberOfEvens=0;
        for(int i=0;i<array.length;i++)
        {
            if(array[i]%2==0)
                numberOfEvens++;
            else
                numberOfOdds++;
        }
        if(numberOfEvens>BOUNDARY || numberOfOdds>BOUNDARY)
            return -1;
        long result=0;
        if(numberOfEvens>0)
            result+=numberOfEvens*(numberOfEvens-1)/2;
        if(result>BOUNDARY)
            return -1;
        if(numberOfOdds>0)
            result+=numberOfOdds*(numberOfOdds-1)/2;
        if(result>BOUNDARY)
            return -1;
        return (int)result;
    }

    public static void main(String[] args){
        int[] array1={2,1,5,-6,9};
        int[] array2={1,3,5,7,9,11};
        int[] array3={2,4,6,8,10};
        System.out.println(numberOfPairs(array1));
        System.out.println(numberOfPairs(array2));
        System.out.println(numberOfPairs(array3));
    }
}

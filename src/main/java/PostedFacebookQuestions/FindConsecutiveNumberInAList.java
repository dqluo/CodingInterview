package PostedFacebookQuestions;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-17
 * Time: 下午1:36
 * To change this template use File | Settings | File Templates.
 */
public class FindConsecutiveNumberInAList {

    public static int[] find(int[] array, int k)
    {
        int start=0;
        int end=0;
        int sum=array[0];
        int[] result=new int[2];
        while(end<array.length)
        {
            if(sum==k)
            {
                result[0]=start;
                result[1]=end;
                return result;
            }
            else if(sum<k)
            {
                end++;
                if(end<array.length)
                    sum+=array[end];
            }
            else
            {
                sum-=array[start];
                start++;
            }
        }
        result[0]=-1;
        result[1]=-1;
        return result;
    }

    public static void main(String[] args)
    {
        int[] array={3,6,1,2,3,7,8,4,5};
        int[] result=find(array, 12);
        System.out.println(result[0]+" "+result[1]);
        result=find(array, 18);
        System.out.println(result[0]+" "+result[1]);
        result=find(array, 3);
        System.out.println(result[0]+" "+result[1]);
    }
}

package PostedFacebookQuestions;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-12
 * Time: 下午1:26
 * To change this template use File | Settings | File Templates.
 */
public class FindMaximumAndMinimum {

    public static Pair findMaxAndMin(int[] array)
    {
        Pair result=new Pair();
        int index;
        if(array.length%2==1)
        {
            if(array.length==1)
            {
                result.max=array[0];
                result.min=array[0];
            }
            index=1;
        }
        else
        {
            if(array[0]>array[1])
            {
                result.max=array[0];
                result.min=array[1];
            }
            else
            {
                result.max=array[1];
                result.min=array[0];
            }
            index=2;
        }
        while(index<array.length-1)
        {
            if(array[index]>array[index+1])
            {
                if(array[index]>result.max)
                    result.max=array[index];
                if(array[index+1]<result.min)
                    result.min=array[index+1];
            }
            else
            {
                if(array[index]<result.min)
                    result.min=array[index];
                if(array[index+1]>result.max)
                    result.max=array[index+1];
            }
            index=index+2;
        }
        return result;
    }

    public static void main(String[] args)
    {
        int[] array={2,9,6,4,3,1,5,7,8,10};
        Pair result=findMaxAndMin(array);
        System.out.println("max: "+result.max);
        System.out.println("min: "+result.min);
    }
}

class Pair{
    public int max;
    public int min;
}

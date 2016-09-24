package PostedFacebookQuestions;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-17
 * Time: 上午11:53
 * To change this template use File | Settings | File Templates.
 */
public class RobHouseWithLargestAmountOfMoney {

    public static int robHouse(int[] array)
    {
        int[] cache=new int[array.length];
        if(array.length==1)
            return array[0];
        if(array.length==2)
            return Math.max(array[0],array[1]);
        cache[0]=array[0];
        cache[1]=array[1];
        for(int i=2;i<array.length;i++)
        {
            cache[i]=Math.max(cache[i-2]+array[i], cache[i-1]);
        }
        return cache[array.length-1];
    }

    public static void main(String[] args){
        int[] array={1,2,3,4,5,6,7,8,9};
        System.out.println(robHouse(array));
    }
}

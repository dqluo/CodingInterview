package PostedFacebookQuestions;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-17
 * Time: 下午2:49
 * To change this template use File | Settings | File Templates.
 */
public class RemoveDuplicate {

    public static ArrayList<Integer> remove(int[] array, int k){
        ArrayList<Integer> result=new ArrayList<Integer>();
        if(array.length==0)
            return result;
        result.add(array[0]);
        int number=array[0];
        int index=0;
        for(int i=1;i<array.length;i++)
        {
            if(array[i]==number && i-index<k)
                result.add(array[i]);
            else if(array[i]!=number)
            {
                number=array[i];
                index=i;
                result.add(array[i]);
            }
        }
        return result;
    }

    public static void main(String[] args)
    {
        int[] array={1,1,1,3,4,5,6,6,6,6,7,3,3,3,3,2,2,2,2};
        ArrayList<Integer> result=remove(array, 2);
        for(int i=0;i<result.size();i++)
        {
            System.out.print(result.get(i));
        }
    }
}

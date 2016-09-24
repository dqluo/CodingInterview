package PostedFacebookQuestions;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-17
 * Time: 上午7:24
 * To change this template use File | Settings | File Templates.
 */
public class MinimumSetOfNumber {

    public static ArrayList<Integer> minimum(int[] array, int k)
    {
        ArrayList<Integer> result=new ArrayList<Integer>();
        return minimum(array, k, 0, array.length-1, result);
    }

    private static ArrayList<Integer> minimum(int[] array, int k, int start, int end, ArrayList<Integer> result)
    {
        int mid=(start+end)/2;
        int leftEnd=pivotInArray(array, start, end, array[mid]);
        int sum=sum(array, start, leftEnd);
        if(sum<=k)
        {
            for(int i=start;i<=leftEnd;i++)
                result.add(array[i]);
        }
        if(sum==k || sum-array[leftEnd]<k)
            return result;
        else if(sum>k)
        {
            return minimum(array, k, start, leftEnd, result);
        }
        else
            return minimum(array, k-sum, leftEnd+1, end, result);
    }

    private static int pivotInArray(int[] array, int start, int end, int pivot)
    {
        while(start<=end)
        {
            while(start<=end && array[start]>pivot)
                start++;
            while(start<=end && array[end]<=pivot)
                end--;
            if(start>end)
                return start-1;
            swap(array, start, end);
        }
        return start-1;
    }

    private static void swap(int[] array, int start, int end)
    {
        int temp=array[start];
        array[start]=array[end];
        array[end]=temp;
    }

    private static int sum(int[] array, int start, int end)
    {
        int sum=0;
        for(int i=start;i<=end;i++)
        {
            sum+=array[i];
        }
        return sum;
    }

    public static void main(String[] args)
    {
        int[] array={2,5,7,3,4,9,8,10};
        ArrayList<Integer> result=minimum(array, 30);
        for(int i=0;i<result.size();i++)
        {
            System.out.print(result.get(i)+" ");
        }

    }
}

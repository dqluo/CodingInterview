package PostedFacebookQuestions;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-16
 * Time: 下午2:49
 * To change this template use File | Settings | File Templates.
 */
public class RotateArray {

    public static ArrayList<Integer> rotate(ArrayList<Integer> array, int block)
    {
        if(array.size()<block)
            return array;
        reverse(array, 0, array.size()-1);
        reverse(array, 0, block-1);
        reverse(array, block, array.size()-1);
        return array;
    }

    private static void reverse(ArrayList<Integer> array, int start, int end)
    {
        while(start<end)
        {
            int temp=array.get(start);
            array.set(start, array.get(end));
            array.set(end, temp);
            start++;
            end--;
        }
    }

    public static void main(String[] args)
    {
        ArrayList<Integer> array=new ArrayList<Integer>();
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(6);
        rotate(array, 2);
        for(int i=0;i<array.size();i++)
        {
            System.out.print(array.get(i)+" ");
        }
    }
}

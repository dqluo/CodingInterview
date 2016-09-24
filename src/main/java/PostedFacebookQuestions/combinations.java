package PostedFacebookQuestions;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-17
 * Time: 下午2:06
 * To change this template use File | Settings | File Templates.
 */
public class combinations {

    public static ArrayList<ArrayList<Integer>> combinations(int[] array, int sum)
    {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list=new ArrayList<Integer>();
        combinations(result, array, 0, sum, list);
        return result;
    }

    private static void combinations(ArrayList<ArrayList<Integer>> result, int[] array, int index, int sum, ArrayList<Integer> list)
    {
        if(sum<0)
            return;
        if(sum==0)
        {
            ArrayList<Integer> tempList=new ArrayList<Integer>(list);
            result.add(tempList);
            return;
        }
        for(int i=index;i<array.length;i++){
            list.add(array[i]);
            combinations(result, array, i+1, sum-array[i], list);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args)
    {
        int[] array={2,5,6,7,8,9,3,4,10};
        ArrayList<ArrayList<Integer>> result=combinations(array, 10);
        for(int i=0;i<result.size();i++)
        {
            for(int j=0;j<result.get(i).size();j++)
            {
                System.out.print(result.get(i).get(j));
            }
            System.out.println();
        }
    }
}

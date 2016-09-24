import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-3-16
 * Time: 上午6:30
 * To change this template use File | Settings | File Templates.
 */
public class SumOfTheWindow {
    /*
     * Assumption: if the window size is larger than the size of the list, just return the sum of all the elements in the array.
     * Algorithm: 1. calculate the first sum of the elements within the window size
     *            2. for the follwing elements, the sum of the elements within the window size can use the formular:
     *               sum(i)=sum(i-1)-list.get(i-window)+list.get(i);
     * Time Complexity: O(n)
     *                  The best case would be O(1), since if there is only one element in the list, the we just need
     *                  to return that element.
     *                  The worst case would be O(n), since we need to go through the list for once.
     */
    public static List<Integer> calculateWindowSums(List<Integer> list, int windowSize) {
        List<Integer> result= new ArrayList <Integer>();
        int temp=0;
        //if the size of the list is less than the size of the window, just calculate the sum
        //of the elements in the list and return it.
        if(list.size()<=windowSize)
        {
            for(int i=0;i<list.size();i++)
                temp+=list.get(i);
            result.add(temp);
            return result;
        }
        //calcualte the sum of the elements in the first window
        for(int i=0;i<windowSize;i++)
        {
            temp+=list.get(i);
        }
        result.add(temp);
        //calcualte the sum of the elements in the following window
        for(int i=windowSize;i<list.size();i++)
        {
            temp=temp-list.get(i-windowSize)+list.get(i);
            result.add(temp);
        }
        return result;
    }

    public static void main(String[] args){
        List<Integer> list=new ArrayList<Integer>();
        list.add(4);
        list.add(2);
        list.add(73);
        list.add(11);
        list.add(-5);
        List<Integer> result=calculateWindowSums(list, 2);
        for(int i=0;i<result.size();i++)
        {
            System.out.print(result.get(i)+" ");
        }
        result=calculateWindowSums(list, 3);
        System.out.println();
        for(int i=0;i<result.size();i++)
        {
            System.out.print(result.get(i)+" ");
        }
        result=calculateWindowSums(list, 6);
        System.out.println();
        for(int i=0;i<result.size();i++)
        {
            System.out.print(result.get(i)+" ");
        }
    }
}

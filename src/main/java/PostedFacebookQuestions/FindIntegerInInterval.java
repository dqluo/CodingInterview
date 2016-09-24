package PostedFacebookQuestions;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-16
 * Time: 下午5:16
 * To change this template use File | Settings | File Templates.
 */
public class FindIntegerInInterval {

    public static boolean findInteger(ArrayList<Interval> list, int number)
    {
        Collections.sort(list, new IntervalComparator());
        return findInteger(list, number, 0, list.size()-1);
    }

    public static boolean findInteger(ArrayList<Interval> list, int number, int start, int end)
    {
        if(start>end)
            return false;
        int mid=(start+end)/2;
        Interval midInterval=list.get(mid);
        if(number>=midInterval.start && number<=midInterval.end)
        {
            return true;
        }
        if(number<midInterval.start)
            return findInteger(list, number, start, mid-1);
        else
            return findInteger(list, number, mid+1, end);
    }

    public static void main(String[] args)
    {
        ArrayList<Interval> list=new ArrayList<Interval>();
        list.add(new Interval(1,2));
        list.add(new Interval(4,5));
        list.add(new Interval(7,8));
        list.add(new Interval(10,11));
        list.add(new Interval(13,14));
        list.add(new Interval(16,18));
        list.add(new Interval(21,24));
        list.add(new Interval(27,30));
        System.out.print(findInteger(list, 12));
        System.out.print(findInteger(list, 17));
    }
}

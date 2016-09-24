package PostedFacebookQuestions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-13
 * Time: 上午11:20
 * To change this template use File | Settings | File Templates.
 */
public class MaximumInterval {
    public static int maximumInterval(ArrayList<Interval> list)
    {
        Collections.sort(list, new IntervalComparator());
        int start=0;
        int end=0;
        int max=Integer.MIN_VALUE;
        while(end<list.size())
        {
            while(end<list.size() && list.get(end).start<list.get(start).end)
                end++;
            if(end-start>max)
                max=end-start;
            while(start<list.size() && end<list.size() && list.get(start).end<list.get(end).start)
                start++;
        }
        return max;
    }

    public static void main(String[] args){
        ArrayList<Interval> list=new ArrayList<Interval>();
        list.add(new Interval(1, 4));
        list.add(new Interval(2, 5));
        list.add(new Interval(2, 6));
        list.add(new Interval(3, 9));
        list.add(new Interval(7, 10));
        list.add(new Interval(8, 11));
        System.out.println(maximumInterval(list));
    }
}

class Interval{
    public int start;
    public int end;
    public Interval(int start, int end)
    {
        this.start=start;
        this.end=end;
    }
}

class IntervalComparator implements Comparator<Interval>
{
    public int compare(Interval a, Interval b)
    {
        return a.start-b.start;
    }
}




//    public ArrayList<Boolean> maxCost(ArrayList<Job> jobs)
//    {
//        return (maxCost(new Solution(jobs))).assignments;
//    }
//
//    private Solution maxCost(Solution solution)
//    {
//        Solution falseSol = solution.assignNext(false);
//        Solution trueSol = solution.assignNext(true);
//        return (trueSol.cost > falseSol.cost) ? trueSol : falseSol;
//    }

//public class Solution
//{
//    public ArrayList<Boolean> assignments;
//    public int cost;
//    public ArrayList<Job> jobs;
//
//    public Solution(ArrayList<Job> jobs)
//    {
//        assignments = new ArrayList<Boolean>();
//        this.jobs = jobs;
//        cost = 0;
//    }
//
//    public Solution assignNext(boolean value)
//    {
//        Solution s = new Solution(jobs);
//        for (Boolean b : assignments)
//        {
//            s.assignments.add(b);
//        }
//        s.cost = cost;
//        s.assignments.add(value);
//        if (value)
//        {
//            cost += jobs.get(s.assignments.size()-1).cost;
//            if (conflict(s))
//            {
//                cost = 0;
//            }
//        }
//        return s;
//    }
//
//    private boolean conflict(Solution s)
//    {
//        Job newlyAdded = jobs.get(s.assignments.size()-1);
//        for (int i = 0; i < s.assignments.size()-1; i++)
//        {
//            if (!s.assignments.get(i)) continue;
//            Job alreadyScheduled = jobs.get(i);
//            if ((alreadyScheduled.start < newlyAdded.start) && (newlyAdded.start < alreadyScheduled.stop)) return true;
//            if ((alreadyScheduled.start < newlyAdded.stop) && (newlyAdded.stop < alreadyScheduled.stop)) return true;
//        }
//        return false;
//    }
//}
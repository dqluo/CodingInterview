package leetCode.gimmick;

import leetCode.ADT.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * User: xinyuwan, Date: 12/25/13, Time: 4:31 PM
 */
public class IntervalManipulation
{
    public static void main(String[] args)
    {
        //test inserting interval
        ArrayList<Interval> intervals=new ArrayList<Interval>();
        intervals.add(new Interval(1, 5));
        intervals.add(new Interval(2, 9));
        intervals.add(new Interval(6, 8));
        Interval newInterval=new Interval(3, 7);
        InsertInterval ii=new InsertInterval();
        System.out.println("Before inserting: "+intervals);
        System.out.println("After inserting: "+ii.insert(intervals, newInterval));

        //test merging interval
        ArrayList<Interval> intervals2=new ArrayList<Interval>();
        intervals2.add(new Interval(2, 6));
        intervals2.add(new Interval(1, 3));
        intervals2.add(new Interval(15, 18));
        intervals2.add(new Interval(7, 11));
        intervals2.add(new Interval(4, 16));
        MergeIntervals mi=new MergeIntervals();
        System.out.println("Before inserting: "+intervals2);
        System.out.println("After inserting: "+mi.merge(intervals2));
    }
}

class InsertInterval
{
    /*
     * Problem: Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
     * You may assume that the intervals were initially sorted according to their start times.
     * Example 1: Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
     * Example 2: Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
     * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
     */
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval)
    {
        if(intervals.isEmpty())
        {
            intervals.add(newInterval);
            return intervals;
        }
        int startPosition=search(intervals, newInterval.start);
        int endPosition=search(intervals, newInterval.end);
        int newStart=0;
        //this means newInterval.start falls in range of [intervals(startPos).start, intervals(startPos).end)]
        if(startPosition>=0 && intervals.get(startPosition).end > newInterval.start)
            newStart=intervals.get(startPosition).start;
        //otherwise newInterval.start should be in startPosition+1
        else
        {
            newStart=newInterval.start;
            startPosition++;
        }
        int newEnd=0;
        //same logic as newInterval.start
        if(endPosition >= 0 && intervals.get(endPosition).end > newInterval.end)
            newEnd=intervals.get(endPosition).end;
        //note we don't advance endPosition because we use startPosition to mark the index we
        //should insert in, and endPosition marks the last position to delete
        else
            newEnd=newInterval.end;
        //Note: remove only at the start position, this is because ArrayList will move
        //elements to lower index as we remove the elements
        for(int i=startPosition; i<=endPosition; i++)
            intervals.remove(startPosition);
        intervals.add(startPosition, new Interval(newStart, newEnd));
        return intervals;
    }
    /*
     * This method returns the position of the specified value(newInterval's start/end)
     * If the value falls in certain interval, than the position is the index of that
     * particular interval, otherwise we return the index of the smaller interval
     */
    private int search(ArrayList<Interval> intervals, int value)
    {
        int first=0, last=intervals.size()-1;
        while(first <= last)
        {
            int mid=(first+last)/2;
            if(value==intervals.get(mid).start)
                return mid;
            else if(value < intervals.get(mid).start)
                last=mid-1;
            else
                first=mid+1;
        }
        //Note, now first>last, we return last, which means we return the smaller interval index
        return last;
    }
}

class MergeIntervals
{
    /*
     * Problem: Given a collection of intervals, merge all overlapping intervals.
     * For example, Given [1,3],[2,6],[8,10],[15,18],
     * return [1,6],[8,10],[15,18].
     *
     * Analysis:
     * First sort the intervals based on start, then for each start in intervals, we need to
     * deal with each interval's end if the end of current is greater than a subsequent interval's start.
     * We find the max end during the interation for each combinable interval sequence, and repeat this process.
     */
    public ArrayList<Interval> merge(ArrayList<Interval> intervals)
    {
        if(intervals.size() <=1)
            return intervals;
        Collections.sort(intervals, new IntervalComparator());
        ArrayList<Interval> result=new ArrayList<Interval>();
        int i=0;
        while(i < intervals.size())
        {
            int j=i+1, end=intervals.get(i).end;
            while(j < intervals.size() &&
                    end >= intervals.get(j).start)
            {
                end=Math.max(end, intervals.get(j).end);
                j++;
            }
            result.add(new Interval(intervals.get(i).start, end));
            i=j;
        }
        return result;
    }
    class IntervalComparator implements Comparator<Interval>
    {
        public int compare(Interval i1, Interval i2)
        {
            if(i1.start < i2.start)
                return -1;
            else if(i1.start > i2.start)
                return 1;
            else
                return 0;
        }
    }
}


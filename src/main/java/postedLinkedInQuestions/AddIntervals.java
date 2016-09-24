package postedLinkedInQuestions;

import leetCode.ADT.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danqin on 9/12/14.
 */
public class AddIntervals {

    List<Interval> intervals=new ArrayList<Interval>();

    public void addIntervals(int from, int to){
        int startInterval=search(intervals, from);
        int endInterval=search(intervals, to);
        Interval newInterval=new Interval();
        if(startInterval>=0 && from<intervals.get(startInterval).end){
            newInterval.start=intervals.get(startInterval).start;
        }else{
            newInterval.start=from;
            startInterval++;
        }
        if(endInterval>=0 && intervals.get(endInterval).end>to)
            newInterval.end=intervals.get(endInterval).end;
        else{
            newInterval.end=to;
        }
        for(int i=startInterval;i<=endInterval;i++)
            intervals.remove(i);
        intervals.add(startInterval, newInterval);
    }

    private int search(List<Interval> intervals, int endPoint){
        int start=0;
        int end=intervals.size();
        while(start<=end){
            int mid=(start+end)/2;
            if(endPoint==intervals.get(mid).start){
                return mid;
            }else if(endPoint<intervals.get(mid).start)
                end=mid-1;
            else
                start=mid+1;
        }
        return end;
    }
}

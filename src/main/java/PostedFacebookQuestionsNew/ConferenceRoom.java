package PostedFacebookQuestionsNew;

import java.util.*;

/**
 * 会议室问题, 给一堆会议室的schedule(starTtime, endTime), 算出
 * 需要几个会议室才能满足要求. 解法就是新建一个class{time, isStart},
 * 把schedule转化成这个class, 然后对这些时间进行排序, time相同时,end
 * 排在start前面, 然后对这个排序好的list进行遍历, 需要start,
 * meeting_room_num++, 遇到end --. 得到max
 */
public class ConferenceRoom {

    public static int findConferenceRoom(Schedule[] schedules){
        if(schedules.length==0)
            return 0;
        Arrays.sort(schedules, new ScheduleComparator());
        PriorityQueue<Integer> current=new PriorityQueue<Integer>();
        PriorityQueue<Integer> next=new PriorityQueue<Integer>();
        for(int i=0;i<schedules.length;i++){
            int temp=schedules[i].end;
            boolean done=false;
            while(!current.isEmpty() && !done){
                if(schedules[i].start<current.peek())
                    next.add(current.poll());
                else{
                    current.poll();
                    done=true;
                }
            }
            while(!current.isEmpty())
                next.add(current.poll());
            next.add(temp);
            current=next;
            next=new PriorityQueue<Integer>();
        }
        return current.size();
    }

    static class ScheduleComparator implements Comparator<Schedule> {
        public int compare(Schedule s1, Schedule s2){
            if(s1.start<s2.start)
                return -1;
            else if(s1.start>s2.start)
                return 1;
            else{
                if(s1.end<s2.end)
                    return -1;
                else if(s1.end>s2.end)
                    return 1;
                else
                    return 0;
            }
        }
    }

    static class NumberComparator implements Comparator<Integer>{
        public int compare(Integer x, Integer y){
            return x-y;
        }
    }

    public static void main(String[] args){
        Schedule[] schedules={new Schedule(1,2), new Schedule(2,3), new Schedule(3,4), new Schedule(4,5)};
        System.out.println(findConferenceRoom(schedules));
        Schedule[] schedules1={new Schedule(1,3), new Schedule(3,5), new Schedule(5,7), new Schedule(2,4), new Schedule(4,6), new Schedule(6,8)};
        System.out.println(findConferenceRoom(schedules1));
        Schedule[] schedules2={new Schedule(1,10), new Schedule(2,5), new Schedule(4,6), new Schedule(7,10)};
        System.out.println(findConferenceRoom(schedules2));
    }
}

class Schedule {
    int start;
    int end;

    public Schedule(int start, int end){
        this.start=start;
        this.end=end;
    }
}



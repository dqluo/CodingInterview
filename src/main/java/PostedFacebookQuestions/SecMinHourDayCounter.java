package PostedFacebookQuestions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * http://www.careercup.com/question?id=14974850
 */
public class SecMinHourDayCounter {

    List<Event> eventQueue=new ArrayList<Event>();
    int secondPointer=0;
    int minutePointer=0;
    int hourPointer=0;

    public void enqueue(Event event)
    {
        eventQueue.add(event);
        while(!eventQueue.isEmpty() && (event.timeStamp.getTime()-eventQueue.get(0).timeStamp.getTime())/1000>86400)
            eventQueue.remove(0);
        while((event.timeStamp.getTime()-eventQueue.get(secondPointer).timeStamp.getTime())/1000>1)
            secondPointer++;
        while((event.timeStamp.getTime()-eventQueue.get(minutePointer).timeStamp.getTime())/1000>60)
            minutePointer++;
        while((event.timeStamp.getTime()-eventQueue.get(hourPointer).timeStamp.getTime())/1000>3600)
            hourPointer++;
    }

    public int lastSecondCount(){
        return eventQueue.size()-secondPointer;
    }

    public int lastMinuteCount(){
        return eventQueue.size()-minutePointer;
    }

    public int lastHourCount(){
        return eventQueue.size()-hourPointer;
    }

    public static void main(String[] args){
//        Event event1=new Event(Calendar.getInstance().getTime());
//        System.out.println(event1.timeStamp.getTime());
//        Event event2=new Event(Calendar.getInstance().getTime());
//        System.out.println(event2.timeStamp.getTime());
//        Event event3=new Event(Calendar.getInstance().getTime());
//        System.out.println(event3.timeStamp.getTime());
//        Event event4=new Event(Calendar.getInstance().getTime());
//        System.out.println(event4.timeStamp.getTime());
        SecMinHourDayCounter smhdCounter=new SecMinHourDayCounter();
        for(int i=0;i<10000000;i++)
        {
            Event event1=new Event(Calendar.getInstance().getTime());
            smhdCounter.enqueue(event1);
        }
//        for(int i=0;i<100000;i++)
//            smhdCounter.enqueue(event2);
//        for(int i=0;i<100000;i++)
//            smhdCounter.enqueue(event3);
//        for(int i=0;i<100000;i++)
//            smhdCounter.enqueue(event4);
        System.out.println(smhdCounter.lastSecondCount());
        System.out.println(smhdCounter.lastMinuteCount());
        System.out.println(smhdCounter.lastHourCount());
    }
}

class Event{
    Date timeStamp;

    public Event(Date date)
    {
        timeStamp=date;
    }
}

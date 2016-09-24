package PostedFacebookQuestionsNew;

import leetCode.ADT.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 给一堆用户的登陆日志，要求输出各时间段内的在线用户数。
 * 例子：
 * user1: login_time: 0, logout_time: 1
 * user2: login_time: 0, logout_time: 2
 * user3: login_time: 1, logout_time: 3
 * 输出：
 * [0 - 2]: 2
 * [2 - 3]: 1
 * [3 - infinite]: 0
 * 0 - 1不用输出，因为时间点0有2个在线用户，时间点1也有2个在线用户，在线用户数
 * 没有变，所以不用输出。在时间点2在线用户数变为1，所以输出0 - 2: 2
 * 完成函数：
 * struct Log
 * {
 *    float login_time;
 *    float logout_time;
 * };
 * void online_user(vector<Log> &logs);
 */
public class OnlineUsers {

    public static void onlineUser(List<Interval> list){
        int totalSeconds=0;
        for(int j=0;j<list.size();j++){
            if(list.get(j).end>totalSeconds)
                totalSeconds=list.get(j).end;
        }
        int[] log=new int[totalSeconds+1];
        int[] users=new int[totalSeconds+1];
        for (Interval aList : list) {
            log[aList.start]++;
            log[aList.end]--;
        }
        int i=1;
        users[0]=log[0];
        while(i<totalSeconds){
            users[i]=users[i-1]+log[i];
            i++;
        }
        int count=users[0];
        int firstIndex=0;
        for(int j=1;j<=totalSeconds;j++){
            if(users[j]!=count){
                System.out.println(firstIndex+" to "+j+" : "+count);
                firstIndex=j;
                count=users[j];
            }
        }
        System.out.println(firstIndex+" to infinity: "+count);
    }

    public static void main(String[] args){
        Interval interval1=new Interval(0, 1);
        Interval interval2=new Interval(0, 2);
        Interval interval3=new Interval(1, 3);
        List<Interval> list=new ArrayList<Interval>();
        list.add(interval1);
        list.add(interval2);
        list.add(interval3);
        onlineUser(list);
        Log[] logs={new Log(0, 1), new Log(0, 2), new Log(1, 3)};
        String result=log(logs);
        System.out.println(result);
    }

    static class Log {
        int in,out;
        Log(int a,int b) {
            in=a;
            out=b;
        }
    }
    public static String log(Log[] l) {
        String[] s=new String[2*l.length];
        for(int i=0,j=0;i<l.length;i++) {
            s[j++]=l[i].in+"i";
            s[j++]=l[i].out+"o";
        }
        Arrays.sort(s);
        LinkedHashMap<Integer,Integer> lh=new LinkedHashMap<Integer,Integer>();
        int counter=1;
        for(int i=1;i<s.length;i++) {
            char cur=s[i].charAt(0),pre=s[i-1].charAt(0);
            if(cur!=pre) {
                lh.put(pre-'0',counter);
            }
            if(s[i].charAt(1)=='i')
                counter++;
            else
                counter--;
            if(i==s.length-1)
                lh.put(cur-'0',counter);
        }
        String result="";
        int pre=-1,j=1;
        for(int key:lh.keySet()) {
            if(pre==-1)
                pre=key;
            else if(lh.get(key)!=lh.get(pre)) {
                result+=pre+"-"+key+":"+lh.get(pre)+"  ";
                pre=key;
            }
            if(j==lh.size())
                result+=key+"-infinity:"+lh.get(key);
            j++;
        }
        return result;
    }
}

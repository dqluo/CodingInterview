import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-3-16
 * Time: 上午6:48
 * To change this template use File | Settings | File Templates.
 */
public class AmazonQuestion {
    public static List<Integer> intersection (List<Integer> a, List<Integer> b) {
        if(a==null || b==null){
            return null;
        }

        if(a.size()<=0 || b.size()<=0){
            return null;
        }
        HashSet<Integer> set = new HashSet<Integer>();
        List<Integer> result = new ArrayList<Integer>();
        for(int i=0;i<a.size();i++){
            int temp=a.get(i);
            if(!set.contains(temp)){
                set.add(temp);
            }
        }
        for(int j=0;j<b.size();j++){
            int temp=b.get(j);
            if(set.contains(temp)){
                result.add(temp);
            }
        }
        return result;
    }

    public static void main(String[] args){
        List<Integer> list1=new ArrayList<Integer>();
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        List<Integer> list2=new ArrayList<Integer>();
        list2.add(3);
        list2.add(4);
        list2.add(6);
        list2.add(7);
        List<Integer> result=intersection(list1, list2);
        for(int i=0;i<result.size();i++)
        {
            System.out.print(result.get(i)+" ");
        }
        List<Integer> list3=null;
        System.out.println();
        result=intersection(list1, list3);
        for(int i=0;result!=null &&i<result.size();i++)
        {
            System.out.print(result.get(i)+" ");
        }
        System.out.println();
        result=intersection(list3, list2);
        for(int i=0;result!=null &&i<result.size();i++)
        {
            System.out.print(result.get(i)+" ");
        }

    }
}

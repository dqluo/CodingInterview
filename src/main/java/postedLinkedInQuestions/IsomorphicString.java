package postedLinkedInQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Danqin on 9/6/14.
 */
public class IsomorphicString {

    public static boolean isomorphic(Map<Character, Character> map, String a, String b){
        if(a==null && b==null)
            return true;
        if(a==null)
            return false;
        if(b==null)
            return false;
        if(a.length()!=b.length())
            return false;
        for(int i=0;i<a.length();i++){
            if(map.get(a.charAt(i))!=b.charAt(i))
                return false;
        }
        return true;
    }

    public static boolean ismorphic2(Map<Character, List<Character>> map, String a, String b){
        if(a==null && b==null)
            return true;
        if(a==null)
            return false;
        if(b==null)
            return false;
        if(a.length()!=b.length())
            return false;
        for(int i=0;i<a.length();i++){
            if(!map.containsKey(a.charAt(i)))
                return false;
            List<Character> list=map.get(a.charAt(i));
            boolean notFound=true;
            for(int j=0;j<list.size() && notFound;j++){
                if(list.get(j)==b.charAt(i))
                    notFound=false;
            }
            if(notFound)
                return false;
        }
        return true;
    }

    public static void main(String args[]){
        Map<Character, Character> map=new HashMap<Character, Character>();
        map.put('z', 'f');
        map.put('o', 'e');
        System.out.println(isomorphic(map, "zoo", "fee"));
        Map<Character, List<Character>> map2=new HashMap<Character, List<Character>>();
        List<Character> list1=new ArrayList<Character>();
        list1.add('d');
        list1.add('f');
        List<Character> list2=new ArrayList<Character>();
        list2.add('e');
        list2.add('u');
        List<Character> list3=new ArrayList<Character>();
        list3.add('z');
        List<Character> list4=new ArrayList<Character>();
        list4.add('o');
        map2.put('z', list1);
        map2.put('o', list2);
        map2.put('d', list3);
        map2.put('u', list4);
        System.out.println(ismorphic2(map2, "zoo", "fee"));
        System.out.println(ismorphic2(map2, "zoo", "dui"));
        System.out.println(ismorphic2(map2, "dui", "zoo"));
    }
}

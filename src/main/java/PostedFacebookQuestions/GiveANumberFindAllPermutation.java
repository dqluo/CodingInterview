package PostedFacebookQuestions;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-16
 * Time: 下午5:48
 * To change this template use File | Settings | File Templates.
 */
public class GiveANumberFindAllPermutation {

    static HashMap<Character, String> map=new HashMap<Character, String>();

    public static ArrayList<String> findPermutation(String s)
    {
        ArrayList<String> result=new ArrayList<String>();
        findPermutations(s, "", result);
        return result;
    }

    private static void findPermutations(String s, String buffer, ArrayList<String> result)
    {
        if(s.isEmpty())
        {
            result.add(buffer);
            return;
        }
        String current=map.get(s.charAt(0));
        for(int i=0;i<current.length();i++)
        {
            String tempBuffer=buffer+current.charAt(i);
            findPermutations(s.substring(1), tempBuffer, result);
        }
    }

    public static void main(String[] args)
    {
        map.put('1', "");
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        map.put('0',"+");
        ArrayList<String> result=findPermutation("247");
        for(int i=0;i<result.size();i++)
        {
            System.out.print(result.get(i)+" ");
        }
    }
}

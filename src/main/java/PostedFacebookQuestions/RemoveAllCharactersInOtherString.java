package PostedFacebookQuestions;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-17
 * Time: 下午12:40
 * To change this template use File | Settings | File Templates.
 */
public class RemoveAllCharactersInOtherString {

    public static String remove(String s1, String s2){
        HashSet<Character> set=new HashSet<Character>();
        for(int i=0;i<s1.length();i++)
        {
            set.add(s1.charAt(i));
        }
        StringBuilder sbuilder=new StringBuilder();
        for(int i=0;i<s2.length();i++)
        {
            if(!set.contains(s2.charAt(i)))
                sbuilder.append(s2.charAt(i));
        }
        return sbuilder.toString();
    }

    public static void main(String[] args)
    {
        System.out.println(remove("abcdefg","fetaqetepoil"));
    }
}

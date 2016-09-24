package PostedFacebookQuestions;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-17
 * Time: 上午5:55
 * To change this template use File | Settings | File Templates.
 */
public class RemoveDuplicateCharacterInAString {

    public static String remove(String string)
    {
        HashSet<Character> set=new HashSet<Character>();
        StringBuilder sBuilder=new StringBuilder();
        for(int i=0;i<string.length();i++)
        {
            if(!set.contains(string.charAt(i)))
            {
                set.add(string.charAt(i));
                sBuilder.append(string.charAt(i));
            }
        }
        return sBuilder.toString();
    }

    public static void main(String[] args)
    {
        System.out.println(remove("abceagdce"));
    }
}

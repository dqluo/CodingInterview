package postedLinkedInQuestions;

import java.util.HashSet;
import java.util.Set;

/**
 * careercup. Write a program that gives count of common characters
 * presented in an array of strings..(or array of character arrays)
 * For eg.. for the following input strings..
 * aghkafgklt
 * dfghako
 * qwemnaarkf
 * The output should be 3. because the characters a, f and k are present
 * in all 3 strings.
 * Note: The input strings contains only lower case alphabets
 */
public class GetCommonCharacters {

    public static int getCommonCharacterLength(String[] strings){
        if(strings==null || strings.length<2)
            return 0;
        int[] character=new int[256];
        for(String input:strings){
            Set<Character> set=new HashSet<Character>();
            for(int i=0;i<input.length();i++){
                if(!set.contains(input.charAt(i))){
                    set.add(input.charAt(i));
                    character[(int)input.charAt(i)]++;
                }
            }
        }
        int result=0;
        for(int i=0;i<character.length;i++){
            if(character[i]==strings.length)
                result++;
        }
        return result;
    }

    public static void main(String[] args){
        String[] strings={"aghkafgklt","dfghako","qwemnaarkf"};
        System.out.println(getCommonCharacterLength(strings));
    }
}

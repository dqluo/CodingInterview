package PostedFacebookQuestions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-17
 * Time: 下午4:12
 * To change this template use File | Settings | File Templates.
 */
public class PermutationOfAString {

    public static ArrayList<String> permutations(String s)
    {
        ArrayList<String> result=new ArrayList<String>();
        permutationsAux(s, "", result);
        return result;
    }

    public static void permutationsAux(String string, String buffer, ArrayList<String> result)
    {
        if(string.isEmpty())
        {
            result.add(buffer);
            return;
        }
        for(int i=0;i<string.length();i++)
        {
            permutationsAux(string.substring(0, i) + string.substring(i + 1), buffer + string.charAt(i), result);
        }
    }

    public static ArrayList<String> permutation2(String s)
    {
        char[] charArray=s.toCharArray();
        ArrayList<String> result=new ArrayList<String>();
        permutation2(charArray, result, s.length());
        return result;
    }

    public static void permutation2(char[] charArray, ArrayList<String> result, int n)
    {
        if(n==1)
        {
            String temp= Arrays.toString(charArray);
            result.add(temp);
            return;
        }
        for(int i=0;i<n;i++)
        {
            swap(charArray, i, n-1);
            permutation2(charArray, result, n-1);
            swap(charArray, i, n-1);
        }
    }

    private static void swap(char[] charArray, int i, int j)
    {
        char temp=charArray[i];
        charArray[i]=charArray[j];
        charArray[j]=temp;
    }

    public static void main(String[] args)
    {
        ArrayList<String> result=permutations("abc");
        for(int i=0;i<result.size();i++)
        {
            System.out.print(result.get(i)+" ");
        }
        System.out.println();
        result=permutation2("abc");
        for(int i=0;i<result.size();i++)
        {
            System.out.print(result.get(i)+" ");
        }
    }
}

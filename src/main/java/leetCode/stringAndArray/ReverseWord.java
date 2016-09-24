package leetCode.stringAndArray;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-3-22
 * Time: 上午2:39
 * To change this template use File | Settings | File Templates.
 */
public class ReverseWord {
    public static String reverseWords(String s) {
        if(s==null || s.length()==0)
            return s;
        int start=0;
        int end=s.length()-1;
        while(start<=end && s.charAt(start)==' ')
            start++;
        while(start<=end && s.charAt(end)==' ')
            end--;
        if(start>end)
            return "";
        char[] stoChar=toCharArray(s, start, end);
        reverse(stoChar, 0, stoChar.length-1);
        for(int i=0;i<stoChar.length;i++)
        {
            int j=0;
            if(stoChar[i]==' ' && i>0)
            {
                reverse(stoChar, j, i-1);
                j=i+1;
            }
        }
        return new String(stoChar);
    }

    private static void reverse(char[] s, int start, int end){
        if(start>end)
            return;
        for(int i=start, j=end; i<j;i++, j--)
        {
            char temp=s[i];
            s[i]=s[j];
            s[j]=temp;
        }
    }

    private static char[] toCharArray(String s, int start, int end){
        char[] array=new char[end-start+1];
        int index=0;
        for(int i=start;i<=end;i++)
        {
            array[index]=s.charAt(i);
            index++;
        }
        return array;
    }

    public static void main(String[] args){
        String s=" ";
        System.out.println(reverseWords(s));
    }
}

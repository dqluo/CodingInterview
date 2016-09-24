package postedLinkedInQuestions;

/**
 * Q3: string replace, 给一个原string，一个target，一个替换的新str，把所有出现target str的地方都换成新的str， 长度可以任意
 */
public class StringReplace {

    public static String replace(String s, String target, String str){
        if(s.length()<target.length() )
            return s;
        StringBuilder builder=new StringBuilder();
        int start=0;
        int i=0;
        while(i<s.length()){
            if(s.charAt(i)==target.charAt(0)){
                int index=0;
                start=i;
                while(i<s.length() && index<target.length() && s.charAt(i)==target.charAt(index)){
                    index++;
                    i++;
                }
                if(index==target.length())
                    builder.append(str);
                else
                    builder.append(s.substring(start, i));
            }else{
                builder.append(s.charAt(i));
                i++;
            }
        }
        return builder.toString();
    }

    public static void main(String args[]){
        String s="abcdefgbcdadbbcd";
        String target="babad";
        String str="str";
        System.out.println(replace(s, target, str));
        System.out.println(replace("bababad", target, str));
    }
}

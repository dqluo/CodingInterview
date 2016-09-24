package postedLinkedInQuestions;

/**
 * Q66.后缀波兰表达式STRING转换为中缀表达式的STRING。
 */
public class postOrderToInOrder {

    public static String translate(String s){
        if(s==null)
            return null;
        StringBuilder builder=new StringBuilder();
        Character first=null;
        Character second=null;
        for(int i=0;i<s.length();i++){
            if(Character.isLetterOrDigit(s.charAt(i))){
                if(first==null)
                    first=s.charAt(i);
                else if(second==null){
                    second=s.charAt(i);
                }
            }else if(s.charAt(i)=='+' || s.charAt(i)=='-' || s.charAt(i)=='*' || s.charAt(i)=='/'){
                StringBuilder temp=new StringBuilder(builder);
                builder=new StringBuilder();
                builder.append("(");
                if(first!=null && first=='t')
                    builder.append(temp);
                else
                    builder.append(first);
                builder.append(s.charAt(i));
                builder.append(second);
                builder.append(")");
                first='t';
                second=null;
            }else
                continue;
        }
        return builder.toString();
    }

    public static void main(String[] args){
        String s="ab/c+";
        System.out.println(translate(s));
    }
}

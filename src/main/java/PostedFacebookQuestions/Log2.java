package PostedFacebookQuestions;

/**
 * Created by Danqin on 9/20/14.
 */
public class Log2 {

    public static int log(int num){
        int result=0;
        while(num>0){
            num=num>>1;
            result++;
        }
        return result-1;
    }

    public static void main(String[] args){
        System.out.println(log(128));
        System.out.println(log(127));
    }
}

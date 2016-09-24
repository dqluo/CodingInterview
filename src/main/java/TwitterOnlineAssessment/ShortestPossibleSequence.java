package TwitterOnlineAssessment;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-3-14
 * Time: 下午1:18
 * To change this template use File | Settings | File Templates.
 */
public class ShortestPossibleSequence {

    public static int shortestSequence(int number){
        int result=1;
        int n=1;
        while(n<=number)
        {
            n=n*2;
            result++;
        }
        n=n/2;
        result--;
        if(n==number)
            return result;
        result=result+(number-n);
        return result;
    }

    public static void main(String[] args){
        System.out.println(shortestSequence(17));
        System.out.println(shortestSequence(147483647));
        System.out.println(shortestSequence(32));
        System.out.println(shortestSequence(1));
        System.out.println(shortestSequence(100));
        System.out.println(shortestSequence(18));
        System.out.println(shortestSequence(19));

    }
}

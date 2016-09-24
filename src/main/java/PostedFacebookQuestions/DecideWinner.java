package PostedFacebookQuestions;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-18
 * Time: 下午12:17
 * To change this template use File | Settings | File Templates.
 */
public class DecideWinner {

    public static boolean isFirstPlayerWinner(int n)
    {
        int[] choice=new int[2];
        choice[0]=2;
        choice[1]=9;
        return isFirstPlayerWinner(n, 1, true, choice);
    }

    public static boolean isFirstPlayerWinner(int n, int multiple, boolean isFirstPlayer, int[] choice){
        if(multiple>=n)
            return isFirstPlayer;
        for(int i=0;i<choice.length;i++)
        {
            multiple*=choice[i];
            if(isFirstPlayerWinner(n, multiple, !isFirstPlayer, choice))
                return true;
        }
        return false;
    }

    public static void main(String[] args)
    {
        System.out.println(isFirstPlayerWinner(9));
        System.out.println(isFirstPlayerWinner(10));
    }
}

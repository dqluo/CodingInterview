package PostedFacebookQuestions;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-12
 * Time: 上午7:54
 * To change this template use File | Settings | File Templates.
 */
public class Division {
    public static int divide(int dividend, int division)
    {
        int originalDivision=division;
        if(dividend == division)
            return 1;
        else if(dividend < division)
            return 0;
        int quotient=1;
        while(division<=dividend)
        {
            division<<=1;
            quotient<<=1;
        }
        division>>=1;
        quotient>>=1;
        quotient=sum(quotient, divide(sum(dividend, sum(~division, 1)), originalDivision));
        // quotient= quotient + divide(dividend-division, originalDivision);
        return quotient;
    }

    public static int sum(int a, int b)
    {
        if(b==0)
            return a;
        int sum=a^b;
        int carry=(a&b)<<1;
        return sum(sum, carry);
    }

    public static void main(String[] args)
    {
        System.out.println(divide(8, 6));
    }
}

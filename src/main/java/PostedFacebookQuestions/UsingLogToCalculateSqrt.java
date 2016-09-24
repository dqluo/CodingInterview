package PostedFacebookQuestions;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-17
 * Time: 上午8:53
 * To change this template use File | Settings | File Templates.
 */
public class UsingLogToCalculateSqrt {

    public static double logToSqrt(double val)
    {
        int low=0, high=0;
        double lowValue=0, highValue=0;
        while((1<<high)<=val)
            high++;
        low=high-1;
        double lo=low;
        double hi=high;
        lowValue=1<<low;
        highValue=1<<high;
        while(Math.abs(lowValue-val)>1E-7)
        {
            double mid=(lo+hi)/2;
            double midValue=Math.sqrt(lowValue*highValue);
            if(midValue>val)
            {
                hi=mid;
                highValue=midValue;
            }
            else
            {
                lo=mid;
                lowValue=midValue;
            }
        }
        return lo;
    }

    public static void main(String[] args)
    {
        System.out.println(logToSqrt(4));
        System.out.println(logToSqrt(13));
    }
}

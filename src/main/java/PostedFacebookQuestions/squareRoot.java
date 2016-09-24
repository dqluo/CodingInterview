package PostedFacebookQuestions;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-17
 * Time: 下午3:42
 * To change this template use File | Settings | File Templates.
 */
public class squareRoot {

    public static double sqrt(double number)
    {
        double left=0;
        double right=number;
        while(left+0.0001<right)
        {
            double mid=(left+right)/2;
            if(mid*mid>number)
                right=mid;
            else
                left=mid;
        }
        return left;
    }

    public static void main(String[] args)
    {
        System.out.println(sqrt(4.0));
        System.out.println(sqrt(6.5));
        System.out.println(sqrt(8.6));
    }
}

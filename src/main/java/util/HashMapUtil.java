package util;

/**
 * Created by Danqin on 9/13/14.
 */
public class HashMapUtil
{
    public static int getNextPrime(int n)
    {
        if(n%2==0)
            n=n+1;
        //n now is odd number
        if(n+1==2)
            return 2;
        boolean found=false;
        int result=-1;
        while(!found && n<Integer.MAX_VALUE-1)
        {
            if(isPrime(n))
                result=n;
            n=n+2;
        }
        return result;
    }
    public static boolean isPrime(int n)
    {
        n=Math.abs(n);
        switch(n)
        {
            case 1:
                return false;
            case 2:case 3:case 5:case 7:
            return true;
            default:
                if(n%2==0)
                    return false;
                int odd=3;
                while(odd<=(int)Math.sqrt((double)n))
                {
                    if(n%odd==0)
                        return false;
                }
                return true;
        }
    }
}
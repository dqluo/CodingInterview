package TwitterOnlineAssessment;
import util.ArrayUtil;

import java.util.Arrays;

/**
 * User: xinyuwan, Date: 2/8/14, Time: 12:18 AM
 */
public class FindSumPairs
{
    public static void main(String[] args)
    {
        int[] a={10, -1, 8, 2, 9, 11, 21, 1, 10};
        FindSumPairs fsp=new FindSumPairs();
        Arrays.sort(a);
        ArrayUtil.print(a);
        System.out.println("Total pairs sum to 20: "+fsp.findSum(a, 20));
    }
    public int findSum(int[] a, int k)
    {
        if(a.length==0)
            return 0;
        Arrays.sort(a);
        int count=0;
        //first check all k/2 pairs if k is even number
        if(k%2==0)
        {
            for(int i=0; i<a.length; i++)
            {
                if(a[i]==k/2)
                    count++;
            }
        }
        for(int i=0, j=a.length-1; i<j;)
        {
            int sum=a[i]+a[j];
            if(sum==k)
            {
                count+=2;
                i++; j--;
                int iCount=0, jCount=0;
                while(i<j && a[i]==a[i-1])
                {
                    iCount++;
                    i++;
                }
                while(i<j && a[j]==a[j+1])
                {
                    jCount++;
                    j--;
                }
                count+=iCount*jCount*2;
            }
            else if(sum<k)
                i++;
            else
                j--;
        }
        return count;
    }
}

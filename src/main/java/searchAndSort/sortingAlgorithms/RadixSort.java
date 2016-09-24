package searchAndSort.sortingAlgorithms;
import util.ArrayQueue;
import util.ArrayUtil;

/**
 * User: fisherbill, Date: 13-1-11, Time: 5:25 pm
 */
public class RadixSort
{
    public static void main(String[] args)
    {
        int[] a={1, 15, 324, 356, 421, 478, 205};
        radixSort(a);

        ArrayUtil.print(a);
    }
    public static void radixSort(int[] a)
    {
        int maxDigit=maxDigit(a);
        ArrayQueue<Integer>[] bucket=new ArrayQueue[10];
        for(int i=1; i<=maxDigit; i++)
        {
            int currDigit=0;
            for(int j=0; j<a.length; j++)
            {
                currDigit=getKthDigit(a[j], i);
                if(bucket[currDigit]==null)
                    bucket[currDigit]=new ArrayQueue();
                bucket[currDigit].enqueue(a[j]);
            }
            for(int k=0, h=0; k<bucket.length; k++)
            {
                if(bucket[k]==null)
                    continue;
                while(!bucket[k].isEmpty())
                {
                    a[h]= bucket[k].dequeue();
                    h++;
                }
            }
        }
    }
    private static int getKthDigit(int n, int k)
    {
        int x=(n/(int)Math.pow(10, k-1));
        return x%10;
    }
    private static int maxDigit(int[] a)
    {
        int result=0;
        for(int i=0; i<a.length; i++)
        {
            int len=String.valueOf(a[i]).length();
            if(len>result)
                result=len;
        }
        return result;
    }
}

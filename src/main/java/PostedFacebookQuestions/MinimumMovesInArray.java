package PostedFacebookQuestions;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-18
 * Time: 上午9:23
 * To change this template use File | Settings | File Templates.
 */
public class MinimumMovesInArray {

    public static int miniMove(int[] array, int group)
    {
        int n=array.length;
        int[][] cache=new int[n][group+1];
        for(int i=0;i<n;i++)
            cache[i][0]=Integer.MAX_VALUE;
        for(int i=0;i<n;i++)
        {
            for(int j=1;j<=group;j++)
            {
                cache[i][j]=Integer.MAX_VALUE;
                int dist=0;
                for(int k=i;k>=j-1;k--)
                {
                    if(k>0)
                    {
                        if(cache[k-1][j-1]==Integer.MAX_VALUE)
                            continue;
                        else
                            dist=cache[k-1][j-1];
                    }
                    dist+=minDist(array, k, i);
                    if(dist<cache[i][j])
                        cache[i][j]=dist;
                }
            }
        }

        for(int i=0;i<cache.length;i++)
        {
            for(int j=0;j<cache[0].length;j++)
            {
                if(cache[i][j]==Integer.MAX_VALUE)
                    System.out.print("M ");
                else
                    System.out.print(cache[i][j]+" ");
            }
            System.out.println();
        }
        return cache[n-1][group];
    }

    private static int minDist(int[] array, int start, int end)
    {
        int distance=0;
        for(int i=start+1;i<=end;i++)
        {
            distance+=array[i]-array[start];
        }
        int previous=distance;
        for(int i=start+1;i<=end;i++)
        {
            int dist=previous;
            int diff=array[i]-array[i-1];
            dist+=(i-start)*diff;
            dist-=(end-i+1)*diff;
            distance=Math.min(distance, dist);
            previous=dist;
        }
        return distance;
    }

    public static void main(String[] args)
    {
        int[] array1={1,1,3};
        System.out.println(miniMove(array1, 3));
        int[] array2={1,2,4};
        System.out.println(miniMove(array2, 2));
        int[] array3={1,2,5,7};
        System.out.println(miniMove(array3, 2));
    }
}

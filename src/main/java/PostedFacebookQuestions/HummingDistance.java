package PostedFacebookQuestions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-13
 * Time: 上午10:41
 * To change this template use File | Settings | File Templates.
 */
public class
        HummingDistance {

    public int hummingDistance(int one, int two)
    {
        int count=0;
        for(int i=one^two; i>0; i=(i&(i-1)))
        {
            count++;
        }
        return count;
    }

    public int hummingDistanceInAList(int[] array)
    {
        int count=0;
        Map<Integer, Integer> cache=new HashMap<Integer, Integer>();
        for(int i=1; i<array.length; i++)
        {
            for(int j=0; j<i; j++)
            {
                int xor=array[i]^array[j];
                if(cache.containsKey(xor))
                {
                    count+=cache.get(xor);
                }
                else
                {
                    cache.put(xor, hummingDistance(array[i], array[j]));
                    count+=cache.get(xor);
                }
            }
        }
        return count;
    }
}

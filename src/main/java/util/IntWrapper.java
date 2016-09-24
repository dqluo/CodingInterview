package util;

/**
 * User: xinyuwan, Date: 10/28/13, Time: 5:26 PM
 */
public class IntWrapper implements Comparable<IntWrapper>
{
    public int value;
    public IntWrapper()
    {
        value=0;
    }
    public IntWrapper(int v)
    {
        value=v;
    }
    public int compareTo(IntWrapper iw)
    {
        if(value==iw.value)
            return 0;
        else if(value < iw.value)
            return -1;
        else
            return 1;
    }
}

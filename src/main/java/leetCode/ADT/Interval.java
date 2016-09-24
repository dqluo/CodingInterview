package leetCode.ADT;

/**
 * User: xinyuwan, Date: 12/25/13, Time: 4:41 PM
 */
public class Interval
{

    public int start;
    public int end;
    public Interval()
    {
        start = 0;
        end = 0;
    }
    public Interval(int s, int e)
    {
        start = s;
        end = e;
    }
    public String toString()
    {
        return "["+start+" ,"+end+"]";
    }
}

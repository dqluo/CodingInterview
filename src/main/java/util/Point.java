package util;

/**
 * User: xinyuwan, Date: 12/4/13, Time: 2:20 PM
 */

public class Point
{
    public int x;
    public int y;

    public Point(int r, int c)
    {
        x=r;
        y=c;
    }

    public boolean equals(Object other)
    {
        if(other==null)
            return false;
        if(other==this)
            return true;
        if(!this.getClass().equals(other.getClass()))
            return false;
        Point p=(Point)other;
        return this.x==p.x && this.y==p.y;
    }
    public int hashCode()
    {
        int v=x*1000;
        int w=y *1000;
        return (v | w) ^ v;
    }
    public String toString()
    {
        return "["+x+","+y+"]";
    }
}

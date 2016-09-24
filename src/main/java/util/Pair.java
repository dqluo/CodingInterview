package util;

/**
 * User: xinyuwan, Date: 12/4/13, Time: 1:34 PM
 */

public class Pair
{
    public int row;
    public int col;

    public Pair(int r, int c)
    {
        row=r;
        col=c;
    }

    public boolean equals(Object other)
    {
        if(other==null)
            return false;
        if(other==this)
            return true;
        if(!this.getClass().equals(other.getClass()))
            return false;
        Pair p=(Pair)other;
        return this.row==p.row && this.col==p.col;
    }
    public int hashCode()
    {
        int v=row*1000;
        int w=col *1000;
        return (v | w) ^ v;
    }
    public String toString()
    {
        return "["+row+","+col+"]";
    }
}

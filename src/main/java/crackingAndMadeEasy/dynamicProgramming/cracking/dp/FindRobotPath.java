package crackingAndMadeEasy.dynamicProgramming.cracking.dp;

import java.util.ArrayList;
import java.util.HashMap;

import util.ArrayListUtil;
import util.Point;

/**
 * User: xinyuwan, Date: 12/4/13, Time: 1:32 PM
 */
public class FindRobotPath
{
    public static void main(String[] args)
    {
        Point[] lmtPointsPass={new Point(2, 2), new Point(3, 0), new Point(1, 1)};
        Point[] lmtPointsDead={new Point(2, 2), new Point(2, 0), new Point(1, 1)};
//        Robot r=new Robot(lmtPointsDead);
        Robot r=new Robot(lmtPointsPass);
        r.printPath(3, 2);
    }
}

class Robot
{
    /*
     * Problem: imagen
     */
    private ArrayList<Point> path=new ArrayList<Point>();
    private HashMap<Point, Boolean> cache=new HashMap<Point, Boolean>();
    private Point[] lmtPoints;

    public Robot(Point[] lmtPoints)
    {
        this.lmtPoints=lmtPoints;
    }

    public void printPath(int x, int y)
    {
        if(!getPathDP(x, y))
            System.out.println("No single path found");
        else
            ArrayListUtil.print(path);
    }
    public boolean getPath(int x, int y)
    {
        if(x == 0 && y==0)
        {
            path.add(new Point(0, 0));
            return true;
        }
        boolean success=false;
        if(x > 0  && isFree(x-1, y))
            success= getPath(x-1, y);
        if(!success && y > 0 && isFree(x, y - 1))
            success=getPath(x, y-1);
        if(success)
            path.add(new Point(x, y));
        return success;
    }
    public boolean getPathDP(int x, int y)
    {
        Point newPoint=new Point(x, y);
        if(cache.containsKey(newPoint))
            return cache.get(newPoint);
        if(x==0 && y==0)
        {
            path.add(newPoint);
            return true;
        }
        boolean success=false;
        if(x>0 && isFree(x-1, y))
            success=getPath(x-1, y);
        if(!success && y>0 && isFree(x, y-1))
            success=getPath(x, y-1);
        cache.put(newPoint, success);
        if(success)
            path.add(newPoint);
        return success;
    }

    //This method could be improved by overriding hashCode() first,
    //then use Hashtable, here we simply use array
    private boolean isFree(int x, int y)
    {
        for(Point p: lmtPoints)
        {
            if(p.x==x && p.y==y)
                return false;
        }
        return true;
    }
}

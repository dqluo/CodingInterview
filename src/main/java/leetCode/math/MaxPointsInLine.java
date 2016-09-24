package leetCode.math;

import leetCode.ADT.Point;

import java.util.HashMap;
import java.util.Map;

/**
 * User: xinyuwan, Date: 1/12/14, Time: 9:42 PM
 */
public class MaxPointsInLine
{
    public static void main(String[] args)
    {
        Point[] a={new Point(0, 0),
                   new Point(3, 5),
                   new Point(-1, -3),
                   new Point(6, 10),
                   new Point(-3, -5),
                   new Point(-2, -1),
                   new Point(-4, -2),
                   new Point(-8, 30)};
        MaxPointsInLine mpl=new MaxPointsInLine();
        System.out.println("The max points number: "+mpl.maxPoints(a));

    }
    /*
     * Problem: Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
     *
     * Analysis:
     * y=ax+b, if three points (A,B,C) are on the same line, then the two lines (AB, BC) will have the
     * same slope. so for each node, we store the slope of it and other node in a hashmap, the slope has
     * four situations:
     * 1. x1==x2 && y1==y2, the two nodes are duplicates, we did nothing but increase the duplicate counter
     * 2. x1==x2 && y1!=y2, the slope is infinity
     * 3. x1!=x2 && y1==y2, the slope is 0
     * 4. x1!=x2 && y1!=y2, the slope is (x1-x2)/(y1-y2)
     * then for each node, we count how many nodes are on the same line. Then the node with largest number
     * of the nodes on the same line is the result.
     */
    public int maxPoints(Point[] points)
    {
        if(points.length<=0)
            return 0;
        HashMap<Double, Integer> map=new HashMap<Double, Integer>();
        int result=0;
        for(int i=0;i<points.length;i++)
        {
            map.clear();
            int duplicates=0;
            //Set the tempResult to 1 for the case of only one element
            int tempResult=1;
            for(int j=i+1;j<points.length;j++)
            {
                int temp=0;
                double slope=0;
                //if the two nodes are duplicate, just increase the duplicates and continue
                if(points[i].x==points[j].x && points[i].y==points[j].y)
                {
                    duplicates++;
                    continue;
                }
                //if their x value are the same but y value are not, we set the slope to positive infinity.
                //if we did not set it, it will have two situations, one is positive infinity, the other is
                //negtive infinity
                else if(points[i].x==points[j].x && points[i].y!=points[j].y)
                {
                    slope=Double.POSITIVE_INFINITY;
                }
                //if the x value of two nodes are not the same, but their y value are not the same, we set
                //the slope to 0. if we use (x1-x2)/(y1-y2) to calculate the slope, we will have two different
                //slopes, one as +0.0, the other as -0.0
                else if(points[i].x!=points[j].x && points[i].y==points[j].y)
                {
                    slope=0;
                }
                else
                    slope=(1.0)*(points[i].y-points[j].y)/(points[i].x-points[j].x);
                if(map.containsKey(slope))
                {
                    map.put(slope, map.get(slope)+1);
                    temp=map.get(slope);
                }
                else
                {
                    map.put(slope, 2);
                    temp=2;
                }
                //get the temperary result of the maximun points on the line for each node
                if(temp>tempResult)
                    tempResult=temp;
            }
            //get the largest number of node on the same line for all nodes
            if(tempResult+duplicates>result)
                result=tempResult+duplicates;
        }
        return result;
    }
}

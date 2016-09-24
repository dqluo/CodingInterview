package PostedFacebookQuestions;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-17
 * Time: 上午6:04
 * To change this template use File | Settings | File Templates.
 */
public class TwoSegmentIntersection {

    private static int orientation(Point p1, Point p2, Point p3)
    {
        int val=(p2.y-p1.y)*(p3.x-p2.x)-(p3.y-p2.y)*(p2.x-p1.x);
        if(val==0)
            return 0;
        return val>0? 1: -1;
    }

    private static boolean onSegment(Point p1, Point p2, Point p3)
    {
        if(p2.x<=Math.max(p1.x, p3.x) && p2.x>=Math.min(p1.x, p3.x) && p2.y<=Math.min(p1.y, p3.y) && p2.y<=Math.max(p1.y, p3.y))
            return true;
        return false;
    }

    public static boolean intersection(Point p1, Point p2, Point q1, Point q2)
    {
        int o1=orientation(p1, p2, q1);
        int o2=orientation(p1, p2, q2);
        int o3=orientation(q1, q2, p1);
        int o4=orientation(q1, q2, p2);

        if(o1!=o2 && o3!=o4)
            return true;
        if(o1==0 && onSegment(p1, q1, p2))
            return true;
        if(o2==0 && onSegment(p1, q2, p2))
            return true;
        if(o3==0 && onSegment(q1, p1, q2))
            return true;
        if(o4==0 && onSegment(q1, p2, q2))
            return true;
        return false;
    }

    public static void main(String[] args)
    {
        Point p1=new Point(1,1);
        Point p2=new Point(4,4);
        Point q1=new Point(1,2);
        Point q2=new Point(4,1);
        Point q3=new Point(3,5);
        System.out.print(intersection(p1,p2,q1,q2));
        System.out.print(intersection(p1,p2,q1,q3));
    }
}

class Point
{
    public int x;
    public int y;

    public Point(int x, int y)
    {
        this.x=x;
        this.y=y;
    }
}

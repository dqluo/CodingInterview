package PostedFacebookQuestionsNew;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given set of points in 2d grid space. Find a grid point such that sum
 * of distance from all the points to this common point is minimum.
 * eg: p1: [0, 0] p2: [3, 0] p3: [0, 3]
 * ans: r: [0,0]
 * sum: 0 + 3 + 3 = 6
 * for every other point sum to this ans greater than 6.
 */
public class MinimumSumOfGridPoint {

    public static Point minimum(Point[] points){
        Point result=new Point();
        List<Double> x=new ArrayList<Double>();
        List<Double> y=new ArrayList<Double>();
        for(int i=0;i<points.length;i++){
            x.add(points[i].x);
            y.add(points[i].y);
        }
        Collections.sort(x);
        Collections.sort(y);
        if(x.size()%2==0)
            result.x=(x.get(x.size()/2)+x.get(x.size()/2+1))/2;
        else
            result.x=x.get(x.size()/2+1);
        if(y.size()%2==0)
            result.y=(y.get(y.size()/2)+y.get(y.size()/2+1))/2;
        else
            result.y=y.get(y.size()/2+1);
        return result;
    }

    static class Point{
        double x;
        double y;
        public Point(){
            x=0;
            y=0;
        }

        public Point(double x, double y){
            this.x=x;
            this.y=y;
        }
    }
}

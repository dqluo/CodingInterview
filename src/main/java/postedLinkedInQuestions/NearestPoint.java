package postedLinkedInQuestions;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Danqin on 9/17/14.
 */
public class NearestPoint {

    public ArrayList<Point> points = new ArrayList<Point>();

    public void addPoint(Point point) {
        points.add(point);

    }

    public ArrayList<Point> findNearest(Point center, int m) {
        PriorityQueue<Point> q = new PriorityQueue<Point>();
        for (Point p : points){
            double dist = Math.pow((center.getX() - p.getX()),2) + Math.pow((center.getY() - p.getY()),2) ;
            p.setDistFromCenter(dist);
            q.add(p);
        }
        ArrayList<Point> nearestPoints = new ArrayList<Point>();
        for (int i = 0; i < m; i++){
            nearestPoints.add(q.poll());
        }
        return nearestPoints;
    }

    public static void main (String[] args){
        NearestPoint np=new NearestPoint();
        np.addPoint(new Point(0, 1));
        np.addPoint(new Point(0, 2));
        np.addPoint(new Point(0, 3));
        np.addPoint(new Point(0, 4));
        np.addPoint(new Point(0, 5));

        ArrayList<Point> nearestPoints = np.findNearest(new Point(0, 0), 3) ;

        for (Point point: nearestPoints){
            System.out.println(point.getX() + "," + point.getY());
        }
    }

}

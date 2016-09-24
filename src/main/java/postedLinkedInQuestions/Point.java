package postedLinkedInQuestions;

/**
 * Created by Danqin on 9/17/14.
 */
public class Point implements Comparable{
    double x;
    double y;
    Double distFromCenter = null;

    public Double getDistFromCenter() {
        return distFromCenter;
    }
    public void setDistFromCenter(double distFromCenter) {
        this.distFromCenter = distFromCenter;
    }
    public Point(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }

    public int compareTo(Object y){
        Double y_distFromCenter = ((Point)y).getDistFromCenter();
        if (distFromCenter != null && y_distFromCenter != null){
            if (distFromCenter == y_distFromCenter){
                return 0;
            }else if (distFromCenter > y_distFromCenter){
                return 1;
            }else{
                return -1;
            }
        }
        else
            return 0;
    }

}

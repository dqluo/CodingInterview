/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-3-16
 * Time: 上午7:19
 * To change this template use File | Settings | File Templates.
 */
public class OriginQuestion {
    public static class Point {
        public double x;
        public double y;
        public Point(double x, double y){
            this.x=x;
            this.y=y;
        }
    }
    //construct a Node class to store the point and its distance to Original point
    public static class Node{
        public Point point;
        public double distToO;//distance to oringinal point
        public Node(Point p, double d){ // constructor
            this.distToO = d;
            this.point = p;
        }
    }
    //Asumption1: the result is unsorted array of points
    //Asumption2: the oringinal Point is (0,0) on a 2D plane
    public static Point[] closestk( Point[] myList, int k ) {
        //At fisrt we calculate the distance of each point to oringinal point and store them in a Node array
        //As defined above, the Node array contains the Point, and the distance to oringinal Point(0,0)
        Node[] distance = new Node[myList.length];
        for(int i = 0; i < myList.length; i ++){
            distance[i] = new Node(myList[i],distanceToOrigin(myList[i]));
        }
        //use the helper method to find the result
        Point[] result = findKthNearest(distance,k,0,distance.length-1);
        return result;
    }
    //the helper method findKthNearest is the key method here, the idea is to apply the quick sort algorithm.
    //Everytime I use the first Node in the array range as the pivot, use two pointer left and right and compare
    //the node of left and right with the distance of Pivot Node, make sure all the Nodes that has a distance
    //smaller than that of pivot Node falls before the pivot Node, and higher than pivot falls after the pivot
    //node. After the pivot is in the right place, check if the pivot is the kth element. If the pivot is the
    //kth element, then return the first K points in the Node array
    private static Point[] findKthNearest(Node[] distance, int k, int start, int end){
        int pivot = start;//set pivot is the first Node
        int left = start; //left pointer
        int right = end;	//right pointer
        while(left<=right){
            //If the left node's distance is less than the pivot, increase the left pointer
            while(left<=right && distance[left].distToO<=distance[pivot].distToO){
                ++left;
            }
            //If the right node's distance is greater than the pivot, decrease the right pointer
            while(left<=right && distance[right].distToO>=distance[pivot].distToO){
                --right;
            }
            //If we find a valid pair, swap them
            if(left<right){
                swap(distance,left,right);
            }
        }
        //After the while loop, swap to make sure the pivot is in right place
        swap(distance, pivot,right);

        //Now the position of pivot is the value right. Check if this pivot is a valid point
        if(k==right+1){//user right+1 instead of right because k starts from 1 and while index starts from 0
            return constructResult(distance,k);
        }else if(k>right+1){// this means the kth point can only exist in the right half
            return findKthNearest(distance,k, right+1,end);
        }else{//this means the kth point exist in the left half
            return findKthNearest(distance,k, start,right-1);
        }
    }
    //helper method to calculate the distance to the origin point (0,0)
    private static double distanceToOrigin(Point A){
        return Math.sqrt(A.x * A.x + A.y*A.y);
    }
    //swap the position of two Node in the Node array
    private static void swap(Node[] distance, int a, int b){
        Node tmp = distance[a];
        distance[a] = distance[b];
        distance[b] = tmp;
    }
    //construct an array to have to fisrt k points in the Node array
    private static Point[] constructResult(Node[] distance, int k){
        Point[] result = new Point[k];
        for(int i = 0 ; i< k; i++){
            result[i] = distance[i].point;
        }
        return result;
    }
    //The best case run time is exactly O(n) if after one traverse of the whole array
    //The worset case complexity is O(N^2), which occurs if I have poor pivot selection that
    //every pivot is in the right place already. T(N) = T(N-1)+O(N) = O(N^2)

    public static void main(String[] args){
        Point point1=new Point(1,1);
        Point point2=new Point(1,2);
        Point point3=new Point(2,3);
        Point point4=new Point(4,6);
        Point point5=new Point(5,5);
        Point[] mylist={point1, point2, point3, point4, point5};
        Point[] result=closestk(mylist, 3);
        for(int i=0;i<result.length;i++)
        {
            System.out.println(result[i].x+" "+result[i].y);
        }
        System.out.println();
        result=closestk(mylist, 2);
        for(int i=0;i<result.length;i++)
        {
            System.out.println(result[i].x+" "+result[i].y);
        }

    }
}

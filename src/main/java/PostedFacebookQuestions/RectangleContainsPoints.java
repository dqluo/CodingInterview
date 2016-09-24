package PostedFacebookQuestions;

import java.util.Arrays;
import java.util.Comparator;

/**
 * http://www.careercup.com/question?id=15443800
 */
public class RectangleContainsPoints {

    public static boolean contains(Rectangle[] rectangles, Point point)
    {
        Arrays.sort(rectangles, new RectangleComparatorBottomLeftX());
        int start=0;
        int end=rectangles.length-1;
        int mid=0;
        while(start<=end)
        {
            mid=(start+end)/2;
            if(rectangles[mid].bottom_left.x==point.x)
            {
                if(rectangles[mid].bottom_left.y<=point.y && point.y<=rectangles[mid].top_right.y)
                    return true;
            }
            else if(rectangles[mid].bottom_left.x<point.x)
                start=mid+1;
            else
                end=mid-1;
        }
        if(end<0)
            return false;
        Arrays.sort(rectangles, 0, end, new RectangleComparatorTopRightX());
        int left=0;
        int right=end;
        while(left<=right)
        {
            mid=(left+right)/2;
            if(rectangles[mid].top_right.x==point.x)
            {
                if(rectangles[mid].bottom_left.y<=point.y && point.y<=rectangles[mid].top_right.y)
                    return true;
            }
            else if(rectangles[mid].top_right.x<point.x)
                left=mid+1;
            else
                right=mid-1;
        }
        right=end;
        if(left>right)
            return false;
        Arrays.sort(rectangles, left, right, new RectangleComparatorBottomLeftY());
        start=left;
        while(start<=end)
        {
            mid=(start+end)/2;
            if(rectangles[mid].bottom_left.y==point.y)
            {
                if(rectangles[mid].top_right.x>=point.x && rectangles[mid].bottom_left.x<=point.x)
                    return true;
            }
            else if(rectangles[mid].bottom_left.y<point.y)
                start=mid+1;
            else
                end=mid-1;
        }
        right=end;
        if(left>right)
            return false;
        Arrays.sort(rectangles, left, right, new RectangleComparatorTopRightY());
        start=left;
        while(start<=end)
        {
            mid=(start+end)/2;
            if(rectangles[mid].top_right.y==point.y)
            {
                if(rectangles[mid].bottom_left.x<=point.x && rectangles[mid].top_right.x<=point.x)
                    return true;
            }
            else if(rectangles[mid].top_right.y<point.y)
                start=mid+1;
            else
                end=mid-1;
        }
        if(start>right)
            return false;
        return true;
    }

}

class RectangleComparatorBottomLeftX implements Comparator<Rectangle>
{
    public int compare(Rectangle a, Rectangle b)
    {
        return a.bottom_left.x-b.bottom_left.x;
    }
}

class RectangleComparatorTopRightX implements Comparator<Rectangle>
{
    public int compare(Rectangle a, Rectangle b)
    {
        return a.top_right.x-b.top_right.x;
    }
}

class RectangleComparatorBottomLeftY implements Comparator<Rectangle>
{
    public int compare(Rectangle a, Rectangle b)
    {
        return a.bottom_left.y-b.bottom_left.y;
    }
}

class RectangleComparatorTopRightY implements Comparator<Rectangle>
{
    public int compare(Rectangle a, Rectangle b)
    {
        return a.top_right.y-b.top_right.y;
    }
}

class Rectangle
{
    Point top_right;
    Point bottom_left;

    public Rectangle(Point top_right, Point bottom_left)
    {
        this.top_right=top_right;
        this.bottom_left=bottom_left;
    }
}

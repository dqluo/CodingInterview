package leetCode.gimmick;

/**
 * User: xinyuwan, Date: 12/17/13, Time: 4:55 PM
 */
public class ContainsMostWater
{
    /*
     * Total problems: 2
     */
    public static void main(String[] args)
    {
        //test containsMostWater
        ContainsMostWater cmw=new ContainsMostWater();
        int[] height={9, 3, 10, 2, 18, 15, 6};
        System.out.println("Most water contained: "+cmw.maxArea(height));

        //test trappingWater
        TrappingRainWater trw=new TrappingRainWater();
        int[] bars={0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("Max water trapped: "+trw.trap(bars));
    }
    /*
     * Problem: Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
     * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
     * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
     * Note: You may not slant the container.
     *
     * Analysis: The key to this question is that the area is decided by the shorter line of the two,
     * and the width(difference between first and last index). So we begin by putting first
     * to 0 and last to the last index, and get the largest area possible based on width last-first.
     * After that we only move the shorter line to center in order to get a potential larger area.
     * This is because we haven shrinked the width every step, and if we move longer line to center,
     * we can only get smaller area than current one.
     */
    public int maxArea(int[] height)
    {
        int first=0, last=height.length-1;
        int maxArea=0;
        while(first < last)
        {
            int area=(last-first)*Math.min(height[first], height[last]);
            if(area > maxArea)
                maxArea=area;
            if(height[first] < height[last])
                first++;
            else
                last--;
        }
        return maxArea;
    }
}

class TrappingRainWater
{
    /*
     * Problem: Given n non-negative integers representing an elevation map where the width of each bar is 1,
     * compute how much water it is able to trap after raining.
     * For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
     *
     * Analysis: if we calculate the sum, we need to know how much water can one bar contain most?
     * The value is determined by the min bar of (left, right) - current bar(if the diff>0)
     * Our mission has thus become to finding the left max and right max at each point, and accumulate the sum
     */
    public int trap(int[] A)
    {
        //exception case: len must be at least 3
        if(A.length <=2)
            return 0;
        //construct maxLeft for each index
        int[] maxLeft=new int[A.length];
        maxLeft[0]=A[0];
        for(int i=1; i<A.length; i++)
            maxLeft[i]=A[i]>maxLeft[i-1]? A[i]:maxLeft[i-1];
        //note we don't necessarily need a maxRight array, just keep update maxRight
        //as we go left, meanwhile calculate the the max water for each point.
        int maxRight=A[A.length-1], sum=0;
        for(int i=A.length-2; i>=1; i--)
        {
            if(A[i]>maxRight)
                maxRight=A[i];
            int water=Math.min(maxRight, maxLeft[i])-A[i];
            if(water>0)
                sum+=water;
        }
        return sum;
    }
}

package leetCode.stack;

import util.ArrayUtil;

import java.util.Stack;

/**
 * User: xinyuwan, Date: 1/1/14, Time: 4:47 PM
 */
public class LargestRectangle
{
    /*
     * Total problems: 2
     */
    public static void main(String[] args)
    {
        //test LargestRectangleInHistogram
        int[] height={1, 5, 6, 2, 3};
        LargestRectangleInHistogram lrih=new LargestRectangleInHistogram();
        System.out.println("Max area in histogram is "+lrih.largestRectangleArea(height));

        //test MaxRectangleInMatrix
        String[] arr={"1110", "0111", "1011", "0111"};
        char[][] matrix= ArrayUtil.toCharMatrix(arr);
        MaxRectangleInMatrix mrim=new MaxRectangleInMatrix();
        System.out.println("Max area in matrix is "+mrim.maximalRectangle(matrix));
    }
}

class LargestRectangleInHistogram
{
    /*
     * Problem: Given n non-negative integers representing the histogram's bar height
     * where the width of each bar is 1, find the area of largest rectangle in the histogram.
     * For example, Given height = [2,1,5,6,2,3], return 10.
     *
     * Analysis: the key to this problem is to extend both left and right(max possible width) for a given height to get the max area.
     * Inorder to do this we need a stack to keep track of the height that is smaller than the
     * current one to form increasing sequence in the stack until we hit a smaller height than current.
     * Thus, as long as we meet larger height at the right, we are able to extend the width for the smaller height.
     * For example, for [1, 5, 6, 2, 3],
     * we put 1, 5, 6 to stack because it is possible to get a larger height in the right, but when we hit 2,
     * we need to stop since 2 is smaller 6, which means that 6 cannot extend right any more.
     *
     * To extend left, we use two indexes to identify the boundaries: current index i, and top index in stack.
     * these two marks the start and end (excluded both sides). When we hit 2, we know it's time to calculate
     * 6, and 5 since they cannot extend right anymore. But the leftmost index for a given height to extend is
     * tricky: since the top element at stack has height <= the popped element's height, we know the left boundary
     * index for height 5 is 0. This is not such straight forward when we hit the end of the array. When we hit the
     * end the stack now is (from bottom to top) [1, 2, 3], when we pop 2 out, we can only get the left boundary of
     * 2 from the top of stack(1 at this moment) or we get -1 when stack is empty. We cannot use the popped index as
     * the left boundary because there might be multiple elements between height 1 and 2, which have been popped out
     * during the iteration.
     *
     */
    public int largestRectangleArea(int[] height)
    {
        Stack<Integer> stack=new Stack<Integer>();
        int i=0, maxArea=0;
        while(i<height.length)
        {
            if(stack.isEmpty() || height[i] >= height[stack.peek()])
            {
                stack.push(i);
                i++;
            }
            else
            {
                int unExtensibleHeight=height[stack.pop()];
                int width= stack.isEmpty()? i : i-stack.peek()-1;
                maxArea=Math.max(maxArea, unExtensibleHeight*width);
            }
        }
        while(!stack.isEmpty())
        {
            int unExtensibleHeight=height[stack.pop()];
            int width= stack.isEmpty()? i : i-stack.peek()-1;
            maxArea=Math.max(maxArea, unExtensibleHeight*width);
        }
        return maxArea;
    }
}

class MaxRectangleInMatrix
{
    /*
     * Problem: Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
     *
     * Analysis: we need to convert the matrix to a height matrix where each row stores a height array as the problem in
     * LargestRectangleInHistogram. Then, we can apply the same logic of the algorithm in histogram
     * Note, when we calculate each element in heightMatrix, we only accumulate the 1s in matrix when they are continuous.
     * We set heightMatrix[i][j] to be 0 as long as we hit a '0' in matrix[i][j]. Thus the height array in each row denotes
     * the height values when we use row i as bottom. We can get a O(n2) time complexity
     */
    public int maximalRectangle(char[][] matrix)
    {
        if(matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int rows=matrix.length, cols=matrix[0].length;
        int[][] heightMatrix=new int[rows][cols];
        //initialize the first row
        for(int j=0; j<cols; j++)
        {
            if(matrix[0][j]=='1')
                heightMatrix[0][j]=1;
        }
        //calculate the heightMatrix row by row
        for(int i=1; i<rows; i++)
        {
            for(int j=0; j<cols; j++)
            {
                if(matrix[i][j]=='1')
                    heightMatrix[i][j]=heightMatrix[i-1][j]+1;
                else
                    heightMatrix[i][j]=0;
            }
        }
        //for each row in heightMatrix apply the algorithm in histogram
        Stack<Integer> stack=new Stack<Integer>();
        int maxArea=0;
        for(int i=0; i<rows; i++)
        {
            int j=0;
            while(j<cols)
            {
                if(stack.isEmpty() || heightMatrix[i][j] >= heightMatrix[i][stack.peek()])
                {
                    stack.push(j);
                    j++;
                }
                else
                {
                    int height=heightMatrix[i][stack.pop()];
                    int width=stack.isEmpty()? j : j-stack.peek()-1;
                    maxArea=Math.max(maxArea, height*width);
                }
            }
            while(!stack.isEmpty())
            {
                int height=heightMatrix[i][stack.pop()];
                int width=stack.isEmpty()? j : j-stack.peek()-1;
                maxArea=Math.max(maxArea, height*width);
            }
        }
        return maxArea;
    }
}

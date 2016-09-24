package PostedFacebookQuestions;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-16
 * Time: 下午12:34
 * To change this template use File | Settings | File Templates.
 */
public class MaxMatrixWithAllOnes {

    public static int maxArea(int[][] matrix)
    {
        int[][] sumMatrix=new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix[0].length;i++)
            sumMatrix[0][i]=matrix[0][i];
        for(int i=1;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[0].length;j++)
            {
                if(matrix[i][j]==1)
                    sumMatrix[i][j]=sumMatrix[i-1][j]+1;
            }
        }
        int result=0;
        int height=0;
        int width=0;
        for(int i=0;i<matrix.length;i++)
        {
            Stack<Integer> stack=new Stack<Integer>();
            int index=0;
            while(index<matrix[0].length)
            {
                if(stack.isEmpty() || matrix[i][index]>=stack.peek())
                {
                    stack.push(index);
                    index++;
                }
                else
                {
                    height=stack.pop();
                    width=stack.isEmpty()? index:index-stack.peek()-1;
                    result=Math.max(result, height*width);
                }
            }
            while(!stack.isEmpty())
            {
                height=stack.pop();
                width=stack.isEmpty()? index:index-stack.peek()-1;
                result=Math.max(result, height*width);
            }
        }
        return result;
    }

    public static void main(String[] args)
    {
        int[][] matrix={{0,1,1,1,0,1,0},
                        {1,0,1,1,1,0,0},
                        {1,0,0,1,1,1,1},
                        {1,1,1,1,1,1,1},
                        {1,0,0,1,1,1,1}};

    }
}

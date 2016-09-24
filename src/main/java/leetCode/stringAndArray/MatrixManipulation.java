package leetCode.stringAndArray;

import util.ArrayUtil;

import java.util.ArrayList;

/**
 * User: xinyuwan, Date: 12/17/13, Time: 4:16 PM
 */
public class MatrixManipulation
{
    public static void main(String[] args)
    {
        int[][] result=new SpiralMatirx2().generateMatrix(6);
        ArrayUtil.printMatrix(result);
    }
}

class SetMatrixZero
{
    /*
     * Problem: Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
     */
    public void setZeroes(int[][] matrix)
    {
        /*
         * Use firstRow and firstCol to record whether there's a zero
         * in the first row or first column. Then, we use the first col and row
         * to record the other cols and rows should be marked as zero or not
         */
        int firstRow=1, firstCol=1;
        //check first row and col
        for(int i=0; i<matrix.length; i++)
        {
            if(matrix[i][0]==0)
            {
                firstCol=0;
                break;
            }
        }
        for(int j=0; j<matrix[0].length; j++)
        {
            if(matrix[0][j]==0)
            {
                firstRow=0;
                break;
            }
        }
        //check the other rows and cols to be zero
        for(int i=1; i<matrix.length; i++)
        {
            for(int j=1; j<matrix[0].length; j++)
            {
                if(matrix[i][j]==0)
                {
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }
        //mark zeros
        for(int i=1; i<matrix.length; i++)
        {
            for(int j=1; j<matrix[0].length; j++)
            {
                if(matrix[i][0]==0 || matrix[0][j]==0)
                    matrix[i][j]=0;
            }
        }
        //mark the first row and col to be zeros
        if(firstCol==0)
        {
            for(int i=0; i<matrix.length; i++)
                matrix[i][0]=0;
        }
        if(firstRow==0)
        {
            for(int j=0; j<matrix[0].length; j++)
                matrix[0][j]=0;
        }
    }
}

class RotateImage
{
    /*
     * Problem: You are given an n x n 2D matrix representing an image.
     * Rotate the image by 90 degrees (clockwise).
     */
    public void rotate(int[][] matrix)
    {
        int n=matrix.length;
        for(int layer=0;layer<n/2;layer++)
        {
            for(int i=layer;i<n-1-layer;i++)
            {
                int temp=matrix[layer][i];
                matrix[layer][i]=matrix[n-1-i][layer];
                matrix[n-1-i][layer]=matrix[n-1-layer][n-i-1];
                matrix[n-1-layer][n-i-1]=matrix[i][n-1-layer];
                matrix[i][n-1-layer]=temp;
            }
        }
    }
}

class SpiralMatrix
{
    /*
     * Problem : Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
     */
    public ArrayList<Integer> spiralOrder(int[][] matrix)
    {
        ArrayList<Integer> result=new ArrayList<Integer>();
        if(matrix.length==0 || matrix[0].length==0)
            return result;
        int row = matrix.length;
        int col = matrix[0].length;
        int x = 0, y = 0;
        // focus on the good cases first
        while(row>1 && col>1)
        {
            for(int i = x; i<x+col-1; i++)
                result.add(matrix[y][i]);
            for(int j = y; j<y+row-1; j++)
                result.add(matrix[j][x+col-1]);
            for(int i = x+col-1; i>x; i--)
                result.add(matrix[y+row-1][i]);
            for(int j = y+row-1; j>y; j--)
                result.add(matrix[j][x]);
            row = row - 2;
            col = col - 2;
            x++;
            y++;
        }
        if(row>=1 && col==1)
        {
            for(int i = y; i<y+row; i++)
                result.add(matrix[i][x]);
            col=0;
        }
        if(row==1 && col>=1)
        {
            for(int i = x; i<x+col; i++)
                result.add(matrix[y][i]);
            row=0;
        }
        return result;
    }
}
class SpiralMatirx2
{
    public int[][] generateMatrix(int n)
    {
        int[][] result=new int[n][n];
        if(n<=0)
            return result;
        result[0][0]=1;
        int count=2, i=0, j=0;
        /*
         * while we iterate through the matrix, we set up the next element
         * by ++i or ++j, this allows us to stop at where we should begin for
         * the next loop when we detect the next element has been set.
         */
        while(count <= n*n)
        {
            while(j<n-1 && result[i][j+1]==0)
                result[i][++j]=count++;
            while(i<n-1 && result[i+1][j]==0)
                result[++i][j]=count++;
            while(j>0 && result[i][j-1]==0)
                result[i][--j]=count++;
            while(i>0 && result[i-1][j]==0)
                result[--i][j]=count++;
        }
        return result;
    }
}

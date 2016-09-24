package util;

/**
 * User: xinyuwan, Date: 12/4/13, Time: 1:49 PM
 */
public class MatrixUtil
{
    public static void initMatrix(boolean[][] matrix)
    {
        for(int i=0; i<matrix.length; i++)
        {
            for(int j=0; j<matrix[0].length; j++)
            {
                matrix[i][j]=true;
            }
        }
    }

    public static void printMatrix(boolean[][] matrix)
    {
        for(int i=0; i<matrix.length; i++)
        {
            for(int j=0; j<matrix[0].length; j++)
            {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void printMatrix(int[][] matrix)
    {
        for(int i=0; i<matrix.length; i++)
        {
            for(int j=0; j<matrix[0].length; j++)
            {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void printMatrix(char[][] matrix)
    {
        for(int i=0; i<matrix.length; i++)
        {
            for(int j=0; j<matrix[0].length; j++)
            {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}

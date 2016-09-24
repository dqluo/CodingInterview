package leetCode;

/**
 * User: xinyuwan, Date: 12/12/13, Time: 11:15 PM
 */
public class MatrixPaths
{
    public static void main(String[] args)
    {
        int[][] matrix={{3, 4, 2, 1, 7},
                        {1, 5, 7, 4, 3},
                        {3, 3, 2, 9, 2},
                        {2, 2, 1, 4, 6}};
        //test min path
        MinPathSum mps=new MinPathSum();
        System.out.println(mps.minPathSum(matrix));

        UniquePaths up=new UniquePaths();
        //test gcd
        System.out.println("GCD of 18 and 24: "+up.gcd(18, 24));
        //test unique path
        System.out.println("Number of unique paths: "+up.uniquePaths(3, 2));
        System.out.println("Number of unique paths: "+up.uniquePaths2(3, 2));

        //test unique path2
        int[][] grid={{0, 0, 0},
                      {0, 1, 0},
                      {0, 0, 0}};
        UniquePaths2 up2=new UniquePaths2();
        System.out.println("Number of unique paths with obstacles: "
                +up2.uniquePathsWithObstacles2(grid));
    }
}
class MinPathSum
{
    /*
     * Problem: Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
     * Note: You can only move either down or right at any point in time.
     */

//    public int minPathSum(int[][] grid)
//    {
//        if(grid.length==0)
//            return 0;
//        for(int j=1; j<grid[0].length; j++)
//            grid[0][j]+=grid[0][j-1];
//        for(int i=1; i<grid.length; i++)
//            grid[i][0]+=grid[i-1][0];
//        for(int i=1; i<grid.length; i++)
//        {
//            for(int j=1; j<grid[0].length; j++)
//            {
//                grid[i][j]+=Math.min(grid[i - 1][j], grid[i][j - 1]);
//            }
//        }
//        return grid[grid.length-1][grid[0].length-1];
//    }

    public int minPathSum(int[][] grid)
    {
        if(grid.length==0 || grid[0].length==0)
            return 0;
        int rows=grid.length, cols=grid[0].length;
        int[] cache=new int[cols];
        for(int i=0; i<rows; i++)
        {
            for(int j=0; j<cols; j++)
            {
                if(i>0 && j>0)
                    cache[j]=grid[i][j]+Math.min(cache[j], cache[j-1]);
                else if(j==0)//cols=0
                    cache[j]=grid[i][j]+cache[j];
                else//rows=0
                    cache[j]=grid[i][j]+cache[j-1];
            }
        }
        return cache[cols-1];
    }
}

class UniquePaths
{
    /*
     * Problem: A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * How many possible unique paths are there?
     */
    public int uniquePaths(int m, int n)
    {
        if(m<=0 || n<=0) {
            return 0;
        }
        /*
         * Equivalent to C(m-1, m+n-2)=(m+n-2)!/(m-1)!(n-1)!
         * Note, m and n represent the vertex not the edge, so we need to minus 1 for both
         * We need to be careful of the overflow of the result
         */
        int smaller=m-1, larger=n-1;
        if(m>n)
        {
            smaller=n-1;
            larger=m-1;
        }
        int result=1;
        for(int i=1; i<=smaller; i++)
        {
            int mult=larger+smaller, div=i;
            int gcd=gcd(mult, div);
            mult/=gcd;
            div/=gcd;
            result/=div;
            result*=mult;
            larger--;
        }
        return result;
    }

    protected int gcd(int a, int b)
    {
        if(a==0 || b==0)
            throw new ArithmeticException("parameters cannot be zero");
        int dividend=a, divisor=b, remainder=0;
        if(a<b)
        {
            divisor=a;
            dividend=b;
        }
        while(divisor > 0)
        {
            remainder=dividend%divisor;
            dividend=divisor;
            divisor=remainder;
        }
        return dividend;
    }

    public int uniquePaths2(int m, int n)
    {
        if(m<=0 || n<=0)
            return 0;
        //unlike min path, this time we add one more col and row
        int[] cache=new int[n+1];
        cache[1]=1;
        for(int i=1; i<=m; i++)
        {
            for(int j=1; j<=n; j++)
            {
                cache[j]=cache[j-1]+cache[j];
            }
        }
        return cache[n];
    }
}

class UniquePaths2
{
    /*
     * Problem: Follow up for "Unique Paths":
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
     * For example, There is one obstacle in the middle of a 3x3 grid as illustrated below.
     * [
     *   [0,0,0],
     *  [0,1,0],
     *  [0,0,0]
     * ]
     * The total number of unique paths is 2.
     * Note: m and n will be at most 100.
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid)
    {
        if(obstacleGrid.length==0 || obstacleGrid[0].length==0)
            return 0;
        int rows=obstacleGrid.length, cols=obstacleGrid[0].length;
        int[] cache=new int[cols+1];
        cache[1]=1;
        //Note the index: i is equivalent to grid i, j == grid j +1
        for(int i=0; i<rows; i++)
        {
            for(int j=1; j<=cols; j++)
            {
                if(obstacleGrid[i][j-1]==1)
                    cache[j]=0;
                else
                    cache[j]=cache[j-1]+cache[j];
            }
        }
        return cache[cols];
    }

    int[][] cacheDP;
    public int uniquePathsWithObstacles2(int[][] obstacleGrid)
    {

        if(obstacleGrid.length==0 || obstacleGrid[0].length==0)
            return 0;
        int rows=obstacleGrid.length, cols=obstacleGrid[0].length;
        cacheDP=new int[rows][cols];
        for(int i=0; i<rows; i++)
        {
            for(int j=0; j<cols; j++)
                cacheDP[i][j]=-1;
        }
        return uniquePathsWithObstacles(obstacleGrid, rows-1, cols-1);
    }
    private int uniquePathsWithObstacles(int[][] obstacleGrid, int i, int j)
    {
        if(i<0 || j<0)
            return 0;
        //Note we must check the corner case of whether grid[0][0]==1
        if(i==0 && j==0)
            return obstacleGrid[0][0]==0? 1 : 0;
        if(cacheDP[i][j]>=0)
            return cacheDP[i][j];
        cacheDP[i][j]=obstacleGrid[i][j]==1? 0 :
                uniquePathsWithObstacles(obstacleGrid, i-1, j)+uniquePathsWithObstacles(obstacleGrid, i, j-1);
        return cacheDP[i][j];
    }
}

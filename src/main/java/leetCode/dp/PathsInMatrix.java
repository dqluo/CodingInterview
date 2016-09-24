package leetCode.dp;

/**
 * User: xinyuwan, Date: 1/10/14, Time: 3:28 PM
 */
public class PathsInMatrix
{
    /*
     * Total Problems: 3
     */
    public static void main(String[] args)
    {
        //test MinPathSum
        int[][] matrix={{4, 6, 9, 1},
                     {2, 3, 10, 14},
                     {8, 4, 2, 12},
                     {2, 19, 1, 1}};
        MinPathSum mps=new MinPathSum();
        System.out.println("Min path sum is "+mps.minPathSum(matrix));

        //test UniquePaths
        UniquePaths up=new UniquePaths();
        System.out.println("Total unique paths for 5*3 grid: "+up.uniquePaths(5, 3));
        System.out.println("Total unique paths for 5*3 grid: "+up.uniquePaths2(5, 3));

        //test UniquePaths2
        UniquePaths2 up2=new UniquePaths2();
        int[][] matrix2={{0, 1, 1, 0},
                         {0, 0, 0, 0},
                         {1, 0, 0, 0},
                         {1, 0, 1, 0}};
        System.out.println("Total unique paths for obstacle grid: "+up2.uniquePathsWithObstacles(matrix2));


    }
}

class MinPathSum
{
    /*
     * Problem: Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
     * Note: You can only move either down or right at any point in time.
     */
    public int minPathSum(int[][] grid)
    {
        int rows=grid.length, cols=grid[0].length;
        if(rows==0 || cols==0)
            return 0;
        int[] cache=new int[cols];
        //initialize the first row
        cache[0]=grid[0][0];
        for(int j=1; j<cols; j++)
            cache[j]=grid[0][j]+cache[j-1];
        for(int i=1; i<rows; i++)
        {
            //for every row, we set up the first element and then start from j=1
            cache[0]+=grid[i][0];
            for(int j=1; j<cols; j++)
            {
                cache[j]=grid[i][j]+Math.min(cache[j], cache[j-1]);
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
    //Method1: DP
    public int uniquePaths(int m, int n)
    {
        if(m<=0 || n<=0)
            return 0;
        //unlike min path, this time we add one more col and row
        int[] cache=new int[n+1];
        cache[1]=1;
        //Note that i is not important here, it is only used for control the times of iteration for rows
        for(int i=1; i<=m; i++)
        {
            for(int j=1; j<=n; j++)
            {
                cache[j]=cache[j-1]+cache[j];
            }
        }
        return cache[n];
    }
    //Method2: Math Formula
    public int uniquePaths2(int m, int n)
    {
         if(m<=0 || n<=0)
             return 0;
         /*
          * Equivalent to C(m, m+n)=(m+n)!/m!n!
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

    private int gcd(int a, int b)
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
}

class UniquePaths2
{
    public int uniquePathsWithObstacles(int[][] obstacleGrid)
    {
        int rows=obstacleGrid.length, cols=obstacleGrid[0].length;
        if(rows==0 || cols==0)
             return 0;
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
}

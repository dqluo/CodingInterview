package leetCode.dfs;

import java.util.ArrayList;

/**
 * User: xinyuwan, Date: 1/8/14, Time: 5:43 PM
 */
public class NQueens
{
    /*
     * Total problems: 2
     */
    public static void main(String[] args)
    {
        //test NQueens
        NQueens nq=new NQueens();
        System.out.println("Solution for 4-Queens: ");
        for(String[] solution: nq.solveNQueens(4))
        {
            for(String row : solution)
                System.out.println(row);
            System.out.println();
        }

        //test NQueens2
        NQueens2 nq2=new NQueens2();
        System.out.println("Total solutions for 8-Queens: "+nq2.totalNQueens(8));
    }
    /*
     * Problem: Given an integer n, return all distinct solutions to the n-queens puzzle.
     * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
     * For example,
     * There exist two distinct solutions to the 4-queens puzzle:
     * [ [".Q..",  // Solution 1
     *    "...Q",
     *    "Q...",
     *    "..Q."],
     *  ["..Q.",  // Solution 2
     *   "Q...",
     *   "...Q",
     *   ".Q.."]]
     */

    /*
     * Analysis: we construct the solutions while we recurse down, for each row, after we have identified
     * which column to place the queen, we will convert that row to string and put into solution array
     */

    public ArrayList<String[]> solveNQueens(int n)
    {
        int[] columns=new int[n];
        ArrayList<String[]> result=new ArrayList<String[]>();
        String[] solution=new String[n];
        placeQueens1(0, n, columns, solution, result);
        return result;
    }

    private void placeQueens1(int row, int n, int[] columns, String[] solution, ArrayList<String[]> result)
    {
        if(row==n)
        {
            result.add(solution.clone());
            return;
        }
        for(int col=0; col<n; col++)
        {
            if(checkValid(columns, row, col))
            {
                columns[row]=col;
                String rowString=rowToString(col, n);
                solution[row]=rowString;
                placeQueens1(row+1, n, columns, solution, result);
            }
        }
    }
    private boolean checkValid(int[] columns, int row1, int col1)
    {
        for(int row2=0; row2<row1; row2++)
        {
            int col2=columns[row2];
            //check vertically
            if(col2==col1)
                return false;
            //check diagonally
            int colDiff=Math.abs(col1-col2);
            int rowDiff=row1-row2;
            if(colDiff==rowDiff)
                return false;
        }
        return true;
    }
    //convert each row to the string required by the problem
    private String rowToString(int col, int n)
    {
        StringBuffer sb=new StringBuffer();
        for(int j=0; j<n; j++)
        {
            if(j==col)
                sb.append("Q");
            else
                sb.append(".");
        }
        return sb.toString();
    }
}

class NQueens2
{
    /*
     * Problem: Follow up for N-Queens problem.
     * Now, instead outputting board configurations, return the total number of distinct solutions.
     */
    public int totalNQueens(int n)
    {
        int[] columns=new int[n];
        return placeQueens(columns, 0, n);
    }

    public int placeQueens(int[] columns, int row, int n)
    {
        if(row==n)
            return 1;
        int count=0;
        for(int col=0; col<n; col++)
        {
            if(checkValid(columns, row, col))
            {
                columns[row]=col;
                count+=placeQueens(columns, row+1, n);
            }
        }
        return count;
    }

    private boolean checkValid(int[] columns, int row, int col)
    {
        for(int row2=0; row2 < row; row2++)
        {
            int col2=columns[row2];
            //check vertically
            if(col2 == col)
                return false;
            //check diagonally
            int colDiff=Math.abs(col-col2);
            int rowDiff=row-row2;
            if(colDiff==rowDiff)
                return false;
        }
        return true;
    }
}

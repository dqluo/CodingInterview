package crackingAndMadeEasy.dynamicProgramming.cracking.recursionOnly;

import util.ArrayUtil;

import java.util.ArrayList;

/**
 * User: xinyuwan, Date: 12/5/13, Time: 10:20 PM
 */
public class EightQueens
{
    /*
     * Problem: write an algorithm to print all ways of arranging 8 queens on
     * an 8*8 chess board so that none of them share the same row, column or diagonal.
     * In this case, diagonal means all diagonals, not just the bisect of the board.
     */
    private static Integer[] columns=new Integer[8];
    private static ArrayList<Integer[]> results=new ArrayList<Integer[]>();

    public static void main(String[] args)
    {
        System.out.println("There are total "+placeQueens(0)+" solutions");
        for(Integer[] result : results)
            ArrayUtil.print(result);
    }

    public static int placeQueens(int row)
    {
        int size=columns.length;
        if(row==size)
        {
            results.add(columns.clone());
            return 1;
        }
        int count=0;
        for(int col=0; col<size; col++)
        {
            if(checkValid(row, col))
            {
                columns[row]=col;
                count+=placeQueens(row+1);
            }
        }
        return count;
    }

    private static boolean checkValid(int row, int col)
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

package leetCode.dfs;

import util.ArrayUtil;

/**
 * User: xinyuwan, Date: 12/27/13, Time: 11:33 PM
 */
public class WordSearch
{
    public static void main(String[] args)
    {
        WordSearch ws=new WordSearch();
        String[] arr={"aa"};
        String[] arr1={"mecs", "itse", "acid", "cxro"};
        char[][] board=ArrayUtil.toCharMatrix(arr1);
        boolean result=ws.exist(board, "tcir");
        System.out.println("Search \"tcir\" in board: "+result);
        if(result)
        {
            System.out.print("The path is: ");
            ArrayUtil.print(ws.path);
        }
    }
    /*
     * Problem: Given a 2D board and a word, find if the word exists in the grid.
     * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
     * For example, Given board =
     * [
     * ["ABCE"],
     * ["SFCS"],
     * ["ADEE"]
     * ]
     * word = "ABCCED", -> returns true,
     * word = "SEE", -> returns true,
     * word = "ABCB", -> returns false.
     *
     * Analysis: DFS--basic logic is to find the first the char, and based on that position, we
     * check the neighbor char recursively. To avoid repeat checking, use a path array to keep track
     * of the recursion, which means we will record down the position we have checked and will not do
     * a a->b and b->a check
     */

    Pair[] path;
    public boolean exist(char[][] board, String word)
    {
        if(word==null || word.isEmpty())
            return false;
        path=new Pair[word.length()];
        char firstChar=word.charAt(0);
        for(int i=0; i<board.length; i++)
        {
            for(int j=0; j<board[0].length; j++)
            {
                if(board[i][j]==firstChar)
                {
                    path[0]=new Pair(i, j);
                    if(findInNeighbor(board, word, i, j, 1))
                    return true;
                }
            }
        }
        return false;

    }
    //findInNeighbor only goes to four directions and if one of them is true, return true
    private boolean findInNeighbor(char[][] board, String word, int row, int col, int index)
    {
        if(index == word.length())
            return true;
        char current=word.charAt(index);
        if(row > 0 && board[row-1][col] == current && !isVisited(row-1, col, index))
        {
            path[index]=new Pair(row-1, col);
            if(findInNeighbor(board, word, row-1, col, index+1))
                return true;
        }
        if(col >0 && board[row][col-1] == current && !isVisited(row, col-1, index))
        {
            path[index]=new Pair(row, col-1);
            if(findInNeighbor(board, word, row, col-1, index+1))
                return true;
        }
        if(row < board.length-1 && board[row+1][col] == current && !isVisited(row+1, col, index))
        {
            path[index]=new Pair(row+1, col);
            if(findInNeighbor(board, word, row+1, col, index+1))
                return true;
        }
        if(col < board[0].length-1 && board[row][col+1] == current && !isVisited(row, col+1, index))
        {
            path[index]=new Pair(row, col+1);
            if(findInNeighbor(board, word, row, col+1, index+1))
                return true;
        }
        return false;
    }
    private boolean isVisited(int row, int col, int index)
    {
        for(int i=0; i<index; i++)
        {
            if(path[i].row==row && path[i].col==col)
                return true;
        }
        return false;
    }
    class Pair
    {
        public int row;
        public int col;

        public Pair(int r, int c)
        {
            row=r;
            col=c;
        }
        public String toString()
        {
            return "["+row+","+col+"]";
        }
    }
}

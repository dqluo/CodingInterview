package leetCode.dfs;

import util.ArrayUtil;

/**
 * User: xinyuwan, Date: 12/21/13, Time: 8:26 PM
 */
public class Sudoku
{
    /*
     * Total Problems: 2
     */
    public static void main(String[] args)
    {
        String[] sdk={"53..7....",
                      "6..195...",
                      ".98....6.",
                      "8...6...3",
                      "4..8.3..1",
                      "7...2...6",
                      ".6....28.",
                      "...419..5",
                      "....8..79"};
        char[][] sudoku= ArrayUtil.toCharMatrix(sdk);
        ValidSudoku vs=new ValidSudoku();
        System.out.println("Before solving, is valid? "+vs.isValidSudoku(sudoku));
        SudokuSolver sv=new SudokuSolver();
        sv.solveSudoku(sudoku);
        System.out.println("After solving, is valid? "+vs.isValidSudoku(sudoku));
        System.out.println("Now the matrix is: ");
        ArrayUtil.printMatrix(sudoku);

        System.out.println(sv.isValid(sudoku, 2, 0));
    }
}

class ValidSudoku
{
    /*
     * Problem: Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
     * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
     *
     * Analysis: inorder to determine a valid sudoku, we need to check each row, col and 3*3 box in the
     * board to see whether there is any dup. We can improve the performance of checkValid by using a
     * boolean array indicating 1..9 (corresponding to index 0~8) has appeared or not. Note we need to
     * reset the boolean array after we've check each row, col or box.
     */
    public boolean isValidSudoku(char[][] board)
    {
        return isValidRow(board) && isValidCol(board) && isValidBox(board);
    }
    private boolean isValidBox(char[][] board)
    {
        boolean[] check=new boolean[9];
        for(int i=0; i<9; i+=3)
        {
            for(int j=0; j<9; j+=3)
            {
                for(int m=0; m<3; m++)
                {
                    for(int n=0; n<3; n++)
                    {
                        if(!checkValid(check, board[i+m][j+n]))
                            return false;
                    }
                }
                reset(check);
            }
        }
        return true;
    }
    private boolean isValidCol(char[][] board)
    {
        boolean[] check=new boolean[9];
        for(int j=0; j<9; j++)
        {
            for(int i=0; i<9; i++)
            {
                if(!checkValid(check, board[i][j]))
                    return false;
            }
            reset(check);
        }
        return true;
    }
    private boolean isValidRow(char[][] board)
    {
        boolean[] check=new boolean[9];
        for(int i=0; i<9; i++)
        {
            for(int j=0; j<9; j++)
            {
                if(!checkValid(check, board[i][j]))
                    return false;
            }
            reset(check);
        }
        return true;
    }
    private void reset(boolean[] a)
    {
        for(int i=0; i<a.length; i++)
            a[i]=false;
    }
    protected boolean checkValid(boolean[] a, char c)
    {
        if(c=='.')
            return true;
        //we need to calculate the diff between c and '1' to find the corresponding index
        int index=c-'1';
        if(!a[index])
        {
            a[index]=true;
            return true;
        }
        else
            return false;
    }
}

class SudokuSolver
{
    /*
     * Problem: Write a program to solve a Sudoku puzzle by filling the empty cells.
     * Empty cells are indicated by the character '.'.
     * You may assume that there will be only one unique solution.
     *
     * Analysis:
     * Use dfs to solve the board: for each point on the board, we try to put 1 to 9 in it,
     * and we have a isValid method to check whether the row, col, and box have dups.
     * If it passes this method, we continue to solve this board, if both isValid and solveSudoku
     * return true, we have solved the board, otherwise we stop going forward and return false
     */
    public void solveSudoku(char[][] board)
    {
        solveSudokuAux(board);
    }
    public boolean solveSudokuAux(char[][] board)
    {
        for(int i=0; i<9; i++)
        {
            for(int j=0; j<9; j++)
            {
                if(board[i][j]=='.')
                {
                    for(int k=1; k<=9; k++)
                    {
                        //set the board[i][j] but need to convert to char
                        board[i][j]=(char)(k+'0');
                        if(isValid(board, i, j) && solveSudokuAux(board))
                            return true;
                    }
                    //we fail to solve the board, so we need to restore to '.'
                    board[i][j]='.';
                    return false;
                }
            }
        }
        //if we reach this point, it means there's no '.' in board, so we don't need to solve
        return true;
    }
    protected boolean isValid(char[][] board, int row, int col)
    {
        char valueToCheck=board[row][col];
        //check column at index col, row by row
        for(int i=0; i<9; i++)
        {
            if(i!=row && board[i][col]==valueToCheck)
                return false;
        }
        //check row at index row, column by column
        for(int j=0; j<9; j++)
        {
            if(j!=col && board[row][j]==valueToCheck)
                return false;
        }
        //check the 3*3 box where board[row][col] is located at
        for(int i=(row/3)*3; i<(row/3+1)*3; i++)
        {
            for(int j=(col/3)*3; j<(col/3+1)*3; j++)
            {
                if(i!=row && j!=col && board[i][j]==valueToCheck)
                    return false;
            }
        }
        return true;
    }
}


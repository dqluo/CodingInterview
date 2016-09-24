package leetCode.bfs;

import java.util.LinkedList;
import java.util.Queue;

import static util.ArrayUtil.printMatrix;
import static util.ArrayUtil.toCharMatrix;

/**
 * User: xinyuwan, Date: 12/30/13, Time: 9:54 PM
 */
public class SurroundingRegion
{
    public static void main(String[] args)
    {
        String[] arr={"XOXXO",
                      "XXOOX",
                      "XOXOX",
                      "XXXXX"};
        String[] arr2={"OOO", "OOO", "OOO"};
        char[][] board=toCharMatrix(arr2);
        SurroundingRegion sr=new SurroundingRegion();
        printMatrix(board);
        System.out.println();
        sr.solve(board);
        printMatrix(board);

    }
    /*
     * Problem: Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
     * A region is captured by flipping all 'O's into 'X's in that surrounded region .
     * For example,
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * After running your function, the board should be:
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     *
     * We only care about the 'O's on the 4 edges of the board since the 'O's that connected to
     * these boundary 'O's are those that we cannot flip. So for each such 'O', we need to do a
     * BFS to mark the reachable 'O's to be 'N', which means that these 'O's are all non-flippable.
     * For other 'O's, we can safely flip them to 'X' since we have made sure that they cannot be
     * reached from any edges.
     *
     * Note for 2 optimizations:
     * 1. we don't need a boolean matrix to mark visited elements, this is because we mark a 'O' to
     * be 'N' as long as we identify that 'O' is connected. We do this while we BFS from a start 'O',
     * when we revisit that 'O' from other parent 'O', it has been marked 'N' already.
     * 2. We don't need to mark the four edges, and don't even need to check the four vertices of the
     * matrix since they won't affect our decisions. And when we do the flip process, we only need to
     * start from [1, 1] to [rows-2][cols-2].
     */
    public void solve(char[][] board)
    {
        if(board.length==0 || board[0].length==0) {
            return;
        }
        int rows=board.length, cols=board[0].length;
        //mark non-flippable 'O' from four edges excluded,
        // if cols<=2 || rows <=2 we don't need to do this
        for(int i=1; cols>2 && i<rows-1; i++)
        {
            if(board[i][0]=='O')
            {
                markBFS(board, i, 1);
            }
            if(board[i][cols-1]=='O')
                markBFS(board, i, cols-2);
        }
        for(int j=1; rows>2 && j<cols-1; j++)
        {

            if(board[0][j]=='O')
                markBFS(board, 1, j);
            if(board[rows-1][j]=='O')
                markBFS(board, rows-2, j);
        }
        for(int i=1; i<rows-1; i++)
        {
            for(int j=1; j<cols-1; j++)
            {
                if(board[i][j]=='O')
                    board[i][j]='X';
                else if(board[i][j]=='N')
                    board[i][j]='O';
            }
        }
    }

    private void markBFS(char[][] board, int row, int col)
    {
        if(board[row][col]!='O')
            return;
        Queue<Pair> q=new LinkedList<Pair>();
        board[row][col]='N';
        q.add(new Pair(row, col));
        while(!q.isEmpty())
        {
            Pair p=q.remove();
            //be very careful about the boundary case for both sides:
            //we only go forward when the current index is in range [2, rows-3][2, cols-3]
            if(p.row > 1 && board[p.row-1][p.col]=='O')
            {
                board[p.row-1][p.col]='N';
                q.add(new Pair(p.row-1, p.col));
            }

            if(p.row < board.length-2 && board[p.row+1][p.col]=='O')
            {
                board[p.row+1][p.col]='N';
                q.add(new Pair(p.row+1, p.col));
            }
            if(p.col > 1 && board[p.row][p.col-1]=='O')
            {
                board[p.row][p.col-1]='N';
                q.add(new Pair(p.row, p.col-1));
            }
            if(p.col < board[0].length-2 && board[p.row][p.col+1]=='O')
            {
                board[p.row][p.col+1]='N';
                q.add(new Pair(p.row, p.col+1));
            }
        }
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

package leetCode.searchAndSort;

/**
 * User: xinyuwan, Date: 12/21/13, Time: 4:03 PM
 */
public class Search2DMatrix {
    public static void main(String[] args) {
        int[][] a = {{1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}};
        int[][] b = {{1}, {3}};
        Search2DMatrix s2m = new Search2DMatrix();
        s2m.searchMatrix2(b, 1);
        int[] test = {5, 17, 1, 50, 21};
        System.out.print("Search result: ");
        for (int i = 0; i < test.length; i++) {
            System.out.print(s2m.searchMatrix(a, test[i]) + " ");
        }
    }

    /*
     * Problem: Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
     * 1.Integers in each row are sorted from left to right.
     * 2. The first integer of each row is greater than the last integer of the previous row.
     *
     * Analysis: the logic is to scan from the last row, compare target with matrix[i][0] to identify which row
     * the target is possibly located. If we find such row, then scan for left to right until we find the target,
     * or stop finding, if the target value<current element.
     * Note the commented solution is more suitable for more generic version of this problem.
     * That is, the second limitation does not exist.
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = matrix.length - 1;
        while (i >= 0 && target < matrix[i][0])
            i--;
        if (i < 0)
            return false;
        int j = 0;
        while (j < matrix[0].length) {
            if (matrix[i][j] == target)
                return true;
            else if (matrix[i][j] > target)
                return false;
            else
                j++;
        }
        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        if (target < matrix[0][0])
            return false;
        int i = 0;
        while (i < matrix.length - 1) {
            if (target > matrix[i + 1][0])
                i++;
        }
        if (i == matrix.length)
            return false;
        int j = 0;
        while (j < matrix[0].length) {
            if (target == matrix[i][j])
                return true;
            else if (target < matrix[i][j])
                return false;
            else
                j++;
        }
        return false;
    }
    // public boolean searchMatrix(int[][] matrix, int target)
    // {
    //     for(int i=matrix.length-1, j=0; i>=0 && j<matrix[0].length;)
    //     {
    //         int current=matrix[i][j];
    //         if(target == current)
    //             return true;
    //         else if(target < current)
    //             i--;
    //         else
    //             j++;
    //     }
    //     return false;
    // }
}

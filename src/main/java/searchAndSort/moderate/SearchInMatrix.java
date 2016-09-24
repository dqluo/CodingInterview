package searchAndSort.moderate;

/**
 * User: xinyuwan, Date: 12/2/13, Time: 7:48 PM
 */
public class SearchInMatrix
{
    /*
     * Problem: search in matrix, where each row and column is in ascending order
     *
     */
    public static void main(String[] args)
    {
        int[][] a={{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 12, 15, 17}, {19, 27, 35, 48}};
        search(a, a.length, 4);
        search(a, a.length, 8);
        search(a, a.length, 48);
        search(a, a.length, 19);
        search(a, a.length, 15);
        search(a, a.length, 26);

    }
    //search for k in matrix a, return false if no such element exists
    //The method takes O(n) complexity

    public static boolean search(int[][] a, int n, int k)
    {
        for(int i=n-1, j=0; i>=0 && j<=n-1;)
        {
            if(k == a[i][j])
            {
                System.out.println("Found in row: "+i+" col: "+j);
                return true;
            }
            else if(k < a[i][j])
                i--;
            else
                j++;
        }
        System.out.println("Not found");
        return false;

    }
}

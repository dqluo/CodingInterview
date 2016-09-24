package crackingAndMadeEasy.searchAndSort.moderate;

import util.ArrayUtil;

/**
 * User: xinyuwan, Date: 12/2/13, Time: 7:38 PM
 */
public class SortBToA
{
    public static void main(String[] args)
    {
        int[] a={2, 8, 13, 16, 29, 38, 0, 0, 0, 0, 0, 0};
        int[] b={1, 3, 7, 15, 26, 29};
        sort(a, b, a.length-b.length, b.length);
        ArrayUtil.print(a);
    }
    public static void sort(int[] a, int[] b, int m, int n)
    {
        int i=m-1, j=n-1, k=m+n-1;
        while(i>=0 && j>=0)
        {
            if(a[i] <= b[j])
            {
                a[k]=b[j];
                j--;
            }
            else
            {
                a[k]=a[i];
                i--;
            }
            k--;
        }
        while(j >=0)
        {
            a[k]=b[j];
            j--;
            k--;
        }
    }
}

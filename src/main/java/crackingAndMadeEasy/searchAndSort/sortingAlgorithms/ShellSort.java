package crackingAndMadeEasy.searchAndSort.sortingAlgorithms;

import util.ArrayUtil;

/**
 * User: fisherbill, Date: 13-1-10, Time: 11:05 pm
 */
public class ShellSort
{
    public static void main(String[] args)
    {
        int[] a={9, 3, 2, 15, 12, 7, 10, 6, 11, 8};
      //  spaceInsSort(a, 1, 3);

        shellSort(a);
        ArrayUtil.print(a);
    }
    //worst case complexity O(nlog2n)
    public static void shellSort(int[] a)
    {
        int space=a.length/2;
        while(space>=1)
        {
            if(space%2==0)
                space+=1;
            for(int i=0; i<=space; i++)
            {
                spaceInsSort(a, i, space);
            }
            space=space/2;

        }
    }
    public static void spaceInsSort(int[] a, int begin, int space)
    {
        for(int i=begin+space; i<a.length; i+=space)
        {
            int next=a[i];
            int j=i;
            for(; j>begin && a[j-space]>next; j-=space)
                a[j]=a[j-space];
            a[j]=next;
        }

    }

}

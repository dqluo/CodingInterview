package util;

public class  ArrayUtil
{
    public static void swap(int[] a, int i, int j)
    {
        //if i==j a[i] and a[j] refer to the same value
        if(i==j)
            return;
        a[i]=a[i]^a[j];
        a[j]=a[i]^a[j];
        a[i]=a[j]^a[i];
        /*
         * a[i]=a[i]-a[j];
         * a[j]=a[i]+a[j];
         * a[i]=a[j]-a[i];
         */
    }
    public static void swap(char[] a, int i, int j)
    {
        if(i==j)
            return;
        a[i]=(char)(a[i]^a[j]);
        a[j]=(char)(a[i]^a[j]);
        a[i]=(char)(a[i]^a[j]);
    }
    public static <T> void swap(T[] a, int i, int j)
    {
        T temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    public static void print(int[] a, int start, int end)
    {
        for(int i=start; i<=end; i++)
            System.out.print(a[i]+" ");
        System.out.println();
    }
    public static void print(int[] a)
    {
        for(int i=0; i<a.length; i++)
        {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
    public static void print(char [] a)
    {
        for(int i=0; i<a.length; i++)
        {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    public static <T> void print(T[] a)
    {
        for(int i=0; i<a.length; i++)
        {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
    public static <T> void printMatrix(T[][] a)
    {
        for(int i=0; i<a.length; i++)
        {
            for(int j=0; j<a[0].length; j++)
                System.out.print(a[i][j]+" ");
            System.out.println();
        }
    }
    public static void printMatrix(char[][] a)
    {
        for(int i=0; i<a.length; i++)
        {
            for(int j=0; j<a[0].length; j++)
                System.out.print(a[i][j]+" ");
            System.out.println();
        }
    }
    public static void printMatrix(int[][] a)
    {
        for(int i=0; i<a.length; i++)
        {
            for(int j=0; j<a[0].length; j++)
                System.out.print(a[i][j]+" ");
            System.out.println();
        }
    }
    public static char[][] toCharMatrix(String[] arr)
    {
        char[][] matrix=new char[arr.length][arr[0].length()];
        for(int i=0; i<matrix.length; i++)
        {
            for(int j=0; j<matrix[0].length; j++)
            {
                matrix[i][j]=arr[i].charAt(j);
            }
        }
        return matrix;
    }
}

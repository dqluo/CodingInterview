package leetCode.gimmick;

import util.ArrayListUtil;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * User: xinyuwan, Date: 12/11/13, Time: 10:37 PM
 */
public class RecurringPattern
{
    /*
     * Total problems: 4
     */
    public static void main(String[] args)
    {
        //test PascalTriangle
        PascalsTriangle pt=new PascalsTriangle();
        System.out.println("The first 5 row of Pascal Triangle:");
//        System.out.println(pt.generate(5));

        //test PascalTriangle2
        PascalsTriangle2 pt2=new PascalsTriangle2();
        System.out.println("The row at index 4 is: " + pt2.getRow(4));

        //test Triangle
        Triangle t=new Triangle();
        Integer[][] arr={ {2},
                      {3,4},
                      {6,5,7},
                      {4,1,8,3}};
        ArrayList<ArrayList<Integer>> list=new ArrayList<ArrayList<Integer>>();
        for(Integer[] a : arr)
        {
            list.add(new ArrayList<Integer>(Arrays.asList(a)));
        }
        System.out.println("Min path sum in triangle: "+t.minimumTotal(list));


        //test GrayCode
        GrayCode gc=new GrayCode();
        System.out.println("Print gray code from 0 to 4: ");
        for(int i=0; i<=4; i++)
            System.out.println(i+" digit: "+gc.grayCode(i));
    }
}

//class PascalsTriangle
//{
//    /*
//     * Problem: Given numRows, generate the first numRows of Pascal's triangle.
//     * For example, given numRows = 5, Return
//     * [
//            [1],
//           [1,1],
//          [1,2,1],
//         [1,3,3,1],
//        [1,4,6,4,1]
//       ]
//     *
//     * Analysis: find the pattern that for current list, the length=prev.length+1, if we left justify the list,
//     * we will get the fomula: current[j]=prev[j]+prev[j-1]. We add two 1s at the begin and end, which together
//     * form the list for each level
//     */
//    public ArrayList<ArrayList<Integer>> generate(int numRows)
//    {
//        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
//        if(numRows<=0)
//            return result;
//        ArrayList<Integer> current=new ArrayList<Integer>();
//        current.add(1);
//        result.add(current);
//        for(int i=1; i<numRows; i++)
//        {
//            ArrayList<Integer> prev=current;
//            current=new ArrayList<Integer>();
//            //add the first 1
//            current.add(1);
//            //from index 1 to i-1, we need to calculate based on prev value
//            for(int j=1; j<i; j++)
//            {
//                current.add(prev.get(j)+prev.get(j-1));
//            }
//            //add the last 1
//            current.add(1);
//            result.add(current);
//        }
//        return result;
//    }
//}

class PascalsTriangle2
{
    /*
     * Given an index k, return the kth row of the Pascal's triangle.
     * For example, given k = 3, Return [1,3,3,1].
     * Note: Could you optimize your algorithm to use only O(k) extra space?
     *
     * Analysis:
     * 1 0 0 0
     * 1 1 0 0
     * 1 2 1 0
     * 1 3 3 1
     * The logic is the same, except we go from the back to front, and we assign rowIndex+1 elements(to be 0)
     * at the begining, and iterate row by row, set the last element first, and go from index last-1 to 1.
     */
    public ArrayList<Integer> getRow(int rowIndex)
    {
        ArrayList<Integer> result=new ArrayList<Integer>(rowIndex+1);
        //Initialize the element to be 0
        for(int i=0; i<=rowIndex; i++)
            result.add(0);
        result.set(0, 1);
        for(int i=1; i<=rowIndex; i++)
        {
            result.set(i, 1);
            for(int j=i-1; j>0; j--)
                result.set(j, result.get(j)+result.get(j-1));
        }
        return result;
    }
}

//class Triangle
//{
//    /*
//     * Problem: Given a triangle, find the minimum path sum from top to bottom.
//     * Each step you may move to adjacent numbers on the row below.
//     * For example, given the following triangle
//     *
//     * [
//     *    [2],
//     *   [3,4],
//     *  [6,5,7],
//     * [4,1,8,3]
//     * ]
//     * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
//     * Bonus point if you are able to do this using only O(n) extra space,
//     * where n is the total number of rows in the triangle.
//     *
//     * Analysis: define cache[j] as min path sum value from the bottom to current level's j position.
//     * cache[j]=currentLevelList.get(j)+Min(cache[j], cache[j+1])
//     * We need to calculate cache[0].
//     */
//    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle)
//    {
//        if(triangle.isEmpty())
//            return 0;
//        ArrayList<Integer> lastList=triangle.get(triangle.size()-1);
//        //initialize the cache with elements in last row
//        int[] cache=new int[lastList.size()];
//        for(int i=0; i<lastList.size(); i++)
//            cache[i]=lastList.get(i);
//        //go from last-1 to 0
//        for(int i=triangle.size()-2; i>=0; i--)
//        {
//            ArrayList<Integer> list=triangle.get(i);
//            for(int j=0; j<list.size(); j++)
//            {
//                cache[j]=list.get(j)+Math.min(cache[j], cache[j+1]);
//            }
//        }
//        return cache[0];
//    }
//}
//
//
//class GrayCode
//{
//    /*
//     * Problem: The gray code is a binary numeral system where two successive values differ in only one bit.
//     * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
//     * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
//     * 00 - 0
//     * 01 - 1
//     * 11 - 3
//     * 10 - 2
//     * Note:
//     * For a given n, a gray code sequence is not uniquely defined.
//     * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
//     *
//     * Analysis:
//     * Let's take a look at the following example:
//     * 2            =>          3
//     * 00          00          000
//     * 01          01          001
//     * 11     =>   11      =>  011
//     * 10          10          010
//     *             10          110
//     *             11          111
//     *             01          101
//     *             00          100
//
//     * From the above example we can see the difference between 2 and 3.
//     * First, 'mirror' the sequence.(the red sequence is mirrored from the original sequence)
//     * Then, add 1 in front of the new codes.(the green digits are added)
//     */
//    public ArrayList<Integer> grayCode(int n)
//    {
//        ArrayList<Integer> result=new ArrayList<Integer>();
//        result.add(0);
//        if(n==0)
//            return result;
//        int num=1;
//        for(int i=1; i<=n; i++)
//        {
//            int size=result.size();
//            for(int j=size-1; j>=0; j--)
//                result.add(num+result.get(j));
//            num<<=1;
//        }
//
//        return result;
//
//    }
//
//    /*
//     *   Method2: math formula
//     */
//    public ArrayList<Integer> grayCode2(int n)
//    {
//        ArrayList<Integer> result=new ArrayList<Integer>();
//        int size=1<<n;
//        for(int i=0; i<size; i++)
//        {
//            result.add((i >> 1) ^ i);
//        }
//        return result;
//    }
//}

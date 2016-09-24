package leetCode.gimmick;

import util.ArrayListUtil;

import java.util.ArrayList;

/**
 * User: xinyuwan, Date: 12/11/13, Time: 10:37 PM
 */
public class PascalsTriangle
{
    public static void main(String[] args)
    {
        ArrayList<ArrayList<Integer>> result=new PT().generate(5);
        ArrayListUtil.print(result);
    }
}

class PT
{
    /*
     * Problem1: Given numRows, generate the first numRows of Pascal's triangle.
     * For example, given numRows = 5, Return
     * [
            [1],
           [1,1],
          [1,2,1],
         [1,3,3,1],
        [1,4,6,4,1]
       ]
     */
    public ArrayList<ArrayList<Integer>> generate(int numRows)
    {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        if(numRows<=0)
            return result;
        ArrayList<Integer> current=new ArrayList<Integer>();
        current.add(1);
        result.add(current);
        if(numRows==1)
            return result;
        for(int i=1; i<numRows; i++)
        {
            ArrayList<Integer> prev=current;
            current=new ArrayList<Integer>();
            current.add(1);
            for(int j=0; j<i-1; j++)
            {
                current.add(prev.get(j)+prev.get(j+1));
            }
            current.add(1);
            result.add(current);
        }
        return result;
    }
}

class PT2
{
    /*
     * Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?

Discuss


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

class Triangle
{
    /*
     * Problem: Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
     * For example, given the following triangle
     *
     * [
     *    [2],
     *   [3,4],
     *  [6,5,7],
     * [4,1,8,3]
     * ]
     * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
     * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
     */
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle)
    {
        if(triangle.isEmpty())
            return 0;
        ArrayList<Integer> lastList=triangle.get(triangle.size()-1);
        int[] cache=new int[lastList.size()];
        for(int i=0; i<lastList.size(); i++)
            cache[i]=lastList.get(i);
        for(int i=triangle.size()-2; i>=0; i--)
        {
            ArrayList<Integer> list=triangle.get(i);
            for(int j=0; j<list.size(); j++)
            {
                cache[j]=list.get(j)+Math.min(cache[j], cache[j+1]);
            }
        }
        return cache[0];
    }
}

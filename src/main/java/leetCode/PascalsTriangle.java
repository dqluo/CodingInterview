package leetCode;

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

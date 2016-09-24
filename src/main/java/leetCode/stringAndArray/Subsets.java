package leetCode.stringAndArray;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * User: xinyuwan, Date: 1/8/14, Time: 3:03 PM
 */
public class Subsets
{
    /*
     * Total problems: 2
     */
    public static void main(String[] args)
    {
        int[] set1={8, 6, 9};
        int[] set2={2, 1, 1, 5};

        Subsets s=new Subsets();
        System.out.println(s.subsets(set1));
        System.out.println(s.subsets2(set1));

        Subsets2 s2=new Subsets2();
        System.out.println(s2.subsetsWithDup(set2));
    }
    /*
     * Solution using iteration
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] S)
    {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        if(S==null)
            return null;
        Arrays.sort(S);
        result.add(new ArrayList<Integer>());
        for(int i=0; i<S.length; i++)
        {
            int current=S[i];
            ArrayList<ArrayList<Integer>> moreSets=new ArrayList<ArrayList<Integer>>();
            for(ArrayList<Integer> subset : result)
            {
                ArrayList<Integer> newSubset=new ArrayList<Integer>();
                newSubset.addAll(subset);
                newSubset.add(current);
                moreSets.add(newSubset);
            }
            result.addAll(moreSets);
        }
        return result;
    }
    /*
     * Solution using bit manipulation
     */
    public ArrayList<ArrayList<Integer>> subsets2(int[] S)
    {
         ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
         if(S==null)
             return null;
         //we need to generate a max that is pow(2, n) by do a left shift n bits to 1.
         int max=1 << S.length;
         Arrays.sort(S);
         for(int i=0; i<max; i++)
         {
             ArrayList<Integer> list=convertNumToSet(i, S);
             result.add(list);
         }
         return result;
    }
    private ArrayList<Integer> convertNumToSet(int k, int[] S)
    {
         ArrayList<Integer> result=new ArrayList<Integer>();
         int index=0;
         for(int i=k; i>0; i>>=1)
         {
             if((i&1)==1)
                 result.add(S[index]);
             index++;
         }
         return result;
    }
}

class Subsets2
{
    /*
     * Problem: Given a collection of integers that might contain duplicates, S, return all possible subsets.
     * Note: Elements in a subset must be in non-descending order.
     * The solution set must not contain duplicate subsets.
     * For example, If S = [1,2,2], a solution is:
     * [[2],
     * [1],
     * [1,2,2],
     * [2,2],
     * [1,2],
     * []]
     */
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num)
    {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        if(num==null)
            return null;
        Arrays.sort(num);
        result.add(new ArrayList<Integer>());
        /*
         * The only difference between this problem and subset1 is that we only
         * extend a subset when the current element is not dup of previous:
         * e.g. [[], [1]]
         * the current element is 2, 2!=1, so we can extend both [] and [1];
         *[[], [1], [2], [1,2]]
         * now, the current element is still 2, and 2==2, because we have extended [][1] using
         * 2 in last iteration, we don't have to do that in this iteration, we only start from
         * the first new subset after extending the previous subset, which corresponds to index=previous size;
         * We use start to keep the value of the index where we should begin extend subset in the current iteration,
         * every time if we finish adding all the new extended subsets into result, we need to check if the next
         * element is equal to current, if so we update start to size, otherwise set start to 0
         */
        int start=0;
        for(int i=0; i<num.length; i++)
        {
            int current=num[i];
            int size=result.size();
            /*
             * Note here, we cannot use j<result.size(), otherwise will run out of memory.
             * This is becuase we add a newsubset to result everytime, which will update the
             * result.size(), and the loop will therefore never ends
             */
            for(int j=start; j<size; j++)
            {
                ArrayList<Integer> newSubset=new ArrayList<Integer>();
                newSubset.addAll(result.get(j));
                newSubset.add(current);
                result.add(newSubset);
            }
            if(i<num.length-1 && num[i]==num[i+1])
                start=size;
            else
                start=0;
        }
        return result;
    }
}

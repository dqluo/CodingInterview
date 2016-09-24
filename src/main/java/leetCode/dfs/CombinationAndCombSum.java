package leetCode.dfs;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * User: xinyuwan, Date: 12/23/13, Time: 1:55 AM
 */
public class CombinationAndCombSum
{
    /*
     * Total problems: 3
     */
    public static void main(String[] args)
    {
        //test Combinations
        Combinations c=new Combinations();
        System.out.print("C2/4: ");
        System.out.println(c.combine(3, 2));
        System.out.print("C3/5: ");
        System.out.println(c.combine(5, 3));

        //test CombinationSum
        int[] a={1, 2};
        CombinationSum cs=new CombinationSum();
        System.out.print("Combination sum 5: ");
        System.out.println(cs.combinationSum(a, 5));

        //test CombinationSum2
        int[] b={1, 2, 2, 3};
        CombinationSum2 cs2=new CombinationSum2();
        System.out.print("Combination sum 5: ");
        System.out.println(cs2.combinationSum2(b, 5));

    }
}

class Combinations
{
    /*
     * Problem: Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     * For example, If n = 4 and k = 2, a solution is:
     * [ [2,4],
         [3,4],
         [2,3],
         [1,2],
         [1,3],
         [1,4]]
     */
    public ArrayList<ArrayList<Integer>> combine(int n, int k)
    {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        int[] comb=new int[k];
        combine(n, k, comb, result);
        return result;
    }
    private void combine(int n, int k, int[] comb, ArrayList<ArrayList<Integer>> result)
    {
        //k is responsible for controlling the base case, only when k==0 we add comb to result
        if(k == 0)
        {
            result.add(cloneToList(comb));
            return;
        }
        for(int i=n; i>=1; i--)
        {
            //put the larger element in larger position
            comb[k-1]=i;
            //k-1 reduces the recursion level, i-1 reduces the span of for loop
            combine(i-1, k-1, comb, result);
        }
    }
    private ArrayList<Integer> cloneToList(int[] a)
    {
        ArrayList<Integer> list=new ArrayList<Integer>();
        for(int i=0; i<a.length; i++)
            list.add(a[i]);
        return list;
    }
}


class CombinationSum
{
    /*
     * Problem: Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
     * The same repeated number may be chosen from C unlimited number of times.
     * Note: All numbers (including target) will be positive integers.
     * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
     * The solution set must not contain duplicate combinations.
     * For example, given candidate set 2,3,6,7 and target 7,
     * A solution set is:  [7]  [2, 2, 3]
     */
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target)
    {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        int[] solution=new int[target];
        Arrays.sort(candidates);
        combinationSum(candidates, target, 0, solution, 0, result);
        return result;
    }
    private void combinationSum(int[] candidates, int target, int start, int[] solution, int index, ArrayList<ArrayList<Integer>> result)
    {
        if(target == 0)
        {
            result.add(cloneToList(solution, index-1));
            return;
        }
        //it's crucial to check target-candidate[i]>=0, since we don't what target to be negative when
        //passing down. Also, since array is sorted, when target < candidates[i], we don't need to go further in the loop
        for(int i=start; i<candidates.length && target-candidates[i]>=0; i++)
        {
            solution[index]=candidates[i];
            combinationSum(candidates, target-candidates[i], i, solution, index+1, result);
        }
    }
    private ArrayList<Integer> cloneToList(int[] array, int last)
    {
        ArrayList<Integer> list=new ArrayList<Integer>();
        for(int i=0; i<=last; i++)
            list.add(array[i]);
        list.trimToSize();
        return list;
    }
}

class CombinationSum2
{
    /*
     * Problem:Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
     * Each number in C may only be used once in the combination.
     * Note: All numbers (including target) will be positive integers.
     * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
     * The solution set must not contain duplicate combinations.
     * For example, given candidate set 10,1,2,7,6,1,5 and target 8,
     * A solution set is:  [1, 7] [1, 2, 5] [2, 6] [1, 1, 6]
     */
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target)
    {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        //since no repeat is allowed, the max length of solution is the length of num
        int[] solution=new int[num.length];
        Arrays.sort(num);
        combinationSum2(num, target, 0, solution, 0, result);
        return result;
    }
    private void combinationSum2(int[] num, int target, int start, int[] solution, int index, ArrayList<ArrayList<Integer>> result)
    {
        if(target == 0)
        {
            result.add(cloneToList(solution, index-1));
            return;
        }
        /*
         * The only diff here is that num can have dups, so we skip the same element as previous
         * because, if we have dfs the previous element, we have covered every possible combinations
         * that starts with the previous element, so no need to do it to the current(we cannot store dup sets in result)
         */
        int previous=-1;
        for(int i=start; i<num.length && target-num[i]>=0; i++)
        {
            if(num[i]!=previous)
            {
                solution[index]=num[i];
                //advance start in each iteration, since this time we cannot use the element more than one time
                combinationSum2(num, target-num[i], i+1, solution, index+1, result);
                previous=num[i];
            }
        }
    }
    private ArrayList<Integer> cloneToList(int[] array, int last)
    {
        ArrayList<Integer> list=new ArrayList<Integer>();
        for(int i=0; i<=last; i++)
            list.add(array[i]);
        list.trimToSize();
        return list;
    }
}

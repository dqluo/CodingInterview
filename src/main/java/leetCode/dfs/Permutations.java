package leetCode.dfs;

import util.ArrayListUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * User: xinyuwan, Date: 12/15/13, Time: 5:53 PM
 */
public class Permutations
{
    /*
     * Total problems: 2
     */
    public static void main(String[] args)
    {
        int[] a={1, 2, 3};
        Permutations p=new Permutations();
        System.out.print("Permutations of [1, 2, 3]: ");
        System.out.println(p.permute(a));
        System.out.print("Permutations of [1, 2, 3]: ");
        System.out.println(p.permute2(a, 0));

        int[] b={1, 1, 1, 2};
        Permutations2 p2=new Permutations2();
        ArrayList<ArrayList<Integer>> result=p2.permuteUnique(b);
        System.out.print("Permutations of [1, 1, 1, 2]: ");
        System.out.println(p2.permuteUnique(b));
        System.out.print("Permutations of [1, 1, 1, 2]: ");
    }

    /*
     * Problem: Given a collection of numbers, return all possible permutations.
     *
     * For example, [1,2,3] have the following permutations:
     * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
     *
     * Analysis: method1 uses dfs by traverse down each possible perm starting with num[i], and recursively
     * goes down to the base case of num.length=perm.size(). Note, to avoid select the same number in different
     * recursion level, we need to keep a boolean array to keep track of the visited elements.
     * For a single perm, we use ArrayList<Integer> to store.
     * Pros: when hitting the base case, we only need to clone() it once to the final result(if using array,
     * we need to Arrays.asList() first, and then new ArrayList());
     * Cons: We recurse down and return up, we have to delete the added element.
     */
    public ArrayList<ArrayList<Integer>> permute(int[] num)
    {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        int n=num.length;
        if(n==0)
            return result;
        boolean[] visited=new boolean[n];
        permute(num, visited, new ArrayList<Integer>(n), result);
        return result;
    }
    private void permute(int[] num, boolean[] visited, ArrayList<Integer> perm, ArrayList<ArrayList<Integer>> result)
    {
        if(perm.size()==num.length)
        {
            result.add((ArrayList<Integer>)perm.clone());
            return;
        }
        for(int i=0; i<num.length; i++)
        {
            if(!visited[i])
            {
                perm.add(num[i]);
                visited[i]=true;
                permute(num, visited, perm, result);
                visited[i]=false;
                perm.remove(perm.size()-1);
            }
        }
    }
    /*
     * Analysis: method2 is more like bfs thought, construct the result level by level, insert current
     * element to every number in the perm of the previous result.
     */
    private ArrayList<ArrayList<Integer>> permute2(int[] num, int index)
    {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        if(index == num.length)
        {
            result.add(new ArrayList<Integer>());
            return result;
        }
        int current=num[index];
        ArrayList<ArrayList<Integer>> perms=permute2(num, index+1);
        for(ArrayList<Integer> perm : perms)
        {
            int size=perm.size();

            for(int i=0; i<=size; i++)
            {
                ArrayList<Integer> newPerm=insertAt(perm, current, i);
                result.add(newPerm);
            }
        }
        return result;
    }
    //this method insert num to index, and returns ArrayList.
    private ArrayList<Integer> insertAt(ArrayList<Integer> list, int num, int index)
    {
        if(index<0 || index >list.size())
            return list;
        ArrayList<Integer> result=new ArrayList<Integer>();
        //to avoid shifting elements, first copy list[0..index-1], then add num, and then copy the rest
        for(int i=0; i<index; i++)
            result.add(list.get(i));
        result.add(num);
        for(int i=index; i<list.size(); i++)
            result.add(list.get(i));
        return result;
    }
}

class Permutations2
{
    /*
     * Problem: Given a collection of numbers that might contain duplicates, return all possible unique permutations.
     *
     * For example, [1,1,2] have the following unique permutations:
     * [1,1,2], [1,2,1], and [2,1,1].
     *
     */
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num)
    {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        result.addAll(permuteUnique(num, 0));
        return result;
    }

    private Set<ArrayList<Integer>> permuteUnique(int[] num, int index)
    {
        Set<ArrayList<Integer>> result=new HashSet<ArrayList<Integer>>();
        if(index == num.length)
        {
            result.add(new ArrayList<Integer>());
            return result;
        }
        int current=num[index];
        Set<ArrayList<Integer>> perms=permuteUnique(num, index+1);
        for(ArrayList<Integer> perm : perms)
        {
            int size=perm.size();
            for(int i=0; i<=size; i++)
            {
                //this can filter some dup cases(like 1, 1, 2) but we still need hashset
                if(i > 0 && current == perm.get(i-1))
                    continue;
                ArrayList<Integer> newPerm=insertAt(perm, current, i);
                result.add(newPerm);
            }
        }
        return result;
    }
    private ArrayList<Integer> insertAt(ArrayList<Integer> list, int num, int index)
    {
        if(index<0 || index >list.size())
            return list;
        ArrayList<Integer> result=new ArrayList<Integer>();
        for(int i=0; i<index; i++)
            result.add(list.get(i));
        result.add(num);
        for(int i=index; i<list.size(); i++)
            result.add(list.get(i));
        return result;
    }

    public ArrayList<ArrayList<Integer>> permuteUnique2(int[] num)
    {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        int n=num.length;
        if(n==0)
            return result;
        Set<ArrayList<Integer>> set=new HashSet<ArrayList<Integer>>();
        permuteUnique2(num, new boolean[n], new ArrayList<Integer>(n), set);
        result.addAll(set);
        return result;
    }
    private void permuteUnique2(int[] num, boolean[] visited, ArrayList<Integer> perm, Set<ArrayList<Integer>> result)
    {
        if(perm.size()==num.length)
        {
            result.add((ArrayList<Integer>)perm.clone());
            return;
        }
        for(int i=0; i<num.length; i++)
        {
            if(!visited[i])
            {
                perm.add(num[i]);
                visited[i]=true;
                permuteUnique2(num, visited, perm, result);
                visited[i]=false;
                perm.remove(perm.size()-1);
            }
        }
    }
}

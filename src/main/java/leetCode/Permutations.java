package leetCode;

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
    public static void main(String[] args)
    {
        int[] num={1, 1, 1, 2};
        Perms p=new Perms();
        ArrayList<ArrayList<Integer>> result=p.permute(num);
     //   ArrayListUtil.print(result);
        ArrayListUtil.print(p.permute2(num, 0));
    }
}

class Perms
{
    /*
     * Problem: Given a collection of numbers, return all possible permutations.
     */
    ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
    public ArrayList<ArrayList<Integer>> permute(int[] num)
    {
        int n=num.length;
        if(n==0)
            return result;
        Integer[] perm=new Integer[n];
        boolean[] visited=new boolean[n];
        permute(num, visited, perm, 0);
        return result;
    }
    private void permute(int[] num, boolean[] visited, Integer[] perm, int index)
    {
        if(index==num.length)
        {
            result.add(new ArrayList<Integer>(Arrays.asList(perm)));
            return;
        }
        for(int i=0; i<num.length; i++)
        {
            if(!visited[i])
            {
                perm[index]=num[i];
                visited[i]=true;
                permute(num, visited, perm, index+1);
                visited[i]=false;
            }
        }
    }

    public ArrayList<ArrayList<Integer>> permute2(int[] num, int index)
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

}

class PermsUnique
{
    /*
     * Problem: Given a collection of numbers that might contain duplicates, return all possible unique permutations.
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
}

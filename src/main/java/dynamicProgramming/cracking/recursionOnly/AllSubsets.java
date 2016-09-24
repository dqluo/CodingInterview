package dynamicProgramming.cracking.recursionOnly;

import java.util.ArrayList;

/**
 * User: xinyuwan, Date: 12/4/13, Time: 2:59 PM
 */
public class AllSubsets
{
    /*
     * Problem: write a function to return all subsets of a set(powerset)
     */
    public static void main(String[] args)
    {
        ArrayList<Integer> set=new ArrayList<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);
        System.out.println(getSubsets(set, 0));
        System.out.println(getSubsets2(set));
        System.out.println(getSubsets3(set));
    }

    public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index)
    {
        ArrayList<ArrayList<Integer>> allSubset=null;
        if(index == set.size())
        {
            allSubset=new ArrayList<ArrayList<Integer>>();
            allSubset.add(new ArrayList<Integer>());
            return allSubset;
        }
        allSubset=getSubsets(set, index+1);
        int currentElement=set.get(index);
        ArrayList<ArrayList<Integer>> moreSubset=new ArrayList<ArrayList<Integer>>();
        for(ArrayList<Integer> subset : allSubset)
        {
            ArrayList<Integer> newSubset=new ArrayList<Integer>();
            newSubset.addAll(subset);
            newSubset.add(currentElement);
            moreSubset.add(newSubset);
        }
        allSubset.addAll(moreSubset);
        return allSubset;
    }

    public static ArrayList<ArrayList<Integer>> getSubsets2(ArrayList<Integer> set)
    {
        ArrayList<ArrayList<Integer>> allSubsets=new ArrayList<ArrayList<Integer>>();
        int max=1 << set.size();
        for(int i=0; i<max; i++)
            allSubsets.add(convertIntToSet(i, set));
        return allSubsets;
    }
    private static ArrayList<Integer> convertIntToSet(int k, ArrayList<Integer> set)
    {
        ArrayList<Integer> resultSet=new ArrayList<Integer>();
        int index=0;
        for(int i=k; i>0; i>>=1)
        {
            if((i & 1) ==1)
                resultSet.add(set.get(index));
            index++;
        }
        return resultSet;
    }

    public static ArrayList<ArrayList<Integer>> getSubsets3(ArrayList<Integer> set)
    {
        ArrayList<ArrayList<Integer>> allSubsets=new ArrayList<ArrayList<Integer>>();
        allSubsets.add(new ArrayList<Integer>());
        for(int i=0; i<set.size(); i++)
        {
            int currentElement=set.get(i);
            /*
             * we need to create a new ArrayList moreSubset to prevent
             * ConcurrentModificationException; in other word, do not modify a
             * collection while iterating through it
             */
            ArrayList<ArrayList<Integer>> moreSubset=new ArrayList<ArrayList<Integer>>();
            for(ArrayList<Integer> subset : allSubsets)
            {
                ArrayList<Integer> newSubset=new ArrayList<Integer>();
                newSubset.addAll(subset);
                newSubset.add(currentElement);
                moreSubset.add(newSubset);
            }
            allSubsets.addAll(moreSubset);
        }
        return allSubsets;
    }
}

package leetCode.searchAndSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * User: xinyuwan, Date: 1/13/14, Time: 4:49 PM
 */
public class BucketSortVariation {
    public static void main(String[] args) {
        //test FirstMissingPositive
        int[] a = {0, 1, 2};
        int[] a1 = {3, 4, -1, 1};
        int[] a2 = {2, 2, 1};
        FirstMissingPositive fmp = new FirstMissingPositive();
        System.out.println("In a, first missing positive is " + fmp.firstMissingPositive(a));
        System.out.println("In a1, first missing positive is " + fmp.firstMissingPositive(a1));
        System.out.println("In a2, first missing positive is " + fmp.firstMissingPositive(a2));

        //test Anagram
        String[] arr = {"aoe", "eao", "cat", "tca", "mccc", "wasd", "atc"};
        Anagrams anagrams = new Anagrams();
        System.out.println("Anagrams: " + anagrams.anagrams(arr));
    }
}

class FirstMissingPositive {
    /*
     * Problem: Given an unsorted integer array, find the first missing positive integer.
     * For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2.
     * Your algorithm should run in O(n) time and uses constant space.
     *
     * Analysis: To obtain O(n) time complexity, we need to resolve to a "bucket sort" idea.
     * ideally, the array should be like {1, 2, 3} for index {0, 1, 2} if we sort the array.
     * However, since we miss one positive, and the array is not ordered, we can swap the element
     * to where it should be i -> i+1, index A[i]-1 -> A[i]. We need to pay special attention to
     * the corner cases:
     * 1. A[i] is not in the range of Array's index
     * 2. A[i] is already set to be i+1
     * 3. A[A[i]-1] is already set to be A[i]
     * we'll pass these three cases and only swap A[i] with A[A[i]-1] if none of above is true
     */
    public int firstMissingPositive(int[] A) {
        //Note the basecase: if A is empty, we make it return 1
        if (A.length == 0)
            return 1;
        for (int i = 0; i < A.length; ) {
            if (A[i] != i + 1 && (A[i] > 0 && A[i] < A.length + 1) && A[A[i] - 1] != A[i])
                swap(A, i, A[i] - 1);
            else
                i++;
        }
        for (int i = 0; i < A.length; i++) {
            if (A[i] != i + 1)
                return i + 1;
        }
        return A.length + 1;
    }

    private void swap(int[] A, int i, int j) {
        if (i == j)
            return;
        A[i] = A[i] ^ A[j];
        A[j] = A[i] ^ A[j];
        A[i] = A[i] ^ A[j];
    }
}

class Anagrams {
    /*
     * Problems: Given an array of strings, return all groups of strings that are anagrams.
     * Note: All inputs will be in lower-case.
     */
    public ArrayList<String> anagrams(String[] strs) {
        ArrayList<String> result = new ArrayList<String>();
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (String str : strs) {
            String sortedStr = sortChars(str);
            ArrayList<String> list = null;
            if (map.containsKey(sortedStr))
                list = map.get(sortedStr);
            else {
                list = new ArrayList<String>();
                map.put(sortedStr, list);
            }
            list.add(str);
        }
        for (String key : map.keySet()) {
            //Note here, we only add the list that has more than one string anagram
            ArrayList<String> anagrams = map.get(key);
            if (anagrams.size() > 1)
                result.addAll(anagrams);
        }
        return result;
    }

    private String sortChars(String s) {
        char[] c = s.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }
}


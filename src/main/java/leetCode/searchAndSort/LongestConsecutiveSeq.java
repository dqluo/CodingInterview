package leetCode.searchAndSort;

import java.util.HashMap;

/**
 * User: xinyuwan, Date: 12/20/13, Time: 4:18 PM
 */
public class LongestConsecutiveSeq {
    public static void main(String[] args) {
        int[] arr = {100, 26, 28, 19, 4, 27, 2, 18, 1, 3, 29, 25};
        LongestConsecutiveSeq lcs = new LongestConsecutiveSeq();
        System.out.println("Longest consecutive sequence: " + lcs.longestConsecutive(arr));
    }

    /*
     * Problem: Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
     * For example, Given [100, 4, 200, 1, 3, 2],
     * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
     * Your algorithm should run in O(n) complexity.
     */
    public int longestConsecutive(int[] num) {
        HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        for (int i = 0; i < num.length; i++)
            map.put(num[i], true);
        int maxCount = 0;
        for (int i = 0; i < num.length; i++) {
            int count = 0;
            int value = num[i];
            //start checking the larger numbers sequence
            while (map.containsKey(value)) {
                map.remove(value);
                value++;
                count++;
            }
            //then check the smaller numbers sequence
            value = num[i] - 1;
            while (map.containsKey(value)) {
                map.remove(value);
                value--;
                count++;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }
}

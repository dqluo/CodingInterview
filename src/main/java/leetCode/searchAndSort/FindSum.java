package leetCode.searchAndSort;

import util.ArrayListUtil;

import java.util.*;

/**
 * User: xinyuwan, Date: 12/26/13, Time: 1:03 PM
 */
public class FindSum {
    /*
     * Total Problems: 4
     */
    public static void main(String[] args) {
        //test for ThreeSum
        int[] a3 = {-1, 0, 1, 2, -1, -4};
        ThreeSum ths = new ThreeSum();
        System.out.println("Result for three sum:");
        ArrayListUtil.print(ths.threeSum(a3));

        //test for ThreeSumClosest
        int[] a4 = {0, 2, 1, -3};
        ThreeSumClosest thsc = new ThreeSumClosest();
        System.out.println(thsc.threeSumClosest(a4, 1));
    }
}

class TwoSum {
    /*
     * Problem: Given an array of integers, find two numbers such that they add up to a specific target number.
     * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
     * You may assume that each input would have exactly one solution.
     * Input: numbers={2, 7, 11, 15}, target=9
     * Output: index1=1, index2=2
     *
     * Analysis: Because we insert into hashtable while we traverse the array, we don't have to check situation like
     * 6=3+3 && 3 only appears once in the array. Also, since we can only discover the two elements when we
     * iterate to the second one, we need to put i to result[1] and the other to result[0] to keep order
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                result[1] = i;
                result[0] = map.get(target - numbers[i]);
                break;
            } else
                map.put(numbers[i], i);
        }
        return result;
    }
}

class FourSum {
    /*
     * Problem: Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
     * Note: Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
     * The solution set must not contain duplicate quadruplets.
     *
     * For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
     * A solution set is:
     * (-1,  0, 0, 1)
     * (-2, -1, 1, 2)
     * (-2,  0, 0, 2)
     *
     * Analysis: we need to use a Set to store the final solutions since they might be dup when array has dup elements.
     */
    public static void main(String[] args) {
        int[] a = {0, 0, 0, 0};
        FourSum fs = new FourSum();
        ArrayListUtil.print(fs.fourSum(a, 0));
    }

    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        Set<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
        Arrays.sort(num);
        for (int h = 0; h < num.length - 3; h++) {
            for (int k = h + 1; k < num.length - 2; k++) {
                int i = k + 1, j = num.length - 1;
                while (i < j) {
                    int sum = num[h] + num[k] + num[i] + num[j];
                    if (sum == target) {
                        ArrayList<Integer> solution = new ArrayList<Integer>();
                        solution.add(num[h]);
                        solution.add(num[k]);
                        solution.add(num[i]);
                        solution.add(num[j]);
                        set.add(solution);
                        i++;
                        j--;
                    } else if (sum < target)
                        i++;
                    else
                        j--;
                }
            }
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        result.addAll(set);
        return result;
    }
}

class ThreeSum {
    /*
     * Problem: Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
     * Note:
     * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
     * The solution set must not contain duplicate triplets.
     * For example, given array S = {-1 0 1 2 -1 -4},
     * A solution set is:
     * (-1, 0, 1)
     * (-1, -1, 2)
     *
     * Analysis: we can apply the same approach in FourSum, but we use a different approach here:
     * by checking if the current element is dup of previous for both k, i, j, we can avoid using a
     * set to do the filtering step. Be very careful that we need check all three indexes for dup cases
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        int n = num.length;
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (n < 3)
            return result;
        Arrays.sort(num);
        for (int k = 0; k <= n - 3; k++) {
            //avoid dup cases: will happen when num[k]==num[k-1], we skip
            //the whole iteration since we've checked every possible combination of i and j
            if (k == 0 || num[k] > num[k - 1]) {
                int i = k + 1, j = n - 1;
                while (i < j) {
                    int sum = num[i] + num[j];
                    if (sum == -num[k]) {
                        ArrayList<Integer> solution = new ArrayList<Integer>();
                        solution.add(num[k]);
                        solution.add(num[i]);
                        solution.add(num[j]);
                        result.add(solution);
                        i++;
                        j--;
                        while (i < j && num[i] == num[i - 1]) //avoid dup case for num[i]
                            i++;
                        while (i < j && num[j] == num[j + 1]) //avoid dup case for num[j]
                            j--;
                    } else if (sum < -num[k])
                        i++;
                    else
                        j--;
                }
            }
        }
        return result;
    }
}

class ThreeSumClosest {
    /*
     * Problem: Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
     * Return the sum of the three integers. You may assume that each input would have exactly one solution.
     * For example, given array S = {-1 2 1 -4}, and target = 1.
     * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     */
    public int threeSumClosest(int[] num, int target) {
        Arrays.sort(num);
        int closeSum = 0, min = Integer.MAX_VALUE;
        for (int k = 0; k < num.length - 2; k++) {
            for (int i = k + 1, j = num.length - 1; i < j; ) {
                int sum = num[k] + num[i] + num[j];
                if (sum == target)
                    return sum;
                else if (sum < target) {
                    //compare with the min separately in both sum < target and sum > target cases
                    if (min > target - sum) {
                        min = target - sum;
                        closeSum = sum;
                    }
                    i++;
                } else {
                    if (min > sum - target) {
                        min = sum - target;
                        closeSum = sum;
                    }
                    j--;
                }
            }
        }
        return closeSum;
    }
}

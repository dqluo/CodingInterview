package leetCode.searchAndSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * User: xinyuwan, Date: 1/13/14, Time: 4:50 PM
 */
public class StartEndIndexIteration {
    /*
     * Total problems: 3
     */
    public static void main(String[] args) {
        //test LongestSubstringWithoutRep
        LongestSubstringWithoutRep lswr = new LongestSubstringWithoutRep();
        String s = "bbbb";
        String s2 = "abcbdef";
        String s3 = "wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco";
        System.out.println("LSWR: " + lswr.lengthOfLongestSubstring(s));
        System.out.println("LSWR: " + lswr.lengthOfLongestSubstring(s2));
        System.out.println("LSWR: " + lswr.lengthOfLongestSubstring2(s3));

        //test MinWindowSubstring
        String S = "ADOBECODEBANC", T = "ABC";
        MinWindowSubstring mws = new MinWindowSubstring();
        System.out.println("Min window for T " + mws.minWindow(S, T));

        //test SubstringWithConcatOfAllWords
        String[] L = {"foo", "bar", "foo", "bar", "the"};
        String str = "foobarbarthefoothefoobarbarfoothe";
        SubstringWithConcatOfAllWords swca = new SubstringWithConcatOfAllWords();
        System.out.println("Substring with concatenation of all words in str has start indexes: ");
        System.out.println(swca.findSubstring(str, L));
    }
}

class LongestSubstringWithoutRep {

    /*
     * Problem: Given a string, find the length of the longest substring without repeating characters. For example,
     * the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb"
     * the longest substring is "b", with the length of 1.
     *
     * By using an array with index representing ASCII chars, we'll get O(1) in looking up if a character had existed in the
     * substring. As we traverse through the string we update the array to mark the char as true or false.
     *
     * When we find a repeated char(e.g. "abcdcedf", when we found the second "c"), it means that the current substring(excluded the
     * repeated char) is a potential max(current is from start to end-1). But, meanwhile it also means that the repeated char must have
     * appeared before at index i, where i is less than end. Note that i could be same as start or larger than start. If the latter is
     * the case, we need to update all the chars from start to i-1 in the array as false, since later on they won't be in our current
     * substring. Because all substrings that start before or at index i would be less than current sum, we can safely start to look
     * for the second substring with head starts at i+1.
     *
     * Therefore, you would need two indices to record the head and the tail of the
     * current substring. Since start and end both traverse at most n steps, the worst case would be 2n steps, which the run time
     * complexity must be O(n).
     *
     * Note, since we only update the max when we encounter a repeat, we need to update the max after we step out from the loop in case
     * we haven't found any repeat in the last iteration.
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty())
            return 0;
        boolean[] cache = new boolean[256];
        int start = 0, end = 0, max = 0;
        while (end < s.length()) {
            char c = s.charAt(end);
            if (cache[c]) {
                max = Math.max(max, end - start);
                while (s.charAt(start) != c) {
                    cache[s.charAt(start)] = false;
                    start++;
                }
                start++;
                end++;
            } else {
                cache[c] = true;
                end++;
            }
        }
        return Math.max(max, end - start);
    }
    public int lengthOfLongestSubstring2(String s) {
        int max=0;
        if(s==null || s.length()==0)
            return max;
        Set<Character> set=new HashSet<Character>();
        int start=0;
        int count=0;
        for(int i=0;i<s.length();i++){
            char temp=s.charAt(i);
            if(set.contains(temp)){
                count=i-start;
                max=Math.max(count, max);
                while(set.contains(temp)){
                    set.remove(s.charAt(start));
                    start++;
                }
                set.add(temp);
                start++;
            }else
                set.add(temp);
        }
        count=s.length()-start;
        max=Math.max(count, max);
        return max;
    }

}

class MinWindowSubstring {
    /*
     * Problem: Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
     * For example, S = "ADOBECODEBANC", T = "ABC", Minimum window is "BANC".
     * Note: If there is no such window in S that covers all characters in T, return the emtpy string "".
     * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
     *
     * Analysis: we need two pointers to mark the start and end of the window. First iterate the end to position i,
     * where S[start, i] covers T[0, len-1]. Then shrink start as much as possible. Finally compare the min length
     * of window with end-start+1. We control this flow by keeping two int[] arrays for both the chars found and need to find.
     * To determine when we can shrink start, use count as marker.
     */
    public String minWindow(String S, String T) {
        if (S == null || T == null || S.isEmpty() || T.isEmpty())
            return "";
        //count records the number of chars of T found in S
        int start = 0, end = 0, count = 0, min = Integer.MAX_VALUE;
        String minWindow = "";
        //these two records the chars of T that need to find, and has found in S
        int[] needToFind = new int[256];
        int[] hasFound = new int[256];
        //construct needToFind by traversing T
        for (int i = 0; i < T.length(); i++)
            needToFind[T.charAt(i)]++;
        for (; end < S.length(); end++) {
            //ignore the case of chars not in T
            if (needToFind[S.charAt(end)] == 0)
                continue;
            //update hasFound as long as we find char in T
            hasFound[S.charAt(end)]++;
            //only update count when we haven't found all chars in T
            if (hasFound[S.charAt(end)] <= needToFind[S.charAt(end)])
                count++;
            //we will hit this after the first time we foudn all chars in T,
            //we will shrink start as much as possible
            if (count == T.length()) {
                //we can advance start in two cases: 1. not char in T; 2 found more chars than need
                while (needToFind[S.charAt(start)] == 0 ||
                        hasFound[S.charAt(start)] > needToFind[S.charAt(start)]) {
                    if (hasFound[S.charAt(start)] > needToFind[S.charAt(start)])
                        hasFound[S.charAt(start)]--;
                    start++;
                }
                if (end - start + 1 < min) {
                    min = end - start + 1;
                    minWindow = S.substring(start, end + 1);
                }
            }
        }
        return minWindow;
    }
}

class SubstringWithConcatOfAllWords {
    /*
     * Problem: You are given a string, S, and a list of words, L, that are all of the same length. Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.
     * For example, given:
     * S: "barfoothefoobarman"
     * L: ["foo", "bar"]
     * You should return the indices: [0,9]. (order does not matter).
     *
     * Analysis: logic is similar to the "window", hashmap could contain the value null
     * 1. put all the element in L into hashmap(key: word, value: number of occur)
     * 2. for each index of the length of the string in array L, we check whether we can find all the strings
     * in string S.
     * 3. if we have did not find a substring in string s, we clear all the key-value pair in hashmap
     * 4. if we found the substring in string s, we continue to find other string in L, if the number of current
     * substring equals to what we need to find, we will remove the start string.
     * 5. No matter the how many substring we have found, if it is what we need to find, we will put them in
     *t he hashmap called hasFound
     */
    public ArrayList<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (L.length == 0)
            return result;
        //first construct needToFind
        HashMap<String, Integer> needToFind = new HashMap<String, Integer>();
        for (int i = 0; i < L.length; i++) {
            if (needToFind.containsKey(L[i]))
                needToFind.put(L[i], needToFind.get(L[i]) + 1);
            else
                needToFind.put(L[i], 1);
        }

        int stringLen = L[0].length();
        //the outside loop only needs to go from 0 to stringLen-1, we need to cover the cases that
        //each beginning word starts from 0, 1, .. stringLen-1, and then we update end index by stringLen for each inside iteration.
        for (int i = 0; i < stringLen; i++) {
            HashMap<String, Integer> hasFound = new HashMap<String, Integer>();
            int start = i;
            //use end to iterate S, each time update end by stringLen
            for (int end = start; end <= S.length() - stringLen; end += stringLen) {
                String current = S.substring(end, end + stringLen);
                if (needToFind.containsKey(current)) {
                    //the first thing to do is to compare the number of occur in hasFound with needToFind,
                    //as long as the former is not less the latter, we need to delete the same substring of current
                    //that appears before current, as well as all other strings in L that found between start and current
                    while (hasFound.get(current) == needToFind.get(current)) {
                        String temp = S.substring(start, start + stringLen);
                        start = start + stringLen;
                        hasFound.put(temp, hasFound.get(temp) - 1);
                    }
                    //we can now safely add current in hasFound
                    if (hasFound.containsKey(current))
                        hasFound.put(current, hasFound.get(current) + 1);
                    else
                        hasFound.put(current, 1);
                    //every time we find a L's string, we need to check count==L.length*stringLen
                    if (end - start + stringLen == stringLen * L.length)
                        result.add(start);
                }
                //we find a string not in L, need to clear the hashmap for reuse. Also, advance start to next possible index
                else {
                    hasFound.clear();
                    start = end + stringLen;
                }
            }
        }
        return result;
    }
}

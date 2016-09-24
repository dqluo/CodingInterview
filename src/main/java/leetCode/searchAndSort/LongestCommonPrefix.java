package leetCode.searchAndSort;

/**
 * User: xinyuwan, Date: 12/21/13, Time: 7:57 PM
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] arr = {"afafa", "afafbmx", "afaf", "afad"};
        LongestCommonPrefix lcp = new LongestCommonPrefix();
        System.out.println("Longest common prefix: " + lcp.longestCommonPrefix(arr));
    }

    /*
     * Problem: Write a function to find the longest common prefix string amongst an array of strings.
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                //for each other str in strs, if it's shorter than i+1 or has a different char, we've found the lcp
                if (i >= strs[j].length() || c != strs[j].charAt(i))
                    return strs[0].substring(0, i);
            }
        }
        //we've reached the end of the strs[0], so we the lcp is strs[0]
        return strs[0];
    }
}

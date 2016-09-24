package leetCode.dp;

/**
 * User: xinyuwan, Date: 1/10/14, Time: 10:16 PM
 */
public class EditDistance
{
    public static void main(String[] args)
    {
        String s1="wonderful";
        String s2="wanted";
        EditDistance ed=new EditDistance();
        System.out.println("Min distance to edit s1 to s2: "+ed.minDistance(s1, s2));
    }
    /*
     * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
     * You have the following 3 operations permitted on a word:
     * a) Insert a character
     * b) Delete a character
     * c) Replace a character
     *
     * Analysis:
     * define cache[i][j] as the minDistance between s[0..i] and s[0..j], such that
     * cache[i][j] =  min (cache[i-1][j-1]+d -- if s[i]==s[j] d=0, else d=1 (replace s[i] with s[j])
     *                     cache[i-1][j]+1 -- if s[i-1]==s[j] (delete s[i])
     *                     cache[i][j-1]+1 -- if s[i]==s[j-1] (insert s[j]) )
     * If we add one more row and column at the beginning, cache[0][j] means the minDistance
     * to transform "" to s[0..j], which will be j; also, cache[i][0]=i and we need to initialize
     * the cache this way.
     */
    public int minDistance(String word1, String word2)
    {
        //use 1-D array cache to store intermediate result
        int[] cache=new int[word2.length()+1];
        //initialize the cache
        for(int i=0; i<=word2.length(); i++)
            cache[i]=i;
        //from here we contruct the cache row by row, for each column in row, we
        //do it as if a real matrix exists. leftTop store the value at the left diagonal
        //of current, cache[j] stores the value above, cache[j-1] stores the left
        for(int i=1; i<=word1.length(); i++)
        {
            int leftTop=cache[0];
            //initialize first column each time when we go down for next row
            cache[0]=i;
            for(int j=1; j<=word2.length(); j++)
            {
                int distance=word1.charAt(i-1)==word2.charAt(j-1)? 0 : 1;
                int current=Math.min(distance+leftTop,
                        Math.min(1+cache[j-1], 1+cache[j]));
                leftTop=cache[j];
                cache[j]=current;
            }
        }
        return cache[word2.length()];
    }


}

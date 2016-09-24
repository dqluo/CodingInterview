package dynamicProgramming.classic;

import util.ArrayUtil;

/**
 * User: xinyuwan, Date: 12/10/13, Time: 4:42 PM
 */
public class EditDistance
{
    private static int[][] cache;
    public static void main(String[] args)
    {
        String str1="pneumonoultramicroscopicsilicovolcanoconiosis";
        String str2="ultramicroscopically";
        System.out.println(minDistance3(str1, str2));

    }
    public static int minDistance(String word1, String word2)
    {
        cache=new int[word1.length()][word2.length()];
        return editDP(word1, word2, word1.length()-1, word2.length()-1);
    }
    private static int editDP(String word1, String word2, int i, int j)
    {
        if(i<0 || j<0)
            return i<0? j+1 : i+1;
        if(cache[i][j]>0)
            return cache[i][j];
        int distance=0;
        if(word1.charAt(i) != word2.charAt(j))
            distance=1;
        cache[i][j]=Math.min(distance+editDP(word1, word2, i-1, j-1),
                Math.min(1+editDP(word1, word2, i, j-1),
                        1+editDP(word1, word2, i-1, j)));
        return cache[i][j];
    }

    public static int minDistance2(String word1, String word2)
    {
        //we add one more row and column to form the base case: minDistance("", word) or (word,"")
        int[][] cache=new int[word1.length()+1][word2.length()+1];
        //for each element in cache[i][0] or cache[0][j], we initialize it with the value of j or i
        //to indicate for empty string, we simple insert j's or i's chars.
        for(int j=0; j<=word2.length(); j++)
            cache[0][j]=j;
        for(int i=1; i<=word1.length(); i++)
            cache[i][0]=i;
        //because we added one more line for col/row, we can safely apply our algorithm from cache[1][1]
        for(int i=1; i<=word1.length(); i++)
        {
            for(int j=1; j<=word2.length(); j++)
            {
                //Note that i and j correspond to index i-1 or j-1 in String now
                int distance=word1.charAt(i-1)==word2.charAt(j-1)? 0 : 1;
                cache[i][j]=Math.min(distance+cache[i-1][j-1],
                        Math.min(1+cache[i][j-1], 1+cache[i-1][j]));
            }
        }
//        ArrayUtil.printMatrix(cache);
        return cache[word1.length()][word2.length()];
    }

    public static int minDistance3(String word1, String word2)
    {
        int[] cache=new int[word2.length()+1];
        for(int i=0; i<=word2.length(); i++)
            cache[i]=i;

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
        ArrayUtil.print(cache);
        return cache[word2.length()];
    }

}

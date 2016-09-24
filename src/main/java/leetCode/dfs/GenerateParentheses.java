package leetCode.dfs;

import java.util.ArrayList;

/**
 * User: xinyuwan, Date: 1/9/14, Time: 3:40 PM
 */
public class GenerateParentheses
{
    public static void main(String[] args)
    {
        GenerateParentheses gp=new GenerateParentheses();
        System.out.println("All combinations of valid parentheses with 3 pairs: ");
        System.out.println(gp.generateParenthesis(3));
    }
    /*
     * Problem: Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * For example, given n = 3, a solution set is:
     * "((()))", "(()())", "(())()", "()(())", "()()()"
     */
    ArrayList<String> result=new ArrayList<String>();
    public ArrayList<String> generateParenthesis(int n)
    {
        char[] comb=new char[2*n];
        generate(comb, n, n, 0);
        return result;
    }
    private void generate(char[] comb, int leftRem, int rightRem, int index)
    {
        if(index == comb.length)
            result.add(String.copyValueOf(comb));
        if(leftRem > 0)
        {
            comb[index]='(';
            generate(comb, leftRem-1, rightRem, index+1);
        }
        if(rightRem > leftRem)
        {
            comb[index]=')';
            generate(comb, leftRem, rightRem-1, index+1);
        }
    }
}

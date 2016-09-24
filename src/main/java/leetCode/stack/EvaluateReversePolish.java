package leetCode.stack;

import java.util.Stack;

/**
 * User: xinyuwan, Date: 12/23/13, Time: 8:24 PM
 */
public class EvaluateReversePolish
{
    /*
     * Problem: Evaluate the value of an arithmetic expression in Reverse Polish Notation.
     * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
     * Some examples:  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
     * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
     */
    public int evalRPN(String[] tokens)
    {
        if(tokens.length==0)
            return 0;
        Stack<Integer> stack=new Stack<Integer>();
        for(int i=0; i<tokens.length; i++)
        {
            if(isOperator(tokens[i]))
            {
                int right=stack.pop();
                int left=stack.pop();
                stack.push(calculate(left, right, tokens[i]));
            }
            else
                stack.push(Integer.valueOf(tokens[i]));
        }
        return stack.pop();
    }
    private int calculate(int left, int right, String opt)
    {
        int result=0;
        if(opt.equals("+"))
            result=left+right;
        else if(opt.equals("-"))
            result=left-right;
        else if(opt.equals("*"))
            result=left*right;
        else if(opt.equals("/"))
            result=left/right;
        else
            result=Integer.MIN_VALUE;
        return result;
    }
    private boolean isOperator(String s)
    {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }
    private int StringToInt(String s)
    {
        int result=0;
        for(int i=0; i<s.length(); i++)
        {
            result=result*10+Character.getNumericValue(s.charAt(i));
        }
        return result;
    }
}

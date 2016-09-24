package leetCode.stack;

import java.util.Stack;

/**
 * User: xinyuwan, Date: 12/20/13, Time: 5:01 PM
 */
public class ValidParentheses
{
    /*
     * Total problems: 2
     */
    public static void main(String[] args)
    {
        String s1="((){}[{()}]", s2="((){}[{()}])";
        ValidParentheses vp=new ValidParentheses();
        System.out.println("s1 valid? "+vp.isValid(s1)+", s2 valid? "+vp.isValid(s2));

        String s3=")(()()(())", s4="(()))()())((((()))";
        LongestValidParentheses vp2=new LongestValidParentheses();
        System.out.println("s3 LVP: "+vp2.longestValidParentheses(s3)+
                ", s4 LVP: "+vp2.longestValidParentheses(s4));
    }
    /*
     * Problem: Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
     *
     */
    public boolean isValid(String s)
    {
        if(s==null || s.isEmpty())
            return true;
        Stack<Character> stack=new Stack<Character>();
        for(int i=0; i<s.length(); i++)
        {
            char c=s.charAt(i);
            if(c=='(' || c=='{' || c=='[')
                stack.push(c);
            else if(c==')' || c==']' || c=='}')
            {
                if(stack.isEmpty() || !checkMatch(stack.pop(), c))
                    return false;
            }
            else//other chars that unlikely to happen
                return false;
        }
        return stack.isEmpty();
    }
    private boolean checkMatch(char a, char b)
    {
        switch(a)
        {
            case '(':
                return b==')';
            case '{':
                return b=='}';
            case '[':
                return b==']';
            default:
                return false;
        }
    }
}

class LongestValidParentheses
{
    /*
     * Problem Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
     * For "(()", the longest valid parentheses substring is "()", which has length = 2.
     * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
     *
     *
     * We apply the similar idea here. First notice that when we hit a ' ) '  and the stack is NOT empty,
     * it means that we found a valid substring ends with this ' ) '. Now we need to find the length
     * of the substring in order to update the maximum length, which is the trick part of this problem.
     * In other words, we need to keep track of the starting index of the valid substring. Let's denote it as s.
     * Note that s can only be the index of a ' ( '.
     *
     * Consider the following cases when hitting a ' ) '. Denote the index of current ' ) ' as i.
     * 1. Stack is empty. This means we found an invalid substring like '( ) ) ' and we set s=i+1 as the next index of ')'
     * since no substring contains this ')' will be a valid one.
     * 2. Stack is not empty but there is only one '(' in the stack. This means we found a complete valid substring
     * like '( ( ) )' or '( ) ( )' starting from s. The length of substring is i−s +1.
     * 3. The stack is not empty and there is more than one '( ' in the stack. This means we found a partial valid
     * substring like '( ( ) ( ) ', you can see that s should be the index of the top element in the stack after
     * popping a '( '. To get the index easily, we push the index of ' ( ' into the stack directly.
     * The length of the substring is i−s.top().
     *
     * This algorithm runs in O(n) time and O(n) space in worst case.
     */
    public int longestValidParentheses(String s)
    {
        if(s==null || s.isEmpty())
            return 0;
        int maxLength=0, endOfLastValid=-1;
        Stack<Integer> stack=new Stack<Integer>();
        for(int i=0; i<s.length(); i++)
        {
            if(s.charAt(i)=='(')
                stack.push(i);
            else
            {
                //we encounter an invalid ')' with no '(' preceding it, so the next
                // possible valid sequence starts after i
                if(stack.isEmpty())
                    endOfLastValid=i;
                else
                {
                    stack.pop();
                    //we meet a complete valid sequence like"()()" or "(())"
                    if(stack.isEmpty())
                        maxLength=Math.max(maxLength, i-endOfLastValid);
                    //we meet a sequence with extra "(" on the left like"(()()"
                    else
                        maxLength=Math.max(maxLength, i-stack.peek());
                }
            }
        }
        return maxLength;
    }
}

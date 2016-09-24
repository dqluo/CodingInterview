package leetCode.stack;

import java.util.Stack;

/**
 * User: xinyuwan, Date: 12/31/13, Time: 7:32 PM
 */
public class SimplifyPath
{
    public static void main(String[] args)
    {
        String path="///home/", path2="/a/./b/../../c/";
        SimplifyPath sp=new SimplifyPath();
        System.out.println("Simplified path: "+sp.simplifyPath(path));
        System.out.println("Simplified path2: "+sp.simplifyPath(path2));
    }
    /*
     * Problem: Given an absolute path for a file (Unix-style), simplify it.
     * For example, path = "/home/", => "/home" path = "/a/./b/../../c/", => "/c"
     * Corner Cases:
     * Did you consider the case where path = "/../"?
     * In this case, you should return "/".
     * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
     * In this case, you should ignore redundant slashes and return "/home/foo".
     *
     * Analysis: split the string of path first based on "/" we'll get tokens like "home, .., ., ". Then we
     * use a stack to filter the tokens to store only the valid tokens.
     */
    public String simplifyPath(String path)
    {
        if(path==null || path.isEmpty())
            return path;
        String[] tokens=path.split("/");
        StringBuilder sb=new StringBuilder();
        Stack<String> stack=new Stack<String>();
        for(int i=0; i<tokens.length; i++)
        {
            //tokens[i] will be "" when path has substring like "///"
            if(tokens[i].isEmpty() || tokens[i].equals("."))
                continue;
            //simply pop the previous valid token in stack when tokens[i] is ".."
            //note that we need write both cases for stack is empty or not.
            else if(tokens[i].equals(".."))
            {
                if(stack.isEmpty())
                    continue;
                else
                    stack.pop();
            }
            else
                stack.push(tokens[i]);
        }
        if(stack.isEmpty())
            sb.append("/");
        else
        {
            while(!stack.isEmpty())
            {
                sb.insert(0, stack.pop());
                sb.insert(0, "/");
            }
        }
        return sb.toString();
    }
}

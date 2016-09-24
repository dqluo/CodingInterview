package leetCode.tree;

import leetCode.ADT.TreeNode;

import java.util.*;

/**
 * User: xinyuwan, Date: 1/5/14, Time: 5:01 PM
 */
public class StandardTraversal
{
    /*
     * Total problems: 6
     */
    public static void main(String[] args)
    {
        TreeNode a=new TreeNode(1);
        TreeNode b=new TreeNode(2);
        TreeNode c=new TreeNode(3);
        TreeNode d=new TreeNode(4);
        TreeNode e=new TreeNode(5);
        a.left=b;
        a.right=c;
        b.left=d;
        c.right=e;
        //test dfs
        System.out.print("Preorder: ");
        System.out.println(new BinaryTreePreorder().preorderTraversal(a));
        System.out.print("Inorder: ");
        System.out.println(new BinaryTreeInorder().inorderTraversal(a));
        System.out.print("Postorder: ");
        System.out.println(new BinaryTreePostorder().postorderTraversal(a));

        //test levelorder
        System.out.print("Levelorder: ");
        System.out.println(new BinaryTreeLevelorder().levelOrder2(a));
        System.out.print("Levelorder bottom: ");
        System.out.println(new BinaryTreeLevelorder2().levelOrderBottom(a));

        //test zigzag
        System.out.print("Zigzag: ");
        System.out.println(new BinaryTreeZigzag().zigzagLevelOrder(a));
    }
}

/*
 * DFS traversal
 */
class BinaryTreePreorder
{
    /*
     * Problem: Given a binary tree, return the preorder traversal of its nodes' values.
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root)
    {
        ArrayList<Integer> result=new ArrayList<Integer>();
        //if root==null, we cannot push it into the stack, otherwise will throw NullPointerExption
        if(root==null)
            return result;
        Stack<TreeNode> s=new Stack<TreeNode>();
        s.push(root);
        while(!s.isEmpty())
        {
            TreeNode next=s.pop();
            result.add(next.val);
            if(next.right!=null)
                s.push(next.right);
            if(next.left!=null)
                s.push(next.left);
        }
        return result;
    }
}

class BinaryTreeInorder
{
    /*
     * Problem: Given a binary tree, return the inorder traversal of its nodes' values.
     */
    public ArrayList<Integer> inorderTraversal(TreeNode root)
    {
        Stack<TreeNode> s=new Stack<TreeNode>();
        ArrayList<Integer> result=new ArrayList<Integer>();
        TreeNode next=root;
        while(!s.isEmpty() || next!=null)
        {
            while(next!=null)
            {
                s.push(next);
                next=next.left;
            }
            if(!s.isEmpty())
            {
                next=s.pop();
                result.add(next.val);
                //if next.right==null let it be, we'll use the null value in next iteration
                next=next.right;
            }
        }
        return result;
    }
}

class BinaryTreePostorder
{
    /*
     * Problem: Given a binary tree, return the postorder traversal of its nodes' values.
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root)
    {
        Stack<TreeNode> s=new Stack<TreeNode>();
        ArrayList<Integer> result=new ArrayList<Integer>();
        TreeNode next=root, lastPop=null;
        while(!s.isEmpty() || next!=null)
        {
            while(next!=null)
            {
                s.push(next);
                next=next.left;
            }
            if(!s.isEmpty())
            {
                next=s.peek();
                if(next.right!=null && next.right!=lastPop)
                    next=next.right;
                else
                {
                    result.add(next.val);
                    lastPop=s.pop();
                    //we should make next=null to continue the loop
                    next=null;
                }
            }
        }
        return result;
    }
}

class BinaryTreeLevelorder
{
    /*
     * Problem: Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
     */
    //dfs: create one ArrayList for each level, using level to get corresponding list
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root)
    {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        levelOrder(root, 0, result);
        return result;
    }
    private void levelOrder(TreeNode root, int level, ArrayList<ArrayList<Integer>> result)
    {
        if(root==null)
            return;
        int size=result.size();
        ArrayList<Integer> list=null;
        if(level==size)
        {
            list=new ArrayList<Integer>();
            result.add(list);
        }
        else
        {
            list=result.get(level);
        }
        list.add(root.val);
        levelOrder(root.left, level+1, result);
        levelOrder(root.right, level+1, result);
    }
    //bfs: using queue and using iterative approach, remember to check root==null at the beginning
    public ArrayList<ArrayList<Integer>> levelOrder2(TreeNode root)
    {
         ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
         if(root==null)  return result;
         Queue<TreeNode> q=new LinkedList<TreeNode>();
         ArrayList<Integer> list=new ArrayList<Integer>();
         q.add(root);
         q.add(null);
         while(!q.isEmpty())
         {
             root=q.remove();
             if(root==null)
             {
                 result.add(list);
                 list=new ArrayList<Integer>();
                 if(!q.isEmpty())
                     q.add(null);
                 continue;
             }
             list.add(root.val);
             if(root.left!=null)
                 q.add(root.left);
             if(root.right!=null)
                 q.add(root.right);
         }
         return result;
    }
}

class BinaryTreeLevelorder2
{
   /*
    * Problem: Given a binary tree, return the bottom-up level order traversal of its nodes' values.
    * (ie, from left to right, level by level from leaf to root).
    */
   public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root)
   {
       ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        /*
         * We must check root==null or not, otherwise will throw OutOfMemoryException,
         * This is because later on, we will enqueue both root(null) and null, and in the
         * loop, when we find root==null && q is empty, we will continuously add null into q,
         * and run into an infinite loop. Therefore, always check root==null or not if using
         * the "enqueue null" method in level order.
         */
       if(root==null)
           return result;
       Queue<TreeNode> q=new LinkedList<TreeNode>();
       ArrayList<Integer> list=new ArrayList<Integer>();
       q.add(root);
       q.add(null);
       while(!q.isEmpty())
       {
           root=q.remove();
           if(root==null)
           {
               result.add(list);
               list=new ArrayList<Integer>();
               if(!q.isEmpty())
                   q.add(null);
               continue;
           }
           list.add(root.val);
           if(root.left!=null)
               q.add(root.left);
           if(root.right!=null)
               q.add(root.right);
       }
       Collections.reverse(result);
       return result;
   }
}

class BinaryTreeZigzag
{
    /*
     * Problem: Given a binary tree, return the zigzag level order traversal of its nodes' values.
     * (ie, from left to right, then right to left for the next level and alternate between).
     */
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root)
    {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list=new ArrayList<Integer>();
        Stack<TreeNode> currentLevel=new Stack<TreeNode>();
        Stack<TreeNode> nextLevel=new Stack<TreeNode>();
        boolean goLeft=true;
        //if root==null we return an empty result
        if(root!=null)
            currentLevel.push(root);
        while(!currentLevel.isEmpty())
        {
            root=currentLevel.pop();
            list.add(root.val);
            if(goLeft)
            {
                if(root.left!=null)
                    nextLevel.push(root.left);
                if(root.right!=null)
                    nextLevel.push(root.right);
            }
            else
            {
                if(root.right!=null)
                    nextLevel.push(root.right);
                if(root.left!=null)
                    nextLevel.push(root.left);
            }
            if(currentLevel.isEmpty())
            {
                Stack<TreeNode> temp=currentLevel;
                currentLevel=nextLevel;
                nextLevel=temp;
                goLeft=!goLeft;
                //we only add the list if currentLevel iteration is finished
                result.add(list);
                list=new ArrayList<Integer>();
            }
        }
        return result;
    }
}

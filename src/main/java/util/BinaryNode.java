package util;

import java.util.*;

public class BinaryNode<T extends Comparable<? super T>> implements Comparable<BinaryNode<T>>
{
    public T data;
    public BinaryNode<T> left;
    public BinaryNode<T> right;
    public BinaryNode(T newData)
    {
        this(newData, null, null);
    }
    public BinaryNode(T newData, BinaryNode newLeft, BinaryNode newRight)
    {
        data=newData;
        left=newLeft;
        right=newRight;
    }
    public int compareTo(BinaryNode<T> node)
    {
        return this.data.compareTo(node.data);
    }
    public boolean hasLeft()
    {
        return left!=null;
    }
    public boolean hasRight()
    {
        return right!=null;
    }
    public boolean isLeaf()
    {
        return left==null && right==null;
    }
    public int getSize()
    {
        return getSize(this);
    }
    public int getHeight()
    {
        return getHeight(this);
    }
    private int getSize(BinaryNode root)
    {
        if(root==null)
            return 0;
        int size=1+getSize(root.left)+getSize(root.right);
        return size;
    }
    private int getHeight(BinaryNode root)
    {
        if(root==null)
            return 0;
        int height=1+Math.max(getHeight(root.left), getHeight(root.right));
        return height;
    }
    /*
     * Public method of traversals for printout each node
     */
    public void inorder()
    {
        List<T> list=inorder2(this);
        for(T data: list)
            System.out.print(data+" ");
        System.out.println();
    }
    public void preorder()
    {
        List<T> list=preorder2(this);
        for(T data: list)
            System.out.print(data+" ");
        System.out.println();
    }
    public void postorder()
    {
        List<T> list=postorder2(this);
        for(T data: list)
            System.out.print(data+" ");
        System.out.println();
    }
    /*
     * Recurive approach to traverals
     */
    private void inorder(BinaryNode root)
    {
        if(root==null)
            return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }
    private void preorder(BinaryNode root)
    {
        if(root==null)
            return;
        System.out.print(root.data+" ");
        preorder(root.left);
        preorder(root.right);
    }
    private void postorder(BinaryNode root)
    {
        if(root==null)
            return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data+" ");
    }
    /*
     * Iterative approach of traverals
     */
    public List<T> inorder2(BinaryNode<T> root)
    {
        List<T> resultList=new ArrayList<T>();
        Stack<BinaryNode> stack=new Stack<BinaryNode>();
        BinaryNode<T> next=root;
        while(!stack.isEmpty() || next!=null)
        {
            while(next!=null)
            {
                stack.push(next);
                next=next.left;
            }
            if(!stack.isEmpty())
            {
                next=stack.pop();
                resultList.add(next.data);
                next=next.right;
            }
        }
        return resultList;
    }
    public List<T> preorder2(BinaryNode<T> root)
    {
        if(root==null)
            return new ArrayList<T>();
        List<T> resultList=new ArrayList<T>();
        Stack<BinaryNode> stack=new Stack<BinaryNode>();
        stack.push(root);
        BinaryNode<T> next=null;
        while(!stack.isEmpty())
        {
            next=stack.pop();
            resultList.add(next.data);
            if(next.hasRight())
                stack.push(next.right);
            if(next.hasLeft())
                stack.push(next.left);
        }
        return resultList;
    }
    public List<T> postorder2(BinaryNode<T> root)
    {
        List<T> resultList=new ArrayList<T>();
        Stack<BinaryNode> stack=new Stack<BinaryNode>();
        BinaryNode<T> next=root;
        BinaryNode<T> lastPop=null;
        while(!stack.isEmpty() || next!=null)
        {
            while(next!=null)
            {
                stack.push(next);
                next=next.left;
            }
            if(!stack.isEmpty())
            {
                next=stack.peek();
                if(next.hasRight() && next.right!=lastPop)
                    next=next.right;
                else
                {
                    lastPop=stack.pop();
                    next=null;
                    resultList.add(lastPop.data);
                }
            }
        }
        return resultList;
    }

    public void levelorder(BinaryNode root)
    {
        if(root==null)
            return;
        Queue<BinaryNode> q=new LinkedList<BinaryNode>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty())
        {
            BinaryNode next=q.remove();
            /*
             * when next==null, we have traversed every node on current level,
             * and meanwhile, we've added every child of them, so at this point,
             * we add a null into the queue to mark the end of the next level
             */
            if(next==null)
            {
                if(!q.isEmpty())
                {
                    q.add(null);
                    System.out.println();
                }
                continue;
            }
            System.out.print(next.data+" ");
            if(next.hasLeft())
                q.add(next.left);
            if(next.hasRight())
                q.add(next.right);
        }
        System.out.println();
    }

    /*
     * BSTree Methods
     */
    public boolean contains(BinaryNode<T> root, T data)
    {
        if(root==null)
            return false;
        if(root.data.equals(data))
            return true;
        if(root.data.compareTo(data)<0)
            return contains(root.right, data);
        else
            return contains(root.left, data);
    }
    public BinaryNode add(BinaryNode<T> root, T data)
    {
        if(root==null)
            return new BinaryNode(data);
        if(data.compareTo(root.data)<=0)
        {
            if(root.hasLeft())
                root.add(root.left, data);
            else
                root.left=new BinaryNode<T>(data);
        }
        else
        {
            if(root.hasRight())
                root.add(root.right, data);
            else
                root.right=new BinaryNode<T>(data);
        }
        return root;
    }
    public void add(T data)
    {
        BinaryNode<T> next=this;
        while(next!=null)
        {
            if(data.compareTo(next.data)<=0)
            {
                if(next.hasLeft())
                    next=next.left;
                else
                {
                    next.left=new BinaryNode<T>(data);
                    break;
                }
            }
            else
            {
                if(next.hasRight())
                    next=next.right;
                else
                {
                    next.right=new BinaryNode<T>(data);
                    break;
                }
            }
        }
    }

    public BinaryNode<T> remove(BinaryNode<T> root, T entry, BinaryNode<T> parent)
    {
        BinaryNode<T> result=null;
        if(root!=null)
        {
            if(root.data.equals(entry))
            {
                if(parent==null)
                    result=removeFromRoot(root, null);
                else
                    removeFromRoot(root, parent);
            }
            else if(entry.compareTo(root.data)<0)
            {
                parent=root;
                result=remove(root.left, entry, parent);
            }
            else
            {
                parent=root;
                result=remove(root.right, entry, parent);
            }
        }
        return result==null? root:result;


    }
    //return the the new root node after root is removed
    public BinaryNode<T> removeFromRoot(BinaryNode<T> root, BinaryNode<T> parent)
    {

        BinaryNode<T> result=null;
        if(!root.hasLeft() || !root.hasRight())
        {
            result=root.hasLeft()? root.left : root.right;
            if(parent!=null)
            {
                if(root==parent.left)
                    parent.left=result;
                else
                    parent.right=result;
            }
        }
        else// root has two child nodes
        {
            BinaryNode<T> newParent=parentOfRightMostInLeft(root);
            BinaryNode<T> newRoot=null;
            if (newParent==root)
                newRoot=newParent.left;
            else
                newRoot=newParent.right;
            root.data=newRoot.data;
            result=removeFromRoot(newRoot, newParent);
            if(parent==null)
                result=root;
        }
        return result;
    }
    private BinaryNode parentOfRightMostInLeft(BinaryNode root)
    {
        BinaryNode parent=root;
        root=root.left;
        while(root.hasRight())
        {
            parent=root;
            root=root.right;
        }
        return parent;
    }
    public int getHeightDifference(BinaryNode root)
    {
        int left=getHeight(root.left);
        int right=getHeight(root.right);
        return left-right;
    }
    public BinaryNode rebalance(BinaryNode root)
    {
        int hd=getHeightDifference(root);
        if(hd>1)
        {
            if(getHeightDifference(root.left)>0)
                root=rotateRight(root);
            else
                root=rotateLeftRight(root);
        }
        if(hd<-1)
        {
            if(getHeightDifference(root.right)<0)
                root=rotateLeft(root);
            else
                root=rotateRightLeft(root);
        }
        return root;
    }

    public BinaryNode addAVL(BinaryNode root, T data)
    {
        if(root==null)
            return new BinaryNode(data);
        if(root.data.compareTo(data)>=0)
        {
            if(root.hasLeft())
            {
                root.left=root.addAVL(root.left, data);
            }
            else
                root.left=new BinaryNode(data);
        }
        else
        {
            if(root.hasRight())
            {
                root.right=root.addAVL(root.right, data);
            }
            else
                root.right=new BinaryNode(data);
        }
        return rebalance(root);
    }
    private BinaryNode rotateLeft(BinaryNode nodeN)
    {
        BinaryNode nodeC=nodeN.right;
        nodeN.right=nodeC.left;
        nodeC.left=nodeN;
        return nodeC;
    }
    private BinaryNode rotateRight(BinaryNode nodeN)
    {
        BinaryNode nodeC=nodeN.left;
        nodeN.left=nodeC.right;
        nodeC.right=nodeN;
        return nodeC;
    }
    private BinaryNode rotateRightLeft(BinaryNode nodeN)
    {
        BinaryNode nodeC=nodeN.right;
        nodeN.right=rotateRight(nodeC);
        return rotateLeft(nodeN);
    }
    private BinaryNode rotateLeftRight(BinaryNode nodeN)
    {
        BinaryNode nodeC=nodeN.left;
        nodeN.left=rotateLeft(nodeC);
        return rotateRight(nodeN);
    }




}

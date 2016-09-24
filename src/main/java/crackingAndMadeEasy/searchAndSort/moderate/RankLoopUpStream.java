package crackingAndMadeEasy.searchAndSort.moderate;

/**
 * User: xinyuwan, Date: 12/2/13, Time: 7:59 PM
 */
public class RankLoopUpStream
{
    /*
     * Problem: given a stream of integers, create data structure to support operations:
     * 1. track(int x): when x is generated, call track to store x appropriately in the inner data structure
     * 2. getRank(int x): given x, check the number of values less than or equal to x(not including x)
     */
    public static void main(String[] args)
    {
        int[] a={5, 1, 4, 4, 4, 5, 9, 7, 13, 3};
        RLUS rs=new RLUS();
        int i=0;
        for(; i<a.length; i++)
            rs.track(a[i]);
        int[] testArr={1, 4, 13};
        for(i=0; i<testArr.length; i++)
            System.out.println("rank of "+testArr[i]+" is "+rs.getRank(testArr[i]));
    }
}

class RLUS
{
    RankNode root;
    public void track(int x)
    {
        RankNode newNode=new RankNode(x);
        if(root==null)
            root=newNode;
        else
            root.insert(x);
    }
    public int getRank(int x)
    {
        if(root==null)
            return -1;
        return root.getRank(x);
    }
    class RankNode
    {
        int data;
        int leftSize=0;
        RankNode left, right;

        public RankNode(int d)
        {
            data=d;
        }

        public void insert(int x)
        {
            if(x < data)
            {
                if(this.left==null)
                    this.left=new RankNode(x);
                else
                    left.insert(x);
                leftSize++;
            }
            else
            {
                if(this.right==null)
                    this.right=new RankNode(x);
                else
                    right.insert(x);
            }
        }
        public int getRank(int x)
        {
            if(x == data)
                return leftSize;
            else if(x < data)
            {
                if(left == null)
                    return -1;
                else
                    return left.getRank(x);
            }
            else
            {
                if(right==null)
                    return -1;
                int rightRank=right.getRank(x);
                if(rightRank==-1)
                    return -1;
                return leftSize+1+rightRank;
            }
        }

    }
}
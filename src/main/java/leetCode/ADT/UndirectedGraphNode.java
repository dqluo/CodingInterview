package leetCode.ADT;

import java.util.ArrayList;

/**
 * User: xinyuwan, Date: 1/3/14, Time: 7:46 PM
 */

public class UndirectedGraphNode
{
    public int label;
    public ArrayList<UndirectedGraphNode> neighbors;
    public UndirectedGraphNode(int x)
    {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}

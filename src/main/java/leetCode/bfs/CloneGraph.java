package leetCode.bfs;

import leetCode.ADT.UndirectedGraphNode;

import java.util.*;

/**
 * User: xinyuwan, Date: 1/3/14, Time: 7:45 PM
 */
public class CloneGraph
{
    public static void main(String[] args)
    {
        UndirectedGraphNode a=new UndirectedGraphNode(1);
        UndirectedGraphNode b=new UndirectedGraphNode(2);
        UndirectedGraphNode c=new UndirectedGraphNode(3);
        UndirectedGraphNode d=new UndirectedGraphNode(4);
        a.neighbors.add(b);
        a.neighbors.add(d);
        b.neighbors.add(a);
        b.neighbors.add(c);
        c.neighbors.add(b);
        c.neighbors.add(d);
        d.neighbors.add(c);
        d.neighbors.add(a);
        CloneGraph cg=new CloneGraph();
        cg.bfs(cg.cloneGraph(a));
        cg.dfs(cg.cloneGraph2(a));
    }
    /*
     * Problem: Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
     *
     * Analysis: The logic is similar to copy linked list with random pointer. We copy the value of the node first,
     * and while we iterate through the neighbors of the node, we add the copy of neighbor to the node neighbors list
     */

    //bfs
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node)
    {
        if(node==null)
            return null;
        Queue<UndirectedGraphNode> q=new LinkedList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map=new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        UndirectedGraphNode nodeCopy=new UndirectedGraphNode(node.label);
        map.put(node, nodeCopy);
        q.add(node);
        while(!q.isEmpty())
        {
            UndirectedGraphNode parent=q.remove();
            UndirectedGraphNode parentCopy=map.get(parent);
            for(UndirectedGraphNode child : parent.neighbors)
            {
                if(!map.containsKey(child))
                {
                    map.put(child, new UndirectedGraphNode(child.label));
                    q.add(child);
                }
                parentCopy.neighbors.add(map.get(child));
            }
        }
        return map.get(node);
    }

    //dfs
    public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node)
    {
        return cloneGraphDFS(node, new HashMap<UndirectedGraphNode, UndirectedGraphNode>());
    }
    public UndirectedGraphNode cloneGraphDFS(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> map)
    {
        if(node==null)
            return null;
        if(map.containsKey(node))
            return map.get(node);
        UndirectedGraphNode nodeCopy=new UndirectedGraphNode(node.label);
        map.put(node, nodeCopy);
        for(UndirectedGraphNode child : node.neighbors)
        {
            nodeCopy.neighbors.add(cloneGraphDFS(child, map));
        }
        return nodeCopy;
    }

    private void dfs(UndirectedGraphNode node)
    {
        dfsAux(node, new HashSet<UndirectedGraphNode>());
    }
    private void dfsAux(UndirectedGraphNode node, Set<UndirectedGraphNode> visited)
    {
        if(node==null)
            return;
        System.out.print(node.label+" ");
        visited.add(node);
        for(UndirectedGraphNode child : node.neighbors)
        {
            if(!visited.contains(child))
            {
                dfsAux(child, visited);
            }
        }
    }
    private void bfs(UndirectedGraphNode node)
    {
        if(node==null)
            return;
        Queue<UndirectedGraphNode> q=new LinkedList<UndirectedGraphNode>();
        Set<UndirectedGraphNode> visited=new HashSet<UndirectedGraphNode>();
        q.offer(node);
        visited.add(node);
        while(!q.isEmpty())
        {
            UndirectedGraphNode parent=q.poll();
            System.out.print(parent.label+" ");
            for(UndirectedGraphNode child : parent.neighbors)
            {
                if(!visited.contains(child))
                {
                    q.offer(child);
                    visited.add(child);
                }
            }
        }
        System.out.println();
    }
}

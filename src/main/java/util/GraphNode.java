package util;

import java.util.HashMap;
import java.util.Map;

/**
 * User: xinyuwan, Date: 9/18/14, Time: 10:25 AM
 */
public class GraphNode
{
    public int label;
    public int indegree;
    public Map<GraphNode, Integer> neighbors;
    public GraphNode(int label)
    {
        this.label = label;
        neighbors = new HashMap<GraphNode, Integer>();
    }
    public int weight(GraphNode g)
    {
        return neighbors.get(g);
    }
    public void addEdge(GraphNode neighbor, int weight)
    {
        neighbors.put(neighbor, weight);
        neighbor.neighbors.put(this, weight);
        this.indegree++;
        neighbor.indegree++;
    }
    public void addEdgeDirected(GraphNode neighbor, int weight)
    {
        neighbors.put(neighbor, weight);
        neighbor.indegree++;
    }
    @Override
    public String toString()
    {
        return String.valueOf(label);
    }

}
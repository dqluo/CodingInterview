package PostedFacebookQuestionsNew;

import util.GraphNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;

/**
 * 检测一个图是否是二分图, 就是BFS, 然后给每个节点上色.
 */
public class bipartite {

    public static boolean isBipartite(GraphNode gNode){
        if(gNode==null)
            return true;
        Map<GraphNode, Integer> map=new HashMap<GraphNode, Integer>();
        map.put(gNode, 1);
        Queue<GraphNode> queue= new LinkedList<GraphNode>();
        queue.add(gNode);
        while(!queue.isEmpty()){
            GraphNode temp=queue.remove();
            for(GraphNode node:temp.neighbors.keySet()){
                if(!map.containsKey(node)) {
                    map.put(node, 1 - map.get(temp));
                    queue.add(node);
                }else{
                    if(map.get(node).equals(map.get(temp)))
                        return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        GraphNode g1=new GraphNode(1);
        GraphNode g2=new GraphNode(2);
        GraphNode g3=new GraphNode(3);
        GraphNode g4=new GraphNode(4);
        GraphNode g5=new GraphNode(5);
        GraphNode g6=new GraphNode(6);
        g1.addEdge(g2, 1);
        g2.addEdge(g3, 1);
        g3.addEdge(g4, 1);
        g4.addEdge(g5, 1);
        g5.addEdge(g6, 1);
        g6.addEdge(g1, 1);
        System.out.println(isBipartite(g1));

        GraphNode g11=new GraphNode(1);
        GraphNode g12=new GraphNode(2);
        GraphNode g13=new GraphNode(3);
        GraphNode g14=new GraphNode(4);
        GraphNode g15=new GraphNode(5);
        g11.addEdge(g12, 1);
        g12.addEdge(g13, 1);
        g13.addEdge(g14, 1);
        g14.addEdge(g15, 1);
        g15.addEdge(g11, 1);
        System.out.println(isBipartite(g11));
    }
}

package leetCode.bfs;

import util.ArrayListUtil;

import java.util.*;

/**
 * User: xinyuwan, Date: 12/29/13, Time: 4:15 PM
 */
public class WordLadder
{
    /*
     * Total Problems: 2
     */
    public static void main(String[] args)
    {
        String start="crab", end="ache";
        String[] arr={"wilt","vows","pity","shat","foot","eden","boom","anne","huhs",
                "java","gnus","yeps","floe","eyck","died","flab","urge","styx","iota",
                "gybe","grit","anus","crab","zebu","pods","cabs","kurt","bald","rips",
                "pace","jays","acts","ryan","acne","inti","snip","heep","cruz","grin",
                "last","rake","rush","whir","crew","kane","rasp","slop","shim","fain",
                "howl","tuns","bias","junk","zeke","jock","kens","rope","mars","arty",
                "tuna","naps","pols","judo","gone","rule","sale","guff","jove","porn",
                "self","etta","sump","ibex","saar","grus","rive","arid","face","ante",
                "grab","hobs","clam","brad","zits","alps","acid","grub","fade","sulk",
                "rent","nick","puns","west","pies","quiz","colo","hock","whiz","dick",
                "zibo","hack","care","hurl","open","lift","fend","pros","beth","neva",
                "drag","colt","ants","tamp","amen","visa","nets","ache","turd"};
        HashSet<String> dict=new HashSet<String>();
        String start1="hot", end1="dog";
        String[] arr1={"hot","dog","cog","pot","dot"};
        HashSet<String> dict1=new HashSet<String>();
        String start2="red", end2= "tax";
        String[] arr2={"ted","tex","red","tax","tad","den","rex","pee"};
        HashSet<String> dict2=new HashSet<String>();

        dict.addAll(Arrays.asList(arr));
        dict1.addAll(Arrays.asList(arr1));
        dict2.addAll(Arrays.asList(arr2));

        //test WordLadder
        WordLadder wl=new WordLadder();
        System.out.println(wl.ladderLength2(start, end, dict));

        //test WordLadder2
        WordLadder2 wl2=new WordLadder2();
        ArrayListUtil.print(wl2.findLadders(start, end, dict));
    }

    /*
     * Problem1: Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:
     * Only one letter can be changed at a time
     * Each intermediate word must exist in the dictionary
     * Note: Return 0 if there is no such transformation sequence. All words have the same length. All words contain only lowercase alphabetic characters.
     *
     * Analysis: This is a BFS algorithm in essence. We need get to end from start. And each word in the graph
     * will have adjacent nodes, which are generated from the getAdjacent methods(change one char at each index,
     * try to see if it's a valid word in dictionary). We use a queue as common BFS approach, a set to record the
     * visited nodes. We distinguish nodes in different levels by q.size, and iterate all nodes at this level. In
     * this way, we will keep an integer count to keep track of level. Then, we can update count at the end of
     * each level, and if childWord==end, we've found the shortest path.
     */

    public int ladderLength(String start, String end, HashSet<String> dict)
    {
        Set<String> visited=new HashSet<String>();
        Queue<String> q=new LinkedList<String>();
        HashMap<String, String> path=new HashMap<String, String>();

        q.add(start);
        visited.add(start);
        while(!q.isEmpty())
        {
            String parentWord=q.remove();
            Set<String> adjacent=getAdjacent(parentWord, dict);
            for(String childWord : adjacent)
            {
                if(childWord.equals(end))
                {
                    int count=1;
                    while(parentWord!=null)
                    {
                        parentWord=path.get(parentWord);
                        count++;
                    }
                    return count;
                }
                if(!visited.contains(childWord))
                {
                    visited.add(childWord);
                    q.add(childWord);
                    path.put(childWord, parentWord);
                }
            }
        }
        return 0;
    }

    public int ladderLength2(String start, String end, HashSet<String> dict)
    {
        Queue<String> q=new LinkedList<String>();
        Set<String> visited=new HashSet<String>();
        int count=1;

        q.add(start);
        visited.add(start);
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0; i<size; i++)
            {
                String parentWord=q.remove();
                Set<String> adjacent=getAdjacent(parentWord, dict);
                for(String childWord : adjacent)
                {
                    //we need to advance count here since we return count without stepping out the loop
                    if(childWord.equals(end))
                    {
                        return count+1;
                    }
                    //we eliminate both the case of "red->ted->red" and "red->ted(rex)->tex"
                    if(!visited.contains(childWord))
                    {
                        visited.add(childWord);
                        q.add(childWord);
                    }

                }
            }
            count++;
        }
        return 0;
    }

    private Set<String> getAdjacent(String s, HashSet<String> dict)
    {
        Set<String> result=new HashSet<String>();
        char[] word=s.toCharArray();
        for(int i=0; i<s.length(); i++)
        {
            char originalChar=word[i];
            for(char c='a'; c<='z'; c++)
            {
                //we need to reset the char at index i every time we change word[i]
                if(originalChar!=c)
                {
                    word[i]=c;
                    String newWord=String.valueOf(word);
                    if(dict.contains(newWord))
                        result.add(newWord);
                    word[i]=originalChar;
                }
            }
        }
        return result;
    }
}

class WordLadder2
{
    /*
     * Problems2:  Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:
     * Only one letter can be changed at a time
     * Each intermediate word must exist in the dictionary
     * For example, Given: start = "hit" end = "cog" dict = ["hot","dot","dog","lot","log"]
     * Return
     * [
     * ["hit","hot","dot","dog","cog"],
     * ["hit","hot","lot","log","cog"]
     * ]
     * Note: All words have the same length. All words contain only lowercase alphabetic characters.
     */

    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict)
    {
        ArrayList<ArrayList<String>> result=new ArrayList<ArrayList<String>>();
        Queue<GraphNode> q=new LinkedList<GraphNode>();
        //need a hashmap to store GraphNode as value, because we will modify the node's level and prev in iteration
        HashMap<String, GraphNode> visited=new HashMap<String, GraphNode>();
        boolean done=false;

        GraphNode node=new GraphNode(0, start), endNode=null;
        q.add(node);
        visited.put(start, node);
        while(!q.isEmpty() && !done)
        {
            int size=q.size();
            for(int i=0; i<size; i++)
            {
                GraphNode parentWord=q.remove();
                Set<String> adjacent=getAdjacent(parentWord.val, dict);
                for(String childWord : adjacent)
                {
                    //note this time we first check the childNode is visited or not, since no matter it
                    //equals to end or not, we need to add it to queue, and proceed until the end of this level
                    if(!visited.containsKey(childWord))
                    {
                        node=new GraphNode(parentWord.level+1, childWord);
                        node.addPrev(parentWord);
                        visited.put(childWord, node);
                        q.add(node);
                        if(childWord.equals(end))
                        {
                            done=true;
                            endNode=node;
                        }
                    }
                    else
                    {
                        if(visited.get(childWord).level==parentWord.level+1)
                        {
                            node=visited.get(childWord);
                            node.addPrev(parentWord);
                        }
                    }
                }
            }
        }
        //backtracking: we don't need count here, level will be used instead
        if(endNode!=null)
        {
            String[] path=new String[endNode.level+1];
            path[endNode.level]=endNode.val;
            backTrack(endNode, result, path, endNode.level-1);
        }
        return result;
    }
    private void backTrack(GraphNode endNode, ArrayList<ArrayList<String>> result, String[] path, int index)
    {
        if(index<0)
        {
            result.add(cloneToList(path));
            return;
        }
        for(GraphNode node : endNode.prev)
        {
            path[index]=node.val;
            backTrack(node, result, path, index-1);
        }
    }
    private ArrayList<String> cloneToList(String[] a)
    {
        ArrayList<String> result=new ArrayList<String>();
        for(int i=0; i<a.length; i++)
        {
            result.add(a[i]);
        }
        result.trimToSize();
        return result;
    }
    private Set<String> getAdjacent(String s, HashSet<String> dict)
    {
        Set<String> result=new HashSet<String>();
        char[] word=s.toCharArray();
        for(int i=0; i<s.length(); i++)
        {
            char originalChar=word[i];
            for(char c='a'; c<='z'; c++)
            {
                //we need to reset the char at index i every time we change word[i]
                if(originalChar!=c)
                {
                    word[i]=c;
                    String newWord=String.valueOf(word);
                    if(dict.contains(newWord))
                        result.add(newWord);
                    word[i]=originalChar;
                }
            }
        }
        return result;
    }
}

class GraphNode
{
    int level;
    String val;
    LinkedList<GraphNode> prev;

    GraphNode(int l, String v)
    {
        this.level=l;
        this.val = v;
        this.prev=new LinkedList<GraphNode>();
    }

    void addPrev(GraphNode pNode)
    {
        prev.add(pNode);
    }
}
package util;

import java.util.LinkedList;
import java.util.List;

/**
 * User: xinyuwan, Date: 12/6/13, Time: 6:13 PM
 */
public class Trie
{
    public static void main(String[] args)
    {
        Trie trie = new Trie();
        trie.insert("ball");
        trie.insert("balls");
        trie.insert("sense");

        // testing delete
        System.out.println("search full stringAndArray: "+trie.search("balls"));
        System.out.println("search full stringAndArray: "+trie.search("ba"));
        System.out.println("search prefix: "+trie.searchPrefix("ba"));
        trie.delete("balls");
        System.out.println("search \"balls\" after delete: "+trie.search("balls"));
        System.out.println("search \"ball\" after delete: "+trie.search("ball"));
    }
    TrieNode root;

    public Trie()
    {
        root=new TrieNode(' ');
    }
    public void insert(String s)
    {
        if(search(s))
            return;
        TrieNode current=root;
        for(int i=0; i<s.length(); i++)
        {
            char c=s.charAt(i);
            TrieNode child=current.getChild(c);
            if(child==null)
            {
                current.children.add(new TrieNode(c));
                current=current.getChild(c);
            }
            else
                current=child;
            //either way, we need to increase the value of count
            current.count++;
        }
        current.isEnd=true;
    }
    public void delete(String s)
    {
        if(!search(s))
            return;
        TrieNode current=root;
        for(char c : s.toCharArray())
        {
            TrieNode child=current.getChild(c);
            if(child.count==1)
            {
                current.children.remove(child);
                return;
            }
            else
            {
                current=child;
                current.count--;
            }
        }
        /*
         * if we reach here, it means the word we're deleting
         * is part of another word in the trie, which means we're
         * stopping at a char that is the last char of the word to
         * delete, but in the middle of another word. so we need to
         * mark isEnd to be false.
         */
        current.isEnd=false;
    }
    public boolean search(String s)
    {
        TrieNode current=root;
        for(int i=0; i<s.length(); i++)
        {
            char c=s.charAt(i);
            TrieNode child=current.getChild(c);
            if(child==null)
                return false;
            current=child;
        }
        return current.isEnd;
    }
    public boolean searchPrefix(String s)
    {
        TrieNode current=root;
        for(int i=0; i<s.length(); i++)
        {
            char c=s.charAt(i);
            TrieNode child=current.getChild(c);
            if(child==null)
                return false;
            current=child;
        }
        return true;
    }

    private class TrieNode
    {
        char data;
        boolean isEnd;
        int count;
        List<TrieNode> children;

        public TrieNode(char d)
        {
            this.data=d;
            isEnd=false;
            count=0;
            children=new LinkedList<TrieNode>();
        }

        public TrieNode getChild(char c)
        {
            for(TrieNode child : children)
            {
                if(child.data == c)
                    return child;
            }
            return null;
        }
    }
}

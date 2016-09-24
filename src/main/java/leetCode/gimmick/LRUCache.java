package leetCode.gimmick;

import java.util.HashMap;

/**
 * User: xinyuwan, Date: 12/29/13, Time: 1:55 PM
 */
public class LRUCache
{
    public static void main(String[] args)
    {
        LRUCache lru=new LRUCache(1);
        lru.set(2, 1);
        System.out.println(lru.get(2));
        lru.set(3, 2);
        System.out.println(lru.get(2));
        System.out.println(lru.get(3));
    }

    /*
     * Problem: Design and implement a data structure for Least Recently Used (LRU) cache.
     * It should support the following operations: get and set.
     * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
     * set(key, value) - Set or insert the value if the key is not already present.
     * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
     */
    private HashMap<Integer, DoublyLinkedListNode> map;
    private DoublyLinkedListNode head;
    private DoublyLinkedListNode last; //keep this pointer so we can remove the least used node when capcity is reached
    private int capacity;
    private int size;

    public LRUCache(int capacity)
    {
        this.map=new HashMap<Integer, DoublyLinkedListNode>();
        this.capacity=capacity;
        this.size=0;
    }
    //do two things here: 1. fetch the value based on key; 2 update the node to head
    public int get(int key)
    {
        int result=-1;
        if(map.containsKey(key))
        {
            DoublyLinkedListNode node=map.get(key);
            result=node.value;
            remove(node);
            addToHead(node);
        }
        return result;
    }

    public void set(int key, int value)
    {
        DoublyLinkedListNode node=null;
        //this does the same thing to get(key), except we set the node's value to be new value
        if(map.containsKey(key))
        {
            node=map.get(key);
            node.value=value;
            remove(node);
            addToHead(node);
        }
        //first check the capacity, if capacity is reached, remove nodes from BOTH map and list
        else
        {
            if(capacity > 0 && size >= capacity)
            {
                map.remove(last.key);
                remove(last);
                size--;
            }
            node=new DoublyLinkedListNode(key, value);
            map.put(key, node);
            addToHead(node);
            size++;
        }
    }
    //pay attention to the 3 corner cases
    private void remove(DoublyLinkedListNode node)
    {
        if(size==1)
        {
            head=null;
            last=null;
        }
        else if(node==head)
        {
            head=node.next;
            head.prev=null;
        }
        else if(node==last)
        {
            last=node.prev;
            last.next=null;
        }
        else
        {
            node.prev.next=node.next;
            node.next.prev=node.prev;
        }
    }
    private void addToHead(DoublyLinkedListNode node)
    {
        if(head!=null)
        {
            node.next=head;
            head.prev=node;
            head=node;
        }
        else
        {
            head=node;
            last=node;
        }
    }
    private class DoublyLinkedListNode
    {
        int key;
        int value;
        DoublyLinkedListNode prev;
        DoublyLinkedListNode next;

        DoublyLinkedListNode(int k, int v)
        {
            key=k;
            value=v;
        }
    }
}

package postedLinkedInQuestions;

import static util.HashMapUtil.getNextPrime;

/**
 * User: xinyuwan, Date: 9/13/14, Time: 3:16 PM
 */
public class HashMapChaining<K, V>
{
    private int numberOfElements;
    private int locationUsed;
    private double DEFAULT_LOAD_FACTOR = 0.5;
    private int DEFAULT_LENGTH = 101;
    private MapListNode<K, V>[] map = new MapListNode[DEFAULT_LENGTH];


    public V get(K key)
    {
        int index = index(key);
        MapListNode<K, V> head = map[index];
        while(head != null && !head.key.equals(key))
            head = head.next;
        return head == null ? null : head.value;
    }
    public void put(K newKey, V newValue)
    {
        if(isFull())
            rehash();
        int index = index(newKey);
        MapListNode<K, V> head = map[index];
        if (head == null)
        {
            map[index] = new MapListNode<K, V>(newKey, newValue);
            locationUsed++;
            numberOfElements++;
        }
        else if(head.key.equals(newKey))
            head.value = newValue;
        else
        {
            while(head.next != null && !head.next.key.equals(newKey))
                head = head.next;
            if(head.next != null)
                head.next.value = newValue;
            else
            {
                head.next = new MapListNode<K, V>(newKey, newValue);
                numberOfElements++;
            }
        }
    }
    public V remove(K key)
    {
        if(isEmpty())
            return null;
        int index = index(key);
        MapListNode<K, V> head = map[index];
        V result = null;
        if(head == null)
            result = null;
        else if(head.key.equals(key))
        {
            result = head.value;
            map[index] = head.next;
            numberOfElements--;
        }
        else
        {
            while(head.next != null && !head.next.key.equals(key))
                head = head.next;
            if(head.next != null)
            {
                result = head.next.value;
                head.next = head.next.next;
                numberOfElements--;
            }
        }
        return result;
    }
    public boolean isEmpty()
    {
        return numberOfElements == 0;
    }
    public boolean containsKey(K key)
    {
        int index = index(key);
        MapListNode<K, V> head = map[index];
        while(head != null && head.key.equals(key))
            head = head.next;
        return head == null;
    }
    public int size()
    {
        return numberOfElements;
    }

    private int index(K key)
    {
        return key.hashCode() % map.length;
    }
    private boolean isFull()
    {
        int loadFactor = locationUsed/map.length;
        return loadFactor >= DEFAULT_LOAD_FACTOR;
    }
    private void rehash()
    {
        int newSize = getNextPrime(2 * map.length);
        MapListNode<K, V>[] oldMap = map;
        map = new MapListNode[newSize];
        for(MapListNode<K, V> node : oldMap)
        {
            while(node != null)
            {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }
    private static class MapListNode<K, V>
    {
        private K key;
        private V value;
        private MapListNode<K, V> next;
        public MapListNode(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
    }
}
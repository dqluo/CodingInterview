package treeAndGraph.graphHeapAndOthers.priorityQueueAndHeap;

public class PriorityQueue<T extends Comparable<? super T>>
{
    private MinHeap<T> queue;

    public PriorityQueue()
    {
        queue=new MinHeap<T>();
    }
    public void add(T data)
    {
       queue.add(data);
    }
    public T remove()
    {
        return queue.remove();
    }
    public T remove(T data)
    {
        return queue.removeElement(data);
    }
    public T peek()
    {
        return queue.getFront();
    }
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }
    public int size()
    {
        return queue.size();
    }
}

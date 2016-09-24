package crackingAndMadeEasy.treeAndGraph.graphHeapAndOthers.priorityQueueAndHeap;

public class MinHeap<T extends Comparable<? super T>> extends Heap<T>
{


    public MinHeap()
    {
        heap=(T[])new Comparable[25];
    }
    public MinHeap(int[] entry)
    {
        heap=(T[])new Comparable[entry.length];
        System.arraycopy(entry, 0, heap, 0, entry.length);
        lastIndex=entry.length-1;
        for(int i=(lastIndex-1)/2; i>=0; i--)
            reheap(i);
    }
    public int size()
    {
        return lastIndex+1;
    }
    public void add(T data)
    {
        if(isFull())
            doubleArr();
        int availIndex=++lastIndex;
        int parentIndex=(availIndex-1)/2;
        while(availIndex>0 && data.compareTo(heap[parentIndex])<0)
        {
            heap[availIndex]=heap[parentIndex];
            availIndex=parentIndex;
            parentIndex=(availIndex-1)/2;
        }
        heap[availIndex]=data;
    }
    public T remove()
    {
        T result=null;
        if(!isEmpty())
        {
            result=heap[0];
            heap[0]=heap[lastIndex];
            heap[lastIndex]=null;
            lastIndex--;
            reheap(0);
        }
        return result;
    }
    public T removeElement(T data)
    {
        int i=indexOf(data);
        if(i==-1)
            return null;
        heap[i]=heap[lastIndex--];
        reheap(i);
        return data;
    }
    private int indexOf(T data)
    {
        for(int i=0; i<=lastIndex; i++)
        {
            if(heap[i].equals(data))
                return i;
        }
        return -1;
    }
    private void reheap(int rootIndex)
    {
        T data=heap[rootIndex];
        int smallerChild=0;
        while(2*rootIndex+1<=lastIndex)
        {
            if(2*rootIndex+1==lastIndex)
                smallerChild=lastIndex;
            else
                smallerChild=heap[rootIndex*2+1].compareTo(heap[rootIndex*2+2])<0?
                        rootIndex*2+1 : rootIndex*2+2;
            if(data.compareTo(heap[smallerChild])>0)
            {
                heap[rootIndex]=heap[smallerChild];
                rootIndex=smallerChild;
            }
            else
                break;
        }
        heap[rootIndex]=data;
    }
    public boolean isFull()
    {
        return lastIndex>=heap.length-1;
    }
    private void doubleArr()
    {
        T[] newHeap=(T[])new Comparable[heap.length*2];
        System.arraycopy(heap, 0, newHeap, 0, lastIndex+1);
        heap=newHeap;
    }
    public boolean isEmpty()
    {
        return lastIndex==-1;
    }
    public void clear()
    {
        for(int i=0; i<=lastIndex; i++)
            heap[i]=null;
        lastIndex=-1;
    }
    public static void print(int[] arr)
    {
        for(int i=0; i<arr.length; i++)
            System.out.print(arr[i]+" ");
        System.out.println();
    }


}



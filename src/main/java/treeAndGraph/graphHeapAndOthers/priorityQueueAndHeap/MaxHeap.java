package treeAndGraph.graphHeapAndOthers.priorityQueueAndHeap;

public class MaxHeap<T extends Comparable<? super T>> extends  Heap<T>
{
    public MaxHeap()
    {
        heap=(T[])new Comparable[25];
    }
    public MaxHeap(int[] entry)
    {
        heap=(T[])new Comparable[entry.length];
        System.arraycopy(entry, 0, heap, 0, entry.length);
        lastIndex=entry.length-1;
        for(int i=(lastIndex-1)/2; i>=0; i--)
            reheap(i);
    }
    public void add(T data)
    {
        if(isFull())
            doubleArr();
        int availIndex=++lastIndex;
        int parentIndex=(availIndex-1)/2;
        //sift down from the parent of lastIndex+1
        while(availIndex>0 && data.compareTo(heap[parentIndex])>0)
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
            //heap[0] stores the max element in heap
            result=heap[0];
            //copy lastIndex element to heap[0]
            heap[0]=heap[lastIndex];
            heap[lastIndex]=null;
            lastIndex--;
            //reheap from the index that has been changed
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
        int largerChildIndex=0;
        while(2*rootIndex+1<=lastIndex)
        {
            //left child of that element is the last element of heap
            if(2*rootIndex+1==lastIndex)
                largerChildIndex=lastIndex;
            else
                largerChildIndex=heap[rootIndex*2+1].compareTo(heap[rootIndex*2+2])>0?
                        rootIndex*2+1 : rootIndex*2+2;
            //sift up from the largerChild of rootIndex
            if(data.compareTo(heap[largerChildIndex])<0)
            {
                heap[rootIndex]=heap[largerChildIndex];
                rootIndex=largerChildIndex;
            }
            else
                break;
        }
        heap[rootIndex]=data;
    }
    private boolean isFull()
    {
        return lastIndex>=heap.length-1;
    }
    public int size()
    {
        return lastIndex+1;
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
    public void display()
    {
        if(isEmpty())
            return;
        for(T data: heap)
            System.out.print(data+" ");
        System.out.println();
    }

    /*
     * Problems
     */
    public T findMin()
    {
        int firstLeafIndex=(lastIndex+1)/2;
        T min=null;
        for(int i=firstLeafIndex; i<=lastIndex; i++)
        {
            if(min.compareTo(heap[i])>0)
                min=heap[i];
        }
        return min;
    }
    public void printAllLessThanK(int index, T k)
    {
        if(index<0 || index>lastIndex)
            return;
        if(heap[index].compareTo(k)<0)
            System.out.print(heap[index]+" ");
        printAllLessThanK(index*2+1, k);
        printAllLessThanK(index*2+2, k);
    }

}


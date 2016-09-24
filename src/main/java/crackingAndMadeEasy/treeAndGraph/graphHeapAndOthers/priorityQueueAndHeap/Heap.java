package crackingAndMadeEasy.treeAndGraph.graphHeapAndOthers.priorityQueueAndHeap;

public abstract class Heap<T extends Comparable<? super T>>
{
    protected int lastIndex=-1;
    protected T[] heap;
    public abstract void add(T data);
    public abstract T remove();
    public abstract T removeElement(T data);
    public abstract int size();
    public abstract boolean isEmpty();
    public abstract void clear();
    public void display()
    {
        if(isEmpty())
            return;
        for(T data: heap)
            System.out.print(data+" ");
        System.out.println();
    }
    public T getFront()
    {
        if(!isEmpty())
            return heap[0];
        return null;
    }
    public static<T extends Comparable<? super T>> void heapSort(T[] arr)
    {
        int n=arr.length;
        for(int i=(n-1)/2; i>=0; i--)
            reheap(arr, i, n-1);
        //heap[0] is the largest, so just swap it with the last element
        swap(arr,  0, n-1);
        //loop from heap[n-2], when lastIndex==0, no need to reheap since heap[0] is the smallest now
        for(int lastIndex=n-2; lastIndex>0; lastIndex--)
        {
            reheap(arr, 0, lastIndex);
            swap(arr, 0, lastIndex);
        }
    }
    private static<T extends Comparable<? super T>> void swap(T[] a, int i, int j)
    {
        T temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    private static<T extends Comparable<? super T>> void reheap(T[] heap, int rootIndex, int lastIndex)
    {
        T data=heap[rootIndex];
        int largerChildIndex=0;
        while(2*rootIndex+1<=lastIndex)
        {
            if(2*rootIndex+1==lastIndex)
                largerChildIndex=lastIndex;
            else
                largerChildIndex=heap[rootIndex*2+1].compareTo(heap[rootIndex*2+2])>0?
                        rootIndex*2+1 : rootIndex*2+2;
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

}

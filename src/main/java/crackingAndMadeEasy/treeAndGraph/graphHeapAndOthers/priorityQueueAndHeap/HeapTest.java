package crackingAndMadeEasy.treeAndGraph.graphHeapAndOthers.priorityQueueAndHeap;

/**
 * Created with IntelliJ IDEA.
 * User: fisherbill
 * Date: 13-1-10
 * Time: ����3:54
 * To change this template use File | Settings | File Templates.
 */
public class HeapTest
{
    public static void main(String[] args)
    {
        int[] arr={20, 40, 30, 10, 90, 70};
        Heap h=new MaxHeap(arr);
        h.display();
        h.add(100);
        h.add(60);
//        Heap.print(h.heap);
//        System.out.println(h.findMin());
//        Heap.heapSort(arr);
//        Heap.print(arr);




    }
}

package leetCode.linkedList;

import leetCode.ADT.ListNode;

import static leetCode.ADT.ListNode.createList;
import static leetCode.ADT.ListNode.printList;

/**
 * User: xinyuwan, Date: 12/22/13, Time: 4:03 PM
 */
public class Reverse {
    /*
     * Total Problems: 3
     */
    public static void main(String[] args) {
        //test ReverseListMN
        int[] a = {3, 5, 10};
        System.out.print("Result of reversing between node 1 and 2: ");
        printList(new ReverseListMN().reverseBetween(createList(a), 1, 2));

        //test SwapNodesInPairs
        int[] c = {1, 2, 3, 4, 5};
        System.out.print("Result of swapping nodes in pairs: ");
        printList(new SwapNodesInPairs().swapPairs(createList(c)));

        //test ReverseListInGroup
        int[] b = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.print("Result of reversing in group 3: ");
        printList(new ReverseListInGroup().reverseKGroup(createList(b), 3));
    }
}

class ReverseListMN {
    /*
     * Problem: Reverse a linked list from position m to n. Do it in-place and in one-pass.
     * For example: Given 1->2->3->4->5->NULL, m = 2 and n = 4,
     * return 1->4->3->2->5->NULL.
     * Note: Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of list.
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n)
            return head;
        ListNode result = new ListNode(0);
        result.next = head;
        ListNode nodeBeforeM = result;
        //Find the node before the mth node
        for (int i = 1; i < m; i++)
            nodeBeforeM = nodeBeforeM.next;
        //start iterate through the nodes from m to n
        head = nodeBeforeM.next;
        ListNode previous = nodeBeforeM;
        for (int i = 1; i <= n - m + 1; i++) {
            ListNode temp = head.next;
            head.next = previous;
            previous = head;
            head = temp;
        }
        //reset the next of nodeM and nodeN
        nodeBeforeM.next.next = head;
        nodeBeforeM.next = previous;
        return result.next;
    }
}

class SwapNodesInPairs {
    /*
     * Problem: Given a linked list, swap every two adjacent nodes and return its head.
     * For example, Given 1->2->3->4, you should return the list as 2->1->4->3.
     * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode result = head.next, temp = null, prev = null;
        //head can be null during iteration, so we must check head!=null first
        while (head != null && head.next != null) {
            temp = head.next;
            head.next = temp.next;
            temp.next = head;
            if (prev != null)
                prev.next = temp;
            prev = head;
            head = head.next;
        }
        return result;
    }
}

class ReverseListInGroup {
    /* Problem: Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
     * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
     * You may not alter the values in the nodes, only nodes itself may be changed.
     * Only constant memory is allowed.
     *
     *
     * Analysis: By using i as the indicator of whether we should reverse between previous to head.next(exclusively),
     * we can reverse the sequence k when i%k==0, or simple move forward head if not. The reverse method is
     * responsible to reverse the nodes between two nodes, and returns the new nodeBeforeStart for the next
     * iteration. We then update head to be the first node to update in the next iteration
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1)
            return head;
        ListNode result = new ListNode(0);
        result.next = head;
        //make sure previous!=null
        ListNode previous = result;
        int i = 0;
        while (head != null) {
            i++;
            if (i % k == 0) {
                previous = reverse(previous, head.next);
                head = previous.next;
            } else
                head = head.next;
        }
        return result.next;
    }

    private ListNode reverse(ListNode nodeBeforeStart, ListNode nodeAfterEnd) {
        ListNode previous = nodeBeforeStart, current = nodeBeforeStart.next,
                result = nodeBeforeStart.next;
        while (current != nodeAfterEnd) {
            ListNode temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
        }
        nodeBeforeStart.next.next = current;
        nodeBeforeStart.next = previous;
        //pay attention to the return node, we return the last node of the reversed sequence
        return result;
    }
}

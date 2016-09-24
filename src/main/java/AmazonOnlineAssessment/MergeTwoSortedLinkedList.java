package AmazonOnlineAssessment;

import util.Node;

/**
 * Created by Danqin on 4/8/14.
 */
public class MergeTwoSortedLinkedList {

    public static Node<Integer> mergeTwoList(Node<Integer> node1, Node<Integer> node2){
        if(node1==null)
            return node2;
        if(node2==null)
            return node1;
        Node<Integer> head=null;
        if(node1.data<node2.data) {
            head = node1;
            node1=node1.next;
        }else {
            head = node2;
            node2=node2.next;
        }
        Node current=head;
        while(node1!=null && node2!=null){
            if(node1.data<node2.data){
                current.next=node1;
                node1=node1.next;
                current=current.next;
            }
            else{
                current.next=node2;
                node2=node2.next;
                current=current.next;
            }
        }
        while(node1!=null){
            current.next=node1;
            node1=node1.next;
            current=current.next;
        }
        while(node2!=null){
            current.next=node2;
            node2=node2.next;
            current=current.next;
        }
        return head;
    }

    public static void main(String[] args){
        Integer[] array1={1,3,5,7,9};
        Integer[] array2={2,4,6,8,10};
        Node node1=Node.createList(array1);
        Node node2=Node.createList(array2);
        Node node=mergeTwoList(node1,node2);
        Node.printList(node);
    }
}

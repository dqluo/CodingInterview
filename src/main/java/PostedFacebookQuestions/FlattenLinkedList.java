package PostedFacebookQuestions;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-12
 * Time: 上午8:45
 * To change this template use File | Settings | File Templates.
 */
public class FlattenLinkedList {

    public static Node mergeTwoList(Node head){
        if(head==null)
            return head;
        Node current=head;
        while(current.next!=null)
        {
            Node next=current.next;
            if(next.optional!=null)
            {
                current.next=next.optional;
                Node optional=next.optional;
                while(optional.next!=null)
                {
                    optional=optional.next;
                }
                optional.next=next;
                next.optional=null;
                break;
            }
            current=current.next;
        }
        return head;
    }

    public static NodePair mergeTwoList2(Node head){
        if(head==null)
            return null;
        NodePair result=new NodePair();
        result.first=head;
        Node current=head;
        while(current.next!=null)
        {
            Node next=current.next;
            if(next.optional!=null)
            {
                NodePair temp=mergeTwoList2(next.optional);
                current.next=temp.first;
                temp.last.next=next;
                next.optional=null;
            }
            current=next;
        }
        result.last=current;
        return result;
    }

    public static void main(String[] args){
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        Node node5=new Node(5);
        Node node6=new Node(6);
        Node node7=new Node(7);
        Node node8=new Node(8);
        node1.next=node5;
        node5.optional=node2;
        node2.next=node4;
        node4.optional=node3;
        node7.next=node8;
        node5.next=node6;
        node6.next=node7;
        node7.next=node8;
        NodePair result=mergeTwoList2(node1);
        Node current=result.first;
        while(current!=null)
        {
            System.out.print(current.data);
            current=current.next;
        }
    }
}

class Node{
    public int data;
    public Node next;
    public Node optional;
    public Node(int d){
        data=d;
    }
}

class NodePair{
    Node first;
    Node last;

    public NodePair(){
        first=null;
        last=null;
    }

    public NodePair(Node first, Node last)
    {
        this.first=first;
        this.last=last;
    }
}

package postedLinkedInQuestions;

/**
 * Q43. Implement LinkedList
 */
public class LinkedList<E> {
    ListNode first;
    ListNode last;
    int size;

    public LinkedList(){
        first=null;
        last=null;
        size=0;
    }

    public void add(int data){
        ListNode l=last;
        ListNode newNode=new ListNode(data);
        last=newNode;
        if(l==null)
            first=newNode;
        else
            l.next=last;
        size++;
    }

    public int remove(){
        if(first==null)
            return 0;
        int data=first.data;
        first=first.next;
        if(first==null)
            last=null;
        return data;
    }

    public int size(){
        return size;
    }

    public void printList(){
        ListNode current=first;
        while(current!=null){
            System.out.print(current.data);
            current=current.next;
        }
    }

    class ListNode{
        int data;
        ListNode next;

        public ListNode(int data){
            this.data=data;
        }

        public ListNode(int data, ListNode next){
            this.data=data;
            this.next=next;
        }
    }

    public static void main(String[] args){
        LinkedList list=new LinkedList();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(7);
        list.add(9);
        list.printList();
        System.out.println();
        System.out.println("list size: "+list.size());
        System.out.println("first item: "+list.first.data);
        System.out.println("last item: "+list.last.data);
        int remove=list.remove();
        System.out.println("removed item: "+remove);
        list.printList();
        System.out.println();
    }
}

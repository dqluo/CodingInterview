package util;

import java.lang.reflect.Array;

/**
 * User: xinyuwan, Date: 10/27/13, Time: 6:13 PM
 */
public class Node<T>
{
    public T data;
    public Node<T> next;

    public Node() {}
    public Node(T data)
    {
        this(data, null);
    }
    public Node(T data, Node next)
    {
        this.data = data;
        this.next = next;
    }

    public static int listSize(Node head)
    {
        int count = 0;
        while (head != null)
        {
            head = head.next;
            count++;
        }
        return count;
    }

    public static void printList(Node head)
    {
        while(head!=null)
        {
            System.out.print(head.data + " ");
            head=head.next;
        }
        System.out.println();
    }

    public static<T> Node<T> createList(T[] a)
    {
        Node<T> current=null;
        for(int i=a.length-1; i>=0; i--)
        {
            current=new Node<T>(a[i], current);
        }
        return current;
    }
    public static<T> T[] convertToArray(Node<T> list, Class<T> cls)
    {
        if(list==null)
            return null;
        T[] result=(T[])Array.newInstance(cls, listSize(list));

        int i=0;
        while(list!=null)
        {
            result[i]= list.data;
            i++;
            list=list.next;
        }
        System.out.println(result.getClass());
        return result;

    }
    public static Node reverseList(Node head)
    {
        Node temp, previous=null;
        while(head!=null)
        {
            temp=head.next;
            head.next=previous;
            previous=head;
            head=temp;
        }
        return previous;
    }
//
//    public static void main(String[] args)
//    {
//        Method[] methods=Node.class.getMethods();
//        for(Method method : methods)
//            System.out.println("method = "+method.getName());
//    }
}

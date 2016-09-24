package util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ArrayListUtil
{
    public static void printLList(ArrayList<LinkedList<BinaryNode>> a)
    {
        for(LinkedList l: a)
        {
            Iterator<BinaryNode> itr=l.iterator();
            while(itr.hasNext())
                System.out.print(itr.next().data + " ");
            System.out.println();
        }
    }
    public static <T> void print(ArrayList<T> list)
    {
        for(T x: list)
            System.out.println(x);
        System.out.println();
    }
}

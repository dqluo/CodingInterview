package postedLinkedInQuestions;

import java.util.Arrays;

/**
 * Q2: 数组排序， 排成a1<a2>a3<a4>a5
 *
 * If it is a1=<a2>=a3=<a4>=a5, then wierdSort and wierdSort2 is good enough.
 * And the time complexity for wierdSort2 is O(n), the time complexity for
 * wierdSort is O(nlogn)
 */
public class WierdSort {

    private static int[] wierdSort(int[] array){
        if(array.length==0)
            return array;
        Arrays.sort(array);
        for(int i=1;i<array.length-1;i=i+2){
            array[i]=array[i]^array[i+1];
            array[i+1]=array[i]^array[i+1];
            array[i]=array[i]^array[i+1];
        }
        return array;
    }

    private static int[] wierdSort2(int[] array){
        if(array.length==0)
            return array;
        for(int i=1;i<array.length-1;i=i+2){
            if(array[i]>array[i-1] && array[i]>array[i+1])
                continue;
            else if(array[i]>array[i-1] && array[i]<array[i+1]) {
                array[i]=array[i]^array[i+1];
                array[i+1]=array[i]^array[i+1];
                array[i]=array[i]^array[i+1];
            }else if(array[i-1]>array[i] && array[i]>array[i+1]) {
                array[i] = array[i] ^ array[i - 1];
                array[i - 1] = array[i] ^ array[i - 1];
                array[i] = array[i] ^ array[i - 1];
            }
        }
        return array;
    }

    private static void print(int[] array){
        for(int i=0;i<array.length;i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println();
    }

    public static void main(String args[]){
        int[] array={1,4,3,6,7,8,3};
        print(wierdSort(array));
        int[] array1={1,4,3,6,7,8,3};
        print(wierdSort2(array1));
    }
}

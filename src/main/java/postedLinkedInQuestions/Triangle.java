package postedLinkedInQuestions;

import java.util.Arrays;

/**
 * Q14: 三角形问题，输入一些线段的长度，找出能形成三角形的三条线段
 */
public class Triangle {

    public static int[] triangle(int[] array){
        int[] result=new int[3];
        Arrays.sort(array);
        for(int i=0;i<array.length-2;i++){
            if(array[i]+array[i+1]>array[i+2]){
                result[0]=array[i];
                result[1]=array[i+1];
                result[2]=array[i+2];
                return result;
            }
        }
        return result;
    }

    public static void main(String args[]){
        int[] array={2,4,7,9,3,5,10};
        int[] result=triangle(array);
        for(int i=0;i<result.length;i++){
            System.out.print(result[i]+", ");
        }
    }
}

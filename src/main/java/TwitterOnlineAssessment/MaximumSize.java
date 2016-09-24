package TwitterOnlineAssessment;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-3-17
 * Time: 上午5:54
 * To change this template use File | Settings | File Templates.
 */
public class MaximumSize {

    public static int maxSize(int[] array){
        Stack<Integer> stack=new Stack<Integer>();
        int[] len=new int[array.length];
        for(int i=0;i<array.length;i++)
        {
            int length=0;
            for(int j=i;j<array.length && array[j]<array.length;j=array[j])
            {
                stack.push(i);
                length++;
            }

        }
        return -1;
    }

    public static int countNum(int[] A, int index) {
        int count = 1;
        int M = A[index];
        boolean[] visited = new boolean[A.length];
        visited[index] = true;
        while(M >= 0 && M < A.length && visited[M] != true) {
            int K = M;
            M = A[K];
            visited[K] = true;
            count++;
        }
        return count;
    }

    public static int maxSequence(int[] A) {
        if(A == null || A.length == 0) return 0;
        int max = -1;
        for(int i = 0; i < A.length; i++) {
            int temp = countNum(A, i);
            max = Math.max(temp, max);
        }
        return max;
    }

    public static void main(String[] args){
        int[] array1={3,0,1,2};
        int[] array2={3, 2, 1, 0, 0, 4, 5};
        System.out.println(maxSequence(array1));
        System.out.println(maxSequence(array2));
    }
}

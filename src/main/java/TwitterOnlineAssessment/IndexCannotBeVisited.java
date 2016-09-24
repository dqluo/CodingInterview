package TwitterOnlineAssessment;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-3-14
 * Time: 下午1:30
 * To change this template use File | Settings | File Templates.
 */
public class IndexCannotBeVisited {

    public static int indexNumbers(int[] array, int n){
        boolean[] canBeReached=new boolean[n];
        int index=0;
        int numberCanBeReached=0;
        while(index>=0 && index<n && canBeReached[index]!=true){
            canBeReached[index]=true;
            if(index<n)
                numberCanBeReached++;
            index=array[index]+index;
        }
        return n-numberCanBeReached;
    }

    public static int nonVisited(int[] A, int N) {
        if(A == null || A.length == 0) return 0;
        boolean[] visited = new boolean[A.length];
        int K = 0;
        int M = K + A[K];
        visited[0] = true;
        while(M >= 0 && M < A.length && visited[M] != true) {
            K = M;
            M = K + A[K];
            visited[K] = true;
        }
        int count = 0;
        for(int i = 0; i < visited.length; i++) {
            if(visited[i] == false) count++;
        }
        return count;
    }


    public static void main(String[] args){
        int[] array1={1, 2, 3};
        int[] array2={3, -5, 0, -1, -3};
        int[] array3={-1, 4,6};
        int[] array4={1,-1};
        int[] array5={1,1,1,1,1};
        System.out.println(indexNumbers(array1, 3));
        System.out.println(indexNumbers(array2, 5));
        System.out.println(indexNumbers(array3, 3));
        System.out.println(indexNumbers(array4, 2));
        System.out.println(indexNumbers(array5, 5));
        System.out.println();
        System.out.println(nonVisited(array1, 3));
        System.out.println(nonVisited(array2, 5));
        System.out.println(nonVisited(array3, 3));
        System.out.println(nonVisited(array4, 2));
        System.out.println(nonVisited(array5, 5));
    }
}

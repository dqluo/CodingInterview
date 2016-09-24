package postedLinkedInQuestions;

/**
 * Q15. 给int[] a,求int[] result。 其中result[i]=a1*a2…*ai-1*ai+1*…an，follow
 * up:不能用除法
 *
 */
public class arrayMultiple {

    public static int[] multiple(int[] array){
        int[] result=new int[array.length];
        int[] leftMultiple=new int[array.length];
        int[] rightMultiple=new int[array.length];
        for(int i=0;i<array.length;i++){
            if(i==0)
                leftMultiple[0]=1;
            else
                leftMultiple[i]=leftMultiple[i-1]*array[i-1];
        }
        int right=1;
        for(int i=array.length-1;i>=0;i--){
            result[i]=right*leftMultiple[i];
            right=right*array[i];
        }
        return result;
    }

    public static void main(String args[]){
        int[] array={2,4,2,5,7,4,5};
        int[] result=multiple(array);
        for(int i=0;i<array.length;i++)
            System.out.print(result[i]+" ");
    }
}

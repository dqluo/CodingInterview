package postedLinkedInQuestions;

/**
 * Q63. 洗牌 要求in-place
 */
public class ShuffleCard {

    public static int[] shuffleCard(int[] array){
        for(int i=0;i<array.length;i++){
            int index=(int)(Math.random()*(array.length-i))+i;
            int temp=array[i];
            array[i]=array[index];
            array[index]=temp;
        }
        return array;
    }

    public static void main(String args[]){
        int[] array={1,2,3,4,5,6,7,8,9,10};
        int[] result=shuffleCard(array);
        for(int i=0;i<result.length;i++){
            System.out.print(result[i]+" ");
        }
    }
}

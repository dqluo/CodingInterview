package postedLinkedInQuestions;

/**
 * Q65. 给你一个数组，其中一个数出现了大于N／３次，N是数组长度。怎么找？
 * 我先说HASHTABLE，他问我还有没有什么办法。想来想去只能SORT. 他就问下一题了。
 * 不知道还有没有什么最优解。我觉得那种针对一个数字出现过大于N／２的VOTING
 * ALGORITHM好象不是很合适吧
 */
public class findNumberMoreThanNDivide3 {

    public static int findNumber(int[] number){
        int[] slot=new int[2];
        int[] counter=new int[2];
        for(int i=0;i<number.length;i++){
            if(slot[0]==0)
                slot[0]=number[i];
            else{
                if(number[i]==slot[0])
                    continue;
                else {
                    slot[1] = number[i];
                    break;
                }
            }
        }
        for(int i=0;i<number.length;i++){
            for(int j=0;j<slot.length;j++){
                if(number[i]==slot[j])
                    counter[j]++;
                else{
                    counter[j]--;
                    if(counter[j]==0){
                        slot[j]=number[i];
                        counter[j]=1;
                    }
                }
            }
        }
        int result=0;
        if(counter[0]>0)
            result=slot[0];
        else
            result=slot[1];
        int count=0;
        for(int i=0;i<number.length;i++){
            if(number[i]==result)
                count++;
        }
        if(count>number.length/3)
            return result;
        return 0;
    }

    public static void main(String[] args){
        int[] array={1,1,4,5,3,1,1,1,2};
        System.out.println(findNumber(array));
    }
}

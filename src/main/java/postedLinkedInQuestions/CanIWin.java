package postedLinkedInQuestions;

/**
 * Q1 (1point3acres)1.	又被问了个面经外的题，目测是杯具了，实在是没梳理出逻辑
 * boolean canIWin(int maxNum, int target)，从1,2...maxNum的数组里两个玩家
 * 轮流选数，第一个达到sum>=target的玩家获胜，问如何判断先选的玩家能获胜。能想到
 * 的就是先求总和sum, 如果sum < target 无解, false。如果sum == target, 根据数
 * 组的长度判断，奇数个则true偶数个false。然后是sum>target, 双方的目标是要至少
 * 保证选完数x后target-x > 数组里留下的最大数，然后递归，根据回合数的奇偶判断是
 * true还是false。然后就这个逻辑不知道该怎么实现了。。。
 */
public class CanIWin {

    public static boolean canIWin(int maxNum, int target){
        boolean[] numbers=new boolean[maxNum];
        if(maxNum<=0)
            return false;
        for(int i=1;i<=maxNum;i++){
            if(i>=target)
                return true;
        }
        return canIWin(numbers, target);
    }

    private static boolean canIWin(boolean[] numbers, int target){
        if(target<=0)
            return false;
        boolean result=false;
        for(int i=1;i<=numbers.length;i++){
            numbers[i-1]=true;
            result=result|| !canIWin(numbers, target-i);
            numbers[i-1]=false;
        }
        return result;
    }

    public static void main(String[] args){
        System.out.println(canIWin(4, 5));
        System.out.println(canIWin(4, 4));
    }
}

package postedLinkedInQuestions;

import java.util.*;

/**
 * print  factors of a given integer
 * example： 12 可以表示为：
 * 12 ＊1
 * 6 ＊2 ＊1
 * 4 ＊3 ＊1
 * 3 ＊2 ＊2 ＊1
 * 要求走几个例子，写出完整的递归的stack trace
 * 题目差不多都做出来了
 *
 */

public class printFactor {

    public static void printFactor(int num){
        Set<List<Integer>> result=new HashSet<List<Integer>>();
        List<Integer> firstList=new ArrayList<Integer>();
        firstList.add(1);
        firstList.add(num);
        result.add(firstList);
        List<Integer> list=new ArrayList<Integer>();
        printFactor(num, result, list);
        for(List<Integer> factorList : result){
            for(int i=0;i<factorList.size()-1;i++)
                System.out.print(factorList.get(i)+"*");
            System.out.println(factorList.get(factorList.size() - 1));
        }
    }

    public static void printFactor(int num, Set<List<Integer>> result, List<Integer> list){
        int sqrt=(int)Math.sqrt(num);
        for(int i=2;i<=sqrt;i++){
            if(num%i==0){
                if(isPrime(num/i)) {
                    List<Integer> tempList = new ArrayList<Integer>(list);
                    tempList.add(i);
                    tempList.add(num / i);
                    Collections.sort(tempList);
                    result.add(tempList);
                    return;
                }else{
                    list.add(i);
                    list.add(num/i);
                    List<Integer> tempList=new ArrayList<Integer>(list);
                    Collections.sort(tempList);
                    result.add(tempList);
                    list.remove(list.size()-1);
                    printFactor(num/i, result, list);
                    list.remove(list.size()-1);
                }
            }
        }
    }

    private static boolean isPrime(int num){
        double result=Math.pow(2.5, 5.6);
        if(num<2)
            return false;
        int sqrt=(int)Math.sqrt(num);
        for(int i=2;i<=sqrt;i++){
            if(num%i==0)
                return false;
        }
        return true;

    }

    public static void main(String args[]){
        printFactor(24);
    }

}

package postedLinkedInQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q6: 查找2个单词的距离
 *
 * Example:
 *   WordDistanceFinder finder = new WordDistanceFinder(Arrays.asList("the",
 "quick", "brown", "fox", "quick"));
 *   assert(finder.distance("fox","the") == 3);
 *   assert(finder.distance("quick", "fox") == 1);
 *
 */

public class FindDistanceBetweenTwoWords {

    public static int findDistance(List<String> source, String target1, String target2){
        List<Integer> position1=new ArrayList<Integer>();
        List<Integer> position2=new ArrayList<Integer>();
        for(int i=0;i<source.size();i++){
            if(source.get(i).equals(target1))
                position1.add(i);
            else if(source.get(i).equals(target2))
                position2.add(i);
        }
        if(position1.size()==0 || position2.size()==0)
            return -1;
        int result=Integer.MAX_VALUE;
        int index1=0;
        int index2=0;
        int distance=0;
        while(index1<position1.size() && index2<position2.size()){
            if(position1.get(index1)<position2.get(index2)){
                distance=position2.get(index2)-position1.get(index1);
                result=Math.min(result, distance);
                index1++;
            }else{
                distance=position1.get(index1)-position2.get(index2);
                result=Math.min(result, distance);
                index2++;
            }
        }
        return result;
    }

    public static void main(String args[]){
        List<String> source= Arrays.asList("the", "is", "us", "quick", "brown", "fox", "quick", "the", "quick");
        System.out.println(findDistance(source, "the", "quick"));
    }
}

package postedLinkedInQuestions;

import java.util.Arrays;

/**
 * Q7: Return the smallest character that is strictly larger than the search character,
 * ['c', 'f', 'j', 'p', 'v'], 'a' => 'c'
 *
 * refer to http://www.mitbbs.com/article_t/JobHunting/32763769.html for different solution:
 *
 * Return the smallest character that is strictly larger than the search
 * character,
 * If no such character exists, return the smallest character in the array
 * @param sortedStr : sorted list of letters, sorted in ascending order.
 * @param c : character for which we are searching.
 * Given the following inputs we expect the corresponding output:
 * ['c', 'f', 'j', 'p', 'v'], 'a' => 'c'
 * ['c', 'f', 'j', 'p', 'v'], 'c' => 'f'
 * ['c', 'f', 'j', 'p', 'v'], 'k' => 'p'
 * ['c', 'f', 'j', 'p', 'v'], 'z' => 'c' // The wrap around case
 * ['c', 'f', 'k'], 'f' => 'k'
 * ['c', 'f', 'k'], 'c' => 'f'
 * ['c', 'f', 'k'], 'd' => 'f'
 */

public class SmallestChar {
    public static char findSmallest(char[] array, char target){
        Arrays.sort(array);
        for(int i=0;i<array.length;i++){
            if(array[i]<=target)
                continue;
            else
                return array[i];
        }
        return ' ';
    }

    public static char findSmallest2(char[] list, char target){
        if (target < list[0] || target > list[list.length - 1])
            return list[0];

        int left = 0, right = list.length - 1;
        char result = list[0];
        while (left < right) {
            int mid = (left + right)/ 2;
            if (list[mid] == target) {
                return list[mid+1];
            }
            else if (list[mid] < target) {
                left = mid + 1;
            }
            else {
                result = list[mid];
                right = mid - 1;
            }
        }
        return result;
    }

    public static void main(String args[]){
        char[] array={'c', 'f', 'j', 'p', 'v'};
        System.out.println(findSmallest(array, 'a'));
        System.out.println(findSmallest2(array, 'a'));
    }
}

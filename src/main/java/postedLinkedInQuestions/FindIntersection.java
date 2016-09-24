package postedLinkedInQuestions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danqin on 9/17/14.
 */
public class FindIntersection {

    public static List<Integer> findIntersection(int[] A, int[] B){
        int L = A.length;
        int K = B.length;

        List<Integer> intersectionArr = new ArrayList<Integer>();
        int i = L - 1;
        int j = K - 1;

        while ((i >= 0) && (j >=  0))
        {
            if (A[i] > B[j])
            {
                i--;
            }
            else if (B[j] > A[i])
            {
                j--;
            }
            else
            {
                intersectionArr.add(A[i]);
                i--;
                j--;
            }
        }
        return intersectionArr;
    }
}

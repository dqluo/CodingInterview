package crackingAndMadeEasy.searchAndSort.basic;

/**
 * User: xinyuwan, Date: 11/29/13, Time: 6:28 PM
 */
public class BinarySearch
{
    public static void main(String[] args)
    {
        //Test BinarySearch
        int[] x={2, 6, 7, 10, 14, 18, 25, 36, 49, 78};
        System.out.println("Search 78: "+(search(x, 78)==-1? "Not found"
                : search(x, 78)+" "+search2(x, 78)));
        System.out.println("Search 6: "+(search(x, 6)==-1? "Not found"
                : search(x, 6)+" "+search2(x, 6)));
        System.out.println("Search -1: "+(search2(x, -1)==-1? "Not found"
                : search2(x, -1)));

        //Test FindTurningPoint
        int[] a={2, 4, 8, 11, 17, 24, 25, 30, 27, 21, 15, 12, 10};
        System.out.println("Turning Point: "+FindTurningPoint.find(a));

        //Test SearchInRotationSeq
        int[] b={18, 20, 21, 24, 35, 50, 2, 5, 9, 12};
        System.out.println("Search 9: "+SearchInRotationSeq.search(b, 9));
        System.out.println("Search 12: "+SearchInRotationSeq.search(b, 12));
        System.out.println("Search 18: "+SearchInRotationSeq.search(b, 18));
        System.out.println("Search 35: "+SearchInRotationSeq.search(b, 35));
        System.out.println("Search 3: "+SearchInRotationSeq.search(b, 3));
        System.out.println("Search 19: "+SearchInRotationSeq.search(b, 19));

        System.out.println("Search 9: "+SearchInRotationSeq.search2(b, 9));
        System.out.println("Search 12: "+SearchInRotationSeq.search2(b, 12));
        System.out.println("Search 18: "+SearchInRotationSeq.search2(b, 18));
        System.out.println("Search 35: "+SearchInRotationSeq.search2(b, 35));
        System.out.println("Search 3: "+SearchInRotationSeq.search2(b, 3));
        System.out.println("Search 19: "+SearchInRotationSeq.search2(b, 19));

        //Test FindMissingInSortedArr
        int[] c={3, 4, 6, 7, 8, 9};
        int[] c1={3, 4, 5, 6, 7, 8, 9};
        System.out.println("The missing element is "+FindMissingInSortedArr.find(c));
        System.out.println("The missing element is "+FindMissingInSortedArr.find(c1));

        //Test FindNumOfOccur
        int[] d={2, 4, 6, 6, 6, 9, 11, 12, 14, 18};
        System.out.println("6 occurs "+SearchNumOfOccur.find(d, 6)+" times");
        System.out.println("2 occurs "+SearchNumOfOccur.find(d, 2)+" times");

        //Test findMagic
        int[] e={-10, -5, 2, 2, 2, 2, 4, 7, 9, 12, 13};
        int[] e1={-40, -20, -1, 1, 2, 3, 5, 7, 9, 12, 13};
        System.out.println("Magic index of e1 is "+FindMagicIndex.find(e1, 0, e1.length-1));
        System.out.println("Magic index of e is "+FindMagicIndex.findWithDup(e, 0, e.length-1));

        //Test SearchFirstOccur
        int[] f={2, 4, 4, 5, 8, 11, 11, 11, 19, 28, 42};
        System.out.println("11 first occurs in "+SearchFirstOccurInDupArr.search(f, 11));

        //Test SearchInArrWithEmptyString
        String[] s={"at", "", "", "", "ball", "", "car", "", "", "dad", "", "", "", "eye"};
        String[] ss={"a", "b", "", "", ""};
        System.out.println(SearchInArrWithEmptyStr.search(s, "dad"));
        System.out.println(SearchInArrWithEmptyStr.search(s, "ball"));
        System.out.println(SearchInArrWithEmptyStr.search(s, "att"));
        System.out.println(SearchInArrWithEmptyStr.search(ss, "a"));

    }
    public static int search(int[] a, int k)
    {
        return search(a, k, 0, a.length-1);
    }
    public static int search(int[] a, int k, int first, int last)
    {
        if(first>last)
            return -1;
        int mid=(first+last)/2;
        if(k == a[mid])
            return mid;
        else if(k < a[mid])
            return search(a, k, first, mid-1);
        else
            return search(a, k, mid+1, last);
    }

    public static int search2(int[] a, int k)
    {
        int first=0, last=a.length-1;
        while(first <= last)
        {
            int mid=(first+last)/2;
            if(k == a[mid])
                return mid;
            else if(k < a[mid])
                last=mid-1;
            else
                first=mid+1;
        }
        return -1;
    }
}

class FindTurningPoint
{
    public static int find(int[] a)
    {
        return find(a, 0, a.length-1);
    }
    private static int find(int[] a, int first, int last)
    {
        if(first == last)
             return a[first];
        if(first+1 == last)
            return Math.max(a[first], a[last]);
        int mid=(first+last)/2;
        if(a[mid] > a[mid-1] && a[mid] > a[mid+1])
            return a[mid];
        else if(a[mid] > a[mid-1] && a[mid] < a[mid+1])
            return find(a, mid+1, last);
        else if(a[mid] < a[mid-1] && a[mid] > a[mid+1])
            return find(a, first, mid-1);
        else
            return -1;
    }


}

class SearchInRotationSeq
{
    public static int search(int[] a, int k)
    {
        return search(a, k, 0, a.length-1);
    }

    public static int search(int[] a, int k, int first, int last)
    {
        if(first > last)
            return -1;
        int mid=(first+last)/2;
        if(a[mid]==k)
            return mid;
        else if(a[mid] > a[first])
        {
            if(k >= a[first] && k < a[mid])
                return search(a, k, first, mid-1);
            else
                return search(a, k, mid+1, last);
        }
        else
        {
            if(k > a[mid] && k <= a[last])
                return search(a, k, mid+1, last);
            else
                return search(a, k, first, mid-1);
        }
    }
    public static int search2(int[] a, int k)
    {
        int pivotIndex=findPivot(a, 0, a.length-1);
        if(k == a[pivotIndex])
            return pivotIndex;
        else if(k >= a[0] && k < a[pivotIndex])
            return BinarySearch.search(a, k, 0, pivotIndex-1);
        else
            return BinarySearch.search(a, k, pivotIndex+1, a.length-1);
    }
    private static int findPivot(int[] a, int first, int last)
    {
        if(first==last)
            return a[first];
        if(first+1==last)
            return a[first] >= a[last] ? first : last;
        int mid=(first+last)/2;
        if(a[mid] >= a[first])
            return findPivot(a, mid, last);
        else
            return findPivot(a, first, mid);
    }
}

class FindMissingInSortedArr
{
    public static int find(int[] a)
    {
        if(a.length<=1)
            return Integer.MAX_VALUE;
        return find(a, 0, a.length-1);
    }

    private static int find(int[] a, int first, int last)
    {
        if(first+1 == last)
            return a[first]+2==a[last]? a[first]+1 : -1;
        int mid=(first+last)/2;
        double expectMedian=(a[first]+a[last])/(2.0);
        double median=(last-first+1)%2==0? (a[mid]+a[mid+1])/2.0 : a[mid];
        if(median > expectMedian)
            return find(a, first, mid);
        else
            return find(a, mid, last);
    }
}

class SearchNumOfOccur
{
    public static int find(int[] a, int k)
    {
        return find(a, k, 0, a.length-1);
    }

    public static int find(int[] a, int k, int first, int last)
    {
        if(first > last)
            return 0;
        int mid=(first+last)/2;
        if(k == a[mid])
        {
            int i=mid-1, j=mid+1, count=1;
            while(i > 0 && a[i]==k)
            {
                count++;
                i--;
            }
            while(j < a.length && a[j] == k)
            {
                count++;
                j++;
            }
            return count;
        }
        else if(k < a[mid])
            return find(a, k, first, mid-1);
        else
            return find(a, k, mid+1, last);
    }
}

class FindMagicIndex
{
    //all elements are distinctive
    public static int find(int[] a, int first,  int last)
    {
        if(first > last)
            return -1;
        int mid=(first+last)/2;
        if(a[mid] == mid)
            return mid;
        else if(a[mid] > mid)
            return find(a, first, mid-1);
        else
            return find(a, mid+1, last);
    }
    //has duplicate
    public static int findWithDup(int[] a, int first, int last)
    {
        //make sure that the index passed in is in valid range
        if(first > last || first < 0 || last >= a.length)
            return -1;
        int mid=(first+last)/2;
        if(a[mid] == mid)
            return mid;
        int left=findWithDup(a, first, Math.min(a[mid], mid-1));
        if(left > 0)
            return left;
        return findWithDup(a, Math.max(a[mid], mid+1), last);
    }
}

class SearchFirstOccurInDupArr
{
    public static int search(int[] a, int k)
    {
        return search(a, k, 0, a.length-1);
    }
    public static int search(int[] a, int k , int first,  int last)
    {
        if(first > last)
            return -1;
        int mid=(first+last)/2;
        if((a[mid] == k && mid == first)
            || (a[mid] == k && a[mid-1] < a[mid]))
            return mid;
        else if(k < a[mid])
            return search(a, k, first, mid-1);
        else
            return search(a, k, mid+1, last);
    }
}


class SearchInArrWithEmptyStr
{
    public static int search(String[] a, String k)
    {
        if(a==null || k==null || k.isEmpty())
            return -1;
        return search(a, k, 0, a.length-1);
    }
    public static int search(String[] a, String k, int first, int last)
    {
        if(first > last)
            return -1;
        int mid=(first+last)/2;
        if(a[mid].equals(""))
        {
            int left=mid-1, right=mid+1;
            while(true)
            {
                if(left < first && right > last)
                    return -1;
                if(left >= first && !a[left].equals(""))
                {
                    mid=left;
                    break;
                }
                if(right <=last && !a[right].equals(""))
                {
                    mid=right;
                    break;
                }
                left--;
                right++;
            }
        }
        if(k.equals(a[mid]))
            return mid;
        else if(k.compareTo(a[mid]) < 0)
            return search(a, k, first, mid-1);
        else
            return search(a, k, mid+1, last);

    }
}



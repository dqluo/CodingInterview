package leetCode.math;

/**
 * User: xinyuwan, Date: 1/5/14, Time: 12:42 AM
 */
public class ZigZagConversion
{
    public static void main(String[] args)
    {
        String s="PAYPALISHIRING";
        ZigZagConversion zzc=new ZigZagConversion();
        System.out.println(zzc.convert(s, 3));
    }

    /*
     * Problem: The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * And then read line by line: "PAHNAPLSIIGYIR"
     * Write the code that will take a string and make this conversion given a number of rows:
     * string convert(string text, int nRows);
     * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
     *
     * Analysis: This problem is only tricky in doing the math in index
     * Observe that we have a 'V' shape for every 6 chars(with the bottom char used twice) until the end of the string.
     * For every two columns, if the index of the former is x, then the latter is x+2*n-2. This enough for the
     * first and last rows. For other rows in between, we need to add one more char between the former and the latter.
     * The index can be calculated as x+2*n-2-2*i. i is the row index(0....nRow-1). For each row, we calculate the next
     * char to be added into the final string, distinguishing the two cases.
     */
    public String convert(String s, int nRows)
    {
        if(s==null || s.length()<=1 || nRows==1 || s.length()<=nRows)
            return s;
        StringBuilder sb=new StringBuilder();
        for(int i=0; i<nRows; i++)
        {
            int diff=2*nRows-2;
            int index=i;
            if(i==0 || i==nRows-1)
            {
                while(index < s.length())
                {
                    sb.append(s.charAt(index));
                    index+=diff;
                }
            }
            else
            {
                while(index < s.length())
                {
                    sb.append(s.charAt(index));
                    if(index+diff-2*i < s.length())
                        sb.append(s.charAt(index+diff-2*i));
                    index+=diff;
                }
            }
        }
        return sb.toString();
    }
}

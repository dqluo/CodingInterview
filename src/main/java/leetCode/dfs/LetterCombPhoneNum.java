package leetCode.dfs;

import java.util.ArrayList;

/**
 * User: xinyuwan, Date: 12/23/13, Time: 1:37 AM
 */
public class LetterCombPhoneNum
{
    public static void main(String[] args)
    {
        String digits="23";
        LetterCombPhoneNum lcp=new LetterCombPhoneNum();
        System.out.println(lcp.letterCombinations(digits));
    }
    /*
     * Problem: Given a digit string, return all possible letter combinations that the number could represent.
     * A mapping of digit to letters (just like on the telephone buttons) is given below.
     *
     * Input: Digit string "23"
     * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */

    public ArrayList<String> letterCombinations(String digits)
    {
        ArrayList<String> result=new ArrayList<String>();
        String[] buttons=initButtons();
        letterCombinations(digits, buttons, 0, new StringBuffer(), result);
        return result;
    }
    private void letterCombinations(String digits, String[] buttons, int index, StringBuffer sb, ArrayList<String> result)
    {
        if(index==digits.length())
        {
            result.add(sb.toString());
            return;
        }
        //to convert char to int, we need to use char - charZero
        String current=buttons[digits.charAt(index)-'0'];
        //still typical dfs: index controls recursion, i controls for loop span
        for(int i=0; i<current.length(); i++)
        {
            sb.append(current.charAt(i));
            letterCombinations(digits, buttons, index+1, sb, result);
            sb.deleteCharAt(index);
        }

    }
    private String[] initButtons()
    {
        String[] buttons=new String[10];
        buttons[0]=" ";buttons[1]="1";buttons[2]="abc";
        buttons[3]="def";buttons[4]="ghi";buttons[5]="jkl";
        buttons[6]="mno";buttons[7]="pqrs";buttons[8]="tuv";buttons[9]="wxyz";
        return buttons;
    }
}

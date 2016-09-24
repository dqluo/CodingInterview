package leetCode.gimmick;

import java.util.ArrayList;

/**
 * User: xinyuwan, Date: 1/14/14, Time: 12:48 AM
 */
public class TextJustification
{
    public static void main(String[] args)
    {
        String[] words={"This", "is", "an", "example", "of", "text", "justification."};
        TextJustification tj=new TextJustification();
        System.out.println("Result of full justification: ");
        for(String line : tj.fullJustify(words, 16))
        {
            System.out.println("["+line+"]");
        }
    }
    public ArrayList<String> fullJustify(String[] words, int L)
    {
        ArrayList<String> result=new ArrayList<String>();
        for(int start=0; start<words.length;)
        {
            int count=words[start].length();
            StringBuilder sbuilder=new StringBuilder(words[start]);
            int next=start+1;
            //first append one space preceding each word to see how far we can in the words array
            while(count<L && next<words.length)
            {
                count+=1+words[next].length();
                //do not advance next furthermore if count is already greater than L
                if(count<=L)
                    next++;
            }
            //the last word of this row has exceeds L, we need to roll back and only append range: [start+1, next-1]
            if(count > L)
            {
                count-=1+words[next].length();
                next--;
                //if there's more than one word in this row, we recalculate the spaces
                if(next>start)
                {
                    int averageBlank=(L-count+next-start)/(next-start);
                    int extraBlank=(L-count+next-start)%(next-start);
                    for(int i=start+1;i<=start+extraBlank;i++)
                    {
                        //for each word in range[start+1, start+extraBlank], append averageBlank+1 spaces
                        for(int j=0;j<averageBlank+1;j++)
                            sbuilder.append(" ");
                        sbuilder.append(words[i]);
                    }
                    //now append the rest of words, we are at start+extraBlank, so we need to start from start+extraBlank+1
                    for(int i=start+extraBlank+1;i<=next;i++)
                    {
                        for(int j=0;j<averageBlank;j++)
                            sbuilder.append(" ");
                        sbuilder.append(words[i]);
                    }
                }
                //next==start only one word in this line, just append spaces for the rest of this row
                else
                {
                    for(int i=0;i<L-count;i++)
                        sbuilder.append(" ");
                }
                next++;
            }
            //count==L OR next == words.length, we've reached the last line
            //for both cases, the valid range of words is [start, next-1]
            else
            {
                for(int i=start+1;i<next;i++)
                {
                    sbuilder.append(" ");
                    sbuilder.append(words[i]);
                }
                for(int i=0;i<L-count;i++)
                    sbuilder.append(" ");
            }
            start=next;
            result.add(sbuilder.toString());
        }
        return result;
    }
}

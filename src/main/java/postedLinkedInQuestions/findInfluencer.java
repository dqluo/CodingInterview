package postedLinkedInQuestions;

import java.util.Stack;

/**
 * Q13. 给一个矩阵，followMatrix[i][j]==true，表示j影响了i。求influencer，即影响
 * 所有人，且不被任何人影响
 *
 */

public class findInfluencer {

    public static int findInfluencer(boolean[][] matrix){
        if(matrix.length==1)
            return 0;
        Stack<Integer> stack=new Stack<Integer>();
        for(int i=0;i<matrix.length;i++)
            stack.push(i);
        int result=-1;
        while(!stack.isEmpty()){
            int first=stack.pop();
            int second=stack.pop();
            if(matrix[first][second]) {
                if(stack.isEmpty()) {
                    result = first;
                    break;
                }else
                    stack.push(first);
            }else{
                if(stack.isEmpty()) {
                    result=second;
                    break;
                }else
                    stack.push(second);
            }
        }
        for(int i=0;i<matrix.length;i++){
            if(i!=result){
                if(!matrix[result][i] || matrix[i][result])
                    return -1;
            }
        }
        return result;
    }

    public static void main(String args[]){
        boolean[][] matrix={{false, true, false, false},
                            {false, false, false, false},
                            {true, true, false, true},
                            {false, true, false, false}};
        System.out.println(findInfluencer(matrix));
    }
}

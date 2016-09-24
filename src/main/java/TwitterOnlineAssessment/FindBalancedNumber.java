package TwitterOnlineAssessment;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-3-14
 * Time: 下午3:00
 * To change this template use File | Settings | File Templates.
 */
public class FindBalancedNumber {

    public static int findNumber(int[][] matrix){
        formMatrix(matrix);
        int result=0;
        if(matrix[0][matrix[0].length-1]==0 && matrix[matrix.length-1][0]==0)
            result++;
        for(int i=1; i<matrix.length; i++)
        {
            if(matrix[i-1][0]==(matrix[matrix.length-1][0]-matrix[i][0]) && (matrix[i][matrix[0].length-1]==0))
                result++;
        }
        for(int i=1; i<matrix[0].length; i++)
        {
            if(matrix[0][i-1]==(matrix[0][matrix[0].length-1]-matrix[0][i]) && (matrix[matrix.length-1][i]==0))
                result++;
        }
        for(int i=1; i<matrix.length; i++)
        {
            for(int j=1; j<matrix[0].length; j++)
            {
                int rowAbove=matrix[i-1][j]-matrix[i-1][j-1];
                int rowBelow=matrix[matrix.length-1][j]-matrix[matrix.length-1][j-1]-(matrix[i][j]-matrix[i][j-1]);
                int colBefore=matrix[i][j-1]-matrix[i-1][j-1];
                int colAfter=matrix[i][matrix[0].length-1]-matrix[i-1][matrix[0].length-1]-(matrix[i][j]-matrix[i-1][j]);
                if(rowAbove==rowBelow && colBefore==colAfter)
                    result++;
            }
        }
        return result;
    }

    private static void formMatrix(int[][] matrix){
        for(int i=1;i<matrix.length;i++)
            matrix[0][i]+=matrix[0][i-1];
        for(int i=1;i<matrix[0].length;i++)
            matrix[i][0]+=matrix[i-1][0];
        for(int i=1;i<matrix.length;i++)
        {
            for(int j=1;j<matrix[0].length;j++)
            {
                matrix[i][j]+=matrix[i-1][j]+matrix[i][j-1]-matrix[i-1][j-1];
            }
        }
    }

    public static void main(String[] args){
        int[][] matrix1={{1,1,1,1,1},
                        {1,1,1,1,1},
                        {1,1,1,1,1},
                        {1,1,1,1,1},
                        {1,1,1,1,1}};
        int[][] matrix2={{0,1,2,3,4},
                         {1,3,0,1,3},
                         {2,0,2,-2,2},
                         {3,1,-2,3,1},
                         {4,3,2,1,0}};
        System.out.println(findNumber(matrix1));
        System.out.println(findNumber(matrix2));
    }
}

package PostedFacebookQuestionsNew;

/**
 * 对一个图像做水平对称，功能函数是每次要把1B的数据对称。比如1001110100100001->1000010010111001
 */
public class NumberMirror {

    public static int mirror(int num){
        int result=0;
        for(int i=0;i<16;i++){
            if((num&1)==1)
                result+=(1<<(15-i));
            num=num>>1;
        }
        return result;
    }

    public static void print(int num){
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<16;i++){
            builder.insert(0, num&1);
            num=num>>1;
        }
        System.out.println(builder.toString());
    }

    public static void main(String[] args){
        int num=40225;
        print(num);
        int number=mirror(num);
        print(number);
    }
}

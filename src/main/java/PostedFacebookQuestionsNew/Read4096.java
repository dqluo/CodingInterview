package PostedFacebookQuestionsNew;

/**
 * Given API:
 *
 * int Read4096(char* buf);
 *
 * It reads data from a file and records the position so that the next time when it is called it
 * read the next 4k chars (or the rest of the file, whichever is smaller) from the file.
 *
 * The return is the number of chars read.
 *
 * Todo:
 *
 * Use above API to Implement API "int Read(char* buf, int n)" which reads any number of chars
 * from the file.
 *
 */

public class Read4096 {

//    public static int read(int n, char[] buf){
//        int total=0;
//        for(int i=0;i<n/4096;i++){
//            char[] internal_buffer=new char[4096];
//            int read=read4096(internal_buffer);
//            total=total+copy(buf, total, internal_buffer, read);
//            if(read<4096)
//                return total;
//        }
//        char[] internal_buffer=new char[4096];
//        int read=read4096(internal_buffer);
//        total=total+copy(buf, total, internal_buffer, read);
//        return total;
//    }
//
//    private static int copy(char[] buf, int total, char[] buffer, int num){
//        for(int i=0;i<num;i++){
//            buf[total+i]=buffer[i];
//        }
//        return num;
//    }
}

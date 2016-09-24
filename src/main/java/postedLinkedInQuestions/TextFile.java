package postedLinkedInQuestions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * 7. Implement a (Java) Iterable object that iterates lines one by one from a text file.
 . 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
 ** A reference to a file.
 * public class TextFile implements Iterable<String>
 * {
 *   public TextFile(String fileName) { // please implement this
 *       // Begin reading the file, line by line. The returned Iterator.next() will return a line.
 *       @Override. from: 1point3acres.com/bbs
 *       public Iterator<String> iterator() { // please implement this
 */
public class TextFile implements Iterable<String>{

    String fileName;

    public TextFile(String fileName) {
        this.fileName=fileName;
    }

    @Override
    public Iterator<String> iterator(){
        return new TextFileIterator(fileName);
    }

    class TextFileIterator implements Iterator<String>{

        BufferedReader reader;
        String nextLine;

        public TextFileIterator(String fileName){
            try {
                reader = new BufferedReader(new FileReader(fileName));
                nextLine = reader.readLine();
            } catch (IOException e){
                throw new IllegalArgumentException(e);
            }
        }

        public boolean hasNext(){
            return nextLine != null;
        }

        public String next(){
            try{
                if(nextLine!=null){
                    nextLine=reader.readLine();
                }
                if(nextLine==null)
                    reader.close();
                return nextLine;
            } catch (IOException e){
                throw new IllegalArgumentException(e);
            }
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        String filename = "src/main/java/postedLinkedInQuestions/TextFile.java";

        TextFile textFile=new TextFile(filename);
        Iterator<String> iterator=textFile.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}

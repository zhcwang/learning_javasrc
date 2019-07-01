package learning.java.source.io.bytestream;

import java.io.*;

public class FileInputStreamTest {

    public static void main(String[] args) throws IOException {
        File file1 = new File("src/learning/java/source/io/test.txt");
        InputStream inputStream = new FileInputStream(file1);
        int count = inputStream.read();
        System.out.println(count);
    }
}

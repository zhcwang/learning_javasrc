package learning.java.source.io.bytestream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PushbackInputStream;

/**
 *  主要可以回退
 */
public class PushbackInputStreamTest {

    public static void main(String[] args) throws Exception{
        File file = new File("src/learning/java/source/io/test.txt");
        InputStream inputStream = new FileInputStream(file);
        PushbackInputStream in = new PushbackInputStream(inputStream);
        in.unread(10);
        in.close();
    }
}

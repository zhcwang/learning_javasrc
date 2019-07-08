package learning.java.source.io.bytestream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FileInputStreamTest {
    /**
     * 可以看到由于编码格式不同带来的乱码问题，字符型文件不适合用stream来读
     * @throws Exception
     */
    public static void readTest() throws Exception{
        File file = new File("src/learning/java/source/io/test.txt");
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[3];
        while (inputStream.read(bytes) != -1){
            System.out.println(new String(bytes));
        }
        inputStream.close();
    }

    public static void main(String[] args) throws Exception {
        readTest();
    }

}

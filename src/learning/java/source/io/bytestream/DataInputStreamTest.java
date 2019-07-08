package learning.java.source.io.bytestream;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * DataInputStream增加了读取基本类型的方法
 * 通过阅读源码，可以清晰的看到基本类型所占用的字节数
 *  -int 4
 *  -char 2
 *  -boolean 1
 *  -double 8
 *  -float 4
 *  -long 8
 *  -short 2
 */
public class DataInputStreamTest {

    public static void main(String[] args) throws Exception {
        File file = new File("src/learning/java/source/io/test.txt");
        InputStream inputStream = new FileInputStream(file);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int available = dataInputStream.available();
        for (int i = 0; i < available; i++) {
            dataInputStream.readInt();
            dataInputStream.readChar();
            dataInputStream.readUTF();
            dataInputStream.readBoolean();
            dataInputStream.readDouble();
            dataInputStream.readFloat();
            dataInputStream.readLong();
            dataInputStream.readShort();
            System.out.println(dataInputStream.readChar());
        }
        dataInputStream.close();
    }
}

package learning.java.source.io.bytestream;

import java.io.*;

public class FileDescriptor {

    /**
     * 最終真正的指向是控制台
     * @throws Exception
     */
    public static void outTest() throws Exception{
        OutputStream out = new FileOutputStream(java.io.FileDescriptor.out);
        out.write("你好啊！".getBytes());
    }


    public static void main(String[] args) throws Exception {
        outTest();
    }
}

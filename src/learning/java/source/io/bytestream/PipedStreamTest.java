package learning.java.source.io.bytestream;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * PipedStream 用于在程序中创建通信管道
 * 一边发送信息、一边轮询接收信息
 */
public class PipedStreamTest {

    public static void main(String[] args) throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream();
        in.connect(out);
        out.write("你好啊".getBytes());
        byte[] buf = new byte[1024];
        int len = in.read(buf);
        System.out.println("receive message from sender : " + new String(buf, 0, len));
        in.close();
        out.close();
    }
}

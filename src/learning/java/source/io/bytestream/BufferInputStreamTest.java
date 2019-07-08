package learning.java.source.io.bytestream;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 大文件读取BufferInputStream和InputStream的性能对比
 * BufferInputStream的缓冲区默认是8192B = 8KB
 * 当每次读8KB时，缓冲：26538 不缓冲： 1390
 * 当每次读2KB时，缓冲：1683 不缓冲：2758
 * 当每次读1KB时，缓冲：1643 不缓冲：4465
 * 当每次读512B时，缓冲：1643 不缓冲：...more
 * 测试发现了一个有趣的现象
 * buffer 每次读取的大小一定要小于 设置的缓冲区大小
 */
public class BufferInputStreamTest {

    private static final String movie = "F:\\电影\\LS·好.Song.of.Youth.2019.HD1080P.X264.AAC.Mandarin.CHS.Mp4Ba.mp4";   //4.04G

    public static void main(String[] args) {
        long startTime1 = System.currentTimeMillis();
        readByBuffer(movie);
        long endTime1 = System.currentTimeMillis();
        System.out.println(endTime1 - startTime1);

        long startTime2 = System.currentTimeMillis();
        readByInput(movie);
        long endTime2 = System.currentTimeMillis();
        System.out.println(endTime2 - startTime2);
    }


    private static void readByBuffer(String movie) {
        BufferedInputStream bufferedInputStream = null;
        try{
            bufferedInputStream = new BufferedInputStream(new FileInputStream(movie));
            byte[] bytes = new byte[128];
            while (bufferedInputStream.read(bytes) != -1){
            }

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (bufferedInputStream != null){
                try{
                    bufferedInputStream.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private static void readByInput(String movie) {
        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(movie);
            byte[] bytes = new byte[819200];
            while (inputStream.read(bytes) != -1){
            }

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (inputStream != null){
                try{
                    inputStream.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}

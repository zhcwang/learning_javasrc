package learning.java.source.io.file;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class FileTest {

    public static void absolutePath(){
        File file = new File("G:\\workspace\\javasrc\\src\\learning\\java\\source\\io\\test.txt");
        System.out.println(file.exists());
    }

    public static void relativePath(){
        File file = new File("src/learning/java/source/io/test.txt");
        System.out.println(file.exists());
    }

    public static void resourcePath(){
        String filepath = FileTest.class.getResource("../test.txt").getFile();
        File file = new File(filepath);
        System.out.println(file.exists());
    }

    /**
     * 获取类的class文件
     */
    public static void classloaderPath(){
        String classPath = FileTest.class.getName().replaceAll("\\.", "/");
        System.out.println(classPath);
        String filepath = Thread.currentThread().getContextClassLoader().getResource("./" + classPath + ".class").getPath();
        System.out.println(filepath);
        File file = new File(filepath);
        System.out.println(file.exists());
    }

    public static void uri() throws URISyntaxException {
        File file = new File("src/learning/java/source/io/test.txt");
        System.out.println(file.toURI());
        URI uri = new URI("file:/G:/workspace/javasrc/src/learning/java/source/io/test.txt");
        File file1 = new File(uri);
        System.out.println(file1.exists());
    }

    /**
     * 如果文件失败则创建失败
     * @throws IOException
     */
    public static void create() throws IOException {
        File file = new File("G:\\workspace\\javasrc\\src\\learning\\java\\source\\io\\file\\test.txt");
        System.out.println(file.exists());
        System.out.println(file.createNewFile());
    }


    /**
     * 只有file是一个路径时，list才好使
     */
    public static void list() {
        File file = new File("G:\\workspace\\javasrc\\src\\learning\\java\\source\\io\\file");
        String[] names = file.list();
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
    }

    public static void space() {
        File file = new File("G:\\workspace\\javasrc\\src\\learning\\java\\source\\io\\test.txt");
        System.out.println("total " + file.getTotalSpace());
        System.out.println("useable " + file.getUsableSpace());
        System.out.println("free " + file.getFreeSpace());
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        absolutePath();
        relativePath();
        resourcePath();
        classloaderPath();
        uri();
        create();
        list();
        space();
    }
}

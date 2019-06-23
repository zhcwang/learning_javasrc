package learning.java.source.collections.map;

import java.io.*;
import java.util.Properties;

public class PropertiesTest {
    public static void testAddProperties(){
        Properties p1 = new Properties();
        p1.setProperty("3", "4");
        p1.setProperty("4", "4");
        // not recommend to do so , this means insert entries whose keys or values are not
        p1.put("5","5");
        // this element can not be reached
        p1.put(1234, "789");
        Properties p = new Properties(p1);
        p.setProperty("1", "1");
        p.setProperty("1", "2");
        System.out.println(p.getProperty("3"));
        System.out.println(p.getProperty("5"));
        System.out.println(p.getProperty(String.valueOf(1234)));
        // the young child can also visit the grandfather's key
        Properties pFinal = new Properties(p);
        System.out.println(pFinal.getProperty("3"));
    }

    public static void testLoadProperties() throws Exception{
        File file = new File("G:\\workspace\\javasrc\\src\\learning\\java\\source\\collections\\map\\test.properties");
        InputStream in = new FileInputStream(file);
        Properties p = new Properties();
        p.load(in);
        System.out.println(p.get("a"));
        System.out.println(p.get("b"));
        System.out.println(p.get("c"));
        System.out.println(p.get("d"));

    }

    public static void testStoreProperties() throws Exception{
        File file = new File("G:\\workspace\\javasrc\\src\\learning\\java\\source\\collections\\map\\test.properties");
        InputStream in = new FileInputStream(file);
        Properties p = new Properties();
        p.load(in);
        File outFile = new File("G:\\workspace\\javasrc\\src\\learning\\java\\source\\collections\\map\\store.properties");
        Writer w = new BufferedWriter(new FileWriter(outFile));
        p.store(w, "测试writer");
        File outFile1 = new File("G:\\workspace\\javasrc\\src\\learning\\java\\source\\collections\\map\\store1.properties");
        OutputStream outputStream = new FileOutputStream(outFile1);
        p.store(outputStream, "测试writer");
    }

    public static void testStoreToXml() throws Exception{
        File file = new File("G:\\workspace\\javasrc\\src\\learning\\java\\source\\collections\\map\\test.properties");
        InputStream in = new FileInputStream(file);
        Properties p = new Properties();
        p.load(in);
        File outFile1 = new File("G:\\workspace\\javasrc\\src\\learning\\java\\source\\collections\\map\\store.xml");
        OutputStream outputStream = new FileOutputStream(outFile1);
        p.storeToXML(outputStream,"testWrite2Xml");
    }

    public static void main(String[] args) throws Exception{
        testAddProperties();
        testLoadProperties();
        testStoreProperties();
        testStoreToXml();
    }
}

package learning.java.source.io.bytestream;

import java.io.*;

/**
 * 本测试用例使用组合和继承两种方式说明Obj in-out stream的用法
 * 1、对象使用哪个构造器构造的，则反序列化时则使用哪个构造器
 * 2、如果使用组合的模式，如果实例对象属性没有实现序列化接口，那么则无法序列化例对象属性
 *      切记：构造函数不能包含不能序列化的变量，否则inputStream直接报错
 * 3、使用继承模式，同样如果父类不实现Serializable，则反序列化时丢失属性
 * 4、
 *
 */
public class ObjectInputStreamTest {

    static public class Sub1 implements Serializable {

        private static final long serialVersionUID = 9527l;

        private String subStr;
        private int subInt;
        private Parent p;


        Sub1(String subStr, int subInt, Parent p) {
            this.subStr = subStr;
            this.subInt = subInt;
            this.p = p;
        }


        Sub1(String subStr, int subInt) {
            this.subStr = subStr;
            this.subInt = subInt;
        }

        String getSubStr() {
            return subStr;
        }

        void setSubStr(String subStr) {
            this.subStr = subStr;
        }

        public int getSubInt() {
            return subInt;
        }

        public void setSubInt(int subInt) {
            this.subInt = subInt;
        }

        Parent getP() {
            return p;
        }

        void setP(Parent p) {
            this.p = p;
        }
    }

    static public class Sub2 extends Parent implements Serializable {

        private static final long serialVersionUID = 95272;

        private String subStr;
        private int subInt;
        private static int staticInt = 10;
        Sub2(String parentStr, String subStr, int subInt) {
            super(parentStr);
            this.subStr = subStr;
            this.subInt = subInt;
        }

        String getSubStr() {
            return subStr;
        }

        public void setSubStr(String subStr) {
            this.subStr = subStr;
        }

        public int getSubInt() {
            return subInt;
        }

        public void setSubInt(int subInt) {
            this.subInt = subInt;
        }

        static int getStaticInt() {
            return staticInt;
        }

        static void setStaticInt(int staticInt) {
            Sub2.staticInt = staticInt;
        }
    }

    /**
     * 父类如果不实现序列化接口，则子类序列化与反序列化后，父类信息丢失
     */
    static public class Parent{

        private static final long serialVersionUID = 95273;

        private String parentStr;

        /**
         * 父类必须有可用的无参构造函数，否则反序列化子类的时候抛出：
         * .InvalidClassException: learning.java.source.io.bytestream.ObjectInputStreamTest$Sub2; no valid constructor
         */
        public Parent() {
        }

        public Parent(String parentStr) {
            this.parentStr = parentStr;
        }

        String getParentStr() {
            return parentStr;
        }

        void setParentStr(String parentStr) {
            this.parentStr = parentStr;
        }
    }


    public static void main(String[] args) throws Exception{
        Parent p = new Parent("pStr");
        Sub1 sub1 = new Sub1("subStr", 100);
        Sub2 sub2 = new Sub2("pStr", "subStr", 101);
        File file = new File("src/learning/java/source/io/obj.txt");
        OutputStream out = new ObjectOutputStream(new FileOutputStream(file));
       ((ObjectOutputStream) out).writeObject(sub2);
        File inFile = new File("src/learning/java/source/io/obj.txt");
        InputStream inputStream = new ObjectInputStream(new FileInputStream(inFile));
        Sub2 sub2Deserialize = (Sub2) ((ObjectInputStream) inputStream).readObject();
        sub2Deserialize.setStaticInt(100);
        System.out.println(sub2Deserialize.getSubStr());
        System.out.println(sub2Deserialize.getParentStr());
        System.out.println(sub2Deserialize.getStaticInt());

        /**
         * 如果父类没有实现序列化接口，则直接抛出
         * NotSerializableException: learning.java.source.io.bytestream.ObjectInputStreamTest$Parent
         */
        ((ObjectOutputStream) out).writeObject(sub1);
        Sub1 sub1Deserialize = (Sub1) ((ObjectInputStream) inputStream).readObject();
        System.out.println(sub1Deserialize.getSubStr());
        System.out.println(sub1Deserialize.getP());
    }
}

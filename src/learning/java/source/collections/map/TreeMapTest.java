package learning.java.source.collections.map;

import java.util.TreeMap;

public class TreeMapTest {

    private static class TestPojo implements Comparable{

        private String name ;
        private int age;

        public TestPojo(String name, int age) {
            this.name = name;
            this.age = age;
        }

        /**
         * 此处说明一个严重的问题，compareTo方法如果只片面的取一个值的话，结果和想想大不同
         * @param o
         * @return
         */
        @Override
        public int compareTo(Object o) {
            if(!(o instanceof TestPojo)){
                throw new ClassCastException();
            }
            return this.age - ((TestPojo)o).age;
        }
    }

    private static void addTest(){
        TreeMap<String, String> map = new TreeMap<>();
        map.put("key1", "value1");
        map.put("key1", "value2");
        map.put(new String("key1"), "value2");
        System.out.println(map.size());
        TreeMap<TestPojo, String> map1 = new TreeMap<>();
        TestPojo pojo1= new TestPojo("zcwang", 27);
        TestPojo pojo2= new TestPojo("zcwang", 27);
        map1.put(pojo1, "1");
        map1.put(pojo2, "2");
        System.out.println(map1.size());
    }

    private static void getCeilingEntry(){
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(1, "value1");
        map.put(2, "value2");
        map.put(5, "value5");
        System.out.println( map.ceilingEntry(8));
        System.out.println( map.ceilingEntry(4));

    }

    private static void getFirstEntry(){
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(2, "value2");
        map.put(1, "value1");
        map.put(5, "value5");
        System.out.println( map.firstEntry());

    }

    public static void main(String[] args) {
        addTest();
        // 获取>=的值
        getCeilingEntry();
        // 获取最小的值， 而不是第一个插入的值
        getFirstEntry();
    }
}

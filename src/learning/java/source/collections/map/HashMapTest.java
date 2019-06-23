package learning.java.source.collections.map;

import java.util.HashMap;

public class HashMapTest {
    /**
     * 在jdk1.7由于头插法和resize导致无限循环，通过打断点调试可以复现
     * 1.8 采用尾插不会出现无限循环的问题
     */
    public static void  infiniteLoopTest(){
        HashMap<Integer,String> map = new HashMap<>(2, 0.75f);
        new Thread("Thread1") {
            public void run() {
                map.put(7, "B");
                System.out.println(map);
            }
        }.start();
        new Thread("Thread2") {
            public void run() {
                map.put(3, "A");
                System.out.println(map);
            }
        }.start();
    }

    public static void computeDemo(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1","1");
        hashMap.put("2","2");
        hashMap.put("3","3");
        hashMap.compute("4", (k, v) -> v == null? "change":"unchange");
        hashMap.compute("2", (k, v) -> {
            if("3".equals(v)){
                return v + "super";
            } else {
                return v;
            }
        });
        System.out.println(hashMap);
    }

    public static void computeIfAbsentDemo(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1","1");
        hashMap.put("2","2");
        hashMap.put("3","3");
        hashMap.computeIfAbsent("4", (k) -> "10");
        hashMap.computeIfAbsent("3", (k) -> "10");
        System.out.println(hashMap);
    }

    public static void computeIfPresentDemo(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1","1");
        hashMap.put("2","2");
        hashMap.put("3","3");
        hashMap.computeIfPresent("4", (k, v) -> "10");
        hashMap.computeIfPresent("3", (k, v) -> "10");
        System.out.println(hashMap);
    }

    public static void mergeDemo(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1","1");
        hashMap.put("2","2");
        hashMap.put("3","3");
        hashMap.merge("3", "1", (oldV, newV) -> oldV + newV);
        hashMap.merge("4", "4", (oldV, newV) -> oldV + newV);
        System.out.println(hashMap);
    }


    public static void main(String[] args) {
        //infiniteLoopTest();
        computeDemo();
        computeIfAbsentDemo();
        computeIfPresentDemo();
        mergeDemo();
    }
}

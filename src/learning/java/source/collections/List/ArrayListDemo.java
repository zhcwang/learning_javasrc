package learning.java.source.collections.List;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class ArrayListDemo {

    public static void addDemo(){
       List<String> s = new ArrayList<>(1);
       s.add("1");
       s.add("2");
       s.add("3");
       System.out.println(s.size());
    }

    public static void arrayCopyDemo(){
        int[] isSource = new int[]{1, 2, 3, 4};
        int[] isTarget = new int[]{5, 6, 7};
        System.arraycopy(isSource,1,isTarget,2,1);
        for (int i = 0; i < isTarget.length; i++) {
            System.out.println(isTarget[i]);
        }

    }

    public static void twoIteratorDemo(){
        List<String> strList = new ArrayList<>();
        Iterator<String> oneWay = strList.iterator();
        ListIterator<String> dualWay = strList.listIterator();
    }

    public static void consumerDemo(){
        List<String> stringList = new ArrayList<>();
        stringList.add("one");
        stringList.add("two");
        stringList.add("three");
        Consumer<String> stringConsumer = s -> {
            s += "_1";
            System.out.println(s);
        };
        stringList.iterator().forEachRemaining(stringConsumer);
        for (int i = 0; i < stringList.size(); i++) {
            // 说明String是值传递
            System.out.println(stringList.get(i));
        }
    }

    public static void forEachDEMO(){
        List<String> stringList = new ArrayList<>();
        stringList.add("one");
        stringList.add("two");
        stringList.add("three");
        Consumer<String> stringConsumer = s -> {
            s += "_1";
            System.out.println(s);
        };
        stringList.forEach(stringConsumer);
    }

    public static void spliteratorDemo(){
        AtomicInteger count = new AtomicInteger(0);
        List<String> strList = createList();
        Spliterator spliterator = strList.spliterator();
        for(int i=0;i<10;i++){
            new MyThread(count,spliterator).start();
        }
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结果为：" + count);
    }

    private static List<String> createList(){
        List<String> result = new ArrayList<>();
        for(int i=0; i<100; i++){
            if(i % 10 == 0){
                result.add(i+"");
            }else{
                result.add("aaa");
            }
        }
        return result;
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    static class MyThread extends Thread{
        private Spliterator spliterator;
        private AtomicInteger count;

        public MyThread(AtomicInteger count, Spliterator spliterator) {
            this.spliterator = spliterator;
            this.count = count;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("线程"+threadName+"开始运行-----");
            spliterator.trySplit().forEachRemaining(o -> {
                if(isInteger((String)o)){
                    int num = Integer.parseInt(o +"");
                    count.getAndAdd(num);
                    System.out.println("数值："+num+"------"+threadName);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("线程"+threadName+"运行结束-----");
        }
    }

    @SuppressWarnings("unchecked")
    public static void containSelfDemo(){
        List<Object> list = new ArrayList<>();
        list.add(list);
        list.add("1");
        List<Object> o = (List<Object>)list.get(0);
        System.out.println(o.get(1));
        System.out.println(list.get(1));
        // 链表套用自己的写法，会在某些方法调用时造成栈溢出
        System.out.println(list.hashCode());
    }

    public static void main(String[] args) {
        //ArrayListDemo.addDemo();
        //ArrayListDemo.forEachDEMO();
        //ArrayListDemo.spliteratorDemo();
        ArrayListDemo.containSelfDemo();
    }
}

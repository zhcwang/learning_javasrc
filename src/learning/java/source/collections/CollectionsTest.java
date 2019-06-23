package learning.java.source.collections;

import java.util.*;

public class CollectionsTest {
    public static void sortTest(){
        List<String> ss = new ArrayList<>();
        ss.add("100");
        ss.add("1");
        ss.add("10");
        ss.add("9");
        ss.add("7");
        ss.add("8");
        Collections.sort(ss);
        List<String> ssl = new LinkedList<>();
        ssl.add("100");
        ssl.add("1");
        ssl.add("10");
        ssl.add("9");
        ssl.add("7");
        ssl.add("8");
        Collections.sort(ssl);
        ss.forEach(System.out::println);
        ssl.forEach(System.out::println);
    }

    /**
     * binarySearch 二分法查找，对于array和link分别直接使用下标/通过listIterator优先next到mid
     * 要求参数有序，否则会返回的结果出乎预料
     */
    public static void searchTest(){
        List<String> ss = new ArrayList<>();
        ss.add("100");
        ss.add("1");
        ss.add("10");
        ss.add("9");
        ss.add("7");
        ss.add("8");
        Collections.sort(ss);
        int i = Collections.binarySearch(ss, "1");
        System.out.println(i);
    }

    /**
     * 反序，核心算法是从两头开始交换一直到中间。对于链表形式，依旧使用listIterator，一个定位在头，一个定位在位
     */
    public static void reverseTest(){
        List<String> ss = new ArrayList<>();
        ss.add("100");
        ss.add("1");
        ss.add("10");
        ss.add("9");
        ss.add("7");
        ss.add("8");
        ss.forEach(System.out::println);
        Collections.reverse(ss);
        ss.forEach(System.out::println);
    }

    /**
     * 随机打乱,利用的特性是random.nextInt [0, index) 从后向前挨个交换， 为什么不从前向后遍历其实就是random.nextInt特性决定的
     * swap的写法太经典了 : l.set(i, l.set(j, l.get(i)));
     */
    public static void shuffleTest(){
        List<String> ss = new ArrayList<>();
        ss.add("100");
        ss.add("1");
        ss.add("10");
        ss.add("9");
        ss.add("7");
        ss.add("8");
        Collections.shuffle(ss);
        ss.forEach(System.out::println);
    }

    public static void fillTest(){
        List<String> ss = new ArrayList<>();
        ss.add("100");
        ss.add("1");
        ss.add("10");
        ss.add("9");
        ss.add("7");
        ss.add("8");
        Collections.fill(ss, "1");
        ss.forEach(System.out::println);
    }

    /**
     * 1、首先可以通过源码发现是浅拷贝
     * 2、不会动态扩容，要求desc长度必须必src大
     */
    public static void copyTest(){
        List<String> src = new ArrayList<>();
        src.add("100");
        src.add("1");
        src.add("10");
        src.add("9");
        src.add("7");
        src.add("8");
        List<String> dest = new LinkedList<>();
        Collections.copy(dest, src);
        dest.forEach(System.out::println);
    }


    public static void minMaxTest(){
        List<String> src = new ArrayList<>();
        src.add("100");
        src.add("1");
        src.add("10");
        src.add("9");
        src.add("7");
        src.add("8");
        String min = Collections.min(src);
        String max = Collections.max(src);
        System.out.println(min);
        System.out.println(max);
    }

    /**
     * 旋转，可以理解为连成环之后移动，如果移动distance > 0,理解为后移动， <0 理解为前移
     */
    public static void rotateTest(){
        List<String> src = new ArrayList<>();
        src.add("100");
        src.add("1");
        src.add("10");
        src.add("9");
        src.add("7");
        src.add("8");
        Collections.rotate(src, 2);
        src.forEach(System.out::println);
    }

    public static void replaceAllTest(){
        List<String> src = new ArrayList<>();
        src.add("100");
        src.add("1");
        src.add("10");
        src.add("9");
        src.add("7");
        src.add("8");
        Collections.replaceAll(src, "1", "2");
        src.forEach(System.out::println);
    }

    /**
     * 该方法之前在一些笔试题中见过，在此说明一下算法
     * 假设有两个数组src,target, 返回在src中是否有target
     * 1、要明白一点，我要循环遍历src，i 计算i到i+target.size个元素是否和target完全相同
     * 2、最多遍历多少次就可以呢？ src.size() - target.size()
     *   nextCand:
     *             for (int candidate = 0; candidate <= maxCandidate; candidate++) {
     *                 for (int i=0, j=candidate; i<targetSize; i++, j++)
     *                     if (!eq(target.get(i), source.get(j)))
     *                         continue nextCand;  // Element mismatch, try next cand
     *                 return candidate;  // All elements of candidate matched target
     *             }
     */
    public static void indexOfSubListTest(){
        List<String> src = new ArrayList<>();
        src.add("100");
        src.add("1");
        src.add("10");
        src.add("9");
        src.add("7");
        src.add("8");
        List<String> target = new LinkedList<>();
        target.add("9");
        target.add("7");
        System.out.println(Collections.indexOfSubList(src, target));
    }

    /**
     * 装饰器模式在实际中的应用，可变的方法全给覆盖了
     * 各种Unmodifiable占据了大约1000行代码，其实干的事就在覆盖可变方法，具体里面还有没有其它设计思想暂时不花费时间研究了
     */
    public static void unmodifyTest(){
        List<String> src = new ArrayList<>();
        src.add("100");
        src.add("1");
        src.add("10");
        src.add("9");
        src.add("7");
        src.add("8");
        Collection<String> un1 = Collections.unmodifiableCollection(src);
        un1.forEach(System.out::println);
    }

    /**
     * synchronized各种容器类其实都是通过一个Object mutex来实现的
     * 调用需要同步的方式时 synchronized(mutex){// dosomething}
     * 该方法实现较为粗暴，并发性能不能保证，同时即使Collection.sync...之后，调用iterator方法时也不能保证并发
     * 因为返回的还是原始集合的iterator，使用时一定要对iterator代码块加锁
     */
    public static void synchronizedTest(){
        long start = System.currentTimeMillis();
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            a.add(i);
        }
        long end = System.currentTimeMillis() - start;
        System.out.println(end);
        List<Integer> aa = new ArrayList<>();
        List<Integer> syncList = Collections.synchronizedList(aa);
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            syncList.add(i);
        }
        end = System.currentTimeMillis() - start;
        System.out.println(end);
    }

    /**
     * 把编译期检查无法确定类型的不安全方法变成安全的，否则以下代码只有在运行期时才会抛出异常
     */
    public static void checkedCollectionTest(){
        List<String> list = Arrays.asList("12","23");
        List obj = list;
        //list中存在了一个非String类型，程序执行到这里会报错
        obj.add(112);
        Collection<String> strings = Collections.checkedCollection(list, String.class);
        //strings.add(112);
    }

    /**
     * Collections提供了各种不可变的final的空集合类实现，主要用于在程序判断==null又不想返回null引起上级代码null指针异常时用
     */
    public static void emptyTest(){
        List<Object> list = Collections.emptyList();
        list.add(1);
    }

    public static void singletonTest(){
        List<String> list = Collections.singletonList("1234");
        System.out.println(list.get(0));
        list.add("123");
    }

    public static void reverseOrderTest(){
        Comparator<Object> reverseOrder = Collections.reverseOrder();
    }

    /**
     * 如果两个集合没有交集则返回true
     */
    public static void disjointTest(){
        List<String> l1 = new ArrayList<>();
        l1.add("1");
        l1.add("2");
        List<String> l2 = new ArrayList<>();
        l2.add("3");
        l2.add("4");
        List<String> l3 = new ArrayList<>();
        l3.add("1");
        l3.add("5");
        System.out.println(Collections.disjoint(l1, l2));
        System.out.println(Collections.disjoint(l1, l3));

    }

    public static void main(String[] args) {
        //sortTest();
        //searchTest();
        //reverseTest();
        //shuffleTest();
        //fillTest();
        //copyTest();
        //minMaxTest();
        //rotateTest();
        //replaceAllTest();
        //indexOfSubListTest();
        //unmodifyTest();
        //synchronizedTest();
        //checkedCollectionTest();
        // emptyTest();
        //singletonTest();
        disjointTest();
    }
}

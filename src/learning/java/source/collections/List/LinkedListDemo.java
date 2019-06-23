package learning.java.source.collections.List;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedListDemo {
    /**
     * 三种迭代器
     */
    public static void iterator(){
        List<String> l = new LinkedList<>();
        Iterator<String> iterator = l.iterator();
        ListIterator<String> listIterator = l.listIterator();
        Iterator<String> descendingIterator = ((LinkedList<String>) l).descendingIterator();
    }

    public static void toArray(){
        List<String> l = new LinkedList<>();
        l.add("1");
        l.add("2");
        l.add("3");
        l.add("4");
        String[] s = new String[]{"1", "2", "3", "4", "5"};
        String[] result = l.toArray(s);
        for (String tmp : result){
            System.out.println(tmp);
        }
    }

    public static void main(String[] args) {
        LinkedListDemo.toArray();
        System.out.println(1 << 10);
    }
}

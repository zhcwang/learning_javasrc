package learning.java.source.collections.set;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest {

    public static Set<String> getSet(){
        Set<String> tree = new TreeSet<>();
        tree.add("bbb");
        tree.add("ddd");
        tree.add("eee");
        tree.add("aaa");
        tree.add("fff");
        tree.add("hhh");
        tree.add("kkk");
        tree.add("ggg");
        return tree;
    };

    public static void ascDemo(TreeSet set){
        System.out.println("----------------asc----------------");
        for (Iterator iterator = set.iterator(); iterator.hasNext() ;){
            System.out.println(iterator.next());
        }
    };

    public static void descDemo(TreeSet set){
        System.out.println("----------------desc----------------");
        for (Iterator iterator = set.descendingIterator(); iterator.hasNext() ;){
            System.out.println(iterator.next());
        }
    };

    public static void descDemo1(TreeSet set){
        System.out.println("----------------desc----------------");
        NavigableSet navigableSet = set.descendingSet();
        for (Iterator iterator = navigableSet.iterator(); iterator.hasNext() ;){
            System.out.println(iterator.next());
        }
    };

    public static void ceilingDemo(TreeSet set){
        System.out.println("----------------ceiling----------------");
        System.out.println(set.ceiling("ccc"));
        System.out.println(set.ceiling("ddd"));
    };

    public static void higherDemo(TreeSet set){
        System.out.println("----------------higher----------------");
        System.out.println(set.higher("ccc"));
        System.out.println(set.higher("ddd"));
    };

    public static void floorDemo(TreeSet set){
        System.out.println("----------------floor----------------");
        System.out.println(set.floor("ccc"));
        System.out.println(set.floor("ddd"));
    };

    public static void lowerDemo(TreeSet set){
        System.out.println("----------------lower----------------");
        System.out.println(set.lower("ccc"));
        System.out.println(set.lower("ddd"));
    };

    public static void firstDemo(TreeSet set){
        System.out.println("----------------first----------------");
        System.out.println(set.first());
    };

    public static void pollfirstDemo(TreeSet set){
        System.out.println("----------------pollfirst----------------");
        System.out.println(set.pollFirst());
        System.out.println(set.first());
    };


    public static void main(String[] args) {
        Set<String> tree = getSet();
        ascDemo((TreeSet)tree);
        descDemo((TreeSet)tree);
        descDemo1((TreeSet)tree);
        ceilingDemo((TreeSet)tree);
        higherDemo((TreeSet)tree);
        floorDemo((TreeSet)tree);
        lowerDemo((TreeSet)tree);
        firstDemo((TreeSet)tree);
        pollfirstDemo((TreeSet)tree);
    }
}

package learning.java.source.collections.set;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;

public class EnumSetTest {

    public static void noneOfDemo(){
        EnumSet<Fruits> fruits = EnumSet.noneOf(Fruits.class);
        System.out.println(fruits);
    }

    public static void  allOfDemo(){
        EnumSet<Vegetables> vegetables = EnumSet.allOf(Vegetables.class);
        System.out.println(vegetables);
    }

    public static void addDifferentDemo(){
        EnumSet<Vegetables> vegetables = EnumSet.allOf(Vegetables.class);
        Collection c = new HashSet();
        c.clear();
        c.add(Fruits.APPLE);
        vegetables.addAll(c);
        System.out.println(vegetables);
    }

    public static void main(String[] args) {
        noneOfDemo();
        allOfDemo();
        addDifferentDemo();
    }
}

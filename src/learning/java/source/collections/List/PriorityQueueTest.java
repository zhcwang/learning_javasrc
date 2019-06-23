package learning.java.source.collections.List;

import java.util.PriorityQueue;
import java.util.Random;

public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int anInt = random.nextInt(100);
            priorityQueue.add(anInt);
        }
        // 迭代器返回的是无序的
       /* Iterator<Integer> iterator = priorityQueue.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }*/
        Integer poll;
        while ( (poll = priorityQueue.poll()) != null){
            System.out.println(poll);
        }
    }
}

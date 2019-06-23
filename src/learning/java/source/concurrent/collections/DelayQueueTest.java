package learning.java.source.concurrent.collections;

import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

    static class DelayEvent implements Delayed{
        private long executeTime;

        public DelayEvent(long executeTime) {
            super();
            this.executeTime = executeTime;
        }

        @Override
        public int compareTo(Delayed o) {
            long result = this.getDelay(null)
                    - o.getDelay(null);
            if (result < 0) {
                return -1;
            } else if (result > 0) {
                return 1;
            } else {
                return 0;
            }
        }
        @Override
        public long getDelay(TimeUnit unit) {
            return this.executeTime - System.currentTimeMillis();
        }
    }

    static public class DelayTask implements Runnable {
        private int id;
        private DelayQueue<DelayEvent> queue;
        public DelayTask(int id, DelayQueue<DelayEvent> queue) {
            super();
            this.id = id;
            this.queue = queue;
        }
        @Override
        public void run() {
            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                // 当前时间加0-5秒执行
                DelayEvent delayEvent = new DelayEvent(System.currentTimeMillis() + random.nextInt(5000));
                queue.add(delayEvent);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        DelayQueue<DelayEvent> queue = new DelayQueue<>();
        Thread threads[] = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            DelayTask task = new DelayTask(i + 1, queue);
            threads[i] = new Thread(task);
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*int size = queue.size();
        System.out.println(size);*/
        Iterator<DelayEvent> iterator = queue.iterator();
        while (iterator.hasNext()) {
            DelayEvent delayEvent = iterator.next();
            if (delayEvent.getDelay(null) < 3000){
                iterator.remove();
            }
            //System.out.println(delayEvent.getDelay(null));
        }

        int total = 0;
        do {
            int counter = 0;
            DelayEvent delayEvent;
            do {
                delayEvent = queue.poll();
                if (delayEvent != null) {
                    counter++;
                    total++;
                }
            } while (delayEvent != null);
            System.out.println("At " + new Date() + " you have read " + counter+ " event" + " total " + total);
            TimeUnit.MILLISECONDS.sleep(500);
        } while (queue.size() > 0);
    }

}

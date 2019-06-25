package learning.java.source.concurrent.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量用来严格控制同一资源的并发访问量，依赖于AQS实现公平和非公平资源访问权限
 * 公平和非公平的区别在于当调用tryAcquireShared（）方法时，公平锁检测当前线程是否有前序等待节点，如果直接放弃争夺。非公平锁则看谁先CAS操作成功。
 * acquire(int)表示要获取多少个执行权限和release(int)表示要获取多少个访问权限
 * 一般acquire(1)和release(1)数目是相同的
 * 如果new Semaphore（1）则语义上表示单线程
 * 如果new Semaphore（n）, 线程acquire( > n) 则线程永久阻塞，如果acquire( =n )那么表示想独占资源
 */
public class SemaphoreTest {

    static class SemaphoreThread implements Runnable{
        private Semaphore semaphore;
        private int i;

        public SemaphoreThread(Semaphore semaphore, int i) {
            this.semaphore = semaphore;
            this.i = i;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire(1);
                System.out.println(System.currentTimeMillis() + " 线程 " + i + " 获取访问权限，开始执行");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(1);
            }
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2, true);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            SemaphoreThread thread = new SemaphoreThread(semaphore, i);
            executorService.execute(thread);
        }

    }
}

package learning.java.source.concurrent.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier 表示所有线程在同时开始执行
 * 主要用于分布计算，并且可以传入一个runnable的钩子，当栏杆打开后回调
 * 实现原理：使用count表示当前已经参与等待的线程，使用可重入锁下的condition表示人都凑齐了的状态
 * 每次调用CyclicBarrier#await()方法时，--count，并且如果count==0 则所有线程调用Condition.signalAll()方法
 * 否则自旋调用Conditon.await()方法
 */
public class CyclicBarrierTest {
    static class WaitForTheLast implements Runnable{

        private CyclicBarrier barrier;

        public WaitForTheLast(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                // 随机休眠0-5秒
                barrier.await();
                System.out.println("开始执行。" + System.currentTimeMillis());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //CyclicBarrier barrier = new CyclicBarrier(9);
        CyclicBarrier barrier = new CyclicBarrier(10, () -> {
            System.out.println("所有计算开始，开始计算结果");
        });
        long start = System.currentTimeMillis();
        Executor e = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            e.execute(new CyclicBarrierTest.WaitForTheLast(barrier));
        }
        long duration = System.currentTimeMillis() - start;
        // 可以看到duration一定比所有执行线程的最长执行时间略大
        System.out.println(duration);
    }
}

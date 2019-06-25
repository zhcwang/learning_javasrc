package learning.java.source.concurrent.util;

import java.util.Random;
import java.util.concurrent.*;

/**
 * CountDownLatch主要是用来并行计算，当所有线程都计算完后，主线程再进行其他操作
 * 以下测试模仿工作中compare并发从源和目标读数据的相关功能代码
 * 在CountDownLatch的源码示例中，还可以通过两个latch让任务同时开始，同时结束
 * CountDownLatch是AQS的一个基本应用，原理如下：
 * 初始一个状态 = CountDownLatch定义的线程数
 * countdown()方法就是CAS state - 1 操作
 * await()方法就是自旋阻塞等待state = 0
 */
public class CountDownLatchTest {

    static class WaitForTheLast implements Runnable{

        private CountDownLatch latch;

        public WaitForTheLast(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                // 随机休眠0-5秒
                int time = new Random().nextInt(5000);
                System.out.println("休眠 " + time + "秒");
                Thread.sleep(time);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        long start = System.currentTimeMillis();
        Executor e = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            e.execute(new WaitForTheLast(latch));
        }
        latch.await();
        long duration = System.currentTimeMillis() - start;
        // 可以看到duration一定比所有执行线程的最长执行时间略大
        System.out.println(duration);
    }

}

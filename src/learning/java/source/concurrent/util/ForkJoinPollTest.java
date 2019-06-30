package learning.java.source.concurrent.util;

import java.util.concurrent.*;

/**
 * ForkJoin线程池主要用来把大任务拆分成小任务并行计算
 * 分为两种用法：
 *  1、有返回值 {@link RecursiveTask}
 *  2、无返回值 {@link RecursiveAction}
 *
 * 主要思想就是使用分治法来解决问题，比如常见的归并，快速排序等
 */
public class ForkJoinPollTest {
    /**
     * 无返回值的任务
     */
    static class PrintTask extends RecursiveAction {
        private static final int THRESHOLD = 50; //最多只能打印50个数
        private int start;
        private int end;

        public PrintTask(int start, int end) {
            super();
            this.start = start;
            this.end = end;
        }


        @Override
        protected void compute() {

            if (end - start < THRESHOLD) {
                for (int i = start; i < end; i++) {
                    System.out.println(Thread.currentThread().getName() + "的i值：" + i);
                }
            } else {
                int middle = (start + end) / 2;
                PrintTask left = new PrintTask(start, middle);
                PrintTask right = new PrintTask(middle, end);
                //并行执行两个“小任务”
                left.fork();
                right.fork();
            }

        }

    }

    /**
     * 有返回值的任务
     */
    static class SumTask extends RecursiveTask<Integer> {
        private static final int THRESHOLD = 20; //每个小任务 最多只累加20个数
        private int arry[];
        private int start;
        private int end;

        /**
         * Creates a new instance of SumTask.
         * 累加从start到end的arry数组
         *
         * @param arry
         * @param start
         * @param end
         */
        public SumTask(int[] arry, int start, int end) {
            super();
            this.arry = arry;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            //当end与start之间的差小于threshold时，开始进行实际的累加
            if (end - start < THRESHOLD) {
                for (int i = start; i < end; i++) {
                    sum += arry[i];
                }
                return sum;
            } else {//当end与start之间的差大于threshold，即要累加的数超过20个时候，将大任务分解成小任务
                int middle = (start + end) / 2;
                SumTask left = new SumTask(arry, start, middle);
                SumTask right = new SumTask(arry, middle, end);
                //并行执行两个 小任务
                left.fork();
                right.fork();
                //把两个小任务累加的结果合并起来
                return left.join() + right.join();
            }

        }

    }

    public static void main(String[] args) throws Exception {
        PrintTask task = new PrintTask(0, 300);
        //创建实例，并执行分割任务
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(task);
        //线程阻塞，等待所有任务完成
        pool.awaitTermination(2, TimeUnit.SECONDS);
        pool.shutdown();

        ForkJoinPool pool1 = ForkJoinPool.commonPool();
        int[] ints = new int[100];
        for (int i = 0; i < 100; i++) {
            ints[i] = i;
        }
        ForkJoinTask<Integer> futureResult = pool1.submit(new SumTask(ints, 0, ints.length));
        System.out.println(futureResult.get());
        pool.shutdown();
    }
}

package learning.java.source.concurrent.locks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试Condition
 * {@link Condition}
 * 主要方法：await 、 signal
 *  condition有几个特点：
 *  1、必须搭配lock来使用，否则抛出IllegalMonitorStateException异常
 *    所有被lock的代码段共享一把锁,理解为synchronized代码块,比如一个Map读、写就是两个原子过程，不能执行一半就进行线程调度
 *  2、一个锁可以有多个condition
 *  3、condition 阻塞方式有多样，可中断阻塞，不可中断阻塞，限时阻塞，不限时阻塞
 *      {@link Condition#await(long, TimeUnit)}
 *      {@link Condition#await()}
 *      {@link Condition#awaitUninterruptibly()}
 *      {@link Condition#awaitNanos(long)}
 *      {@link Condition#awaitUntil(Date)}
 *  4、通知方法没什么特别
 *      {@link Condition#signal()}
 *      {@link Condition#signalAll()}
 *  5、常用的场景:有界阻塞队列
 */
public class ConditionTest {

    private static final ReentrantLock lock = new ReentrantLock();

    /**
     * 表示一个条件
     */
    private static final Condition notReach10 = lock.newCondition();

    private static final List<Integer> list  = new ArrayList<>();;

    private static final Thread setThread = new Thread(() -> {
        try {
            lock.lock();
            while (list.size() < 10){
                System.out.println("set:" + list.size());
                list.add(list.size());
                Thread.sleep(500);
            }
            //notReach10.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
           lock.unlock();
        }
    });

    private static final Thread getThread = new Thread(() -> {
        try {
            lock.lock();
            while(list.size() < 10){
                notReach10.await(10, TimeUnit.SECONDS);;
            }
            for (int i = 0; i < list.size(); i++) {
                System.out.println("get:" + list.get(i));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    });

    private static void waitTest(){
        getThread.start();;
        setThread.start();
    }

    /**
     * 锁的顺序必须是先锁再释放，再锁
     * 不按正确顺序lock、unlock 会抛出java.lang.IllegalMonitorStateException
     */
    private static void stateTest(){
        // first lock
        lock.lock();
        // second unlock
        lock.unlock();
        // third unlock
        lock.unlock();
    }

    private static void awaitTest(){
        try {
            lock.lock();
            notReach10.await();
            notReach10.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        waitTest();
        //stateTest();
        //awaitTest();
    }


}

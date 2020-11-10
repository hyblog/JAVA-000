package Week_04.demo.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ipipman on 2020/11/10.
 *
 * @version V1.0
 * @Package Week_04.demo.locks
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/10 10:02 下午
 */
public class LockInterruptiblyDemo {


    public void test3() throws Exception {
        final Lock lock = new ReentrantLock();
        lock.lock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " interrupted.");
                }
            }
        }, "child thread -1");

        t1.start();
        Thread.sleep(1000);

        t1.interrupt();

        Thread.sleep(1000000);
    }

    public static void main(String[] args) throws Exception {
        new LockInterruptiblyDemo().test3();
    }

}



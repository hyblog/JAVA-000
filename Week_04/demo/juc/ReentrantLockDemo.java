package Week_04.demo.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ipipman on 2020/11/9.
 *
 * @version V1.0
 * @Package Week_04.demo.juc
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/9 4:24 下午
 */
public class ReentrantLockDemo {

    static int num = 0;

    public static Thread reentrantAdd() throws InterruptedException{
       Thread t1 = new Thread(() -> num++);
       t1.start();
       return t1;
    }

    public static void concurrentAdd(ReentrantLock lock) {
        try {
            lock.lock();
            num++;

            //可重入性
            if (num == 99) {
                reentrantAdd().join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //可重入锁，公平锁
        ReentrantLock lock = new ReentrantLock(true);
        for (int i = 0; i < 100; i++) {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            concurrentAdd(lock);
                        }
                    }
            ).start();
        }
        try {
            lock.lock();
            System.out.println(num); //99
        } finally {
            lock.unlock();
        }
    }
}

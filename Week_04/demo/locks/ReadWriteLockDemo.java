package Week_04.demo.locks;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by ipipman on 2020/11/9.
 *
 * @version V1.0
 * @Package Week_04.demo.juc
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/9 4:45 下午
 */
public class ReadWriteLockDemo {

    static int num = 0;

    public static void main(String[] args) {

        //可冲入读写锁，公平锁
        ReadWriteLock lock = new ReentrantReadWriteLock(true);
        for (int i = 0; i < 100; i++) {
            //读锁，共享锁，保证可见性
            new Thread(() -> {
                readLock(lock);
            }).start();


            //写锁，独占锁，被读锁排斥
            new Thread(() -> {
                try {
                    writeLock(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();


            //读锁，共享锁，保证可见性
            new Thread(() -> {
                readLock(lock);
            }).start();

        }
    }

    //写锁
    public static void writeLock(ReadWriteLock lock) throws InterruptedException {
        try {
            lock.writeLock().lock();
            num++;
            Thread.sleep(200);
            System.out.println("我写锁了->" + num);
        } finally {
            System.out.println("我写释放锁了->" + num);
            lock.writeLock().unlock();
        }
    }

    //读锁
    public static void readLock(ReadWriteLock lock) {
        try {
            System.out.println("我读锁了->" + num);
            lock.readLock().lock();
        } finally {
            System.out.println("我读释放锁了->" + num);
            lock.readLock().unlock();
        }
    }
}

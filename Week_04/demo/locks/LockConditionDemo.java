package Week_04.demo.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ipipman on 2020/11/9.
 *
 * @version V1.0
 * @Package Week_04.demo.juc
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/9 5:04 下午
 */
public class LockConditionDemo {


    static int num = 0;

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock = new ReentrantLock(true);
        //生产者锁
        Condition producerLock = lock.newCondition();
        //消费者锁
        Condition consumerLock = lock.newCondition();


        for (int i = 0; i < 100; i++) {
            //生产
            new Thread(() -> {
                producer(lock, producerLock, consumerLock);
            }).start();




            //消费
            new Thread(() -> {
                consumer(lock, producerLock, consumerLock);
            }).start();

//            new Thread(()->{
//                consumer(lock, producerLock, consumerLock);
//            }).start();
        }


    }

    //生产者
    public static void producer(ReentrantLock lock, Condition producerLock, Condition consumerLock) {
        lock.lock();
        try {
            num++;
            System.out.println(num);
            if (num >= 10) {
                System.out.println("生产" + num);
                producerLock.await();
                consumerLock.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //消费者
    public static void consumer(ReentrantLock lock, Condition producerLock, Condition consumerLock) {
        lock.lock();
        try {
            num--;
            if (num <= 0) {
                System.out.println("消费" + num);
                consumerLock.await();
                producerLock.signalAll();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

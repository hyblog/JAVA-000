package Week_04.demo.semaphore;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ipipman on 2020/11/9.
 *
 * @version V1.0
 * @Package Week_04.demo.semaphore
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/9 8:06 下午
 */
public class CountDownLatchDemo {

    static CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String[] args) {

        while (true){
            System.out.println(countDownLatch.getCount());
            for (int i = 0; i < 5; i++) {
                new Thread(() -> {
                    printThreadName();
                }).start();
            }
            //等待线程执行完毕
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(countDownLatch.getCount());
            countDownLatch = new CountDownLatch(5);
        }
    }

    public static void printThreadName() {
        try {
            System.out.println(Thread.currentThread().getName());
        } finally {
            //当前可执行线程数-1
            countDownLatch.countDown();
        }
    }
}

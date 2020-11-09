package Week_04.demo.semaphore;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by ipipman on 2020/11/9.
 *
 * @version V1.0
 * @Package Week_04.demo.semaphore
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/9 8:22 下午
 */
public class CyclicBarrierDemo {

    //设置并发数，设置回调
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new CustomRunnable());

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                try {
                    printThreadName();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void printThreadName() throws InterruptedException, BrokenBarrierException {
        try {
            System.out.println(Thread.currentThread().getName());
        } finally {
            cyclicBarrier.await();
        }
    }

    static class CustomRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("我跑完了一组线程-" + Thread.currentThread().getName());
        }
    }
}

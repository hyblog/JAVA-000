package Week_04.demo.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by ipipman on 2020/11/9.
 *
 * @version V1.0
 * @Package Week_04.demo.semaphore
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/9 7:52 下午
 */
public class SemaphoreDemo {

    private static int sum = 0;

    //信号量，控制并发数
    private static Semaphore readSemaphore = new Semaphore(100, true);
    private static Semaphore writeSemaphore = new Semaphore(1);

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            //写
            new Thread(() -> {
                System.out.println(incrAndGet());
            }).start();

            //读
            new Thread(()-> {
                // System.out.println(getSum());
            }).start();
        }
    }

    public static int incrAndGet() {
        try {
            writeSemaphore.acquireUninterruptibly();
            return ++sum;
        } finally {
            writeSemaphore.release();
        }
    }

    public static int getSum() {
        try {
            readSemaphore.acquireUninterruptibly();
            return sum;
        } finally {
            readSemaphore.release();
        }
    }


}

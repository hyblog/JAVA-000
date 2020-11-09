package Week_04.demo.juc;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by ipipman on 2020/11/9.
 *
 * @version V1.0
 * @Package Week_04.demo.juc
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/9 5:43 下午
 */
public class LockSupportDemo {

    static int num = 0;

    static Thread t = null;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //暂停线程
            new Thread(() -> {
                add();
                if (num == 20) {
                    t = Thread.currentThread();
                    stop();
                }
            }).start();

            //恢复线程
            new Thread(() -> {
                if (num == 20) {
                    num = 100;
                    resumer(t);
                }
            }).start();
        }
        System.out.println(num);
    }


    public static void add() {
        num++;
    }

    //暂停
    public static void stop() {
        System.out.println("我被暂停了-num-" + num);
        LockSupport.park();

    }

    //恢复
    public static void resumer(Thread t) {
        System.out.println("我被恢复了-num-" + num);
        LockSupport.unpark(t);
    }


}

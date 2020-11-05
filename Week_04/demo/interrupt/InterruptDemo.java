package Week_04.demo.interrupt;

/**
 * Created by ipipman on 2020/11/5.
 *
 * @version V1.0
 * @Package Week_04.practive.interrupt
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/5 6:16 下午
 */
public class InterruptDemo {

    public static void main(String[] args){
        Runnable1 runnable1 = new Runnable1();
        Thread thread1 = new Thread(runnable1);

        Runnable2 runnable2 = new Runnable2();
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();

        thread2.interrupt();

        System.out.println(Thread.activeCount());
    }
}

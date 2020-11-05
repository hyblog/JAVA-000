package Week_04.demo.interrupt;

/**
 * Created by ipipman on 2020/11/5.
 *
 * @version V1.0
 * @Package Week_04.practive.interrupt
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/5 6:13 下午
 */
public class Runnable2 implements Runnable {


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("进入Runnable2运行状态....." + i);
        }

        boolean result = Thread.currentThread().isInterrupted();
        boolean result1 = Thread.interrupted();
        boolean result3 = Thread.currentThread().isInterrupted();

        System.out.println("Runnable2.run result ==>" + result);
        System.out.println("Runnable2.run result ==>" + result1);
        System.out.println("Runnable2.run result ==>" + result3);
    }
}

package Week_04.demo.join;

/**
 * Created by ipipman on 2020/11/5.
 *
 * @version V1.0
 * @Package Week_04.practive.join
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/5 7:38 下午
 */
public class ThreadJoinDemo {


    public static void main(String[] args) {
        Object oo = new Object();

        MyThread myThread = new MyThread("A");
        myThread.setOo(oo);
        myThread.start();

        synchronized (myThread) {
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    try {
                        myThread.join(); //会释放myThread锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " --> " + i);
            }
        }
    }


}


class MyThread extends Thread {

    private String name;

    private Object oo;

    public MyThread(String name) {
        this.name = name;
    }

    public void setOo(Object oo) {
        this.oo = oo;
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println(name + " --> " + i);
            }
        }
    }
}
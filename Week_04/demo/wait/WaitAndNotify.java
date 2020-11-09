package Week_04.demo.wait;


/**
 * Created by ipipman on 2020/11/5.
 *
 * @version V1.0
 * @Package Week_04.practive.wait
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/5 7:47 下午
 */
public class WaitAndNotify {

    public static void main(String[] args){

        MyThread myThread = new MyThread();

        //生产线程
        Thread t1 = new Thread(()->{
            try{
                myThread.product();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }, "product");

        //消费线程1
        Thread t2 = new Thread(()->{
            try{
                myThread.customer();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }, "customer1");

        //消费线程2
        Thread t3 = new Thread(()->{
            try{
                myThread.customer();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }, "customer2");

        t1.start();
        t2.start();
        t3.start();
    }

}


class MyThread {

    private final int MAX_COUNT = 10;

    private volatile Integer productIndex = 0;

    public synchronized void product() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + "  --> " + productIndex);
            Thread.sleep(10);
            if (productIndex >= MAX_COUNT) {
                System.out.println("暂停生产");
                wait();
            } else {
                productIndex++;
            }

            notifyAll();
        }
    }

    public synchronized void customer() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + "  --> " + productIndex);
            Thread.sleep(10);
            if (productIndex >= 0) {
                productIndex--;
            } else {
                System.out.println("暂停消费");
                wait();
            }
            notifyAll();
        }
    }

}
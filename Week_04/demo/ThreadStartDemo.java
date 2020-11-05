package Week_04.demo;

/**
 * Created by ipipman on 2020/11/5.
 *
 * @version V1.0
 * @Package Week_04.practive
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/5 4:36 下午
 */
public class ThreadStartDemo {

    public static void main(String[] args) throws InterruptedException{

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                Thread t = Thread.currentThread();
                System.out.println("当前守护线程：" + t.getName());
            }
        };

        Thread thread = new Thread(task);
        thread.setName("test-thead-1");
        thread.setDaemon(true);
        thread.start();



        Thread.sleep(6000);

    }
}

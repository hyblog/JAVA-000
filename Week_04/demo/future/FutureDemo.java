package Week_04.demo.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by ipipman on 2020/11/9.
 *
 * @version V1.0
 * @Package Week_04.demo.future
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/9 8:41 下午
 */
public class FutureDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Integer> result = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt();
            }
        });
        executorService.shutdown();
        try {
            System.out.println(result.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

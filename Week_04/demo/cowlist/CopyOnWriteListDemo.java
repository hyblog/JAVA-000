package Week_04.demo.cowlist;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by ipipman on 2020/11/10.
 *
 * @version V1.0
 * @Package Week_04.demo.cowlist
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/10 8:07 下午
 */
public class CopyOnWriteListDemo {

    static CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();


    static ExecutorService executorService = Executors.newFixedThreadPool(50);

    public static void main(String[] args) {
        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            //写
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(1);
                }
            });
            //读
            Future<Integer> task = executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return list.get(list.size() - 1);
                }
            });
            futureList.add(task);
        }

        futureList.forEach(task -> {
            try {
                System.out.println(task.get());

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("执行完毕 " + list.size());
    }
}

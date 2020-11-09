package Week_04.demo.pool;

import com.sun.jmx.snmp.tasks.ThreadService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ipipman on 2020/11/9.
 *
 * @version V1.0
 * @Package Week_04.demo.pool
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/9 3:04 下午
 */
public class CreateCacheThreadPool {

    static AtomicInteger threadCount = new AtomicInteger(0);

    public static void main(String[] args) {
        //创建Cache线程池
        ExecutorService executorService = initCacheThreadPool();
        //提交任务
        List<Future<Object>> futureList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            try {
                Future<Object> taskItem = executorService.submit(new Callable<Object>() {
                    @Override
                    public Object call() {
                        return Thread.currentThread().getName();
                    }
                });
                futureList.add(taskItem);

            } catch (RejectedExecutionException e) {
                e.printStackTrace();
            }
        }
        try {
            //获取任务结果
            for (Future<Object> taskResult : futureList) {
                System.out.println(taskResult.get().toString());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    //自定义线程工厂类
    static class CustomThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("custom-thread-name-" + threadCount.incrementAndGet());
            return thread;
        }
    }

    //创建缓存线程池
    public static ExecutorService initCacheThreadPool() {
        CustomThreadFactory customThreadFactory = new CustomThreadFactory();
        ExecutorService executorService = Executors.newCachedThreadPool(customThreadFactory);
        return executorService;
    }
}

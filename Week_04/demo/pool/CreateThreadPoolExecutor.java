package Week_04.demo.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by ipipman on 2020/11/9.
 *
 * @version V1.0
 * @Package Week_04.demo.pool
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/9 1:05 下午
 */
public class CreateThreadPoolExecutor {

    static LongAdder taskIndex = new LongAdder();

    public static void main(String[] args) {
        ThreadPoolExecutor executor = initThreadPoolExecutor();
        try {
            List<Future<?>> futureList = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                try {
                    //创建task拿到返回结果
                    Future<?> future = executor.submit(new TaskCallable());
                    futureList.add(future);
                } catch (RejectedExecutionException e) {
                    // e.printStackTrace();
                    System.out.println("线程池的缓冲队列满了...");
                }
            }
            for (Future<?> future : futureList) {
                //阻塞等待，拿到所有线程执行结果
                System.out.println("task-result-" + future.get());
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            executor.shutdown();
        }
    }

    //自定义任务
    static class TaskCallable implements Callable<Object> {
        @Override
        public Object call() throws Exception {
            //查看被ThreadFactory工厂类初始化后的名称
            System.out.println(Thread.currentThread().getName());
            taskIndex.increment();
            return taskIndex.intValue();
        }
    }

    //创建线程池
    public static ThreadPoolExecutor initThreadPoolExecutor() {
        //线程池初始化线程数量=CPU核心数
        int coreSize = 1; //Runtime.getRuntime().availableProcessors();
        //线程池最大线程数量=CPU核心数*2
        int maxSize = 1;// Runtime.getRuntime().availableProcessors() * 2;
        //设置缓冲队列，大小=500
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingDeque<>(500);
        //设置单个初始化线程工厂类
        CustomTheadFactory childTheadFactory = new CustomTheadFactory();
        //设置拒绝策略
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                coreSize,
                maxSize,
                1,
                TimeUnit.MINUTES,
                taskQueue,
                childTheadFactory,
                rejectedExecutionHandler
        );
        return executor;
    }

    //单线程工程类
    static class CustomTheadFactory implements ThreadFactory {
        private AtomicInteger serial = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("CustomThreadFactory--" + serial.incrementAndGet());
            return thread;
        }
    }
}

# 学习笔记

#### 1、线程基础

------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_04/note/%E5%A4%9A%E7%BA%BF%E7%A8%8B%E5%9F%BA%E7%A1%80.png)

------------

#### 2、线程安全

------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_04/note/%E7%BA%BF%E7%A8%8B%E5%AE%89%E5%85%A8.png)

------------

#### 3、线程池

------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_04/note/%E7%BA%BF%E7%A8%8B%E6%B1%A0.png)

------------

#### 4、信号量

------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_04/note/%E4%BF%A1%E5%8F%B7%E9%87%8F%EF%BC%88%E5%B9%B6%E5%8F%91%E6%8E%A7%E5%88%B6%EF%BC%89.png)

------------


#### 5、第七节作业，选题，作业一（手写示例代码）
[https://github.com/hyblog/JAVA-000/tree/main/Week_04/demo](https://github.com/hyblog/JAVA-000/tree/main/Week_04/demo)

------------

#### 6、第七节作业，必做，作业二（在main方法调用新线程，拿到响应后，退出主线程）
############## TheadPoolExecutor + Future 方式
```java
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
```

####### ScheduledThreadPool + Future 方式
```java
package Week_04.demo.pool;

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
 * @date 2020/11/9 2:40 下午
 */
public class CreateScheduledThreadPool {

    static AtomicInteger theadCount = new AtomicInteger(0);

    private static final Integer MAX_POOL = 100;

    public static void main(String[] args) {
        //创建定时线程池
        ScheduledExecutorService executorService = initScheduledThreadPoll();
        try {
            //创建定时任务
            List<ScheduledFuture<String>> scheduleTaskList = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                try {
                    //提交调度任务，3s执行一次
                    ScheduledFuture<String> scheduleTask = executorService.schedule(
                            new Callable<String>() {
                                @Override
                                public String call() {
                                    return Thread.currentThread().getName();
                                }
                            },
                            randomDelay(), //3s
                            TimeUnit.SECONDS
                    );
                    scheduleTaskList.add(scheduleTask);

                } catch (RejectedExecutionException e) {
                    //拒绝策略
                    e.printStackTrace();
                }
            }
            //返回执行结果
            for (ScheduledFuture<String> taskItem : scheduleTaskList) {
                System.out.println(taskItem.get());
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    static int randomDelay(){
        return (int) (Math.random() * 10);
    }

    //自定义线程工常
    static class CustomThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("custom-thead-name-" + theadCount.incrementAndGet());
            return thread;
        }
    }

    //创建定时线程池
    public static ScheduledExecutorService initScheduledThreadPoll() {
        //自定义工厂
        CustomThreadFactory threadFactory = new CustomThreadFactory();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(MAX_POOL, threadFactory);
        return executorService;
    }
}
```

####### CacheThreadPool + Future 方式
```java
package Week_04.demo.pool;


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
```
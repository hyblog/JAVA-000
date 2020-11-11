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

#### 5、并发集合工具类

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_04/note/%E5%B9%B6%E5%8F%91%E9%9B%86%E5%90%88%E7%B1%BB.png)

------------


#### 6、第七节作业，选题，作业一（手写示例代码）
[https://github.com/hyblog/JAVA-000/tree/main/Week_04/demo](https://github.com/hyblog/JAVA-000/tree/main/Week_04/demo)

------------

#### 7、第七节作业，必做，作业二（在main方法调用新线程，拿到响应后，退出主线程）
#####  TheadPoolExecutor + Future 方式
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

##### ScheduledThreadPool + Future 方式
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

##### CacheThreadPool + Future 方式
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

------------

#### 8、第八节作业，选做，作业一（列举常用的并发操作API和工具类，简单分析其使用场景和优缺点）
#####  ReentrantLock
- Sychronized的增强版，可以完全替换Synchroized关键字；
- JDK1.5以前使用Reentrant性能会更好，JDK1.5后JDK对锁做了一些优化（偏向锁->轻量级锁->重量级锁），目前两者性能差不多；
- 针对简单的场景建议使用synchronized关键字锁；
- 场景一：任意线程在获取到锁之后能够再次获取锁，使用ReentrantLock支持可重入锁场景；
- 场景二：可以添加lock.lockInterruptibly()中断锁，方便于检测到中断线程【thead.interrput()】，然后捕获通知释放锁资源后退出线程，避免线程因为卡死或中断带来的死锁问题；
- 场景三：在获取锁时可，可以通过lock.tryLock(n, TimeUnit.XXX)获取锁，如果时间到达仍未获取到锁，就可以执行其他操作，避免了死锁等待；
- 场景四：可以设置公平锁，默认new ReentrantLock()是不公平的，如果任务需要先来先得就可以使用公平锁；

#####  ReadWriteLock
- 读-读线程之间不阻塞，读-写互斥（读阻塞写，写阻塞读，可以保证原子性操作），写-写线程之前会阻塞；
- 读写锁的自定义AQS同步器，在同步状态上维护多个读线程和一个写线程；
- 场景一：在读多写少的场景下，大部分线程只是读操作，这种操作不会影响临界区的值，如果直接加Synchroized锁住整个对象就非常浪费性能了；

#####  Condition
- 类似于Object.wait()和Object.notify()。在Condition中，使用wait()阻塞当前线程，同时释放当前线程的锁，使用signalAll()唤醒等待线程。
- Condition有一个等待队列（FIFO），队列中的每一个节点包含了一个线程的引用，新等待的线程更新到节点的尾部。调用signal()方法将会唤醒等待时间最长的节点线程，并将线程移动到AQS同步队列中执行；
- 使用场景：适用于ReentrantLock场景；

#####  Semaphore
- 信号量，初始化Semaphore(N)时可以设置同时可执行线程数，使用共享锁，可以设置允许多个线程获取【acquire()】许可信号量，同过release()可以释放许可证；
- 使用场景：适用于多线程并发量控制的场景；

#####  CountDownLatch
- 倒数计时器，初始化new CountDownLatch(N)个计数器，每次线程调用执行countDown()释放共享锁进行计数器-1操作，等计数器为0时CountDownLatch不能再复用了。在调用await()时，会阻塞当前线程直到N变为0；
- 使用场景：适合串行流程在并行流程之后，需要等待并行流程执行完成的场景；

#####  CyclicBrrier
- 循环栅栏，类似CountDownLatch，CyclicBrrier可以反复循环执行结果。CyclicBrrier从0开始到达一个屏障时被阻塞，异步的直到最后一个线程达到屏障点，如果设置了回调任务（Runnable/Callable），那么由最后到达线程屏障的线程负责执行。
- 使用场景：适合多个线程处理数据，如文件等，最后由到达屏障点线程负责合并处理结果的场景；

------------

#### 9、第八节作业，选做，作业二（什么是并发? 什么是高并发? 实现高并发高可用系统需要考虑哪 些因素，对于这些你是怎么理解的？）
#####  什么是并发? 什么是高并发? 
- 并发：代表多个线程同时运行，也就是在多个CPU上争抢时间片运行。当相同机器下每个线程操作，都是在操作同一片JMM堆资源时，需要保证线程安全，同时也需要避免死锁问题；
- 高并发：在并发场景下，要尽可能提高多线程的处理速度；

#####   实现高并发高可用系统需要考虑哪 些因素，对于这些你是怎么理解的？
- IO框架相关：使用NIO Reactor模型，如OpenResty、Netty框架等。特点是非阻塞多路IO复用模型、基于事件驱动和回调机制，可同时监听多个IO读/写事件。以回调的方式唤醒处理线程执行业务逻辑，可以充分的利用线程资源。并且它们都是全双工的，在Client和Server场景下都可以使用；
- 数据相关：使用JVM缓存->Ehchache->共享内存->Redis等。不经常改变的热点数据，可以存放到JVM、Ehchache、共享内存中，从而减少I/O消耗。分布式场景下的缓存数据可以使用Redis作为缓存，同时做好缓存降级、缓存穿透等防护。对事务性要求较高的数据直接使用Mysql；
- 调度相关：将一些周期性CPU密集型业务和并发业务进行物理隔离，可以使用XXL-Job等分布式调度框架进行调度执行； 
- 监控相关：日志如Log4j、Ngx_Log、AOP日志等可以进行旁路异步处理，完全和业务逻辑结偶。日志流量采集可以使用ELK。时序监控数据可以使用InfluxDB、Grafana等；
- 流控相关：也可以使用Kong、SpingCloud Gateway做网关，提供可视化控制台，便于管理API，入口限流，软WAF安全控制等功能；
- 微服务相关：项目通过业务模块进行Service解耦，可以通过Dubbo、Thrift等RPC框架，使用Hystrix实现服务熔断，做好服务降级等策略；
- 消息中间件：使用ZK和Kafka为多个Service提供消息生产和消费功能，Kafka还可以用于流数据采集中间件；
- 大数据相关：如果数据量较大，且关注实时高效随机读写能力的话，可以使用CDH集群中的Kudu存储引擎、ImpalaSQL引擎提升读写效率；
- 多线程编码相关：能不使用锁的场景下，尽量不要使用锁。在考虑线程安全的场景下，尽量降低锁的力度。线程要池化，资源需要控制，并且针对业务场景合理使用线程池。控制线程并发数，避免资源过于消耗，做一些无意义的争抢。对核心业务线程设置名称/守护进程/打断策略等，便于监控/预警/补救策略；

------------

#### 10、第八节作业，必做，作业四（把多线程和并发相关知识带你梳理一遍，画一个脑图，截图上传到github 上。）
#####  已置顶！
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
                    ScheduledFuture<String> scheduleTask = executorService.schedule(
                            new Callable<String>() {
                                @Override
                                public String call() {
                                    return Thread.currentThread().getName();
                                }
                            },
                            3, //3s
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
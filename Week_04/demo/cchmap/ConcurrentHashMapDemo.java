package Week_04.demo.cchmap;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ipipman on 2020/11/10.
 *
 * @version V1.0
 * @Package Week_04.demo.cchmap
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/10 8:48 下午
 */
public class ConcurrentHashMapDemo {

    static ConcurrentHashMap<Integer, Integer> hashMap = new ConcurrentHashMap<>();

    static CopyOnWriteArrayList<ConcurrentHashMap<Integer, Integer>> listHashMap = new CopyOnWriteArrayList<>();

    static ExecutorService executorService = Executors.newCachedThreadPool(new CustomFactory());

    static AtomicInteger threadNum = new AtomicInteger(0);

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    hashMap.put(threadNum.get(), threadNum.get());
                    listHashMap.add(hashMap);
                }
            });
        }

        listHashMap.stream().parallel().forEach(map -> {
            map.forEach((k, v) -> {
                System.out.println(k + "-->" + v);
            });
        });

    }

    static class CustomFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r){
            Thread thread = new Thread(r);
            thread.setName("this-thread-name-" + threadNum.incrementAndGet());
            return thread;
        }
    }

}

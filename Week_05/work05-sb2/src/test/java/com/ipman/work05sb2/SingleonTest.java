package com.ipman.work05sb2;

import com.ipman.work05sb2.common.singletons.Singleton1;
import com.ipman.work05sb2.common.singletons.Singleton2;
import com.ipman.work05sb2.common.singletons.Singleton3;
import com.ipman.work05sb2.common.singletons.Singleton4;
import org.junit.jupiter.api.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by ipipman on 2020/11/19.
 *
 * @version V1.0
 * @Package com.ipman.work05sb2
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/19 2:55 下午
 */
@SpringBootTest
class SingleonTest {

    final ExecutorService executorService = Executors.newFixedThreadPool(5);

    /**
     * 工厂类实现方式测试
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {

        List<Future<Integer>> futureList = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            Future<Integer> task = executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    // 懒汉模式，在多线程下对象的HashCode是不一样的
                    // return Singleton1.getInstance().hashCode();

                    // 懒汉线程安全模式，通过synchroized锁解决并发问题
                    // return Singleton2.getInstance().hashCode();

                    // 饿汉方式，静态成员，在classloader类装载期完成初始化，避免线程安全问题
                    // return Singleton3.getInstance().hashCode();

                    // 饿汉方式，静态内部类，在classloader类被装载了，instance不被初始化，初始化再第一次调用，避免线程安全问题
                    return Singleton4.getInstance().hashCode();
            }
        });
        futureList.add(task);
    }

    Map<Integer, Boolean> mapping = new HashMap<>();
        for (Future<Integer> future : futureList) {
        if (mapping.isEmpty()) {
            mapping.put(future.get(), true);
            } else if (!mapping.containsKey(future.get())) {
                System.out.println("懒汉模式，实例变了，线程不安全，hashcode=" + future.get());
            }
        }
    }




}

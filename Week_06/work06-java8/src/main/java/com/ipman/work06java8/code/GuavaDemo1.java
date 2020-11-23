package com.ipman.work06java8.code;

import com.google.common.base.Joiner;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.collect.*;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ipipman on 2020/11/23.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.lambda
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/23 6:37 下午
 */
public class GuavaDemo1 {

    //事件总线，注入事件
    private static EventBus eventBus = new EventBus();

    static {
        eventBus.register(new GuavaDemo1());
    }

    //Guava缓存
    private static CacheLoader<String, String> loader = new CacheLoader<String, String>() {
        @Override
        public String load(String s) throws Exception {
            System.out.println("设置缓存了，" + s);
            return s + 1;
        }
    };
    private static Cache<String, String> cache = CacheBuilder.newBuilder()
            //.maximumSize(2)  //最大两个值
            .expireAfterWrite(6, TimeUnit.SECONDS) //有效时间
            .build(loader);
    static {
        cache.put("name", "ipman");
        cache.put("name1", "ipman");
        cache.put("name2", "ipman");
        cache.put("name3", "ipman");
    }

    @SneakyThrows
    public static void main(String[] args) {

        testList();

        testString();

        testMap();

        testBiMap();

        testEventBus();

        testCache();

    }

    @SneakyThrows
    public static void testCache() {
        System.out.println(cache.getIfPresent("name"));
        System.out.println(cache.getIfPresent("name1"));

        //休眠3s等过期
        Thread.sleep(1000);

        System.out.println(cache.getIfPresent("name2"));
        System.out.println(cache.getIfPresent("name3"));



    }


    public static void testEventBus() {
        Person person = new Person();
        person.setName("ipman");

        //触发事件
        eventBus.post(person);
    }


    public static void testList() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        List<List<Integer>> list1 = Lists.partition(list, 3);
        System.out.println(list1);
    }

    public static void testString() {
        List<String> list = Lists.newArrayList("a", "b", "c", "1", "2", "3");
        String result = Joiner.on(",").join(list);
        System.out.println(result);
    }

    public static void testMap() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        Multimap<Integer, Integer> map = ArrayListMultimap.create();
        list.forEach(i -> {
            map.put(i, i + 1);
        });
        System.out.println(map);
    }

    public static void testBiMap() {
        BiMap<String, Integer> map = HashBiMap.create();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        System.out.println(map.get("a").intValue());
        System.out.println(map.inverse().get(1));
    }

    @Data
    @NoArgsConstructor
    public static class Person {
        private String name;
    }

    @Subscribe
    public static void handler(Person person) {
        System.out.println(person.name + "  is running ...");
    }

}

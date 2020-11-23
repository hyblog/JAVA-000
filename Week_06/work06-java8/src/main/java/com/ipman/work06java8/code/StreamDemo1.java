package com.ipman.work06java8.code;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ipipman on 2020/11/23.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.lambda
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/23 4:10 下午
 */
public class StreamDemo1 {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(6, 2, 3, 3, 4, 5);

        Optional<Integer> first = list.stream().findFirst();
        System.out.println(first.get());
        System.out.println(first.map(i -> i * 100).orElse(100));

        //Reduce归约
        int sum = list.stream()
                .filter(i -> (i < 4))
                .distinct()
                .reduce(0, (a, b) -> (a + b));
        System.out.println(sum);


        //Collectors收集器，类型转换
        Map<Integer, Integer> map =
                list.parallelStream()
                        .collect(Collectors.toMap(a -> a, a -> (a + 1), (a, b) -> a, HashMap::new));
        System.out.println(map);

        //Map元素映射
        List<Integer> list1 =
                map.entrySet().parallelStream()
                        .map(e -> e.getKey() + e.getValue())
                        .collect(Collectors.toList());
        System.out.println(list1);
    }
}

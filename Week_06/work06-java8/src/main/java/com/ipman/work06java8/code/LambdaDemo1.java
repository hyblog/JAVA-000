package com.ipman.work06java8.code;

import java.io.Serializable;
import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Created by ipipman on 2020/11/21.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.lambda
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/21 6:32 下午
 */

public class LambdaDemo1<T extends Serializable> {


    public static void main(String[] args) {
        LambdaDemo1 demo1 = new LambdaDemo1();

        //Java7的写法，重写
//        MathOperation operation = new MathOperation<T>() {
//            @Override
//            public <T> T operation(T a, T b) {
//                return 1;
//            }
//        };
        //Lambda写法
//        MathOperation operation1 = Integer::sum;

//        System.out.println(operation1.operation(1, 2));

        MathOperation operation2 = (a, b) -> {
            int c = (int) a * (int) b;
            return c;
        };

        System.out.println(operation2.operation(1, 2));

        PersonOperation personOperation = message -> {
            System.out.println(message);
        };
        personOperation.sayMessage("ipman");

        Arrays.asList(1, 2, 3).forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
    }


    //定义一个接口，包含一个有返回值操作方法
    interface MathOperation<T> {
        T operation(T a, T b);
    }

    //定一个借口，包含一个无返回值的操作方法
    interface PersonOperation {
        void sayMessage(String message);
    }
}

package com.ipman.work06java8.lambda;

/**
 * Created by ipipman on 2020/11/21.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.lambda
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/21 6:32 下午
 */
public class LambdaDemo1 {


    public static void main(String[] args) {
        LambdaDemo1 demo1 = new LambdaDemo1();

        //Java7的写法，重写
        MathOperation operation = new MathOperation() {
            @Override
            public int operation(int a, int b) {
                return 1;
            }
        };
        //Lambda写法
        MathOperation operation1 = Integer::sum;

        System.out.println(operation1.operation(1, 2));



    }


    //定义一个接口，包含一个有返回值操作方法
    interface MathOperation {
        int operation(int a, int b);
    }

    //定一个借口，包含一个无返回值的操作方法
    interface PersonOperation {
        void sayMessage(String message);
    }
}

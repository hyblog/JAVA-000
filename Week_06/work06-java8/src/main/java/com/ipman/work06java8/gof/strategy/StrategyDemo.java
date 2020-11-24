package com.ipman.work06java8.gof.strategy;

/**
 * Created by ipipman on 2020/11/24.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.strategy
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/24 10:28 下午
 */
public class StrategyDemo {


    //创建一个接口。
    public interface Strategy {
        public int doOperation(int num1, int num2);
    }

    //创建实现接口的实体类。
    public static class OperationAdd implements Strategy{
        @Override
        public int doOperation(int num1, int num2) {
            return num1 + num2;
        }
    }
    public static class OperationSubtract implements Strategy{
        @Override
        public int doOperation(int num1, int num2) {
            return num1 - num2;
        }
    }
    public static class OperationMultiply implements Strategy{
        @Override
        public int doOperation(int num1, int num2) {
            return num1 * num2;
        }
    }

    //创建 Context 类。
    public static class Context {
        private Strategy strategy;

        public Context(Strategy strategy){
            this.strategy = strategy;
        }

        public int executeStrategy(int num1, int num2){
            return strategy.doOperation(num1, num2);
        }
    }

    //一个类的行为或其算法可以在运行时更改。这种类型的设计模式属于行为型模式。
    //在策略模式中，我们创建表示各种策略的对象和一个行为随着策略对象改变而改变的 context 对象。策略对象改变 context 对象的执行算法。
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubtract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}

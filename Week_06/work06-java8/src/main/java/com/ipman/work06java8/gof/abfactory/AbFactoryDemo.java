package com.ipman.work06java8.gof.abfactory;

/**
 * Created by ipipman on 2020/11/24.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.abfactory
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/24 10:59 上午
 */
public class AbFactoryDemo {

    interface IProduct1 {
        public void show();
    }

    interface IProduct2 {
        public void show();
    }

    static class Product1 implements IProduct1 {
        @Override
        public void show() {
            System.out.println("这是1型产品");
        }
    }

    static class Product2 implements IProduct2 {
        @Override
        public void show() {
            System.out.println("这是2型产品");
        }
    }

    interface IFactory {
        public IProduct1 createProduct1();
        public IProduct2 createProduct2();
    }

    static class Factory implements IFactory {
        @Override
        public IProduct1 createProduct1() {
            return new Product1();
        }
        @Override
        public IProduct2 createProduct2() {
            return new Product2();
        }
    }

    //为创建一组相关或相互依赖的对象提供一个接口，而且无需指定他们的具体类。
    public static void main(String[] args) {
        IFactory factory = new Factory();
        factory.createProduct1().show();
        factory.createProduct2().show();
    }
}
